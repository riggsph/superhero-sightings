<%-- 
    Document   : locationDetails
    Created on : Jan 4, 2021, 11:49:52 PM
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
        <title>Location Information</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div id="viewLocation">
                <div>
                    <div class="text-left">
                        <h2 id="viewLocationHeader"><c:out value="${location.name}"/></h2>
                    </div>
                </div>
                <hr class="border"/>
                <div class="col text-left" id="viewLocationFormGroup">
                    <form>
                        <div class="form-group row">
                            <label for="locationDescription" class="col-md-2 font-weight-bold">Description:</label>
                            <div class="col-md-6">
                                <label for="locationDescription" class="font-weight" id="locationDescription"><c:out value="${location.description}"/></label>
                            </div>
                        </div>
                        <div class="form-group row align-items-center">
                            <label for="locationStreet" class="col-md-2 font-weight-bold">Street:</label>
                            <div class="col-md-6">
                                <label for="locationStreet" class="font-weight" id="locationStreet"><c:out value="${location.street}"/></label>
                            </div>
                        </div>
                        <div class="form-group row align-items-center">
                            <label for="locationCity" class="col-md-2 font-weight-bold">City:</label>
                            <div class="col-md-6">
                                <label for="locationCity" class="font-weight" id="locationCity"><c:out value="${location.city}"/></label>
                            </div>
                        </div>
                        <div class="form-group row align-items-center">
                            <label for="locationState" class="col-md-2 font-weight-bold">State:</label>
                            <div class="col-md-2">
                                <label for="locationState" class="font-weight" id="locationState"><c:out value="${location.state}"/></label>
                            </div>
                        </div>
                            <div class="form-group row align-items-center">
                            <label for="locationZip" class="col-md-2 font-weight-bold">Zip Code:</label>
                            <div class="col-md-6">
                                <label for="locationZip" class="font-weight" id="locationZip"><c:out value="${location.zip}"/></label>
                            </div>
                        </div>
                        <div class="form-group row align-items-center">
                            <label for="locationLatitude" class="col-md-2 font-weight-bold">Latitude:</label>
                            <div class="col-md-6">
                                <label for="locationLatitude" class="font-weight" id="locationLatitude"><c:out value="${location.latitude}"/></label>
                            </div>
                        </div>
                        <div class="form-group row align-items-center">
                            <label for="locationLongitude" class="col-md-2 font-weight-bold">Longitude:</label>
                            <div class="col-md-2">
                                <label for="locationLongitude" class="font-weight" id="locationLongitude"><c:out value="${location.longitude}"/></label>
                            </div>
                        </div>
                        
                    </form>
                    <br/>
                    <a href="${pageContext.request.contextPath}/displayLocationsPage">
                        <button type="button" class="btn btn-primary shadow-sm" id="viewLocationBackButton">Back</button>
                    </a>
                </div>
            </div>
        </div>
    </body>
</html>
