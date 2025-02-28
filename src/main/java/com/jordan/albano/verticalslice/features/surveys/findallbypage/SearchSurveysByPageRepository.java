package com.jordan.albano.verticalslice.features.surveys.findallbypage;

import com.jordan.albano.verticalslice.domain.SurveyEntity;
import com.jordan.albano.verticalslice.domain.UserTypeObjective;
import com.jordan.albano.verticalslice.shared.CriteriaDTO;
import com.jordan.albano.verticalslice.shared.SearchRepository;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public class SearchSurveysByPageRepository extends SearchRepository<SurveyEntity> {

    public Page<SurveyEntity> findByPage(CriteriaDTO criteriaDTO) {
        var res = this.findBy(criteriaDTO).stream().toList();
        var totalEntities = this.getAmountOfEntitiesInDb(SurveyEntity.class, criteriaDTO);
        return new PageImpl<>(res, PageRequest.of(criteriaDTO.page(), criteriaDTO.size()), totalEntities);
    }

    public Collection<SurveyEntity> findBy(CriteriaDTO criteria) {
        criteriaBuilder = entityManager.getCriteriaBuilder();
        criteriaQuery = criteriaBuilder.createTupleQuery();
        root = criteriaQuery.from(SurveyEntity.class);
        var predicates = this.getBasePredicates(criteria.filters());
        criteriaQuery.multiselect(
                root.get("id"),
                root.get("name"),
                root.get("userType"),
                root.get("createdOn")
        ).where(predicates.toArray(new Predicate[0]));
        var orders = sort(criteria.orders());
        criteriaQuery.orderBy(orders);
        int offset = (criteria.page()) * criteria.size();
        List<Tuple> tupleResult = entityManager.createQuery(criteriaQuery).setFirstResult(offset).setMaxResults(criteria.size()).getResultList();
        ArrayList<SurveyEntity> result = new ArrayList<>();
        tupleResult.forEach(t -> this.createEntity(result, t));
        entityManager.clear();
        entityManager.close();
        return result;
    }


    private void createEntity(ArrayList<SurveyEntity> result, Tuple t) {
        var entity = new SurveyEntity();
        entity.setId(t.get(0, UUID.class));
        entity.setName(t.get(1, String.class));
        entity.setUserType(t.get(2, UserTypeObjective.class));
        entity.setCreatedOn(t.get(3, LocalDateTime.class));
        result.add(entity);
    }
}