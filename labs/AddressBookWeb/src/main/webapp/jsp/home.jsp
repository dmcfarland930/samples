<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
                    <li role="presentation"><a href="${pageContext.request.contextPath}/address/search/">Search</a></li>
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

                    <h1>Enter Contact Info</h1>
                    </br>
                    <form:form commandName="address" method="POST" action="${pageContext.request.contextPath}/address/create">
                        
                        
                        <fieldset class="form-group">
                            <label class="col-md-3" for="firstName">First Name</label>
                            <div class="col-md-9">
                                <form:input path="firstName" class="form-control" placeholder="ex. John"></form:input>
                                <form:errors path="firstName"/>
                            </div>
                        </fieldset>
                            
                            
                            
                        <fieldset class="form-group">
                            <label class="col-md-3" for="lastName">Last Name</label>
                            <div class="col-md-9">
                                <form:input path="lastName" class="form-control" placeholder="ex. Doe"></form:input>
                                <form:errors path="lastName"/>
                            </div>                           
                        </fieldset>
                        <fieldset class="form-group">
                            <label class="col-md-3" for="streetNumber">Street Number</label>
                            <div class="col-md-9">
                                <form:input path="streetNumber" class="form-control" placeholder="ex. 101"></form:input>
                                <form:errors path="streetNumber"/>
                            </div>
                        </fieldset>
                        <fieldset class="form-group">
                            <label class="col-md-3" for="streetName">Street Name</label>
                            <div class="col-md-9">
                                <form:input path="streetName" class="form-control" placeholder="ex. Eazy "></form:input>
                                <form:errors path="streetName"/>
                            </div>
                        </fieldset>
                        <fieldset class="form-group">
                            <label class="col-md-3" for="city">City</label>
                            <div class="col-md-9">
                                <form:input path="city" class="form-control" placeholder="Compton"></form:input>
                                <form:errors path="city"/>
                            </div>
                        </fieldset>
                        <fieldset class="form-group">
                            <label class="col-md-3" for="state">State</label>
                            <div class="col-md-9">
                                <form:input path="state" class="form-control" placeholder="CA"></form:input>
                                <form:errors path="state"/>
                            </div>
                        </fieldset>
                        <fieldset class="form-group">                        
                            <label class="col-md-3" for="zip">Zip Code</label>
                            <div class="col-md-9">    
                                <form:input path="zip" class="form-control" placeholder="ex. 15003"></form:input>
                                <form:errors path="zip"/>
                            </div>
                        </fieldset>
                        <input  class="btn btn-default" type="submit"/>
                    </form:form>

                </div>
            </div>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

