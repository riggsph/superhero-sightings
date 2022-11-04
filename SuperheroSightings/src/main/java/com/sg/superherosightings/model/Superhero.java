/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.model;

import java.util.List;
import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author peterriggs
 */
public class Superhero {
    
    private int superheroId;
    @NotEmpty(message = "You must supply a value for Name.")
    @Length(max = 75, message = "Name must be no more than 75 characters in length.")
    private String name;
    @Length(max = 255, message = "Description must be no more than 255 characters in length.")
    private String description;
    private List<Organization> organizations;
    private List<Superpower> superpowers;
    private Picture picture;

    public int getSuperheroId() {
        return superheroId;
    }

    public void setSuperheroId(int superheroId) {
        this.superheroId = superheroId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public List<Superpower> getSuperpowers() {
        return superpowers;
    }

    public void setSuperpowers(List<Superpower> superpowers) {
        this.superpowers = superpowers;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.superheroId;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.description);
        hash = 89 * hash + Objects.hashCode(this.organizations);
        hash = 89 * hash + Objects.hashCode(this.superpowers);
        hash = 89 * hash + Objects.hashCode(this.picture);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Superhero other = (Superhero) obj;
        if (this.superheroId != other.superheroId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.organizations, other.organizations)) {
            return false;
        }
        if (!Objects.equals(this.superpowers, other.superpowers)) {
            return false;
        }
        if (!Objects.equals(this.picture, other.picture)) {
            return false;
        }
        return true;
    }
    
    

    
}
