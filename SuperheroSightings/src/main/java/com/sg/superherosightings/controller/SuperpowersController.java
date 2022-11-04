/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.SuperheroSightingsDao;
import com.sg.superherosightings.model.Superpower;
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
public class SuperpowersController {
    
    SuperheroSightingsDao dao;
    
    @Inject
    public SuperpowersController(SuperheroSightingsDao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value = "/displaySuperpowersPage")
    public String displaySuperpowersPage(Model model) {
        List<Superpower> superpowerList = dao.getAllSuperpowers();
        model.addAttribute("superpowerList", superpowerList);
        return "superpowers";
    }
    
    @RequestMapping(value = "/createSuperpower", method = RequestMethod.POST)
    public String createSuperpower(@Valid @ModelAttribute("superpower") Superpower superpower, BindingResult result) {
        if (result.hasErrors()) {
            return "createSuperpowerForm";
        }

        dao.addSuperpower(superpower);

        return "redirect:/displaySuperpowersPage";
    }
    
    @RequestMapping(value = "/displaySuperpowerDetails", method = RequestMethod.GET)
    public String displaySuperpowerDetails(HttpServletRequest request, Model model) {
        String superpowerIdParameter = request.getParameter("superpowerId");
        int superpowerId = Integer.parseInt(superpowerIdParameter);

        Superpower superpower = dao.getSuperpowerById(superpowerId);

        model.addAttribute("superpower", superpower);

        return "superpowerDetails";
    }
    
    @RequestMapping(value = "/displayEditSuperpowerForm", method = RequestMethod.GET)
    public String displayEditSuperpowerForm(HttpServletRequest request, Model model) {
        String superpowerIdParameter = request.getParameter("superpowerId");
        int superpowerId = Integer.parseInt(superpowerIdParameter);
        Superpower superpower = dao.getSuperpowerById(superpowerId);
        model.addAttribute("superpower", superpower);
        return "editSuperpowerForm";
    }
    
    @RequestMapping(value = "/editSuperpower", method = RequestMethod.POST)
    public String editSuperpower(@Valid @ModelAttribute("superpower") Superpower superpower, BindingResult result) {

        if (result.hasErrors()) {
            return "editSuperpowerForm";
        }

        dao.updateSuperpower(superpower);

        return "redirect:/displaySuperpowersPage";
    }
    
    @RequestMapping(value = "/deleteSuperpower", method = RequestMethod.GET)
    public String deleteSuperpower(HttpServletRequest request) {
        String superpowerIdParameter = request.getParameter("superpowerId");
        int superpowerId = Integer.parseInt(superpowerIdParameter);
        dao.deleteSuperpower(superpowerId);
        return "redirect:/displaySuperpowersPage";
    }
}
