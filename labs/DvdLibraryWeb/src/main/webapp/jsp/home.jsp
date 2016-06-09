<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Dvd Library</title>

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">

        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">


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
                    <table class="table table-striped" id="dvd-table">    
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

                    <p>Your Oldest Movie: ${oldest}</p>
                    <p>Your Newest Movie: ${newest}</p>
                    <p>Average Age of Your Movies: ${years} years old</p>
                    <p>Your movies have an average of ${notenum} notes</p>

                </div>
                <div class="col-md-6">

                    <h1>Enter Dvd Info</h1>
                    <br/>
                    <form method="POST" class="form-horizontal">

                        <fieldset class="form-group">
                            <label class="col-md-3" for="title">Title</label>
                            <div class="col-md-9">
                                <input type="text" id="title-input" class="form-control"/>
                            </div>
                        </fieldset>                            

                        <fieldset class="form-group">
                            <label class="col-md-3" for="director">Director</label>
                            <div class="col-md-9">
                                <input type="text" id="director-input" class="form-control"/>
                            </div>                           
                        </fieldset>

                        <fieldset class="form-group">
                            <label class="col-md-3" for="rating">MPAA Rating</label>
                            <div class="col-md-9">
                                <input type="text" id="rating-input" class="form-control"/>
                            </div>
                        </fieldset>

                        <fieldset class="form-group">
                            <label class="col-md-3" for="studio">Studio</label>
                            <div class="col-md-9">
                                <input type="text" id="studio-input" class="form-control"/>
                            </div>
                        </fieldset>

                        <fieldset class="form-group">
                            <label class="col-md-3" for="dvdDate">Date</label>
                            <div class="col-md-9">
                                <input type="text" id="date-input" class="form-control"/>
                            </div>
                        </fieldset>

                        <fieldset class="form-group">
                            <label class="col-md-3" for="notes">Notes</label>
                            <div class="col-md-9">
                                <textarea class="form-control" name="notes" rows="5" placeholder="Enter notes here"></textarea>
                            </div>
                        </fieldset>
                        <input id="create-submit" class="btn btn-default pull-right" type="submit"/>
                    </form>

                </div>
            </div>

        </div>

        <%@include file="showModal.jsp"%> 
        <%@include file="editModal.jsp"%> 


        <!-- Placed at the end of the document so the pages load faster -->
        <script>
            var contextRoot = '${pageContext.request.contextPath}';
        </script>

        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/app.js" ></script>

    </body>
</html>

