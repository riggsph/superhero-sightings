<%-- 
    Document   : organizationDetails
    Created on : Jan 4, 2021, 11:50:09 PM
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
        <title>Organization Information</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div id="viewOrganization">
                <div>
                    <div class="text-left">
                        <h2 id="viewOrganizationHeader"><c:out value="${organization.name}"/></h2>
                    </div>
                </div>
                <hr class="border"/>
                <div class="col text-left" id="viewOrganizationFormGroup">
                    <form>
                        <div class="form-group row">
                            <label for="organizationDescription" class="col-md-2 font-weight-bold">Description:</label>
                            <div class="col-md-6">
                                <label for="organizationDescription" class="font-weight" id="organizationDescription"><c:out value="${organization.description}"/></label>
                            </div>
                        </div>
                        <div class="form-group row align-items-center">
                            <label for="organizationStreet" class="col-md-2 font-weight-bold">Street:</label>
                            <div class="col-md-6">
                                <label for="organizationStreet" class="font-weight" id="organizationStreet"><c:out value="${organization.street}"/></label>
                            </div>
                        </div>
                        <div class="form-group row align-items-center">
                            <label for="organizationCity" class="col-md-2 font-weight-bold">City:</label>
                            <div class="col-md-6">
                                <label for="organizationCity" class="font-weight" id="organizationCity"><c:out value="${organization.city}"/></label>
                            </div>
                        </div>
                        <div class="form-group row align-items-center">
                            <label for="organizationState" class="col-md-2 font-weight-bold">State:</label>
                            <div class="col-md-2">
                                <label for="organizationState" class="font-weight" id="organizationState"><c:out value="${organization.state}"/></label>
                            </div>
                        </div>
                            <div class="form-group row align-items-center">
                            <label for="organizationZip" class="col-md-2 font-weight-bold">Zip Code:</label>
                            <div class="col-md-6">
                                <label for="organizationZip" class="font-weight" id="organizationZip"><c:out value="${organization.zip}"/></label>
                            </div>
                        </div>
                        <div class="form-group row align-items-center">
                            <label for="organizationPhone" class="col-md-2 font-weight-bold">Phone:</label>
                            <div class="col-md-6">
                                <label for="organizationPhone" class="font-weight" id="organizationPhone"><c:out value="${organization.phone}"/></label>
                            </div>
                        </div>
                        <div class="form-group row align-items-center">
                            <label for="organizationEmail" class="col-md-2 font-weight-bold">Email:</label>
                            <div class="col-md-2">
                                <label for="organizationEmail" class="font-weight text-center" id="organizationEmail"><c:out value="${organization.email}"/></label>
                            </div>
                        </div>
                        
                    </form>
                    <br/>
                    <a href="${pageContext.request.contextPath}/displayOrganizationsPage">
                        <button type="button" class="btn btn-primary shadow-sm" id="viewOrganizationBackButton">Back</button>
                    </a>
                </div>
            </div>
        </div>
    </body>
</html>
