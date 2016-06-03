<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
                    <li class="active" role="presentation"><a href="${pageContext.request.contextPath}">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/dvd/search/">Find Dvds</a></li>
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
                            <tr>
                                <td><a href="${pageContext.request.contextPath}/dvd/show/${dvd.id}">${dvd.title}</td>
                                <td>${dvd.director}</td>
                                <td><a href="${pageContext.request.contextPath}/dvd/edit/${dvd.id}">Edit</a></td>
                                <td><a href="${pageContext.request.contextPath}/dvd/delete/${dvd.id}">Delete</a></td>

                                <!--create edit and delete in controller-->

                            </tr>

                        </c:forEach>
                    </table>

                    <p>Your oldest movie is ${oldest}</p>
                    <p>Your newest movie is ${newest}</p>
                    <p>The average age of your movies is ${years} years old</p>
                    <p>Your movies have an average of ${notenum} notes</p>

                </div>
                <div class="col-md-6">

                    <h1>Enter Dvd Info</h1>
                    <br/>
                    <form:form method="POST" commandName="dvd" action="${pageContext.request.contextPath}/dvd/create">
                                               
                        
                        <fieldset class="form-group">
                            <label class="col-md-3" for="title">Title</label>
                            <div class="col-md-9">
                                <form:input path="title" class="form-control" placeholder="ex. Pulp Fiction"></form:input>
                                <form:errors path="title"/>
                            </div>
                        </fieldset>                            
                            
                        <fieldset class="form-group">
                            <label class="col-md-3" for="director">Director</label>
                            <div class="col-md-9">
                                <form:input path="director" class="form-control" placeholder="ex. Quentin Tarantino"></form:input>
                                <form:errors path="director"/>
                            </div>                           
                        </fieldset>
                        <fieldset class="form-group">
                            <label class="col-md-3" for="rating">MPAA Rating</label>
                            <div class="col-md-9">
                                <form:input path="rating" class="form-control" placeholder="ex. R"></form:input>
                                <form:errors path="rating"/>
                            </div>
                        </fieldset>
                        <fieldset class="form-group">
                            <label class="col-md-3" for="studio">Studio</label>
                            <div class="col-md-9">
                                <form:input path="studio" class="form-control" placeholder="ex. MGM Studios "></form:input>
                                <form:errors path="studio"/>
                            </div>
                        </fieldset>
                        <fieldset class="form-group">
                            <label class="col-md-3" for="dvdDate">Date</label>
                            <div class="col-md-9">
                                <form:input path="dvdDate" class="form-control" placeholder="ex. 02/18/1993"></form:input>
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

