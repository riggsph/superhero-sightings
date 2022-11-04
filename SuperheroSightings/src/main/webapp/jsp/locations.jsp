<%-- 
    Document   : Location
    Created on : Jan 3, 2021, 9:26:58 AM
    Author     : peterriggs
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Superhero Sightings</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h1>Superhero Sightings</h1>
            <hr/>
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/">Home</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                  <div class="navbar-nav">
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/displaySightingsPage">Sightings</a>
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/displaySuperheroesPage">Superheroes</a>
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/displayOrganizationsPage">Organizations</a>
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/displaySuperpowersPage">Superpowers</a>
                    <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/displayLocationsPage">Locations <span class="sr-only">(current)</span></a>
                  </div>
                </div>
            </nav>
                  
            <br/>
            <h3>Add New Location</h3>
            <form class="form-horizontal" 
                      role="form" method="POST" 
                      action="createLocation">
                <div class="row">
                    <div class="col-6">
                        <div class="form-group">
                            <input type="text" class="form-control" name="name" placeholder="Name" required/>
                        </div>
                        <div class="form-group">
                                <textarea id="add-description" class="form-control" name="description" placeholder="Description" rows="3" required/></textarea>
                        </div>
                        <div class="form-group">
                            <input type="number" class="form-control" name="latitude" placeholder="Latitude" maxlength="11" required/>
                        </div>
                        <div class="form-group">
                            <input type="number" class="form-control" name="longitude" placeholder="Longitude" maxlength="11" required/>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="form-group">
                            <input type="text" class="form-control" name="street" placeholder="Street" required/>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" name="city" placeholder="City" required/>
                        </div>
                        <div class="form-group">
                            <select class="form-control" name="state" required>
                                <option value="null">-- Select State --</option>
                                <option value="AL">Alabama</option>
                                <option value="AK">Alaska</option>
                                <option value="AZ">Arizona</option>
                                <option value="AR">Arkansas</option>
                                <option value="CA">California</option>
                                <option value="CO">Colorado</option>
                                <option value="CT">Connecticut</option>
                                <option value="DE">Delaware</option>
                                <option value="DC">District Of Columbia</option>
                                <option value="FL">Florida</option>
                                <option value="GA">Georgia</option>
                                <option value="HI">Hawaii</option>
                                <option value="ID">Idaho</option>
                                <option value="IL">Illinois</option>
                                <option value="IN">Indiana</option>
                                <option value="IA">Iowa</option>
                                <option value="KS">Kansas</option>
                                <option value="KY">Kentucky</option>
                                <option value="LA">Louisiana</option>
                                <option value="ME">Maine</option>
                                <option value="MD">Maryland</option>
                                <option value="MA">Massachusetts</option>
                                <option value="MI">Michigan</option>
                                <option value="MN">Minnesota</option>
                                <option value="MS">Mississippi</option>
                                <option value="MO">Missouri</option>
                                <option value="MT">Montana</option>
                                <option value="NE">Nebraska</option>
                                <option value="NV">Nevada</option>
                                <option value="NH">New Hampshire</option>
                                <option value="NJ">New Jersey</option>
                                <option value="NM">New Mexico</option>
                                <option value="NY">New York</option>
                                <option value="NC">North Carolina</option>
                                <option value="ND">North Dakota</option>
                                <option value="OH">Ohio</option>
                                <option value="OK">Oklahoma</option>
                                <option value="OR">Oregon</option>
                                <option value="PA">Pennsylvania</option>
                                <option value="RI">Rhode Island</option>
                                <option value="SC">South Carolina</option>
                                <option value="SD">South Dakota</option>
                                <option value="TN">Tennessee</option>
                                <option value="TX">Texas</option>
                                <option value="UT">Utah</option>
                                <option value="VT">Vermont</option>
                                <option value="VA">Virginia</option>
                                <option value="WA">Washington</option>
                                <option value="WV">West Virginia</option>
                                <option value="WI">Wisconsin</option>
                                <option value="WY">Wyoming</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <input type="number" class="form-control" name="zip" placeholder="Zip Code" minlength="5" maxlength="5" required/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <input type="submit" class="btn btn-primary" value="Create Location"/>
                </div>
            </form>
            
            <div>
                <h3>Locations</h3>
                <table id="superpowerTable" class="table table-hover table-bordered table-striped">
                    <tr>
                        <th width="25%" class="align-middle text-center">Name</th>
                        <th width="35%" class="align-middle text-center">Street</th>
                        <th width="20%" class="align-middle text-center">City</th>
                        <th width="10%" class="align-middle text-center">State</th>
                        <th width="10%" class="align-middle text-center"></th>
                    </tr>
                    <c:forEach var="currentLocation" items="${locationList}">
                        <tr>
                            <td class="align-middle text-center">
                                <a href="displayLocationDetails?locationId=${currentLocation.locationId}">
                                    <c:out value="${currentLocation.name}"/>
                                </a>
                            </td>
                            <td class="align-middle text-center">
                                <c:out value="${currentLocation.street}"/>
                            </td>
                            <td class="align-middle text-center">
                                <c:out value="${currentLocation.city}"/>
                            </td>
                            <td class="align-middle text-center">
                                <c:out value="${currentLocation.state}"/>
                            </td>
                            <td class="align-middle text-center">
                                <a href="displayEditLocationForm?locationId=${currentLocation.locationId}">
                                Edit
                                </a>
                                <a href="deleteLocation?locationId=${currentLocation.locationId}">
                                Delete
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>                    
            </div>
                  
                
        </div>
        
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>


