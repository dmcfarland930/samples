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
                    <form method="POST" action="contact/edit">
                        <fieldset class="form-group">
                            <label for="firstName">First Name</label>
                            <input type="text" class="form-control" name="firstName" placeholder="${contact.firstName}">
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="lastName">Last Name</label>
                            <input type="text" class="form-control" name="lastName" placeholder="${contact.lastName}">
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="company">Company</label>
                            <input type="text" class="form-control" name="company" placeholder="${contact.company}">
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="email">Email</label>
                            <input type="text" class="form-control" name="email" placeholder="${contact.email}">
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="phone">Phone</label>
                            <input type="text" class="form-control" name="phone" placeholder="${contact.phone}">
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
