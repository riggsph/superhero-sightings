/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.model;

import java.util.Objects;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author peterriggs
 */
public class Organization {
    
    private int organizationId;
    @NotEmpty(message = "You must supply a value for Name.")
    @Length(max = 75, message = "Name must be no more than 75 characters in length.")
    private String name;
    @Length(max = 255, message = "Description must be no more than 255 characters in length.")
    private String description;
    @Length(max = 100, message = "Name must be no more than 100 characters in length.")
    private String street;
    @NotEmpty(message = "You must supply a value for City.")
    @Length(max = 100, message = "Name must be no more than 100 characters in length.")
    private String city;
    @NotEmpty(message = "You must supply a value for State.")
    @Length(max = 2, message = "Name must be no more than 2 characters in length.")
    private String state;
    @NotEmpty(message = "You must supply a value for Zip Code.")
    @Length(max = 5, message = "Name must be no more than 5 characters in length.")
    private String zip;
    @NotEmpty(message = "You must supply a value for Phone.")
    @Length(max = 10, message = "Phone must be no more than 10 characters in length.")
    private String phone;
    @NotEmpty(message = "You must supply a value for Email.")
    @Email(message = "Please enter a valid email address.")
    @Length(max = 50, message = "Email must be no more than 50 characters in length.")
    private String email;

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.organizationId;
        hash = 47 * hash + Objects.hashCode(this.name);
        hash = 47 * hash + Objects.hashCode(this.description);
        hash = 47 * hash + Objects.hashCode(this.street);
        hash = 47 * hash + Objects.hashCode(this.city);
        hash = 47 * hash + Objects.hashCode(this.state);
        hash = 47 * hash + Objects.hashCode(this.zip);
        hash = 47 * hash + Objects.hashCode(this.phone);
        hash = 47 * hash + Objects.hashCode(this.email);
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
        final Organization other = (Organization) obj;
        if (this.organizationId != other.organizationId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.street, other.street)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.zip, other.zip)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }
    
    
}
