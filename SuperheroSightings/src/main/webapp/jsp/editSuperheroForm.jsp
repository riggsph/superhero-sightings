<%-- 
    Document   : editSuperheroForm
    Created on : Jan 4, 2021, 11:22:13 PM
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
            <div id="editSuperhero">
                <div class="text-left">
                    <h2 id="editSuperheroHeader">Edit Superhero</h2>
                </div>
                <hr class="border"/>
                <div class="col text-left">
                    <div class="row">
                        <div class="col-3">
                            <img src="data:image/jpg;base64,${superhero.picture.base64Image}" width="150" height="200"/>
                            <br>
                        </div>
                    </div>
                <br/>
                    <div id="editSuperheroFormGroup">
                        <sf:form class="form-horizontal" role="form" modelAttribute="superhero"
                            action="editSuperhero" method="POST">
                        <sf:errors path = "*" cssClass = "errorblock" element = "div" />
                        <sf:hidden path="superheroId"/>
                            <div class="form-group row align-items-center">
                                <label for="superheroName" class="col-md-3 font-weight-bold">Name:</label>
                                <div class="col-md-6">
                                    <sf:input type="name" class="form-control" id="add-name"
                                        path="name" placeholder="Name"/>
                                </div>
                            </div>
                            <div class="form-group row align-items-center">
                                <label for="superheroDescription" class="col-md-3 font-weight-bold">Description:</label>
                                <div class="col-md-6">
                                    <sf:textarea type="text" class="form-control" id="add-description"
                                        rows="4" path="description" placeholder="Description"/>
                                </div>
                            </div>
                            <div class="form-group row align-items-center" id="organizationDiv">
                                <label for="superheroOrganizations" class="col-md-3 font-weight-bold">Organizations:</label>
                                <div class="col-md-6">
                                    <sf:select class="form-control" path="organizations" id="organizationSelect" size="${fn:length(organizationList)}">
                                           <sf:options items="${organizationList}" itemLabel="name" itemValue="organizationId"/>
                                    </sf:select>
                                </div>
                            </div>
                            <div class="form-group row align-items-center">
                                <label for="superheroSuperpowers" class="col-md-3 font-weight-bold">Superpowers:</label>
                                <div class="col-md-6">
                                    <sf:select path="superpowers" class="form-control" size="${fn:length(superpowerList)}">
                                        <sf:options items="${superpowerList}" itemLabel="name" itemValue="superpowerId"/>
                                    </sf:select>
                                </div>
                            </div>
                            <div class="offset-3 col-6 text-left">
                                <a href="${pageContext.request.contextPath}/displaySuperheroesPage">
                                    <button type="button" class="btn btn-primary shadow-sm" id="editCancelButton">Cancel</button>
                                </a>
                                    <button type="submit" class="btn btn-primary shadow-sm" id="editSuperheroButton">Save Changes</button>
                            </div>
                        </sf:form>
                    </div>
                </div>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script type="text/javascript">
            function clearSelectedOrg(){
                var elements = document.getElementById("#organizationSelect").options;

                for(var i = 0; i < elements.length; i++){
                  if(elements[i].selected)
                    elements[i].selected = false;
                }
            }
            function clearSelectedPower(){
                var elements = document.getElementById("#superpowerSelect").options;

                for(var i = 0; i < elements.length; i++){
                  if(elements[i].selected)
                    elements[i].selected = false;
                }
            }
        </script>
    </body>
</html>
