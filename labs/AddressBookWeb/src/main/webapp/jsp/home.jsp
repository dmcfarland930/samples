<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
                    <li class="active" role="presentation"><a href="${pageContext.request.contextPath}">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/address/namefind/">Find by Last Name</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/address/cityfind">Find by City</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/address/statefind">Find by State</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/address/zipfind">Find by Zip</a></li>
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
                                <td><a href="address/show/${address.id}">${address.firstName}</td>
                                <td>${address.lastName}</td>
                                <td><a href="address/edit/${address.id}">Edit</a></td>
                                <td><a href="address/delete/${address.id}">Delete</a></td>
                                <!--create edit and delete in controller-->

                            </tr>

                        </c:forEach>
                    </table>

                </div>
                <div class="col-md-6">

                    <h1>Enter Contact Info</h1>
                    <form method="POST" action="address/create">
                        <fieldset class="form-group">
                            <label for="firstName">First Name</label>
                            <input type="text" class="form-control" name="firstName" placeholder="ex. John">
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="lastName">Last Name</label>
                            <input type="text" class="form-control" name="lastName" placeholder="ex. Doe">
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="streetNumber">Street Number</label>
                            <input type="text" class="form-control" name="streetNumber" placeholder="ex. 101">
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="streetName">Street Name</label>
                            <input type="text" class="form-control" name="streetName" placeholder="ex. Eazy Street">
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="city">City</label>
                            <input type="text" class="form-control" name="city" placeholder="ex. Charlotte">
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="state">State</label>
                            <input type="text" class="form-control" name="state" placeholder="ex. NC">
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="zip">Zip Code</label>
                            <input type="text" class="form-control" name="zip" placeholder="ex. 26000">
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

