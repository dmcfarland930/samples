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
        <title>Contact List</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <div class="container">
            <h1>Contact List</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}">Home</a></li>
                </ul>    
            </div>

            <div class="row">
                <div class="col-md-6">

                    <h1>Your Current Info</h1>
                    <table class="table table-striped"> 
                        <tr>
                            <td>First Name: ${contact.firstName}</td>
                        </tr>

                        <tr>
                            <td>Last Name: ${contact.lastName}</td>
                        </tr>
                        <tr>
                            <td>Company: ${contact.company}</td>
                        </tr>
                        <tr>
                            <td>Contact: ${contact.email}</td>
                        </tr>
                        <tr>
                            <td>Phone: ${contact.phone}</td>
                        </tr>
                    </table>

                </div>

                <div class="col-md-6">

                    <h1>Edit Contact Info</h1>
                    <form:form commandName="cotact" method="POST" action="./">
                        <fieldset class="form-group">
                            <label class="col-md-3" for="firstName">First Name</label>
                            <div class="col-md-9">
                                <form:input path="firstName" class="form-control" placeholder="${address.firstName}"></form:input>
                                <form:errors path="firstName"/>
                            </div>
                        </fieldset>
                        <fieldset class="form-group">
                            <label  class="col-md-3" for="lastName">Last Name</label>
                            <div class="col-md-9">
                                <form:input path="lastName" class="form-control" placeholder="${address.lastName}"></form:input>
                                <form:errors path="lastName"/>
                            </div>                           
                        </fieldset>
                        <fieldset class="form-group">
                            <label  class="col-md-3" for="streetNumber">Street Number</label>
                            <div class="col-md-9">
                                <form:input path="streetNumber" class="form-control" placeholder="${address.streetNumber}"></form:input>
                                <form:errors path="streetNumber"/>
                            </div>
                        </fieldset>
                        <fieldset class="form-group">
                            <label  class="col-md-3" for="streetName">Street Name</label>
                            <div class="col-md-9">
                                <form:input path="streetName" class="form-control" placeholder="${address.streetName}"></form:input>
                                <form:errors path="streetName"/>
                            </div>
                        </fieldset>
                        <fieldset class="form-group">
                            <label  class="col-md-3" for="city">City</label>
                            <div class="col-md-9">
                                <form:input path="city" class="form-control" placeholder="${address.city}"></form:input>
                                <form:errors path="city"/>
                            </div>
                        </fieldset>
                        <fieldset class="form-group">
                            <label  class="col-md-3" for="state">State</label>
                            <div class="col-md-9">
                                <form:input path="state" class="form-control" placeholder="${address.state}"></form:input>
                                <form:errors path="state"/>
                            </div>
                        </fieldset>
                        <fieldset class="form-group">
                            <div  class="col-md-3" class="col-md-9">                            
                                <label for="zip">Zip Code</label>
                                <form:input path="zip" class="form-control" placeholder="${address.zip}"></form:input>
                                <form:errors path="zip"/>
                            </div>
                        </fieldset>

                        <input type="submit"/>
                    </form:form>
                </div>
            </div>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
