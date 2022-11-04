<%-- 
    Document   : Superpower
    Created on : Jan 3, 2021, 9:27:56 AM
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
                    <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/displaySuperpowersPage">Superpowers <span class="sr-only">(current)</span></a>
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/displayLocationsPage">Locations</a>
                  </div>
                </div>
            </nav>
                  
            <br/>
            <h3>Add New Superpower</h3>
            <form class="form-horizontal" 
                  role="form" method="POST" 
                  action="createSuperpower">
                <div class="row">
                    <div class="col-6">
                        <div class="form-group">
                            <input type="text" class="form-control" name="name" placeholder="Name" required/>
                        </div>
                        <div class="form-group">
                            <textarea id="add-description" class="form-control" name="description" rows="4" placeholder="Description" required/></textarea>
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btn btn-primary" value="Create Superpower"/>
                        </div>
                    </div> 
                </div>
            </form>

            <br/>
            <div class="table-responsive">
                <h3>Superpowers</h3>
                <table id="superpowerTable" class="table table-hover table-bordered table-striped">
                    <tr>
                        <th width="35%" class="align-middle text-center">Name</th>
                        <th width="55%" class="align-middle text-center">Description</th>
                        <th width="10%" class="align-middle text-center"></th>
                    </tr>
                    <c:forEach var="currentSuperpower" items="${superpowerList}">
                        <tr>
                            <td class="align-middle text-center">
                                <c:out value="${currentSuperpower.name}"/>
                            </td>
                            <td class="align-middle text-center">
                                <c:out value="${currentSuperpower.description}"/>
                            </td>
                            <td class="align-middle text-center">
                                <a href="displayEditSuperpowerForm?superpowerId=${currentSuperpower.superpowerId}">
                                Edit
                                </a>
                                <a href="deleteSuperpower?superpowerId=${currentSuperpower.superpowerId}">
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

