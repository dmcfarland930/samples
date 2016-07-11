<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Flooring Master</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

        <!-- External Styling -->
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
        <link href='https://fonts.googleapis.com/css?family=Roboto+Slab' rel='stylesheet' type='text/css'>
    </head>
    <body>
        <div class="navbar">
            <ul class="nav nav-tabs">
                <li role="presentation"><a href="${pageContext.request.contextPath}">Flooring Master</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/adminlogin/">Admin Login</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/order/vieworders/">View Orders</a></li>        
            </ul>    
        </div>
        <div class="container">

            <div class="row">
                <div class="col-md-6 col-md-offset-3 formDiv">
                    <h1>Admin Login</h1>
                    <div id="login" >
                        <form method="POST" action="${pageContext.request.contextPath}/adminlogin">
                            <fieldset class="form-group ${hasError}">
                                <input type="password" class="form-control" name="password" placeholder="Password">
                            </fieldset>
                            <p>${error}</p>
                            <div id="buttonLogin">
                                <input class="btn bg-primary button-size" type="submit" value="Log In"/>
                            </div>
                        </form>
                    </div>
                    <div id="errorMessage">
                    </div>
                </div>
            </div>
            <!-- Placed at the end of the document so the pages load faster -->
            <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>

