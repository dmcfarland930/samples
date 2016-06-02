<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>DVD Library</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <div class="container">
            <h1>DVD Library</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li class="active" role="presentation"><a href="${pageContext.request.contextPath}">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/dvd/search/">Find DVDs</a></li>
                </ul>    
            </div>

            <div class="row">
                <div class="col-md-6">

                    <h1>Your DVD Library</h1>
                    <table class="table table-striped">    
                        <thead>
                            <tr>
                                <th>Title</th>
                                <th>Director</th>
                            </tr>
                        </thead>
                        <c:forEach items="${dvds}" var="dvd">
                            <tr>
                                <td><a href="dvd/show/${dvd.id}">${dvd.title}</td>
                                <td>${dvd.director}</td>
                                <td><a href="dvd/edit/${dvd.id}">Edit</a></td>
                                <td><a href="dvd/delete/${dvd.id}">Delete</a></td>

                                <!--create edit and delete in controller-->

                            </tr>

                        </c:forEach>
                    </table>
                    
                    <p>Your oldest movie is ${oldest}</p>
                    <p>Your newest movie is ${newest}</p>
                    <p>The average age of your movies is ${years} old</p>
                    <p>Your movies have an average of ${notenum} notes</p>

                </div>
                <div class="col-md-6">

                    <h1>Enter DVD Info</h1>
                    <form method="POST" action="dvd/create">
                        <fieldset class="form-group">
                            <label for="title">Title</label>
                            <input type="text" class="form-control" name="title" placeholder="ex. Pulp Fiction">
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="director">Director</label>
                            <input type="text" class="form-control" name="director" placeholder="ex. Quentin Tarantino">
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="rating">MPAA Rating</label>
                            <input type="text" class="form-control" name="rating" placeholder="ex. R">
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="studio">Studio</label>
                            <input type="text" class="form-control" name="studio" placeholder="ex. MGM">
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="date">Date</label>
                            <input type="text" class="form-control" name="date" placeholder="ex. 02/14/1993">
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="notes">Notes</label>
                            <textarea class="form-control" name="notes" rows="5" placeholder="Enter notes here"></textarea>
                        </fieldset>
                        
                        <input class="btn bg-primary" type="submit"/>
                    </form>

                </div>
            </div>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

