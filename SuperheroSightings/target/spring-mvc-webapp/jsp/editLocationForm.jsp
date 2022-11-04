<%-- 
    Document   : editLocationForm
    Created on : Jan 4, 2021, 11:21:15 PM
    Author     : peterriggs
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Location</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <style>
            
            a:link, a:visited, a:hover, a:active { 
                text-decoration: none;
            }
            
            .error {
                color: #ff0000;
            }

            .errorblock {
               color: #000;
               background-color: #ffEEEE;
               border: 3px solid #ff0000;
               padding: 8px;
               margin: 16px;
               
        </style>
    </head>
    <body>
        <div class="container">
            <div id="editLocation">
                <div class="text-left">
                    <h2 id="editLocationHeader">Edit Location</h2>
                </div>
                <hr class="border"/>
                <div class="col text-left" id="editLocationFormGroup">
                    <sf:form class="form-horizontal" role="form" modelAttribute="location"
                        action="editLocation" method="POST">
                    <sf:errors path = "*" cssClass = "errorblock" element = "div" />
                        <div class="form-group row align-items-center">
                            <label for="locationName" class="col-md-2 font-weight-bold">Location Name:</label>
                            <div class="col-md-6">
                                <sf:input type="text" class="form-control" id="add-name"
                                    path="name" placeholder="Name"/>
                            </div>
                        </div>
                        <div class="form-group row align-items-center">
                            <label for="locationDescription" class="col-md-2 font-weight-bold">Description:</label>
                            <div class="col-md-6">
                                <sf:textarea type="text" class="form-control" id="add-description"
                                    rows="4" path="description" placeholder="Description"/>
                                <sf:hidden path="locationId"/>
                            </div>
                        </div>
                        <div class="form-group row align-items-center">
                            <label for="locationStreet" class="col-md-2 font-weight-bold">Street:</label>
                            <div class="col-md-6">
                                <sf:input type="text" class="form-control" id="add-street"
                                    path="street" placeholder="Street"/>
                            </div>
                        </div>
                        <div class="form-group row align-items-center">
                            <label for="locationCity" class="col-md-2 font-weight-bold">City:</label>
                            <div class="col-md-6">
                                <sf:input type="text" class="form-control" id="add-city"
                                    path="city" placeholder="City"/>
                            </div>
                        </div>
                        <div class="form-group row align-items-center">
                            <label for="locationState" class="col-md-2 font-weight-bold">State:</label>
                            <div class="col-md-6">
                                <sf:select class="form-control" id="add-state" path="state">
                                    <sf:option value="AL">Alabama</sf:option>
                                    <sf:option value="AK">Alaska</sf:option>
                                    <sf:option value="AZ">Arizona</sf:option>
                                    <sf:option value="AR">Arkansas</sf:option>
                                    <sf:option value="CA">California</sf:option>
                                    <sf:option value="CO">Colorado</sf:option>
                                    <sf:option value="CT">Connecticut</sf:option>
                                    <sf:option value="DE">Delaware</sf:option>
                                    <sf:option value="DC">District Of Columbia</sf:option>
                                    <sf:option value="FL">Florida</sf:option>
                                    <sf:option value="GA">Georgia</sf:option>
                                    <sf:option value="HI">Hawaii</sf:option>
                                    <sf:option value="ID">Idaho</sf:option>
                                    <sf:option value="IL">Illinois</sf:option>
                                    <sf:option value="IN">Indiana</sf:option>
                                    <sf:option value="IA">Iowa</sf:option>
                                    <sf:option value="KS">Kansas</sf:option>
                                    <sf:option value="KY">Kentucky</sf:option>
                                    <sf:option value="LA">Louisiana</sf:option>
                                    <sf:option value="ME">Maine</sf:option>
                                    <sf:option value="MD">Maryland</sf:option>
                                    <sf:option value="MA">Massachusetts</sf:option>
                                    <sf:option value="MI">Michigan</sf:option>
                                    <sf:option value="MN">Minnesota</sf:option>
                                    <sf:option value="MS">Mississippi</sf:option>
                                    <sf:option value="MO">Missouri</sf:option>
                                    <sf:option value="MT">Montana</sf:option>
                                    <sf:option value="NE">Nebraska</sf:option>
                                    <sf:option value="NV">Nevada</sf:option>
                                    <sf:option value="NH">New Hampshire</sf:option>
                                    <sf:option value="NJ">New Jersey</sf:option>
                                    <sf:option value="NM">New Mexico</sf:option>
                                    <sf:option value="NY">New York</sf:option>
                                    <sf:option value="NC">North Carolina</sf:option>
                                    <sf:option value="ND">North Dakota</sf:option>
                                    <sf:option value="OH">Ohio</sf:option>
                                    <sf:option value="OK">Oklahoma</sf:option>
                                    <sf:option value="OR">Oregon</sf:option>
                                    <sf:option value="PA">Pennsylvania</sf:option>
                                    <sf:option value="RI">Rhode Island</sf:option>
                                    <sf:option value="SC">South Carolina</sf:option>
                                    <sf:option value="SD">South Dakota</sf:option>
                                    <sf:option value="TN">Tennessee</sf:option>
                                    <sf:option value="TX">Texas</sf:option>
                                    <sf:option value="UT">Utah</sf:option>
                                    <sf:option value="VT">Vermont</sf:option>
                                    <sf:option value="VA">Virginia</sf:option>
                                    <sf:option value="WA">Washington</sf:option>
                                    <sf:option value="WV">West Virginia</sf:option>
                                    <sf:option value="WI">Wisconsin</sf:option>
                                    <sf:option value="WY">Wyoming</sf:option>
                                </sf:select>
                            </div>
                        </div>
                        <div class="form-group row align-items-center">
                            <label for="locationZip" class="col-md-2 font-weight-bold">Zip:</label>
                            <div class="col-md-6">
                                <sf:input type="text" class="form-control" id="add-zip"
                                    path="zip" placeholder="Zip"/>
                            </div>
                        </div>
                        <div class="form-group row align-items-center">
                            <label for="locationLatitude" class="col-md-2 font-weight-bold">Latitude:</label>
                            <div class="col-md-6">
                                <sf:input type="text" class="form-control" id="add-latitude"
                                    path="latitude" placeholder="Latitude"/>
                            </div>
                        </div>
                        <div class="form-group row align-items-center">
                            <label for="locationLongitude" class="col-md-2 font-weight-bold">Longitude:</label>
                            <div class="col-md-6">
                                <sf:input type="text" class="form-control" id="add-longitude"
                                    path="longitude" placeholder="Longitude"/>
                            </div>
                        </div>
                        <div class="col-md-8 text-right">
                            <a href="${pageContext.request.contextPath}/displayLocationsPage">
                                <button type="button" class="btn btn-primary shadow-sm" id="editCancelButton">Cancel</button>
                            </a>
                                <button type="submit" class="btn btn-primary shadow-sm" id="editLocationButton">Save Changes</button>
                        </div>
                    </sf:form>
                </div>
            </div>
        </div>
    </body>
</html>

