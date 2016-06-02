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
                    <li role="presentation"><a href="${pageContext.request.contextPath}">Home</a></li>
                    <li class="active"  role="presentation"><a href="${pageContext.request.contextPath}/dvd/search/">Find DVDs</a></li>
                </ul>    
            </div>

            <div class="row">
                <div class="col-md-6">

                    <h1>${dvd.title}</h1>
                    <table class="table table-striped"> 
                        <tr>
                            <td>Director: ${dvd.director}</td>
                        </tr>
                        <tr>
                            <td>MPAA Rating: ${dvd.rating}</td>
                        </tr>
                        <tr>
                            <td>Studio: ${dvd.studio}</td>
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

                    <h1>Edit DVD Info</h1>
                    <form method="POST" action="./">
                        <input type="hidden" name="id" id="id" value="${dvd.id}"/>
                        <fieldset class="form-group">
                            <label for="title">Title</label>
                            <input type="text" class="form-control" name="title" placeholder="${dvd.title}">
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="director">Director</label>
                            <input type="text" class="form-control" name="director" placeholder="${dvd.director}">
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="rating">MPAA Rating</label>
                            <input type="text" class="form-control" name="rating" placeholder="${dvd.rating}">
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="studio">Studio</label>
                            <input type="text" class="form-control" name="studio" placeholder="${dvd.studio}">
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="date">Date</label>
                            <input type="text" class="form-control" name="date" placeholder="${date}">
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
