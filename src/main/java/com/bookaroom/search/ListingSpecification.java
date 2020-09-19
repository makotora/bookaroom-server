package com.bookaroom.search;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.bookaroom.entities.ListingDTO;

public class ListingSpecification implements Specification<ListingDTO>
{

    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<ListingDTO> root, CriteriaQuery<?> query, CriteriaBuilder builder)
    {

        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThan(root.<String>get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThan(root.<String>get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("<=")) {
            return builder.lessThanOrEqualTo(root.<String>get(criteria.getKey()),
                                             criteria.getValue().toString());
        }
        if (criteria.getOperation().equalsIgnoreCase(">=")) {
            return builder.greaterThanOrEqualTo(root.<String>get(criteria.getKey()),
                                                criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            }
            else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }

    public ListingSpecification(SearchCriteria criteria)
    {
        super();
        this.criteria = criteria;
    }
}
