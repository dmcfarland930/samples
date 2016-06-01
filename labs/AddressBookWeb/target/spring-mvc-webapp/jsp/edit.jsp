<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
                    <li role="presentation"><a href="${pageContext.request.contextPath}/address/namefind">Find by Last Name</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/address/cityfind">Find by City</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/address/statefind">Find by State</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/address/zipfind">Find by Zip</a></li>
                </ul>    
            </div>

            <div class="row">
                <div class="col-md-6">

                    <h1>Your Current Info</h1>
                    <table class="table table-striped"> 
                        <tr>
                            <td>First Name: ${address.firstName}</td>
                        </tr>

                        <tr>
                            <td>Last Name: ${address.lastName}</td>
                        </tr>
                        <tr>
                            <td>Street: ${address.streetNumber} ${address.streetName}</td>
                        </tr>
                        <tr>
                            <td>City: ${address.city}</td>
                        </tr>
                        <tr>
                            <td>State: ${address.state}</td>
                        </tr>
                        <tr>
                            <td>Zip Code: ${address.zip}</td>
                        </tr>
                    </table>

                </div>

                <div class="col-md-6">

                    <h1>Edit Contact Info</h1>
                    <form method="POST" action="address/create">
                        <fieldset class="form-group">
                            <label for="firstName">First Name</label>
                            <input type="text" class="form-control" name="firstName" placeholder="${address.firstName}">
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="lastName">Last Name</label>
                            <input type="text" class="form-control" name="lastName" placeholder="${address.lastName}">
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="streetNumber">Street Number</label>
                            <input type="text" class="form-control" name="streetNumber" placeholder="${address.streetNumber} ">
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="streetName">Street Name</label>
                            <input type="text" class="form-control" name="streetName" placeholder="${address.streetName}">
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="city">City</label>
                            <input type="text" class="form-control" name="city" placeholder="${address.city}">
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="state">State</label>
                            <input type="text" class="form-control" name="state" placeholder="${address.state}">
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="zip">Zip Code</label>
                            <input type="text" class="form-control" name="zip" placeholder="${address.zip}">
                        </fieldset>

                        <input type="submit"/>
                    </form>
                </div>
            </div>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
