<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>AddressBook</title>
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
                    <li role="presentation"><a href="${pageContext.request.contextPath}">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/address/search/">Search</a></li>
                </ul>    
            </div>

            <div class="row">
                <div class="col-md-6 col-offset-md-3">


                    <table class="table table-striped"> 
                        <tr>
                            <td>First Name: ${address.firstName}</td>
                        </tr>

                        <tr>
                            <td>Last Name: ${address.lastName}</td>
                        </tr>
                        <tr>
                            <td>Street: ${address.streetNumber} ${address.streetName}</td>
                        </tr>
                        <tr>
                            <td>City: ${address.city}</td>
                        </tr>
                        <tr>
                            <td>State: ${address.state}</td>
                        </tr>
                        <tr>
                            <td>Zip Code: ${address.zip}</td>
                        </tr>
                    </table>
                    <a href="${pageContext.request.contextPath}"> <button class="btn bg-primary" type="submit"/>Go Back</button></a>

                </div>


            </div>
            <!-- Placed at the end of the document so the pages load faster -->
            <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

