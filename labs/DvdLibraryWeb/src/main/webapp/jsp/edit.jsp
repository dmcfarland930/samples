<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
            <h1>Dvd Library</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}">Home</a></li>
                    <li class="active"  role="presentation"><a href="${pageContext.request.contextPath}/dvd/search/">Find Dvds</a></li>
                </ul>    
            </div>

            <div class="row">
                <div class="col-md-6">

                    <h1>${dvdShow.title}</h1>
                    <table class="table table-striped"> 
                        <tr>
                            <td>Director: ${dvdShow.director}</td>
                        </tr>
                        <tr>
                            <td>MPAA Rating: ${dvdShow.rating}</td>
                        </tr>
                        <tr>
                            <td>Studio: ${dvdShow.studio}</td>
                        </tr>
                        <tr>
                            <td>Date: ${date}</td>
                        </tr>
                    </table>

                    <h2>Notes:</h2>
                    <table class="table table-striped"> 
                        <c:forEach items="${notes}" var="notes">
                            <tr>
                                <td>${notes.note}</td>
                            </tr>

                        </c:forEach>
                    </table>
                </div>

                <div class="col-md-6">

                    <h1>Edit Dvd Info</h1>
                    <br/>
                    <form:form method="POST" commandName="dvd" action="${pageContext.request.contextPath}/dvd/edit/${dvdShow.id}">
                        
                        
                        
                        <fieldset class="form-group">
                            <label class="col-md-3" for="title">Title</label>
                            <div class="col-md-9">
                                <form:input path="title" class="form-control" placeholder="${dvd.title}"></form:input>
                                <form:errors path="title"/>
                            </div>
                        </fieldset>
                                                        
                        <fieldset class="form-group">
                            <label class="col-md-3" for="director">Director</label>
                            <div class="col-md-9">
                                <form:input path="director" class="form-control" placeholder="${dvd.director}"></form:input>
                                <form:errors path="director"/>
                            </div>                           
                        </fieldset>
                        <fieldset class="form-group">
                            <label class="col-md-3" for="rating">MPAA Rating</label>
                            <div class="col-md-9">
                                <form:input path="rating" class="form-control" placeholder="${dvd.rating}"></form:input>
                                <form:errors path="rating"/>
                            </div>
                        </fieldset>
                        <fieldset class="form-group">
                            <label class="col-md-3" for="studio">Studio</label>
                            <div class="col-md-9">
                                <form:input path="studio" class="form-control" placeholder="${dvd.studio}"></form:input>
                                <form:errors path="studio"/>
                            </div>
                        </fieldset>
                        <fieldset class="form-group">
                            <label class="col-md-3" for="dvdDate">Date</label>
                            <div class="col-md-9">
                                <form:input path="dvdDate" class="form-control" placeholder="${date}"></form:input>
                                <form:errors path="dvdDate"/>
                            </div>
                        </fieldset>
                        <fieldset class="form-group">
                            <label class="col-md-3" for="notes">Notes</label>
                            <div class="col-md-9">
                                <textarea class="form-control" name="notes" rows="5" placeholder="Enter notes here"></textarea>
                            </div>
                        </fieldset>                                              
                        <input class="btn bg-primary" type="submit"/>
                    </form:form>

                </div>
            </div>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>


    </body>
</html>
