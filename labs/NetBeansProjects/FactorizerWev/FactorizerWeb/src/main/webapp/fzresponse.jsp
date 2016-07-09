<%-- 
    Document   : response
    Created on : May 26, 2016, 7:15:06 PM
    Author     : apprentice
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>


    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Factorizer</title>
    </head>
    <body>
        <h2>${message}</h2>
        <h2>${message2}</h2>
        <p>Factors:</p>
        <c:forEach items="${factors}" var="factors">
            ${factors}</br>
        </c:forEach>
        </br>

        <form action="FactorizerServlet" method="POST">
            Enter your favorite number: <input type="text" name="myNumber"  />    
            <input type="submit" value="FACTORIZE"/>
            <p>${error}</p>
    </body>
</html>
