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

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <div class="container">
            <h1>Flooring Master - Login ${test}</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/order/search/">Search Orders</a></li>
                    <li class="active" role="presentation"><a href="${pageContext.request.contextPath}/adminlogin/">Admin Login</a></li>
                </ul>    
            </div>

            <div class="row">
                <h1>${error}</h1>
                <div class="col-md-6 col-md-offset-3">

                    <h1>Admin Login</h1>
                    <form method="POST" action="${pageContext.request.contextPath}/adminlogin" class="form-inline">

                        <fieldset class="form-group">
                            <label for="area">Enter Password: </label>
                            <input type="text" class="form-control" name="password">
                        </fieldset>

                        <input class="btn bg-primary" type="submit"/>
                    </form>

                </div>
            </div>
<!--(${tax.taxRate}%)-->
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

