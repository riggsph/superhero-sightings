/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.SuperheroSightingsDao;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.Superhero;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author peterriggs
 */
@Controller
public class SightingsController {
    
    SuperheroSightingsDao dao;
    
    @Inject
    public SightingsController(SuperheroSightingsDao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value = "/")
    public String displayIndexPage(Model model) {
        List<Sighting> sightingList = dao.getAllSightings();
        model.addAttribute("sightingList", sightingList);
        return "index";
    }
    
    @RequestMapping(value = "/displaySightingsPage")
    public String displaySightingsPage(Model model) {
        List<Sighting> sightingList = dao.getAllSightings();
        List<Location> locationList = dao.getAllLocations();
        List<Superhero> superheroList = dao.getAllSuperheroes();
        model.addAttribute("sightingList", sightingList);
        model.addAttribute("locationList", locationList);
        model.addAttribute("superheroList", superheroList);
        return "sightings";
    }
    
    @RequestMapping(value = "/createSighting", method = RequestMethod.POST)
    public String createSighting(HttpServletRequest request) {
        
        Sighting sighting = new Sighting();
        
        // Format date String for LocalDateTime
        LocalDateTime date = LocalDateTime.parse(request.getParameter("date") + "T" + request.getParameter("time"));
        sighting.setDate(date);
        
        String locationId = request.getParameter("location");
        Location location = dao.getLocationById(Integer.parseInt(locationId));
        sighting.setLocation(location);
        
        // Use superhero strings from request to create Superhero objects and put into list
        String[] superheroes = request.getParameterValues("superheroes");
        List<Superhero> superheroList = new ArrayList<>();
        for (String currentSuperhero : superheroes) {
            superheroList.add(dao.getSuperheroById(Integer.parseInt(currentSuperhero)));
        }
        sighting.setSuperheroes(superheroList);
        
        dao.addSighting(sighting);

        return "redirect:/displaySightingsPage";
    }
    
    @RequestMapping(value = "/displaySightingDetails", method = RequestMethod.GET)
    public String displaySightingDetails(HttpServletRequest request, Model model) {
        String sightingIdParameter = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdParameter);

        Sighting sighting = dao.getSightingById(sightingId);
        
        model.addAttribute("sighting", sighting);

        return "sightingDetails";
    }
    
    @RequestMapping(value = "/displayEditSightingForm", method = RequestMethod.GET)
    public String displayEditSightingForm(HttpServletRequest request, Model model) {
        String sightingIdParameter = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdParameter);
        Sighting sighting = dao.getSightingById(sightingId);
        
        List<Location> locationList = dao.getAllLocations();
        List<Superhero> superheroList = dao.getAllSuperheroes();
        
        // Put superhero in model for spring forms to populate form fields before editing
        model.addAttribute("sighting", sighting);
        model.addAttribute("locationList", locationList);
        model.addAttribute("superheroList", superheroList);
        return "editSightingForm";
    }
    
    @RequestMapping(value = "/editSighting", method = RequestMethod.POST)
    public String editSighting(HttpServletRequest request) {
        
        Sighting sighting = new Sighting();
        sighting.setSightingId(Integer.parseInt(request.getParameter("sightingId")));
        String[] superheroes = request.getParameterValues("superheroes");
        List<Superhero> superheroList = new ArrayList<>();
        for (String currentSuperhero : superheroes) {
            superheroList.add(dao.getSuperheroById(Integer.parseInt(currentSuperhero)));
        }
        sighting.setSuperheroes(superheroList);
        String locationId = request.getParameter("location");
        Location location = dao.getLocationById(Integer.parseInt(locationId));
        sighting.setLocation(location);

        dao.updateSighting(sighting);

        return "redirect:/displaySightingsPage";
    }
    
    @RequestMapping(value = "/deleteSighting", method = RequestMethod.GET)
    public String deleteSighting(HttpServletRequest request) {
        String sightingIdParameter = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdParameter);
        dao.deleteSighting(sightingId);
        return "redirect:/displaySightingsPage";
    }
    
}

