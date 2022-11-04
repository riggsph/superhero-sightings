/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.SuperheroSightingsDao;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Picture;
import com.sg.superherosightings.model.Superhero;
import com.sg.superherosightings.model.Superpower;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author peterriggs
 */
@Controller
public class SuperheroesController {
    
    SuperheroSightingsDao dao;
    
    @Inject
    public SuperheroesController(SuperheroSightingsDao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value = "/displaySuperheroesPage")
    public String displaySuperheroesPage(Model model) {
        List<Superhero> superheroList = dao.getAllSuperheroes();
        List<Organization> organizationList = dao.getAllOrganizations();
        List<Superpower> superpowerList = dao.getAllSuperpowers();
        model.addAttribute("superheroList", superheroList);
        model.addAttribute("organizationList", organizationList);
        model.addAttribute("superpowerList", superpowerList);
        return "superheroes";
    }
    
    @RequestMapping(value = "/createSuperhero", method = RequestMethod.POST)
    public String createSuperhero(HttpServletRequest request,
                                Model model,
                                @RequestParam String imageTitle,
                                @RequestParam MultipartFile pictureFile) throws IOException {
       
        Superhero superhero = new Superhero();
        String name = request.getParameter("name");
        superhero.setName(name);
        
        String description = request.getParameter("description");
        superhero.setDescription(description);
        
        // Use organization strings from request to create Organization objects and put into list
        try {
            String[] organizations = request.getParameterValues("organizations");
            List<Organization> organizationList = new ArrayList<>();
            for (String currentOrganization : organizations) {
                organizationList.add(dao.getOrganizationById(Integer.parseInt(currentOrganization)));
            }
            superhero.setOrganizations(organizationList);
        } catch (NumberFormatException e) {
            superhero.setOrganizations(null);
        }
        
        // Use superpower strings from request to create Superpower objects and put into list
        try {
            String[] superpowers = request.getParameterValues("superpowers");
            List<Superpower> superpowerList = new ArrayList<>();
            for (String currentSuperpower : superpowers) {
                superpowerList.add(dao.getSuperpowerById(Integer.parseInt(currentSuperpower)));
            }
            superhero.setSuperpowers(superpowerList);
        } catch (NumberFormatException e) {
            superhero.setSuperpowers(null);
        }
        
        // Multipart file becomes byte array and set Picture object, associate with superhero
        if (!pictureFile.isEmpty()) {
            try {
                byte [] pictureBytes = pictureFile.getBytes();
                Picture picture = new Picture();
                picture.setTitle(imageTitle);
                picture.setImage(pictureBytes);
                superhero.setPicture(picture);
            } catch (Exception e) {
                model.addAttribute("errorMsg", "File upload failed: " + 
                        e.getMessage());
                return "superheroDetails";
            }
        } else {
            model.addAttribute("errorMsg", 
                               "Please specify a non-empty file.");
            return "superheroDetails";
        }
        
        
        dao.addSuperhero(superhero);

        return "redirect:/displaySuperheroesPage";
    }
    
    @RequestMapping(value = "/displaySuperheroDetails", method = RequestMethod.GET)
    public String displaySuperheroDetails(HttpServletRequest request, Model model) {
        String superheroIdParameter = request.getParameter("superheroId");
        int superheroId = Integer.parseInt(superheroIdParameter);

        Superhero superhero = dao.getSuperheroById(superheroId);
        
        model.addAttribute("superhero", superhero);

        return "superheroDetails";
    }
    
    @RequestMapping(value = "/displayEditSuperheroForm", method = RequestMethod.GET)
    public String displayEditSuperheroForm(HttpServletRequest request, Model model) {
        
        String superheroIdParameter = request.getParameter("superheroId");
        int superheroId = Integer.parseInt(superheroIdParameter);
        Superhero superhero = dao.getSuperheroById(superheroId);
        
        List<Organization> organizationList = dao.getAllOrganizations();
        List<Superpower> superpowerList = dao.getAllSuperpowers();
        
        // Put superhero in model for spring forms to populate form fields before editing
        model.addAttribute("superhero", superhero);
        model.addAttribute("organizationList", organizationList);
        model.addAttribute("superpowerList", superpowerList);
        
        return "editSuperheroForm";
    }
    
    @RequestMapping(value = "/editSuperhero", method = RequestMethod.POST)
    public String editSuperhero(HttpServletRequest request) {

        Superhero superhero = dao.getSuperheroById(Integer.parseInt(request.getParameter("superheroId")));
        String name = request.getParameter("name");
        superhero.setName(name);
        
        String description = request.getParameter("description");
        superhero.setDescription(description);
        
        String[] organizations = request.getParameterValues("organizations");
        List<Organization> organizationList = new ArrayList<>();
        for (String currentOrganization : organizations) {
            organizationList.add(dao.getOrganizationById(Integer.parseInt(currentOrganization)));
        }
        superhero.setOrganizations(organizationList);
        
        String[] superpowers = request.getParameterValues("superpowers");
        List<Superpower> superpowerList = new ArrayList<>();
        for (String currentSuperpower : superpowers) {
            superpowerList.add(dao.getSuperpowerById(Integer.parseInt(currentSuperpower)));
        }
        
        superhero.setSuperpowers(superpowerList);
        
        dao.updateSuperhero(superhero);

        return "redirect:/displaySuperheroesPage";
    }
    
    @RequestMapping(value = "/deleteSuperhero", method = RequestMethod.GET)
    public String deleteSuperhero(HttpServletRequest request) {
        String superheroIdParameter = request.getParameter("superheroId");
        int superheroId = Integer.parseInt(superheroIdParameter);
        dao.deleteSuperhero(superheroId);
        return "redirect:/displaySuperheroesPage";
    }
    
}

