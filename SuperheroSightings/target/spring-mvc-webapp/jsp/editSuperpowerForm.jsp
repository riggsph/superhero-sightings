<%-- 
    Document   : editSuperpowerForm
    Created on : Jan 4, 2021, 11:22:28 PM
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
        <title>Edit Superpower</title>
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
            <div id="editSuperpower">
                <div class="text-left">
                    <h2 id="editSuperpowerHeader">Edit Superpower</h2>
                </div>
                <hr class="border"/>
                <div class="col text-left" id="editSuperpowerFormGroup">
                    <sf:form class="form-horizontal" role="form" modelAttribute="superpower"
                        action="editSuperpower" method="POST">
                    <sf:errors path = "*" cssClass = "errorblock" element = "div" />
                        <div class="form-group row align-items-center">
                            <label for="superpowerName" class="col-md-2 font-weight-bold">Name:</label>
                            <div class="col-md-6">
                                <sf:input type="text" class="form-control" id="add-name"
                                    path="name" placeholder="Name"/>
                            </div>
                        </div>
                        <div class="form-group row align-items-center">
                            <label for="superpowerDescription" class="col-md-2 font-weight-bold">Description:</label>
                            <div class="col-md-6">
                                <sf:textarea type="text" class="form-control" id="add-description"
                                    rows="4" path="description" placeholder="Description"/>
                                <sf:hidden path="superpowerId"/>
                            </div>
                        </div>
                        <div class="col-md-8 text-right">
                            <a href="${pageContext.request.contextPath}/displaySuperpowersPage">
                                <button type="button" class="btn btn-primary shadow-sm" id="editCancelButton">Cancel</button>
                            </a>
                                <button type="submit" class="btn btn-primary shadow-sm" id="editSuperpowerButton">Save Changes</button>
                        </div>
                    </sf:form>
                </div>
            </div>
        </div>
    </body>
</html>
