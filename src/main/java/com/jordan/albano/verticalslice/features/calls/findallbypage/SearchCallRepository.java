package com.jordan.albano.verticalslice.features.calls.findallbypage;

import com.jordan.albano.verticalslice.domain.CallEntity;
import com.jordan.albano.verticalslice.domain.CallState;
import com.jordan.albano.verticalslice.shared.CriteriaDTO;
import com.jordan.albano.verticalslice.shared.SearchRepository;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class SearchCallRepository extends SearchRepository<CallEntity> {

    public Page<CallEntity> findByPage(CriteriaDTO criteriaDTO) {
        var res = this.findBy(criteriaDTO).stream().toList();
        var totalEntities = this.getAmountOfEntitiesInDb(CallEntity.class, criteriaDTO);
        return new PageImpl<>(res, PageRequest.of(criteriaDTO.page(), criteriaDTO.size()), totalEntities);
    }

    public Collection<CallEntity> findBy(CriteriaDTO criteria) {
        criteriaBuilder = entityManager.getCriteriaBuilder();
        criteriaQuery = criteriaBuilder.createTupleQuery();
        root = criteriaQuery.from(CallEntity.class);
        var predicates = this.getBasePredicates(criteria.filters());
        criteriaQuery.multiselect(
                root.get("id"),
                root.get("academicYear"),
                root.get("state"),
                root.get("name")
        ).where(predicates.toArray(new Predicate[0]));
        var orders = sort(criteria.orders());
        criteriaQuery.orderBy(orders);
        int offset = (criteria.page()) * criteria.size();
        List<Tuple> tupleResult = entityManager.createQuery(criteriaQuery).setFirstResult(offset).setMaxResults(criteria.size()).getResultList();
        ArrayList<CallEntity> result = new ArrayList<>();
        for (Tuple t : tupleResult) {
            this.createCall(result, t);
        }
        entityManager.clear();
        entityManager.close();
        return result;
    }


    private void createCall(ArrayList<CallEntity> result, Tuple t) {
        var entity = new CallEntity();
        entity.setId(t.get(0, UUID.class));
        entity.setAcademicYear(t.get(1, Integer.class));
        entity.setState(t.get(2, CallState.class));
        entity.setName(t.get(3, String.class));
        result.add(entity);
    }
}