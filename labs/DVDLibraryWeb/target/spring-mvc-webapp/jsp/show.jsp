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
                <div class="navbar">
                    <ul class="nav nav-tabs">
                        <li role="presentation"><a href="${pageContext.request.contextPath}">Home</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/dvd/search/">Find DVDs</a></li>
                    </ul>    
                </div>
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


            </div>
            <!-- Placed at the end of the document so the pages load faster -->
            <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

