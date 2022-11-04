/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Picture;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.Superhero;
import com.sg.superherosightings.model.Superpower;
import java.util.List;

/**
 *
 * @author peterriggs
 */
public interface SuperheroSightingsDao {
    
    public void addSighting(Sighting sighting);
    
    public void deleteSighting(int sightingId);
    
    public void updateSighting(Sighting sighting);
    
    public Sighting getSightingById(int id);
    
    public List<Sighting> getSightingsByLocationId(int locationId);
    
    public List<Sighting> getSightingsBySuperheroId(int superheroId);
    
    public List<Sighting> getAllSightings();
    
    public void addOrganization(Organization organization);
    
    public void deleteOrganization(int organizationId);
    
    public void updateOrganization(Organization organization);
    
    public Organization getOrganizationById(int id);
    
    public List<Organization> getAllOrganizations();
    
    public void addSuperhero(Superhero superhero);
    
    public void deleteSuperhero(int superheroId);
    
    public void updateSuperhero(Superhero superhero);
    
    public Superhero getSuperheroById(int id);
    
    public List<Superhero> getAllSuperheroes();
    
    public List<Superhero> getSuperheroesByOrganizationId(int organizationId);
    
    public List<Superhero> getSuperheroesBySuperpowerId(int superpowerId);
    
    public void addSuperpower(Superpower superpower);
    
    public void deleteSuperpower(int superpowerId);
    
    public void updateSuperpower(Superpower superpower);
    
    public Superpower getSuperpowerById(int id);
    
    public List<Superpower> getAllSuperpowers();
    
    public void addLocation(Location location);
    
    public void deleteLocation(int locationId);
    
    public void updateLocation(Location location);
    
    public Location getLocationById(int id);
    
    public List<Location> getAllLocations();
    
    public Picture addPicture(Picture picture);
    
    public void deletePicture(int pictureId);
    
    public void updatePicture(Picture picture);
    
    public Picture getPictureById(int pictureId);
    
    public List<Picture> getAllPictures();
}
