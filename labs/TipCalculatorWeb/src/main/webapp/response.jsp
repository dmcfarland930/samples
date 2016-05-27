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
        <title>Tip Calculator</title>
    </head>
    <body>
        <h2>Amount on bill: $${myAmount}</h2>
        <h2>Your gratuity: ${myTip}%</h2>
        <h2>Tip amount: $${tip}</h2>
        <h2>Total: $${total}</h2>
        </br>
        <a href="index.jsp" title="Calculate Again">Calculate Again</a>
</body>
</html>
