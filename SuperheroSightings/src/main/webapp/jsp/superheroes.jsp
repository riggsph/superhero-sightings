<%-- 
    Document   : Superhero
    Created on : Jan 3, 2021, 9:27:41 AM
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
                    <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/displaySuperheroesPage">Superheroes <span class="sr-only">(current)</span></a>
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/displayOrganizationsPage">Organizations</a>
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/displaySuperpowersPage">Superpowers</a>
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/displayLocationsPage">Locations</a>
                  </div>
                </div>
            </nav>
                  
            <br/>
            <h3>Add New Superhero</h3>
                <form class="form-horizontal" 
                    role="form" method="POST" action="createSuperhero" enctype="multipart/form-data">
                    <div class="row">
                        <div class="col-6">
                            <div class="form-group">
                                <input type="name" class="form-control" name="name" placeholder="Name" required/>
                            </div>
                            <div class="form-group">
                                <textarea type="description" class="form-control" name="description" placeholder="Description" required/></textarea>
                            </div>
                            <div class="form-group">
                                <input type="file" id="addImage" name="pictureFile" class="form-control-file" required/>
                                <input type="hidden"id="imageTitle" name="imageTitle" class="form-control" value="${superhero.name}"/>
                                <input type="hidden" name="superheroId" value="${superhero.superheroId}"/>
                            </div>
                            <br/>
                            <div class="form-group">
                                <input type="submit" class="btn btn-primary" value="Create Superhero"/>
                            </div>
                        </div>
                        <div class="col-6">
                            <div class="form-group">
                                <select name="organizations" multiple="true" class="form-control" required>
                                        <option value="null" disabled>-- Select Organization(s) --</option>
                                    <c:forEach var="currentOrganization" items="${organizationList}">
                                        <option value="${currentOrganization.organizationId}">${currentOrganization.name}</option>
                                    </c:forEach>
                                </select>
                                <small id="passwordHelpBlock" class="form-text text-muted">
                                    (Create new organization in the Organizations tab)
                                </small>
                            </div>
                            <div class="form-group">
                                <select name="superpowers" multiple="true" class="form-control" required>
                                        <option value="null" disabled>-- Select Superpower(s) --</option>
                                    <c:forEach var="currentSuperpower" items="${superpowerList}">
                                        <option value="${currentSuperpower.superpowerId}">${currentSuperpower.name}</option>
                                    </c:forEach>
                                </select>
                                <small id="passwordHelpBlock" class="form-text text-muted">
                                    (Create new superpower in the Superpowers tab)
                                </small>
                            </div>
                        </div>
                    </div>
                </form>

            <br/>
            <div>
                <h3>Superheroes</h3>
                <table id="superpowerTable" class="table table-hover table-bordered table-striped">
                    <tr>
                        <th width="40%" class="align-middle text-center">Name</th>
                        <th width="25%" class="align-middle text-center">Superpowers</th>
                        <th width="25%" class="align-middle text-center">Organizations</th>
                        <th width="10%" class="align-middle text-center"></th>
                    </tr>
                    <c:forEach var="currentSuperhero" items="${superheroList}">
                        <tr>
                            <td class="align-middle text-center">
                                <a href="displaySuperheroDetails?superheroId=${currentSuperhero.superheroId}">
                                <img src="data:image/jpg;base64,${currentSuperhero.picture.base64Image}" width="150" height="200"/>
                                <br/>
                                <c:out value="${currentSuperhero.name}"/>
                                </a>
                            </td>
                            <td class="align-middle text-center">
                                <c:forEach var="currentSuperpower" items="${currentSuperhero.superpowers}">
                                    <c:out value="${currentSuperpower.name}"/>
                                    <br>
                                </c:forEach>
                            </td>
                            <td class="align-middle text-center">
                                <c:forEach var="currentOrganization" items="${currentSuperhero.organizations}">
                                    <c:out value="${currentOrganization.name}"/>
                                    <br>
                                </c:forEach>
                            </td>
                            <td class="align-middle text-center">
                                <a href="displayEditSuperheroForm?superheroId=${currentSuperhero.superheroId}">
                                Edit
                                </a>
                                <a href="deleteSuperhero?superheroId=${currentSuperhero.superheroId}">
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


