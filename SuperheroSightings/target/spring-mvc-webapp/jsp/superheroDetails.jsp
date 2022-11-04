<%-- 
    Document   : superheroDetails
    Created on : Jan 5, 2021, 7:11:25 PM
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
        <title>Superhero Information</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div id="viewSuperhero">
                <div>
                    <div class="text-left">
                        <h2 id="viewSuperheroHeader">Superhero Details</h2>
                    </div>
                </div>
                <hr class="border"/>
                <div class="col text-left" id="viewSuperheroFormGroup">
                    <form>
                        <div>
                            <img src="data:image/jpg;base64,${superhero.picture.base64Image}" width="150" height="200"/>
                            <br>
                        </div>
                            <br/>
                        <div class="form-group row">
                            <label for="superheroName" class="col-md-2 font-weight-bold">Name:</label>
                            <div class="col-md-6">
                                <label for="superheroName"id="superheroName"><c:out value="${superhero.name}"/></label>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="superheroDescription" class="col-md-2 font-weight-bold">Description:</label>
                            <div class="col-md-6">
                                <label for="superheroDescription" id="superheroDescription"><c:out value="${superhero.description}"/></label>
                            </div>
                        </div>
                        <div class="form-group row align-items-center">
                            <label for="superheroOrganizations" class="col-md-2 font-weight-bold">Organizations:</label>
                            <div class="col-md-6">
                                <c:forEach var="currentOrganization" items="${superhero.organizations}">
                                <label for="superheroOrganizations" id="superheroOrganizations"><c:out value="${currentOrganization.name}"/></label>
                                <br>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="form-group row align-items-center">
                            <label for="superheroSuperpower" class="col-md-2 font-weight-bold">Superpowers:</label>
                            <div class="col-md-6">
                                <c:forEach var="currentSuperpower" items="${superhero.superpowers}">
                                <label for="superheroSuperpower" id="superheroSuperpowers"><c:out value="${currentSuperpower.name}"/></label>
                                <br>
                                </c:forEach>
                            </div>
                        </div>
                    </form>
                    <br/>
                    <a href="${pageContext.request.contextPath}/displaySuperheroesPage">
                        <button type="button" class="btn btn-primary shadow-sm" id="viewSuperheroBackButton">To Superheroes Page</button>
                    </a>
                </div>
            </div>
        </div>
    </body>
</html>

