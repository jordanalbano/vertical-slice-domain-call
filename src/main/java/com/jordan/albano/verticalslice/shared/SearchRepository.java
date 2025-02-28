package com.jordan.albano.verticalslice.shared;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Repository
public abstract class SearchRepository<T> {
    @PersistenceContext
    protected EntityManager entityManager;
    protected CriteriaBuilder criteriaBuilder;
    protected CriteriaQuery<Tuple> criteriaQuery;
    protected Root<T> root;

    protected int getAmountOfEntitiesInDb(Class<T> entityType, CriteriaDTO filterByUsername) {
        criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        root = criteriaQuery.from(entityType);
        var filters = this.getBasePredicates(filterByUsername.filters());
        String filterByEnabled = "enabled";
        filters.add(criteriaBuilder.equal(root.get(filterByEnabled), true));
        criteriaQuery.select(criteriaBuilder.count(root)).where(filters.toArray(new Predicate[0]));
        return entityManager.createQuery(criteriaQuery).getSingleResult().intValue();
    }

    protected List<Predicate> getFilters(List<SearchCriteriaDTO> filters) {
        List<Predicate> predicates = new ArrayList<>(filters.size());
        filters.forEach(criteria -> {
            switch (criteria.searchOperation()) {
                case GREATER_THAN ->
                        predicates.add(criteriaBuilder.and(criteriaBuilder.greaterThan(root.get(criteria.attr()), criteria.value().toString())));
                case LESS_THAN ->
                        predicates.add(criteriaBuilder.and(criteriaBuilder.lessThan(root.get(criteria.attr()), criteria.value().toString())));
                case GREATER_THAN_EQUAL ->
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get((criteria.attr())), criteria.value().toString()));
                case LESS_THAN_EQUAL ->
                        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(criteria.attr()), criteria.value().toString()));
                case DATE_IS ->
                        predicates.add(criteriaBuilder.equal(root.get(criteria.attr()), LocalDate.parse(criteria.value().toString())));
                case DATE_IS_NOT ->
                        predicates.add(criteriaBuilder.notEqual(root.get(criteria.attr()), LocalDate.parse(criteria.value().toString())));
                case DATE_IS_BEFORE ->
                        predicates.add(criteriaBuilder.lessThan(root.get(criteria.attr()), LocalDate.parse(criteria.value().toString())));
                case DATE_IS_AFTER ->
                        predicates.add(criteriaBuilder.greaterThan(root.get(criteria.attr()), LocalDate.parse(criteria.value().toString())));
                case NOT_EQUAL -> predicates.add(criteriaBuilder.notEqual(root.get(criteria.attr()), criteria.value()));
                case EQUAL -> predicates.add(criteriaBuilder.equal(root.get(criteria.attr()), criteria.value()));
                case OBJECT_INTO_OBJECT ->
                        predicates.add(criteriaBuilder.equal(root.get(criteria.attr()).get("id"), criteria.value()));
                case OBJECT_INTO_OBJECT_INTO_OBJECT ->
                        predicates.add(criteriaBuilder.equal(root.get(criteria.attr().split(",")[0]).get(criteria.attr().split(",")[1]).get("id"), criteria.value()));
                case MATCH ->
                        predicates.add(criteriaBuilder.like(root.get(criteria.attr()).as(String.class), "%" + criteria.value() + "%"));
                case MATCH_END ->
                        predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(criteria.attr())), criteria.value().toString().toLowerCase() + "%"));
                case MATCH_START ->
                        predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(criteria.attr())), "%" + criteria.value().toString().toLowerCase()));
                case IN ->
                        predicates.add(criteriaBuilder.in(root.get(criteria.attr()).get("id")).value(criteria.value()));
                case NOT_IN -> predicates.add(criteriaBuilder.not(root.get(criteria.attr())).in(criteria.value()));
                case OWN_OBJECTS -> {//builder.equal(subRoot.get("name"), builder.literal("jpa")
                    var attrs = criteria.attr().split("\\.");
                    var res = this.getNestedAttribute(root, attrs);
                    predicates.add(criteriaBuilder.equal(res, criteria.value()));
                }
            }

        });
        String filterByEnabled = "enabled";
        predicates.add(criteriaBuilder.equal(root.get(filterByEnabled), true));
        return predicates;
    }

    private Path<Object> getNestedAttribute(Root<T> root, String... attributes) {
        Path<Object> path = root.get(attributes[0]);
        for (int i = 1; i < attributes.length; i++) {
            path = path.get(attributes[i]);
        }
        return path;
    }


    protected List<Predicate> getBasePredicates(List<SearchCriteriaDTO> filters) {


        return this.getFilters(filters);
    }

    protected List<Order> sort(List<SortDTO> sort) {
        if (sort == null || sort.isEmpty()) {
            return List.of(criteriaBuilder.desc(root.get("createdOn")));
        }
        return sort.stream().map(r -> {
            if (r.field().equals("account") || r.field().equals("origin")) {
                return r.direction().equals("ASC") ? criteriaBuilder.asc(root.get(r.field()).get("name")) : criteriaBuilder.desc(root.get(r.field()).get("name"));
            } else {
                return r.direction().equals("ASC") ? criteriaBuilder.asc(root.get(r.field())) : criteriaBuilder.desc(root.get(r.field()));
            }
        }).toList();
    }
}
