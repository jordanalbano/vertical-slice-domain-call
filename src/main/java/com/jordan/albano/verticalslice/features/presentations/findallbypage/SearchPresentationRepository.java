package com.jordan.albano.verticalslice.features.presentations.findallbypage;

import com.jordan.albano.verticalslice.domain.PresentationEntity;
import com.jordan.albano.verticalslice.domain.PresentationState;
import com.jordan.albano.verticalslice.shared.CriteriaDTO;
import com.jordan.albano.verticalslice.shared.SearchCriteriaDTO;
import com.jordan.albano.verticalslice.shared.SearchRepository;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class SearchPresentationRepository extends SearchRepository<PresentationEntity> {

    public Page<PresentationEntity> findByPage(CriteriaDTO criteriaDTO) {
        var res = this.findBy(criteriaDTO).stream().toList();
        var totalEntities = this.getAmountOfEntitiesInDb(PresentationEntity.class, criteriaDTO);
        return new PageImpl<>(res, PageRequest.of(criteriaDTO.page(), criteriaDTO.size()), totalEntities);
    }

    public Collection<PresentationEntity> findBy(CriteriaDTO criteria) {
        criteriaBuilder = entityManager.getCriteriaBuilder();
        criteriaQuery = criteriaBuilder.createTupleQuery();
        root = criteriaQuery.from(PresentationEntity.class);
        var predicates = this.getBasePredicates(criteria.filters());
        var CallId = root.join("callToApply", JoinType.LEFT).get("id");
        var callYear = root.join("callToApply", JoinType.LEFT).get("academicYear");
        criteriaQuery.multiselect(
                root.get("id"),
                root.get("state"),
                callYear,
                CallId
        ).where(predicates.toArray(new Predicate[0]));
        var orders = sort(criteria.orders());
        criteriaQuery.orderBy(orders);
        int offset = (criteria.page()) * criteria.size();
        List<Tuple> tupleResult = entityManager.createQuery(criteriaQuery).setFirstResult(offset).setMaxResults(criteria.size()).getResultList();
        ArrayList<PresentationEntity> result = new ArrayList<>();
        for (Tuple t : tupleResult) {
            this.createPresentation(result, t);
        }
        entityManager.clear();
        entityManager.close();
        return result;
    }

    protected List<Predicate> getBasePredicates(List<SearchCriteriaDTO> filters) {
        Join<Object, Object> callJoin = root.join("callToApply", JoinType.LEFT);
        var predicates = new ArrayList<Predicate>();
        for (SearchCriteriaDTO filter : filters) {
            if (Objects.equals(filter.attr(), "year")) {
                predicates.add(criteriaBuilder.equal(callJoin.get("year"), filter.value()));
            }
            return predicates;
        }
        return predicates;
    }

    private void createPresentation(ArrayList<PresentationEntity> result, Tuple t) {
        var presentation = new PresentationEntity();
        presentation.setId(t.get(0, UUID.class));
        presentation.setState(t.get(1, PresentationState.class));
        presentation.getCallToApply().setAcademicYear(t.get(2, Integer.class));
        presentation.getCallToApply().setId(t.get(3, UUID.class));
        result.add(presentation);
    }
}