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
            <h1>Search</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}">Home</a></li>
                    <li class="active"  role="presentation"><a href="${pageContext.request.contextPath}/address/search/">Search</a></li>
                </ul>    
            </div>

            <div class="row">
                <div class="col-md-6">

                    <h1>Your Address Book</h1>
                    <table class="table table-striped">    
                        <thead>
                            <tr>
                                <th>First Name</th>
                                <th>Last Name</th>
                            </tr>
                        </thead>
                        
                        <c:forEach items="${addresses}" var="address">
                            <tr>
                                <td><a href="${pageContext.request.contextPath}/address/show/${address.id}">${address.firstName}</td>
                                <td>${address.lastName}</td>
                                <td><a href="${pageContext.request.contextPath}/address/edit/${address.id}">Edit</a></td>
                                <td><a href="${pageContext.request.contextPath}/address/delete/${address.id}">Delete</a></td>
                                <!--create edit and delete in controller-->

                            </tr>

                        </c:forEach>
                    </table>

                </div>
                <div class="col-md-6">

                    <h1>Search</h1>
                    <br/><br/>
                    <form class="form-inline" method="POST" action="${pageContext.request.contextPath}/address/search/">

                        <div class="form-group">
                            <!--<label for="searchBy">Search by:</label>-->
                            <select class="form-control" name="selection" id="searchBy">
                                <option value="lastName">Last Name</option>
                                <option value="city">City</option>
                                <option value="state">State</option>
                                <option value="zip">Zip</option>
                            </select>
                        </div>

                        <fieldset class="form-group">
                            <input type="text" class="form-control" name="entry" placeholder="Enter Query">
                        </fieldset>
                        <input class="btn btn-default" type="submit"/>
                    </form>
                </div>
            </div>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
