/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.SuperheroSightingsDao;
import com.sg.superherosightings.model.Organization;
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
public class OrganizationsController {
    
    SuperheroSightingsDao dao;
    
    @Inject
    public OrganizationsController(SuperheroSightingsDao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value = "/displayOrganizationsPage")
    public String displayOrganizationsPage(Model model) {
        List<Organization> organizationList = dao.getAllOrganizations();
        model.addAttribute("organizationList", organizationList);
        return "organizations";
    }
    
    @RequestMapping(value = "/createOrganization", method = RequestMethod.POST)
    public String createOrganization(@Valid @ModelAttribute("organization") Organization organization, BindingResult result) {
        if (result.hasErrors()) {
            return "createOrganizationForm";
        }

        dao.addOrganization(organization);

        return "redirect:/displayOrganizationsPage";
    }
    
    @RequestMapping(value = "/displayOrganizationDetails", method = RequestMethod.GET)
    public String displayOrganizationDetails(HttpServletRequest request, Model model) {
        String organizationIdParameter = request.getParameter("organizationId");
        int organizationId = Integer.parseInt(organizationIdParameter);

        Organization organization = dao.getOrganizationById(organizationId);

        model.addAttribute("organization", organization);

        return "organizationDetails";
    }
    
    @RequestMapping(value = "/displayEditOrganizationForm", method = RequestMethod.GET)
    public String displayEditOrganizationForm(HttpServletRequest request, Model model) {
        String organizationIdParameter = request.getParameter("organizationId");
        int organizationId = Integer.parseInt(organizationIdParameter);
        Organization organization = dao.getOrganizationById(organizationId);
        model.addAttribute("organization", organization);
        return "editOrganizationForm";
    }
    
    @RequestMapping(value = "/editOrganization", method = RequestMethod.POST)
    public String editOrganization(@Valid @ModelAttribute("organization") Organization organization, BindingResult result) {

        if (result.hasErrors()) {
            return "editOrganizationForm";
        }

        dao.updateOrganization(organization);

        return "redirect:/displayOrganizationsPage";
    }
    
    @RequestMapping(value = "/deleteOrganization", method = RequestMethod.GET)
    public String deleteOrganization(HttpServletRequest request) {
        String organizationIdParameter = request.getParameter("organizationId");
        int organizationId = Integer.parseInt(organizationIdParameter);
        dao.deleteOrganization(organizationId);
        return "redirect:/displayOrganizationsPage";
    }
}


