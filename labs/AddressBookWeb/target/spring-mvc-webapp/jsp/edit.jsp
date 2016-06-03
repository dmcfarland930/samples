<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%-- 
    Document   : edit
    Created on : May 31, 2016, 6:55:31 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Address Book</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <div class="container">
            <h1>Address Book</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/address/search/">Search</a></li> </ul>    
            </div>

            <div class="row">
                <div class="col-md-6">

                    <h1>Your Current Info</h1>
                    <table class="table table-striped"> 
                        <tr>
                            <td>First Name: ${addressShow.firstName}</td>
                        </tr>

                        <tr>
                            <td>Last Name: ${addressShow.lastName}</td>
                        </tr>
                        <tr>
                            <td>Street: ${addressShow.streetNumber} ${addressShow.streetName}</td>
                        </tr>
                        <tr>
                            <td>City: ${addressShow.city}</td>
                        </tr>
                        <tr>
                            <td>State: ${addressShow.state}</td>
                        </tr>
                        <tr>
                            <td>Zip Code: ${addressShow.zip}</td>
                        </tr>
                    </table>

                </div>

                <div class="col-md-6">

                    <h1>Edit Contact Info</h1>
                    <br/>
                    <form:form commandName="address" method="POST" action="${pageContext.request.contextPath}/address/edit/${addressShow.id}">
                        <fieldset class="form-group">
                            <label class="col-md-3" for="firstName">First Name</label>
                            <div class="col-md-9">
                                <form:input path="firstName" class="form-control" value="${addresses.firstName}"></form:input>
                                <form:errors path="firstName"/>
                            </div>
                        </fieldset>
                        <fieldset class="form-group">
                            <label  class="col-md-3" for="lastName">Last Name</label>
                            <div class="col-md-9">
                                <form:input path="lastName" class="form-control" value="${addresses.lastName}"></form:input>
                                <form:errors path="lastName"/>
                            </div>                           
                        </fieldset>
                        <fieldset class="form-group">
                            <label  class="col-md-3" for="streetNumber">Street Number</label>
                            <div class="col-md-9">
                                <form:input path="streetNumber" class="form-control" value="${addresses.streetNumber}"></form:input>
                                <form:errors path="streetNumber"/>
                            </div>
                        </fieldset>
                        <fieldset class="form-group">
                            <label  class="col-md-3" for="streetName">Street Name</label>
                            <div class="col-md-9">
                                <form:input path="streetName" class="form-control" value="${addresses.streetName}"></form:input>
                                <form:errors path="streetName"/>
                            </div>
                        </fieldset>
                        <fieldset class="form-group">
                            <label  class="col-md-3" for="city">City</label>
                            <div class="col-md-9">
                                <form:input path="city" class="form-control" value="${addresses.city}"></form:input>
                                <form:errors path="city"/>
                            </div>
                        </fieldset>
                        <fieldset class="form-group">
                            <label  class="col-md-3" for="state">State</label>
                            <div class="col-md-9">
                                <form:input path="state" class="form-control" value="${addresses.state}"></form:input>
                                <form:errors path="state"/>
                            </div>
                        </fieldset>
                        <fieldset class="form-group">                       
                            <label class="col-md-3" for="zip">Zip Code</label>
                            <div class="col-md-9">     
                                <form:input path="zip" class="form-control" value="${addresses.zip}"></form:input>
                                <form:errors path="zip"/>
                            </div>
                        </fieldset>
                        <input class="btn btn-default" type="submit"/>
                    </form:form>
                </div>
            </div>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
