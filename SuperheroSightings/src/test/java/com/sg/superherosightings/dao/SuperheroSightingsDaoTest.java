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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author peterriggs
 */
public class SuperheroSightingsDaoTest {
    
    SuperheroSightingsDao dao;
    
    public SuperheroSightingsDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx
        = new ClassPathXmlApplicationContext("test-applicationContext.xml");

          dao = ctx.getBean("superheroSightingsDao", SuperheroSightingsDao.class);
          /*
          try {
              
              List<Sighting> sightings = dao.getAllSightings();
            for (Sighting currentSighting : sightings) {
                dao.deleteSighting(currentSighting.getSightingId());
            }
          } catch (DateTimeParseException e) {
              
          }
          
          List<Superhero> superheroes = dao.getAllSuperheroes();
          for (Superhero currentSuperhero : superheroes) {
              dao.deleteSuperhero(currentSuperhero.getSuperheroId());
          }
          */
          
          List<Organization> organizations = dao.getAllOrganizations();
          for (Organization currentOrganization : organizations) {
              dao.deleteOrganization(currentOrganization.getOrganizationId());
          }
          
          List<Superpower> superpowers = dao.getAllSuperpowers();
          for (Superpower currentSuperpower : superpowers) {
              dao.deleteSuperpower(currentSuperpower.getSuperpowerId());
          }
          
          List<Location> locations = dao.getAllLocations();
          for (Location currentLocation : locations) {
              dao.deleteLocation(currentLocation.getLocationId());
          }
    }
    
    @After
    public void tearDown() {
    }
