package com.bookaroom.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.bookaroom.entities.ListingDTO;

public class SpecificationsBuilder
{

    private final List<SearchCriteria> params;

    public SpecificationsBuilder()
    {
        params = new ArrayList<SearchCriteria>();
    }

    public SpecificationsBuilder with(String key, String operation, Object value)
    {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<ListingDTO> build()
    {
        if (params.size() == 0) {
            return null;
        }

        List<Specification<ListingDTO>> specs = new ArrayList<Specification<ListingDTO>>();
        for (SearchCriteria param : params) {
            specs.add(new ListingSpecification(param));
        }

        Specification<ListingDTO> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specifications.where(result).and(specs.get(i));
        }
        return result;
    }
}
