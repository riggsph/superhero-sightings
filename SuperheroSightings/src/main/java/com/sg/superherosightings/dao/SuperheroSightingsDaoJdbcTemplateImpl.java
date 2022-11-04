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
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author peterriggs
 */
public class SuperheroSightingsDaoJdbcTemplateImpl implements SuperheroSightingsDao {
    
    private JdbcTemplate jdbcTemplate;
   
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    // Sighting Prepared Statements
    private static final String SQL_INSERT_SIGHTING
        = "insert into sightings (date, location_id) values (?, ?)";
    
    private static final String SQL_INSERT_SIGHTINGS_SUPERHEROES
        = "insert into sightings_superheroes (sighting_id, superhero_id) values (?, ?)";

    private static final String SQL_DELETE_SIGHTING
        = "delete from sightings where sighting_id = ?";

    private static final String SQL_UPDATE_SIGHTING
        = "update sightings set location_id = ? "
        + "where sighting_id = ?";

    private static final String SQL_SELECT_SIGHTING
        = "select * from sightings where sighting_id = ?";

    private static final String SQL_SELECT_SIGHTINGS_BY_SUPERHERO_ID
        = "select si.sighting_id, si.date, si.location_id from sightings si "
        + "join sightings_superheroes shsi on si.sighting_id = shsi.sighting_id where "
        + "shsi.superhero_id = ?";
    
    private static final String SQL_DELETE_SIGHTINGS_SUPERHEROES
        = "delete from sightings_superheroes where sighting_id = ?";

    private static final String SQL_SELECT_ALL_SIGHTINGS
        = "select * from sightings";
    
    // Organization Prepared Statements
    private static final String SQL_INSERT_ORGANIZATION
        = "insert into organizations (name, description, street, city, "
        + "state, zip, phone, email) values (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_ORGANIZATION
        = "delete from organizations where organization_id = ?";

    private static final String SQL_UPDATE_ORGANIZATION
        = "update organizations set name = ?, description = ?, street = ?, "
        + "city = ?, state = ?, zip = ?, phone = ?, email = ? "
        + "where organization_id = ?";

    private static final String SQL_SELECT_ORGANIZATION
        = "select * from organizations where organization_id = ?";

    private static final String SQL_SELECT_ORGANIZATIONS_BY_SUPERHERO_ID
        = "select org.organization_id, org.name, org.description, org.street, "
        + "org.city, org.state, org.zip, org.phone, org.email from organizations org "
        + "join superheroes_organizations so on org.organization_id = so.organization_id where "
        + "so.superhero_id = ?";

    private static final String SQL_SELECT_ALL_ORGANIZATIONS
        = "select * from organizations";
    
    // Superpower Prepared Statements
    private static final String SQL_INSERT_SUPERPOWER
        = "insert into superpowers (name, description) values (?, ?)";

    private static final String SQL_DELETE_SUPERPOWER
        = "delete from superpowers where superpower_id = ?";

    private static final String SQL_UPDATE_SUPERPOWER
        = "update superpowers set name = ?, description = ? where superpower_id = ?";

    private static final String SQL_SELECT_SUPERPOWER
        = "select * from superpowers where superpower_id = ?";

    private static final String SQL_SELECT_SUPERPOWERS_BY_SUPERHERO_ID
        = "select sp.superpower_id, sp.name, sp.description from superpowers sp "
        + "join superheroes_superpowers shsp on sp.superpower_id = shsp.superpower_id where "
        + "shsp.superhero_id = ?";

    private static final String SQL_SELECT_ALL_SUPERPOWERS
        = "select * from superpowers";
    
    // Location Prepared Statements
    private static final String SQL_SELECT_SIGHTINGS_BY_LOCATION_ID
        = "select * from sightings where location_id = ?";
    
    private static final String SQL_INSERT_LOCATION
        = "insert into locations (name, description, street, city, "
        + "state, zip, latitude, longitude) values (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_LOCATION
        = "delete from locations where location_id = ?";

    private static final String SQL_UPDATE_LOCATION
        = "update locations set name = ?, description = ?, street = ?, "
        + "city = ?, state = ?, zip = ?, latitude = ?, longitude = ? "
        + "where location_id = ?";

