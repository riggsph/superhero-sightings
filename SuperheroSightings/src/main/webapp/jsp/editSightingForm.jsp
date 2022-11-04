<%-- 
    Document   : editSightingForm
    Created on : Jan 4, 2021, 11:21:57 PM
    Author     : peterriggs
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Superhero</title>
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
            <div id="editSighting">
                <div class="text-left">
                    <h2 id="editSightingHeader">Edit Sighting</h2>
                </div>
                <hr class="border"/>
                <div class="col text-left" id="editSightingFormGroup">
                    <sf:form class="form-horizontal" role="form" modelAttribute="sighting"
                        action="editSighting" method="POST">
                    <sf:errors path = "*" cssClass = "errorblock" element = "div" />
                    <sf:hidden path="sightingId"/>
                    <input type="hidden" class="form-control" id="add-date"
                                    path="date" value="${sighting.date}"/>
                        <div class="form-group row align-items-center">
                            <label for="sightingLocation" class="col-md-2 font-weight-bold">Location:</label>
                            <div class="col-md-6">
                                <sf:select class="form-control" id="add-location" path="location"
                                           items="${locationList}" itemLabel="name" itemValue="locationId"/>
                            </div>
                        </div>
                        <div class="form-group row align-items-center">
                            <label for="sightingSuperheroes" class="col-md-2 font-weight-bold">Superheroes:</label>
                            <div class="col-md-6">
                                <sf:select id="superheroSelect" class="form-control" path="superheroes" size="${fn:length(superheroList)}">
                                      <sf:options items="${superheroList}" itemLabel="name" itemValue="superheroId"/>
                                </sf:select>
                            </div>
                        </div>
                        <div class="col-md-8 text-right">
                            <a href="${pageContext.request.contextPath}/displaySightingsPage">
                                <button type="button" class="btn btn-primary shadow-sm" id="editCancelButton">Cancel</button>
                            </a>
                                <button type="submit" class="btn btn-primary shadow-sm" id="editSightingButton">Save Changes</button>
                        </div>
                    </sf:form>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            function clearSelectedHero(){
                var elements = document.getElementById("#superheroSelect").options;

                for(var i = 0; i < elements.length; i++){
                  if(elements[i].selected)
                    elements[i].selected = false;
                }
            }
        </script>
    </body>
</html>
