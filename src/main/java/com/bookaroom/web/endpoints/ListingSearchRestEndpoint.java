package com.bookaroom.web.endpoints;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookaroom.entities.ListingDTO;
import com.bookaroom.services.impl.ListingServiceImpl;
import com.bookaroom.services.impl.SearchImpl;
import com.bookaroom.services.impl.UserServiceImpl;

@RestController
public class ListingSearchRestEndpoint
{

    @Autowired
    private ListingServiceImpl listings;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private SearchImpl s;

    @RequestMapping(value = "/doSearch", method = RequestMethod.GET)
    public List<ListingDTO> listingSearch(
        @RequestParam("state") String state,
        @RequestParam("city") String city,
        @RequestParam("country") String country,
        @RequestParam("people") Integer people,
        @RequestParam("inDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date inDate,
        @RequestParam("outDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date outDate,
        Principal principal)
    {
        if (inDate.after(outDate))
            return new ArrayList<ListingDTO>();

        s.addSearch(null, null, null, null, principal.getName(), state, city, country, inDate, outDate);
        List<ListingDTO> searchResults = listings.findListing(state,
                                                              city,
                                                              country,
                                                              people.intValue(),
                                                              inDate,
                                                              outDate);

        return searchResults;
    }

}