    private static final String SQL_SELECT_LOCATION
        = "select * from locations where location_id = ?";

    private static final String SQL_SELECT_LOCATION_BY_SIGHTING_ID
        = "select lo.location_id, lo.name, lo.description, lo.street, "
        + "lo.city, lo.state, lo.zip, lo.latitude, lo.longitude from locations lo "
        + "join sightings si on lo.location_id = si.location_id where "
        + "si.sighting_id = ?";

    private static final String SQL_SELECT_ALL_LOCATIONS
        = "select * from locations";
    
    // Picture Prepared Statements
    private static final String SQL_INSERT_PICTURE
        = "insert into pictures (title, image) values (?, ?)";

    private static final String SQL_DELETE_PICTURE
        = "delete from pictures where picture_id = ?";

    private static final String SQL_UPDATE_PICTURE
        = "update pictures set title = ?, image = ? "
        + "where picture_id = ?";

    private static final String SQL_SELECT_PICTURE
        = "select * from pictures where picture_id = ?";

    private static final String SQL_SELECT_PICTURE_BY_SUPERHERO_ID
        = "select pi.picture_id, pi.title, pi.image from pictures pi "
        + "join superheroes sh on pi.picture_id = sh.picture_id where "
        + "sh.superhero_id = ?";

    private static final String SQL_SELECT_ALL_PICTURES
        = "select * from pictures";
    
    // Superhero Prepared Statements
    private static final String SQL_INSERT_SUPERHERO
        = "insert into superheroes (name, description, picture_id) values (?, ?, ?)";
    
    private static final String SQL_INSERT_SUPERHEROES_ORGANIZATIONS
        = "insert into superheroes_organizations (superhero_id, organization_id) values (?, ?)";
    
    private static final String SQL_INSERT_SUPERHEROES_SUPERPOWERS
        = "insert into superheroes_superpowers (superhero_id, superpower_id) values (?, ?)";

    private static final String SQL_DELETE_SUPERHERO
        = "delete from superheroes where superhero_id = ?";

    private static final String SQL_UPDATE_SUPERHERO
        = "update superheroes set name = ?, description = ? where superhero_id = ?";

    private static final String SQL_SELECT_SUPERHERO
        = "select * from superheroes where superhero_id = ?";

    private static final String SQL_SELECT_ALL_SUPERHEROES
        = "select * from superheroes";
    
    private static final String SQL_SELECT_SUPERHEROES_BY_SIGHTING_ID
        = "select sh.superhero_id, sh.name, sh.description, sh.picture_id from superheroes sh "
        + "join sightings_superheroes sish on sighting_id "
        + "where sh.superhero_id = sish.superhero_id "
        + "and sish.sighting_id = ?;";
    
    private static final String SQL_SELECT_SUPERHEROES_BY_ORGANIZATION_ID
        = "select sh.superhero_id, sh.name, sh.description, sh.picture_id from superheroes sh "
        + "join superheroes_organizations so on organization_id "
        + "where sh.superhero_id = so.superhero_id "
        + "and so.organization_id = ?;";
    
    private static final String SQL_DELETE_SUPERHEROES_ORGANIZATIONS
        = "delete from superheroes_organizations where superhero_id = ?";
    
    private static final String SQL_SELECT_SUPERHEROES_BY_SUPERPOWER_ID
        = "select sh.superhero_id, sh.name, sh.description, sh.picture_id from superheroes sh "
        + "join superheroes_superpowers shsp on superpower_id "
        + "where sh.superhero_id = shsp.superhero_id "
        + "and shsp.superpower_id = ?;";
    
    private static final String SQL_DELETE_SUPERHEROES_SUPERPOWERS
        = "delete from superheroes_superpowers where superhero_id = ?";
    
    
    // Sighting methods
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSighting(Sighting sighting) {
        try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = sighting.getDate().format(formatter);
        formattedDateTime += ":00";
        jdbcTemplate.update(SQL_INSERT_SIGHTING,
                formattedDateTime,
                sighting.getLocation().getLocationId());
        } catch (NullPointerException e) {
            
        }

