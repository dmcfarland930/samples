<%-- 
    Document   : response
    Created on : May 26, 2016, 3:30:19 PM
    Author     : apprentice
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>${message}</h1>
        <c:forEach items="${numbers}" var="numbers">
            ${numbers} <br/>
        </c:forEach>
            <c:if test="${isGoing}">
                This only shows up if isGoing is true
            </c:if>
    </body>
</html>
