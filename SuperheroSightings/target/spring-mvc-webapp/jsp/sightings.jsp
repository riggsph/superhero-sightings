<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title>Superhero Sightings</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        
        <style>
            .map {
                height: 200px;
                width: 100%;
            }
        </style>
        <script src=""></script>
        
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
                    <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/displaySightingsPage">Sightings <span class="sr-only">(current)</span></a>
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/displaySuperheroesPage">Superheroes</a>
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/displayOrganizationsPage">Organizations</a>
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/displaySuperpowersPage">Superpowers</a>
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/displayLocationsPage">Locations</a>
                  </div>
                </div>
            </nav>
                  
            <br/>
            <h3>Add New Sighting</h3>
                <form class="form-horizontal" 
                      method="POST" action="createSighting">
                    <div class="row">
                        <div class="col-6">
                            <div class="form-group">
                                <input type="date" class="form-control" name="date" required/>
                                <div class="valid-feedback">Success!</div>
                                <div class="invalid-feedback">Please select a date.</div>
                            </div>
                            <div class="form-group">
                                <input type="time" class="form-control" name="time" required/>
                            </div>
                            <div class="form-group">
                                <select name="location" class="form-control" required>
                                    <option hidden disabled selected value> -- select a location -- </option>
                                    <c:forEach var="currentLocation" items="${locationList}">
                                        <option value="${currentLocation.locationId}">${currentLocation.name}</option>
                                    </c:forEach>
                                </select>
                                <small class="form-text text-muted">
                                    (Create new location in the Locations tab)<br/>
                                </small>
                            </div>
                            <div class="form-group">
                                <select name="superheroes" multiple="true" class="form-control" required>
                                    <option disabled value="null">-- Select Superhero(es) --</option>
                                    <c:forEach var="currentSuperhero" items="${superheroList}">
                                        <option value="${currentSuperhero.superheroId}">${currentSuperhero.name}</option>
                                    </c:forEach>
                                </select>
                                <small class="form-text text-muted">
                                    (Create new superhero in the Superheroes tab)
                                </small>
                            </div>
                            <div class="form-group">
                                <input type="submit" class="btn btn-primary" value="Create Sighting"/>
                            </div>
                        </div>
                    </div>
                </form>
            

            <br/>
            <div class="table-responsive text-center" id="sightingsTable">
                <h3 class="text-left" >Recent Sightings</h3>
                <table class="table table-bordered table-striped">
                    <thead>
                        <tr class="table-active">
                            <th scope="col" width="45%">Superheroes</th>
                            <th scope="col" width="35%">Location</th>
                            <th scope="col" width="15%">Date</th>
                            <th scope="col" width="5%"></th>
                        </tr>
                    </thead>
                    
                    <tbody id="displayRecentSightings">
                        <c:forEach var="currentSighting" items="${sightingList}">
                            <tr>
                                <td class="align-middle">
                                    <div class="row">
                                    <c:forEach var="currentSuperhero" items="${currentSighting.superheroes}">
                                        <div class="col">
                                        <a href="displaySuperheroDetails?superheroId=${currentSuperhero.superheroId}">
                                        <img src="data:image/jpg;base64,${currentSuperhero.picture.base64Image}" width="100" height="150"/>
                                        <br/>
                                        <c:out value="${currentSuperhero.name}"/>
                                        </a>
                                        </div>
                                    </c:forEach>
                                    </div>
                                </td>
                                <td class="align-middle" id="latitude">
                                    <c:out value="${currentSighting.location.name}"/>
                                    <div class="map" id="${currentSighting.sightingId}">${currentSighting.location.latitude},${currentSighting.location.longitude},9</div>
                                    
                                </td>
                                <td class="align-middle">
                                    <c:set var="dateStr">${currentSighting.date}</c:set>
                                    <c:out value="${fn:replace(dateStr, 'T', ' ')}"/>
                                </td>
                                <td class="align-middle text-center">
                                    <a href="displayEditSightingForm?sightingId=${currentSighting.sightingId}">
                                    Edit
                                    </a>
                                    <br/>
                                    <a href="deleteSighting?sightingId=${currentSighting.sightingId}">
                                    Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- Main Page Content Stop -->    
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/superheroSightings.js"></script>

        <script>
           $('.map').each(function initMap(index, Element) {
                var coords = $(Element).text().split(",");
                if (coords.length !== 3) {
                    $(this).display = "none";
                    return;
                }
                var latlng = new google.maps.LatLng(parseFloat(coords[0]), parseFloat(coords[1]));
                var myOptions = {
                    zoom: parseFloat(coords[2]),
                    center: latlng,
                    mapTypeId: google.maps.MapTypeId.ROADMAP,
                    disableDefaultUI: false,
                    mapTypeControl: true,
                    zoomControl: true,
                    zoomControlOptions: {
                        style: google.maps.ZoomControlStyle.SMALL
                    }
                };
                var map = new google.maps.Map(Element, myOptions);

                var marker = new google.maps.Marker({
                    position: latlng,
                    map: map
                });
            }); 

        </script>
        
    </body>
</html>