        int sightingId = 
                jdbcTemplate.queryForObject("select LAST_INSERT_ID()", 
                                             Integer.class);

        sighting.setSightingId(sightingId);
        
        insertSightingsSuperheroes(sighting);
    }

    @Override
    public void deleteSighting(int sightingId) {
        jdbcTemplate.update(SQL_DELETE_SIGHTING, sightingId);
    }

    @Override
    public void updateSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING,
                sighting.getLocation().getLocationId(),
                sighting.getSightingId());
        
        jdbcTemplate.update(SQL_DELETE_SIGHTINGS_SUPERHEROES, sighting.getSightingId());
        insertSightingsSuperheroes(sighting);
    }

    @Override
    public Sighting getSightingById(int id) {
        try {
            Sighting sighting = jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING, 
                                               new SightingMapper(), 
                                               id);
            sighting.setLocation(findLocationForSighting(sighting));
            sighting.setSuperheroes(findSuperheroesForSighting(sighting));
            return sighting;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Sighting> getSightingsByLocationId(int locationId) {
        List<Sighting> sightingList = 
                jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_LOCATION_ID, 
                                   new SightingMapper(), 
                                   locationId);
        associateLocationAndSuperheroesWithSightings(sightingList);
        return sightingList;
    }
    
    @Override
    public List<Sighting> getSightingsBySuperheroId(int superheroId) {
        List<Sighting> sightingList = jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_SUPERHERO_ID, 
                                             new SightingMapper(),
                                             superheroId);
        return associateLocationAndSuperheroesWithSightings(sightingList);
    }

    @Override
    public List<Sighting> getAllSightings() {
        List<Sighting> sightingList =  jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS, 
                              new SightingMapper());
        
        // Order list by date, then reverse it to make List[0] most recent
        sightingList.sort(Comparator.comparing(Sighting::getDate));
        Collections.reverse(sightingList);
        return associateLocationAndSuperheroesWithSightings(sightingList);
    }
    
    // Organization methods
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addOrganization(Organization organization) {
        jdbcTemplate.update(SQL_INSERT_ORGANIZATION,
                organization.getName(),
                organization.getDescription(),
                organization.getStreet(),
                organization.getCity(),
                organization.getState(),
                organization.getZip(),
                organization.getPhone(),
                organization.getEmail());

        int organizationId = 
                jdbcTemplate.queryForObject("select LAST_INSERT_ID()", 
                                             Integer.class);

        organization.setOrganizationId(organizationId);
    }

    @Override
    public void deleteOrganization(int organizationId) {
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION, organizationId);
    }

    @Override
    public void updateOrganization(Organization organization) {
        jdbcTemplate.update(SQL_UPDATE_ORGANIZATION,
                organization.getName(),
                organization.getDescription(),
                organization.getStreet(),
                organization.getCity(),
                organization.getState(),
                organization.getZip(),
                organization.getPhone(),
                organization.getEmail(),
                organization.getOrganizationId());
    }

    @Override
    public Organization getOrganizationById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION, 
                                               new OrganizationMapper(), 
                                               id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATIONS, 
                              new OrganizationMapper());
    }
    
    // Superpower methods
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSuperpower(Superpower superpower) {
        jdbcTemplate.update(SQL_INSERT_SUPERPOWER,
                superpower.getName(),
                superpower.getDescription());

        int superpowerId = 
                jdbcTemplate.queryForObject("select LAST_INSERT_ID()", 
                                             Integer.class);

        superpower.setSuperpowerId(superpowerId);
    }

    @Override
    public void deleteSuperpower(int superpowerId) {
        jdbcTemplate.update(SQL_DELETE_SUPERPOWER, superpowerId);
    }

    @Override
    public void updateSuperpower(Superpower superpower) {
        jdbcTemplate.update(SQL_UPDATE_SUPERPOWER,
                superpower.getName(),
                superpower.getDescription(),
                superpower.getSuperpowerId());
    }

    @Override
    public Superpower getSuperpowerById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SUPERPOWER, 
                                               new SuperpowerMapper(), 
                                               id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Superpower> getAllSuperpowers() {
        return jdbcTemplate.query(SQL_SELECT_ALL_SUPERPOWERS, 
                              new SuperpowerMapper());
    }
    
    // Location Methods
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addLocation(Location location) {
        jdbcTemplate.update(SQL_INSERT_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getStreet(),
                location.getCity(),
                location.getState(),
                location.getZip(),
                location.getLatitude(),
                location.getLongitude());

        int locationId = 
                jdbcTemplate.queryForObject("select LAST_INSERT_ID()", 
                                             Integer.class);

        location.setLocationId(locationId);
    }

    @Override
    public void deleteLocation(int locationId) {
        jdbcTemplate.update(SQL_DELETE_LOCATION, locationId);
    }

    @Override
    public void updateLocation(Location location) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getStreet(),
                location.getCity(),
                location.getState(),
                location.getZip(),
                location.getLatitude(),
                location.getLongitude(),
                location.getLocationId());
    }

    @Override
    public Location getLocationById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION, 
                                               new LocationMapper(), 
                                               id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS, 
                              new LocationMapper());
    }
    
    // Picture Methods
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Picture addPicture(Picture picture) {
        
        try {
            byte[] imageData = picture.getImage();
            ByteArrayInputStream in = new ByteArrayInputStream(imageData);

            LobHandler lobHandler = new DefaultLobHandler();
            jdbcTemplate.update(SQL_INSERT_PICTURE,
                                new Object[] {
                                    picture.getTitle(),
                                    new SqlLobValue(in, imageData.length, lobHandler)
                                },
                                new int[] {Types.VARCHAR, Types.BLOB});
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        int pictureId = 
                jdbcTemplate.queryForObject("select LAST_INSERT_ID()", 
                                             Integer.class);

        picture.setPictureId(pictureId);
        return picture;
    }

    @Override
    public void deletePicture(int pictureId) {
        jdbcTemplate.update(SQL_DELETE_PICTURE, pictureId);
    }
    
    @Override
    public void updatePicture(Picture picture) {
        byte[] imageData = picture.getImage();
        InputStream in = new ByteArrayInputStream(imageData);
        jdbcTemplate.update(SQL_UPDATE_PICTURE,
                picture.getTitle(),
                in,
                picture.getPictureId());
    }

    @Override
    public Picture getPictureById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_PICTURE, 
                                               new PictureMapper(), 
                                               id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Picture> getAllPictures() {
        return jdbcTemplate.query(SQL_SELECT_ALL_PICTURES, 
                              new PictureMapper());
    }
    
    // Superhero methods
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSuperhero(Superhero superhero) {
        int pictureId = addPicture(superhero.getPicture()).getPictureId();
        jdbcTemplate.update(SQL_INSERT_SUPERHERO,
                superhero.getName(),
                superhero.getDescription(),
                pictureId);

        int superheroId = 
                jdbcTemplate.queryForObject("select LAST_INSERT_ID()", 
                                             Integer.class);

        superhero.setSuperheroId(superheroId);
        try {
            insertSuperheroesOrganizations(superhero);
        } catch (NullPointerException e) {
            // do nothing?
        }
        
        try {
            insertSuperheroesSuperpowers(superhero);
        } catch (NullPointerException e) {
            // do nothing?
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteSuperhero(int superheroId) {
        deletePicture(getSuperheroById(superheroId).getPicture().getPictureId());
        jdbcTemplate.update(SQL_DELETE_SUPERHERO, superheroId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateSuperhero(Superhero superhero) {
        jdbcTemplate.update(SQL_UPDATE_SUPERHERO,
                superhero.getName(),
                superhero.getDescription(),
                superhero.getSuperheroId());
        jdbcTemplate.update(SQL_DELETE_SUPERHEROES_ORGANIZATIONS, superhero.getSuperheroId());
        jdbcTemplate.update(SQL_DELETE_SUPERHEROES_SUPERPOWERS, superhero.getSuperheroId());
        insertSuperheroesOrganizations(superhero);
        insertSuperheroesSuperpowers(superhero);
    }

    @Override
    public Superhero getSuperheroById(int id) {
        try {
            Superhero superhero = jdbcTemplate.queryForObject(SQL_SELECT_SUPERHERO, 
                                                    new SuperheroMapper(), 
                                                    id);
            superhero.setOrganizations(findOrganizationsForSuperhero(superhero));
            superhero.setSuperpowers(findSuperpowersForSuperhero(superhero));
            superhero.setPicture(findPictureForSuperhero(superhero));
            return superhero;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Superhero> getAllSuperheroes() {
        List<Superhero> superheroList = jdbcTemplate.query(SQL_SELECT_ALL_SUPERHEROES, 
                                             new SuperheroMapper());
        return associateOrganizationsAndSuperpowersAndPictureWithSuperheroes(superheroList);
    }

    @Override
    public List<Superhero> getSuperheroesByOrganizationId(int organizationId) {
        List<Superhero> superheroList = jdbcTemplate.query(SQL_SELECT_SUPERHEROES_BY_ORGANIZATION_ID, 
                                             new SuperheroMapper(),
                                             organizationId);
        return associateOrganizationsAndSuperpowersAndPictureWithSuperheroes(superheroList);
    }

    @Override
    public List<Superhero> getSuperheroesBySuperpowerId(int superpowerId) {
        List<Superhero> superheroList = jdbcTemplate.query(SQL_SELECT_SUPERHEROES_BY_SUPERPOWER_ID, 
                                             new SuperheroMapper(),
                                             superpowerId);
        return associateOrganizationsAndSuperpowersAndPictureWithSuperheroes(superheroList);
    }
    
    // Helper Methods
    private void insertSightingsSuperheroes(Sighting sighting) {
        final int sightingId = sighting.getSightingId();
        final List<Superhero> superheroes = sighting.getSuperheroes();

        for (Superhero currentSuperhero : superheroes) {
            jdbcTemplate.update(SQL_INSERT_SIGHTINGS_SUPERHEROES, 
                                sightingId, 
                                currentSuperhero.getSuperheroId());
        }
    }
    
    private void insertSuperheroesOrganizations(Superhero superhero) {
        final int superheroId = superhero.getSuperheroId();
        final List<Organization> organizations = superhero.getOrganizations();

        for (Organization currentOrganization : organizations) {
            jdbcTemplate.update(SQL_INSERT_SUPERHEROES_ORGANIZATIONS, 
                                superheroId, 
                                currentOrganization.getOrganizationId());
        }
    }
    
    private void insertSuperheroesSuperpowers(Superhero superhero) {
        final int superheroId = superhero.getSuperheroId();
        final List<Superpower> superpowers = superhero.getSuperpowers();
        
        for (Superpower currentSuperpower : superpowers) {
            jdbcTemplate.update(SQL_INSERT_SUPERHEROES_SUPERPOWERS, 
                                superheroId, 
                                currentSuperpower.getSuperpowerId());
        }
    }
    
    private List<Superhero> findSuperheroesForSighting(Sighting sighting) {
        List<Superhero> superheroList = new ArrayList<>();
        try{
        superheroList = jdbcTemplate.query(SQL_SELECT_SUPERHEROES_BY_SIGHTING_ID, 
                                  new SuperheroMapper(), 
                                  sighting.getSightingId());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
        associateOrganizationsAndSuperpowersAndPictureWithSuperheroes(superheroList);
        return superheroList;
    }
    
    private Location findLocationForSighting(Sighting sighting) {
        Location location = new Location();
        try{
        location = jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_SIGHTING_ID,
                                           new LocationMapper(),
                                           sighting.getSightingId());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
        return location;
    }
    
    private List<Organization> findOrganizationsForSuperhero(Superhero superhero) {
        return jdbcTemplate.query(SQL_SELECT_ORGANIZATIONS_BY_SUPERHERO_ID, 
                                  new OrganizationMapper(), 
                                  superhero.getSuperheroId());
    }
    
    private List<Superpower> findSuperpowersForSuperhero(Superhero superhero) {
        return jdbcTemplate.query(SQL_SELECT_SUPERPOWERS_BY_SUPERHERO_ID, 
                                  new SuperpowerMapper(), 
                                  superhero.getSuperheroId());
    }
    
    private Picture findPictureForSuperhero(Superhero superhero) {
        Picture picture = new Picture();
        try{
        picture = jdbcTemplate.queryForObject(SQL_SELECT_PICTURE_BY_SUPERHERO_ID,
                                           new PictureMapper(),
                                           superhero.getSuperheroId());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
        return picture;
    }
    
    private List<Superhero> 
        associateOrganizationsAndSuperpowersAndPictureWithSuperheroes(List<Superhero> superheroList) {
        for (Superhero currentSuperhero : superheroList) {
            currentSuperhero.setOrganizations(findOrganizationsForSuperhero(currentSuperhero));
            currentSuperhero.setSuperpowers(findSuperpowersForSuperhero(currentSuperhero));
            currentSuperhero.setPicture(findPictureForSuperhero(currentSuperhero));
        }
        return superheroList;
        }
    
    private List<Sighting>
            associateLocationAndSuperheroesWithSightings(List<Sighting> sightingList) {
        for (Sighting currentSighting : sightingList) {
            currentSighting.setLocation(findLocationForSighting(currentSighting));
            currentSighting.setSuperheroes(findSuperheroesForSighting(currentSighting));
        }
        return sightingList;
    }
    
    
    // Mappers
    private static final class SightingMapper implements RowMapper<Sighting> {

    @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting si = new Sighting();
            si.setSightingId(rs.getInt("sighting_id"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime ldt = LocalDateTime.parse(rs.getString("date"), formatter);
            si.setDate(ldt);
            return si;
        }
    }
    
    private static final class OrganizationMapper implements RowMapper<Organization> {

    @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization org = new Organization();
            org.setOrganizationId(rs.getInt("organization_id"));
            org.setName(rs.getString("name"));
            org.setDescription(rs.getString("description"));
            org.setStreet(rs.getString("street"));
            org.setCity(rs.getString("city"));
            org.setState(rs.getString("state"));
            org.setZip(rs.getString("zip"));
            org.setPhone(rs.getString("phone"));
            org.setEmail(rs.getString("email"));
            return org;
        }
    }
    
    private static final class SuperheroMapper implements RowMapper<Superhero> {

    @Override
        public Superhero mapRow(ResultSet rs, int i) throws SQLException {
            Superhero sh = new Superhero();
            sh.setSuperheroId(rs.getInt("superhero_id"));
            sh.setName(rs.getString("name"));
            sh.setDescription(rs.getString("description"));
            return sh;
        }
    }
    
    private static final class SuperpowerMapper implements RowMapper<Superpower> {

    @Override
        public Superpower mapRow(ResultSet rs, int i) throws SQLException {
            Superpower sp = new Superpower();
            sp.setSuperpowerId(rs.getInt("superpower_id"));
            sp.setName(rs.getString("name"));
            sp.setDescription(rs.getString("description"));
            return sp;
        }
    }
    
    private static final class LocationMapper implements RowMapper<Location> {
        
    @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location lo = new Location();
            lo.setLocationId(rs.getInt("location_id"));
            lo.setName(rs.getString("name"));
            lo.setDescription(rs.getString("description"));
            lo.setStreet(rs.getString("street"));
            lo.setCity(rs.getString("city"));
            lo.setState(rs.getString("state"));
            lo.setZip(rs.getString("zip"));
            lo.setLatitude(rs.getDouble("latitude"));
            lo.setLongitude(rs.getDouble("longitude"));
            return lo;
        }
    }
    
    private static final class PictureMapper implements RowMapper<Picture> {
        
    @Override
        public Picture mapRow(ResultSet rs, int i) throws SQLException {
            Picture pi = new Picture();
            pi.setPictureId(rs.getInt("picture_id"));
            pi.setTitle(rs.getString("title"));
            
            InputStream inputStream = rs.getBlob("image").getBinaryStream();
                 
            // To display in JSP, create byte array out of DB Blob, then encode as base64 image
            String base64Image = "";
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            try {
                
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);                  
                }

                byte[] imageBytes = outputStream.toByteArray();
                base64Image = Base64.getEncoder().encodeToString(imageBytes);


                inputStream.close();
                outputStream.close();
            } catch (IOException e) {

            }

            pi.setBase64Image(base64Image);
            return pi;
        }
    }
}