/*
    // Sighting Tests
    @Test
    public void testAddGetSighting() {
        Location location = new Location();
        location.setName("Pub One");
        location.setDescription("Sweet spot");
        location.setStreet("123 Main Street");
        location.setCity("Publisher City");
        location.setState("OH");
        location.setZip("44123");
        location.setLatitude(090.12345678);
        location.setLongitude(176.12345678);

        dao.addLocation(location);
        
        Organization organization = new Organization();
        organization.setName("Pub One");
        organization.setDescription("Sweet spot");
        organization.setStreet("123 Main Street");
        organization.setCity("Publisher City");
        organization.setState("OH");
        organization.setZip("44123");
        organization.setPhone("555-1212");
        organization.setEmail("organization@main.org");

        dao.addOrganization(organization);
        
        Superpower superpower = new Superpower();
        superpower.setName("Telekinesis");
        superpower.setDescription("Mind control");

        dao.addSuperpower(superpower);
        
        Picture picture = new Picture();
        picture.setTitle("Doctor Strange");
        dao.addPicture(picture);
        
        Superhero superhero1 = new Superhero();
        superhero1.setName("Scarlet Witch");
        superhero1.setDescription("Mind control");
        
        superhero1.setPicture(picture);
        
        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);
        superhero1.setOrganizations(organizations);
        
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        superhero1.setSuperpowers(superpowers);
        
        dao.addSuperhero(superhero1);
        
        Superhero superhero2 = new Superhero();
        superhero2.setName("Scarlet Witch");
        superhero2.setDescription("Mind control");
        
        superhero2.setOrganizations(organizations);
        superhero2.setSuperpowers(superpowers);
        superhero2.setPicture(picture);
        
        dao.addSuperhero(superhero2);
        
        List<Superhero> superheroes = new ArrayList<>();
        superheroes.add(superhero1);
        superheroes.add(superhero2);
        
        Sighting sighting = new Sighting();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse("2020-11-21T12:12:12", formatter);
        sighting.setDate(ldt);
        sighting.setLocation(location);
        sighting.setSuperheroes(superheroes);
        
        dao.addSighting(sighting);

        Sighting fromDao = dao.getSightingById(sighting.getSightingId());
        assertEquals(fromDao.getSightingId(), sighting.getSightingId());
    }

    @Test
    public void testDeleteSighting() {
        Location location = new Location();
        location.setName("Pub One");
        location.setDescription("Sweet spot");
        location.setStreet("123 Main Street");
        location.setCity("Publisher City");
        location.setState("OH");
        location.setZip("44123");
        location.setLatitude(090.12345678);
        location.setLongitude(176.12345678);

        dao.addLocation(location);
        
        Organization organization = new Organization();
        organization.setName("Pub One");
        organization.setDescription("Sweet spot");
        organization.setStreet("123 Main Street");
        organization.setCity("Publisher City");
        organization.setState("OH");
        organization.setZip("44123");
        organization.setPhone("555-1212");
        organization.setEmail("organization@main.org");

        dao.addOrganization(organization);
        
        Superpower superpower = new Superpower();
        superpower.setName("Telekinesis");
        superpower.setDescription("Mind control");

        dao.addSuperpower(superpower);
        
        Picture picture = new Picture();
        picture.setTitle("Doctor Strange");
        dao.addPicture(picture);
        
        Superhero superhero1 = new Superhero();
        superhero1.setName("Scarlet Witch");
        superhero1.setDescription("Mind control");
        
        superhero1.setPicture(picture);
        
        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);
        superhero1.setOrganizations(organizations);
        
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        superhero1.setSuperpowers(superpowers);
        
        dao.addSuperhero(superhero1);
        
        Superhero superhero2 = new Superhero();
        superhero2.setName("Scarlet Witch");
        superhero2.setDescription("Mind control");
        
        superhero2.setOrganizations(organizations);
        superhero2.setSuperpowers(superpowers);
        superhero2.setPicture(picture);
        
        dao.addSuperhero(superhero2);
        
        List<Superhero> superheroes = new ArrayList<>();
        superheroes.add(superhero1);
        superheroes.add(superhero2);
        
        Sighting sighting = new Sighting();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse("2020-11-21T12:12:12", formatter);
        sighting.setDate(ldt);
        sighting.setLocation(location);
        sighting.setSuperheroes(superheroes);
        
        dao.addSighting(sighting);

        Sighting fromDao = dao.getSightingById(sighting.getSightingId());
        assertEquals(fromDao.getSightingId(), sighting.getSightingId());
        
        dao.deleteSighting(sighting.getSightingId());
        assertNull(dao.getSightingById(sighting.getSightingId()));
    }

    @Test
    public void testUpdateSighting() {
        Location location = new Location();
        location.setName("Pub One");
        location.setDescription("Sweet spot");
        location.setStreet("123 Main Street");
        location.setCity("Publisher City");
        location.setState("OH");
        location.setZip("44123");
        location.setLatitude(090.12345678);
        location.setLongitude(176.12345678);

        dao.addLocation(location);
        
        Organization organization = new Organization();
        organization.setName("Pub One");
        organization.setDescription("Sweet spot");
        organization.setStreet("123 Main Street");
        organization.setCity("Publisher City");
        organization.setState("OH");
        organization.setZip("44123");
        organization.setPhone("555-1212");
        organization.setEmail("organization@main.org");

        dao.addOrganization(organization);
        
        Superpower superpower = new Superpower();
        superpower.setName("Telekinesis");
        superpower.setDescription("Mind control");

        dao.addSuperpower(superpower);
        
        Picture picture = new Picture();
        picture.setTitle("Doctor Strange");
        dao.addPicture(picture);
        
        Superhero superhero1 = new Superhero();
        superhero1.setName("Scarlet Witch");
        superhero1.setDescription("Mind control");
        
        superhero1.setPicture(picture);
        
        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);
        superhero1.setOrganizations(organizations);
        
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        superhero1.setSuperpowers(superpowers);
        
        dao.addSuperhero(superhero1);
        
        Superhero superhero2 = new Superhero();
        superhero2.setName("Scarlet Witch");
        superhero2.setDescription("Mind control");
        
        superhero2.setOrganizations(organizations);
        superhero2.setSuperpowers(superpowers);
        superhero2.setPicture(picture);
        
        dao.addSuperhero(superhero2);
        
        List<Superhero> superheroes = new ArrayList<>();
        superheroes.add(superhero1);
        superheroes.add(superhero2);
        
        Sighting sighting = new Sighting();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse("2020-11-21T12:12:12", formatter);
        sighting.setDate(ldt);
        sighting.setLocation(location);
        sighting.setSuperheroes(superheroes);
        
        dao.addSighting(sighting);

        Sighting fromDao = dao.getSightingById(sighting.getSightingId());
        assertEquals(fromDao.getSightingId(), sighting.getSightingId());
        
        LocalDateTime ldtNew = LocalDateTime.parse("2020-11-22T12:12:12", formatter);
        sighting.setDate(ldtNew);
        dao.updateSighting(sighting);
        Sighting updatedFromDao = dao.getSightingById(sighting.getSightingId());
        assertEquals(ldtNew, (updatedFromDao.getDate()));
    }
    
    @Test
    public void getSightingsByLocationId() {
        Location location1 = new Location();
        location1.setName("Pub One");
        location1.setDescription("Sweet spot");
        location1.setStreet("123 Main Street");
        location1.setCity("Publisher City");
        location1.setState("OH");
        location1.setZip("44123");
        location1.setLatitude(090.12345678);
        location1.setLongitude(176.12345678);
        
        dao.addLocation(location1);
        
        Organization organization = new Organization();
        organization.setName("Pub One");
        organization.setDescription("Sweet spot");
        organization.setStreet("123 Main Street");
        organization.setCity("Publisher City");
        organization.setState("OH");
        organization.setZip("44123");
        organization.setPhone("555-1212");
        organization.setEmail("organization@main.org");

        dao.addOrganization(organization);
        
        Superpower superpower = new Superpower();
        superpower.setName("Telekinesis");
        superpower.setDescription("Mind control");

        dao.addSuperpower(superpower);
        
        Picture picture = new Picture();
        picture.setTitle("Doctor Strange");
        dao.addPicture(picture);
        
        Superhero superhero1 = new Superhero();
        superhero1.setName("Scarlet Witch");
        superhero1.setDescription("Mind control");
        
        superhero1.setPicture(picture);
        
        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);
        superhero1.setOrganizations(organizations);
        
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        superhero1.setSuperpowers(superpowers);
        
        dao.addSuperhero(superhero1);
        
        Superhero superhero2 = new Superhero();
        superhero2.setName("Scarlet Witch");
        superhero2.setDescription("Mind control");
        
        superhero2.setOrganizations(organizations);
        superhero2.setSuperpowers(superpowers);
        superhero2.setPicture(picture);
        
        dao.addSuperhero(superhero2);
        
        List<Superhero> superheroes = new ArrayList<>();
        superheroes.add(superhero1);
        superheroes.add(superhero2);
        
        Sighting sighting1 = new Sighting();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime ldt1 = LocalDateTime.parse("2020-11-21T12:12:12", formatter);
        sighting1.setDate(ldt1);
        sighting1.setLocation(location1);
        sighting1.setSuperheroes(superheroes);

        dao.addSighting(sighting1);

        Sighting sighting2 = new Sighting();
        LocalDateTime ldt2 = LocalDateTime.parse("2020-11-22T12:12:12", formatter);
        sighting2.setDate(ldt2);
        sighting2.setLocation(location1);
        sighting2.setSuperheroes(superheroes);

        dao.addSighting(sighting2);
        
        List<Sighting> fromDao = dao.getSightingsByLocationId(location1.getLocationId());
        assertEquals(2, fromDao.size());
    }
    
    @Test
    public void testGetSightingssBySuperheroId() {
        Location location1 = new Location();
        location1.setName("Pub One");
        location1.setDescription("Sweet spot");
        location1.setStreet("123 Main Street");
        location1.setCity("Publisher City");
        location1.setState("OH");
        location1.setZip("44123");
        location1.setLatitude(090.12345678);
        location1.setLongitude(176.12345678);
        
        dao.addLocation(location1);
        
        Organization organization = new Organization();
        organization.setName("Pub One");
        organization.setDescription("Sweet spot");
        organization.setStreet("123 Main Street");
        organization.setCity("Publisher City");
        organization.setState("OH");
        organization.setZip("44123");
        organization.setPhone("555-1212");
        organization.setEmail("organization@main.org");

        dao.addOrganization(organization);
        
        Superpower superpower = new Superpower();
        superpower.setName("Telekinesis");
        superpower.setDescription("Mind control");

        dao.addSuperpower(superpower);
        
        Picture picture = new Picture();
        picture.setTitle("Doctor Strange");
        
        dao.addPicture(picture);
        
        Superhero superhero1 = new Superhero();
        superhero1.setName("Scarlet Witch");
        superhero1.setDescription("Mind control");
        
        superhero1.setPicture(picture);
        
        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);
        superhero1.setOrganizations(organizations);
        
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        superhero1.setSuperpowers(superpowers);
        
        dao.addSuperhero(superhero1);
        
        Superhero superhero2 = new Superhero();
        superhero2.setName("Scarlet Witch");
        superhero2.setDescription("Mind control");
        
        superhero2.setOrganizations(organizations);
        superhero2.setSuperpowers(superpowers);
        superhero2.setPicture(picture);
        
        dao.addSuperhero(superhero2);
        
        List<Superhero> superheroes = new ArrayList<>();
        superheroes.add(superhero1);
        superheroes.add(superhero2);
        
        Sighting sighting1 = new Sighting();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime ldt1 = LocalDateTime.parse("2020-11-21T12:12:12", formatter);
        sighting1.setDate(ldt1);
        sighting1.setLocation(location1);
        sighting1.setSuperheroes(superheroes);

        dao.addSighting(sighting1);

        Sighting sighting2 = new Sighting();
        LocalDateTime ldt2 = LocalDateTime.parse("2020-11-22T12:12:12", formatter);
        sighting2.setDate(ldt2);
        sighting2.setLocation(location1);
        sighting2.setSuperheroes(superheroes);

        dao.addSighting(sighting2);
        
        List<Sighting> fromDao = dao.getSightingsBySuperheroId(superhero1.getSuperheroId());
        assertEquals(2, fromDao.size());
    }
    
    @Test
    public void testGetAllSightings() {
        Location location1 = new Location();
        location1.setName("Pub One");
        location1.setDescription("Sweet spot");
        location1.setStreet("123 Main Street");
        location1.setCity("Publisher City");
        location1.setState("OH");
        location1.setZip("44123");
        location1.setLatitude(090.12345678);
        location1.setLongitude(176.12345678);
        
        dao.addLocation(location1);
        
        Organization organization = new Organization();
        organization.setName("Pub One");
        organization.setDescription("Sweet spot");
        organization.setStreet("123 Main Street");
        organization.setCity("Publisher City");
        organization.setState("OH");
        organization.setZip("44123");
        organization.setPhone("555-1212");
        organization.setEmail("organization@main.org");

        dao.addOrganization(organization);
        
        Superpower superpower = new Superpower();
        superpower.setName("Telekinesis");
        superpower.setDescription("Mind control");

        dao.addSuperpower(superpower);
        
        Picture picture = new Picture();
        picture.setTitle("Doctor Strange");
        
        dao.addPicture(picture);
        
        Superhero superhero1 = new Superhero();
        superhero1.setName("Scarlet Witch");
        superhero1.setDescription("Mind control");
        
        superhero1.setPicture(picture);
        
        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);
        superhero1.setOrganizations(organizations);
        
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        superhero1.setSuperpowers(superpowers);
        
        dao.addSuperhero(superhero1);
        
        Superhero superhero2 = new Superhero();
        superhero2.setName("Scarlet Witch");
        superhero2.setDescription("Mind control");
        
        superhero2.setOrganizations(organizations);
        superhero2.setSuperpowers(superpowers);
        superhero2.setPicture(picture);
        
        dao.addSuperhero(superhero2);
        
        List<Superhero> superheroes = new ArrayList<>();
        superheroes.add(superhero1);
        superheroes.add(superhero2);
        
        Sighting sighting1 = new Sighting();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime ldt1 = LocalDateTime.parse("2020-11-21T12:12:12", formatter);
        sighting1.setDate(ldt1);
        sighting1.setLocation(location1);
        sighting1.setSuperheroes(superheroes);

        dao.addSighting(sighting1);

        Sighting sighting2 = new Sighting();
        LocalDateTime ldt2 = LocalDateTime.parse("2020-11-22T12:12:12", formatter);
        sighting2.setDate(ldt2);
        sighting2.setLocation(location1);
        sighting2.setSuperheroes(superheroes);

        dao.addSighting(sighting2);
        
        List<Sighting> sightingList = dao.getAllSightings();
        assertEquals(2, sightingList.size());
    }

    // Organization Tests
    @Test
    public void testAddGetOrganization() {
        Organization organization = new Organization();
        organization.setName("Pub One");
        organization.setDescription("Sweet spot");
        organization.setStreet("123 Main Street");
        organization.setCity("Publisher City");
        organization.setState("OH");
        organization.setZip("44123");
        organization.setPhone("555-1212");
        organization.setEmail("organization@main.org");

        dao.addOrganization(organization);

        Organization fromDao = dao.getOrganizationById(organization.getOrganizationId());
        assertEquals(fromDao, organization);
    }

    @Test
    public void testDeleteOrganization() {
        Organization organization = new Organization();
        organization.setName("Pub One");
        organization.setDescription("Sweet spot");
        organization.setStreet("123 Main Street");
        organization.setCity("Publisher City");
        organization.setState("OH");
        organization.setZip("44123");
        organization.setPhone("555-1212");
        organization.setEmail("organization@main.org");

        dao.addOrganization(organization);

        Organization fromDao = dao.getOrganizationById(organization.getOrganizationId());
        assertEquals(fromDao, organization);
        
        assertEquals(fromDao, organization);
        dao.deleteOrganization(organization.getOrganizationId());
        assertNull(dao.getOrganizationById(organization.getOrganizationId()));
    }

    @Test
    public void testUpdateOrganization() {
        Organization organization = new Organization();
        organization.setName("Pub One");
        organization.setDescription("Sweet spot");
        organization.setStreet("123 Main Street");
        organization.setCity("Publisher City");
        organization.setState("OH");
        organization.setZip("44123");
        organization.setPhone("555-1212");
        organization.setEmail("organization@main.org");

        dao.addOrganization(organization);

        Organization fromDao = dao.getOrganizationById(organization.getOrganizationId());
        assertEquals(fromDao, organization);
        
        organization.setState("IN");
        dao.updateOrganization(organization);
        Organization updatedFromDao = dao.getOrganizationById(organization.getOrganizationId());
        assertEquals("IN", updatedFromDao.getState());
    }

    @Test
    public void testGetAllOrganizations() {
        Organization organization1 = new Organization();
        organization1.setName("Pub One");
        organization1.setDescription("Sweet spot");
        organization1.setStreet("123 Main Street");
        organization1.setCity("Publisher City");
        organization1.setState("OH");
        organization1.setZip("44123");
        organization1.setPhone("555-1212");
        organization1.setEmail("organization@main.org");
        
        dao.addOrganization(organization1);
        
        Organization organization2 = new Organization();
        organization2.setName("Pub Two");
        organization2.setDescription("Sweet spot");
        organization2.setStreet("123 Main Street");
        organization2.setCity("Publisher City");
        organization2.setState("OH");
        organization2.setZip("44123");
        organization2.setPhone("555-1212");
        organization2.setEmail("organization@main.org");
        
        dao.addOrganization(organization2);
        
        List<Organization> organizationList = dao.getAllOrganizations();
        assertEquals(2, organizationList.size());
    }

    // Superhero Tests
    @Test
    public void testAddGetSuperhero() {
        
        Organization organization = new Organization();
        organization.setName("Pub One");
        organization.setDescription("Sweet spot");
        organization.setStreet("123 Main Street");
        organization.setCity("Publisher City");
        organization.setState("OH");
        organization.setZip("44123");
        organization.setPhone("555-1212");
        organization.setEmail("organization@main.org");

        dao.addOrganization(organization);
        
        Superpower superpower = new Superpower();
        superpower.setName("Telekinesis");
        superpower.setDescription("Mind control");

        dao.addSuperpower(superpower);
        
        Picture picture = new Picture();
        picture.setTitle("Doctor Strange");
        
        dao.addPicture(picture);
        
        Superhero superhero = new Superhero();
        superhero.setName("Scarlet Witch");
        superhero.setDescription("Mind control");
        
        superhero.setPicture(picture);
        
        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);
        superhero.setOrganizations(organizations);
        
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        superhero.setSuperpowers(superpowers);
        
        dao.addSuperhero(superhero);
        
        Superhero fromDao = dao.getSuperheroById(superhero.getSuperheroId());
        
        assertEquals(fromDao.getSuperheroId(), superhero.getSuperheroId());
    }

    @Test
    public void testDeleteSuperhero() {
        
        Organization organization = new Organization();
        organization.setName("Pub One");
        organization.setDescription("Sweet spot");
        organization.setStreet("123 Main Street");
        organization.setCity("Publisher City");
        organization.setState("OH");
        organization.setZip("44123");
        organization.setPhone("555-1212");
        organization.setEmail("organization@main.org");

        dao.addOrganization(organization);
        
        Superpower superpower = new Superpower();
        superpower.setName("Telekinesis");
        superpower.setDescription("Mind control");

        dao.addSuperpower(superpower);
        
        Picture picture = new Picture();
        picture.setTitle("Doctor Strange");
        
        dao.addPicture(picture);
        
        Superhero superhero = new Superhero();
        superhero.setName("Scarlet Witch");
        superhero.setDescription("Mind control");
        
        superhero.setPicture(picture);
        
        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);
        superhero.setOrganizations(organizations);
        
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        superhero.setSuperpowers(superpowers);
        
        dao.addSuperhero(superhero);
        
        Superhero fromDao = dao.getSuperheroById(superhero.getSuperheroId());
        
        assertEquals(fromDao.getSuperheroId(), superhero.getSuperheroId());
        dao.deleteSuperhero(superhero.getSuperheroId());
        assertNull(dao.getSuperheroById(superhero.getSuperheroId()));
    }

    @Test
    public void testUpdateSuperhero() {
        
        Organization organization = new Organization();
        organization.setName("Pub One");
        organization.setDescription("Sweet spot");
        organization.setStreet("123 Main Street");
        organization.setCity("Publisher City");
        organization.setState("OH");
        organization.setZip("44123");
        organization.setPhone("555-1212");
        organization.setEmail("organization@main.org");

        dao.addOrganization(organization);
        
        Superpower superpower = new Superpower();
        superpower.setName("Telekinesis");
        superpower.setDescription("Mind control");

        dao.addSuperpower(superpower);
        
        Picture picture = new Picture();
        picture.setTitle("Doctor Strange");
        
        dao.addPicture(picture);
        
        Superhero superhero = new Superhero();
        superhero.setName("Scarlet Witch");
        superhero.setDescription("Mind control");
        
        superhero.setPicture(picture);
        
        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);
        superhero.setOrganizations(organizations);
        
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        superhero.setSuperpowers(superpowers);
        
        dao.addSuperhero(superhero);
        
        Superhero fromDao = dao.getSuperheroById(superhero.getSuperheroId());
        
        assertEquals(fromDao.getSuperheroId(), superhero.getSuperheroId());
        
        superhero.setName("Vision");
        dao.updateSuperhero(superhero);
        Superhero updatedFromDao = dao.getSuperheroById(superhero.getSuperheroId());
        assertEquals("Vision", updatedFromDao.getName());
    }

    @Test
    public void testGetAllSuperheroes() {
        
        Organization organization1 = new Organization();
        organization1.setName("Pub One");
        organization1.setDescription("Sweet spot");
        organization1.setStreet("123 Main Street");
        organization1.setCity("Publisher City");
        organization1.setState("OH");
        organization1.setZip("44123");
        organization1.setPhone("555-1212");
        organization1.setEmail("organization@main.org");

        dao.addOrganization(organization1);
        
        Superpower superpower1 = new Superpower();
        superpower1.setName("Telekinesis");
        superpower1.setDescription("Mind control");

        dao.addSuperpower(superpower1);
        
        Picture picture1 = new Picture();
        picture1.setTitle("Doctor Strange");
        
        dao.addPicture(picture1);
        
        Superhero superhero1 = new Superhero();
        superhero1.setName("Scarlet Witch");
        superhero1.setDescription("Mind control");
        
        superhero1.setPicture(picture1);
        
        List<Organization> organizations1 = new ArrayList<>();
        organizations1.add(organization1);
        superhero1.setOrganizations(organizations1);
        
        List<Superpower> superpowers1 = new ArrayList<>();
        superpowers1.add(superpower1);
        superhero1.setSuperpowers(superpowers1);
        
        dao.addSuperhero(superhero1);
        
        Organization organization2 = new Organization();
        organization2.setName("Pub One");
        organization2.setDescription("Sweet spot");
        organization2.setStreet("223 Main Street");
        organization2.setCity("Publisher City");
        organization2.setState("OH");
        organization2.setZip("44223");
        organization2.setPhone("555-2222");
        organization2.setEmail("organization@main.org");

        dao.addOrganization(organization2);
        
        Superpower superpower2 = new Superpower();
        superpower2.setName("Telekinesis");
        superpower2.setDescription("Mind control");

        dao.addSuperpower(superpower2);
        
        Picture picture2 = new Picture();
        picture2.setTitle("Doctor Strange");
        
        dao.addPicture(picture2);
        
        Superhero superhero2 = new Superhero();
        superhero2.setName("Scarlet Witch");
        superhero2.setDescription("Mind control");
        
        superhero2.setPicture(picture2);
        
        List<Organization> organizations2 = new ArrayList<>();
        organizations2.add(organization2);
        superhero2.setOrganizations(organizations2);
        
        List<Superpower> superpowers2 = new ArrayList<>();
        superpowers2.add(superpower2);
        superhero2.setSuperpowers(superpowers2);
        
        dao.addSuperhero(superhero2);
        
        List<Superhero> superheroesList = dao.getAllSuperheroes();
        assertEquals(2, superheroesList.size());
    }
    
    @Test
    public void testGetSuperheroesByOrganizationId() {
        
        Organization organization1 = new Organization();
        organization1.setName("Pub One");
        organization1.setDescription("Sweet spot");
        organization1.setStreet("123 Main Street");
        organization1.setCity("Publisher City");
        organization1.setState("OH");
        organization1.setZip("44123");
        organization1.setPhone("555-1212");
        organization1.setEmail("organization@main.org");

        dao.addOrganization(organization1);
        
        Superpower superpower1 = new Superpower();
        superpower1.setName("Telekinesis");
        superpower1.setDescription("Mind control");

        dao.addSuperpower(superpower1);
        
        Picture picture1 = new Picture();
        picture1.setTitle("Doctor Strange");
        
        dao.addPicture(picture1);
        
        Superhero superhero1 = new Superhero();
        superhero1.setName("Scarlet Witch");
        superhero1.setDescription("Mind control");
        
        superhero1.setPicture(picture1);
        
        List<Organization> organizations1 = new ArrayList<>();
        organizations1.add(organization1);
        superhero1.setOrganizations(organizations1);
        
        List<Superpower> superpowers1 = new ArrayList<>();
        superpowers1.add(superpower1);
        superhero1.setSuperpowers(superpowers1);
        
        dao.addSuperhero(superhero1);
        
        Organization organization2 = new Organization();
        organization2.setName("Pub One");
        organization2.setDescription("Sweet spot");
        organization2.setStreet("223 Main Street");
        organization2.setCity("Publisher City");
        organization2.setState("OH");
        organization2.setZip("44223");
        organization2.setPhone("555-2222");
        organization2.setEmail("organization@main.org");

        dao.addOrganization(organization2);
        
        Superpower superpower2 = new Superpower();
        superpower2.setName("Telekinesis");
        superpower2.setDescription("Mind control");

        dao.addSuperpower(superpower2);
        
        Picture picture2 = new Picture();
        picture2.setTitle("Doctor Strange");
        
        dao.addPicture(picture2);
        
        Superhero superhero2 = new Superhero();
        superhero2.setName("Scarlet Witch");
        superhero2.setDescription("Mind control");
        
        superhero2.setPicture(picture2);
        
        superhero2.setOrganizations(organizations1);
        
        List<Superpower> superpowers2 = new ArrayList<>();
        superpowers2.add(superpower2);
        superhero2.setSuperpowers(superpowers2);
        
        dao.addSuperhero(superhero2);
        
        List<Superhero> superheroesList = dao.getSuperheroesByOrganizationId(organization1.getOrganizationId());
        assertEquals(2, superheroesList.size());
    }
    
    @Test
    public void testGetSuperheroesBySuperpowerId() {
        
        Organization organization1 = new Organization();
        organization1.setName("Pub One");
        organization1.setDescription("Sweet spot");
        organization1.setStreet("123 Main Street");
        organization1.setCity("Publisher City");
        organization1.setState("OH");
        organization1.setZip("44123");
        organization1.setPhone("555-1212");
        organization1.setEmail("organization@main.org");

        dao.addOrganization(organization1);
        
        Superpower superpower1 = new Superpower();
        superpower1.setName("Telekinesis");
        superpower1.setDescription("Mind control");

        dao.addSuperpower(superpower1);
        
        Picture picture1 = new Picture();
        picture1.setTitle("Doctor Strange");
        
        dao.addPicture(picture1);
        
        Superhero superhero1 = new Superhero();
        superhero1.setName("Scarlet Witch");
        superhero1.setDescription("Mind control");
        
        superhero1.setPicture(picture1);
        
        List<Organization> organizations1 = new ArrayList<>();
        organizations1.add(organization1);
        superhero1.setOrganizations(organizations1);
        
        List<Superpower> superpowers1 = new ArrayList<>();
        superpowers1.add(superpower1);
        superhero1.setSuperpowers(superpowers1);
        
        dao.addSuperhero(superhero1);
        
        Organization organization2 = new Organization();
        organization2.setName("Pub One");
        organization2.setDescription("Sweet spot");
        organization2.setStreet("223 Main Street");
        organization2.setCity("Publisher City");
        organization2.setState("OH");
        organization2.setZip("44223");
        organization2.setPhone("555-2222");
        organization2.setEmail("organization@main.org");

        dao.addOrganization(organization2);
        
        Superpower superpower2 = new Superpower();
        superpower2.setName("Telekinesis");
        superpower2.setDescription("Mind control");

        dao.addSuperpower(superpower2);
        
        Picture picture2 = new Picture();
        picture2.setTitle("Doctor Strange");
        
        dao.addPicture(picture2);
        
        Superhero superhero2 = new Superhero();
        superhero2.setName("Scarlet Witch");
        superhero2.setDescription("Mind control");
        
        superhero2.setPicture(picture2);
        
        List<Organization> organizations2 = new ArrayList<>();
        organizations2.add(organization2);
        superhero2.setOrganizations(organizations2);
        
        superhero2.setSuperpowers(superpowers1);
        
        dao.addSuperhero(superhero2);
        
        List<Superhero> superheroesList = dao.getSuperheroesBySuperpowerId(superpower1.getSuperpowerId());
        assertEquals(2, superheroesList.size());
    }
    */

    // Superpower Tests
    @Test
    public void testAddGetSuperpower() {
        Superpower superpower = new Superpower();
        superpower.setName("Telekinesis");
        superpower.setDescription("Mind control");

        dao.addSuperpower(superpower);

        Superpower fromDao = dao.getSuperpowerById(superpower.getSuperpowerId());
        assertEquals(fromDao, superpower);
    }

    @Test
    public void testDeleteSuperpower() {
        Superpower superpower = new Superpower();
        superpower.setName("Telekinesis");
        superpower.setDescription("Mind control");

        dao.addSuperpower(superpower);

        Superpower fromDao = dao.getSuperpowerById(superpower.getSuperpowerId());
        assertEquals(fromDao, superpower);
        
        assertEquals(fromDao, superpower);
        dao.deleteSuperpower(superpower.getSuperpowerId());
        assertNull(dao.getSuperpowerById(superpower.getSuperpowerId()));
    }

    @Test
    public void testUpdateSuperpower() {
        Superpower superpower = new Superpower();
        superpower.setName("Telekinesis");
        superpower.setDescription("Mind control");

        dao.addSuperpower(superpower);

        Superpower fromDao = dao.getSuperpowerById(superpower.getSuperpowerId());
        assertEquals(fromDao, superpower);
        
        superpower.setDescription("Super Mind Control");
        dao.updateSuperpower(superpower);
        Superpower updatedFromDao = dao.getSuperpowerById(superpower.getSuperpowerId());
        assertEquals("Super Mind Control", updatedFromDao.getDescription());
    }

    @Test
    public void testGetAllSuperpowers() {
        Superpower superpower1 = new Superpower();
        superpower1.setName("Telekinesis");
        superpower1.setDescription("Mind control");

        dao.addSuperpower(superpower1);
        
        Superpower superpower2 = new Superpower();
        superpower2.setName("Speed");
        superpower2.setDescription("Faster than a speeding bullet");

        dao.addSuperpower(superpower2);
        
        List<Superpower> superpowerList = dao.getAllSuperpowers();
        assertEquals(2, superpowerList.size());
    }
    
    // Location Tests
    @Test
    public void testAddGetLocation() {
        Location location = new Location();
        location.setName("Pub One");
        location.setDescription("Sweet spot");
        location.setStreet("123 Main Street");
        location.setCity("Publisher City");
        location.setState("OH");
        location.setZip("44123");
        location.setLatitude(090.12345678);
        location.setLongitude(176.12345678);

        dao.addLocation(location);

        Location fromDao = dao.getLocationById(location.getLocationId());
        assertEquals(fromDao.getLocationId(), location.getLocationId());
    }

    @Test
    public void testDeleteLocation() {
        Location location = new Location();
        location.setName("Pub One");
        location.setDescription("Sweet spot");
        location.setStreet("123 Main Street");
        location.setCity("Publisher City");
        location.setState("OH");
        location.setZip("44123");
        location.setLatitude(090.12345678);
        location.setLongitude(176.12345678);

        dao.addLocation(location);

        Location fromDao = dao.getLocationById(location.getLocationId());
        
        assertEquals(fromDao.getLocationId(), location.getLocationId());
        dao.deleteLocation(location.getLocationId());
        assertNull(dao.getLocationById(location.getLocationId()));
    }

    @Test
    public void testUpdateLocation() {
        Location location = new Location();
        location.setName("Pub One");
        location.setDescription("Sweet spot");
        location.setStreet("123 Main Street");
        location.setCity("Publisher City");
        location.setState("OH");
        location.setZip("44123");
        location.setLatitude(090.12345678);
        location.setLongitude(176.12345678);

        dao.addLocation(location);

        Location fromDao = dao.getLocationById(location.getLocationId());
        assertEquals(fromDao.getLocationId(), location.getLocationId());
        
        location.setState("IN");
        dao.updateLocation(location);
        Location updatedFromDao = dao.getLocationById(location.getLocationId());
        assertEquals("IN", updatedFromDao.getState());
    }
    
    @Test
    public void testGetAllLocations() {
        Location location1 = new Location();
        location1.setName("Pub One");
        location1.setDescription("Sweet spot");
        location1.setStreet("123 Main Street");
        location1.setCity("Publisher City");
        location1.setState("OH");
        location1.setZip("44123");
        location1.setLatitude(090.12345678);
        location1.setLongitude(176.12345678);

        dao.addLocation(location1);
        
        Location location2 = new Location();
        location2.setName("Pub Two");
        location2.setDescription("Sweet spot");
        location2.setStreet("123 Main Street");
        location2.setCity("Publisher City");
        location2.setState("OH");
        location2.setZip("44123");
        location2.setLatitude(090.12345678);
        location2.setLongitude(176.12345678);

        dao.addLocation(location2);
        
        List<Location> locationList = dao.getAllLocations();
        assertEquals(2, locationList.size());
    }
    
}