/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.SuperheroSightingsDao;
import com.sg.superherosightings.model.Location;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author peterriggs
 */
@Controller
public class LocationsController {
    
    SuperheroSightingsDao dao;
    
    @Inject
    public LocationsController(SuperheroSightingsDao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value = "/displayLocationsPage")
    public String displayLocationsPage(Model model) {
        List<Location> locationList = dao.getAllLocations();
        model.addAttribute("locationList", locationList);
        return "locations";
    }
    
    @RequestMapping(value = "/createLocation", method = RequestMethod.POST)
    public String createLocation(@Valid @ModelAttribute("location") Location location, BindingResult result) {
        if (result.hasErrors()) {
            return "createLocationForm";
        }

        dao.addLocation(location);

        return "redirect:/displayLocationsPage";
    }
    
    @RequestMapping(value = "/displayLocationDetails", method = RequestMethod.GET)
    public String displayLocationDetails(HttpServletRequest request, Model model) {
        String locationIdParameter = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdParameter);

        Location location = dao.getLocationById(locationId);

        model.addAttribute("location", location);

        return "locationDetails";
    }
    
    @RequestMapping(value = "/displayEditLocationForm", method = RequestMethod.GET)
    public String displayEditLocationForm(HttpServletRequest request, Model model) {
        String locationIdParameter = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdParameter);
        Location location = dao.getLocationById(locationId);
        model.addAttribute("location", location);
        return "editLocationForm";
    }
    
    @RequestMapping(value = "/editLocation", method = RequestMethod.POST)
    public String editLocation(@Valid @ModelAttribute("location") Location location, BindingResult result) {

        if (result.hasErrors()) {
            return "editLocationForm";
        }

        dao.updateLocation(location);

        return "redirect:/displayLocationsPage";
    }
    
    @RequestMapping(value = "/deleteLocation", method = RequestMethod.GET)
    public String deleteLocation(HttpServletRequest request) {
        String locationIdParameter = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdParameter);
        dao.deleteLocation(locationId);
        return "redirect:/displayLocationsPage";
    }
}

