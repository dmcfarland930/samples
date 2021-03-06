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
        <title>Dvd Library</title>
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
                    <li class="active"  role="presentation"><a href="${pageContext.request.contextPath}/dvd/search/">Find Dvds</a></li>
                </ul>    
            </div>

            <div class="row">
                <div class="col-md-6">

                    <h1>Your Dvd Library</h1>
                    <table class="table table-striped">    
                        <thead>
                            <tr>
                                <th>Title</th>
                                <th>Director</th>
                            </tr>
                        </thead>
                        <c:forEach items="${dvds}" var="dvd">
                            <tr id="dvd-row-${dvd.id}">
                                <td><a data-dvd-id="${dvd.id}" data-toggle="modal" data-target="#showDvdModal">${dvd.title}</td>
                                <td>${dvd.director}</td>
                                <td><a data-dvd-id="${dvd.id}" data-toggle="modal" data-target="#editDvdModal">Edit</a></td>
                                <td><a data-dvd-id="${dvd.id}" class="delete-link">Delete</a></td>

                                <!--create edit and delete in controller-->

                            </tr>

                        </c:forEach>
                    </table>

                </div>
                <div class="col-md-6">

                    <h1>Search</h1>
                    <br/><br/>
                    <form class="form-inline" method="POST" action="${pageContext.request.contextPath}/dvd/search/">

                        <div class="form-group">
                            <!--<label for="searchBy">Search by:</label>-->
                            <select class="form-control" name="selection" id="searchBy">
                                <option value="title">Title</option>
                                <option value="director">Director</option>
                                <option value="rating">MPAA Rating</option>
                                <option value="studio">Studio</option>
                                <option value="after">Find after 'N' Years</option>
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
                    
                    
        <%@include file="showModal.jsp"%> 
        <%@include file="editModal.jsp"%>  
        
        
        <!-- Placed at the end of the document so the pages load faster -->
        <script>
            var contextRoot = '${pageContext.request.contextPath}/';
        </script>
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/app.js"></script>

    </body>
</html>
