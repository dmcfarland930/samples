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
        <title>Interest Calculator</title>
    </head>    

    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        th, td {
            padding: 5px;
        }
    </style>

    <body>
        <h1>${message}</h1>
        <table>
            <tr>
                <th>Year</th>
                <th>Beginning of Year Balance</th>
                <th>Interest added per compound</th>
                <th>Interest added this year</th>
                <th>End of Year Balance</th>
            </tr>
            <c:forEach items="${investments}" var="investments"> 

                <tr>
                    <td>${investments.year}</td>
                    <td>${investments.startInvest}</td>
                    <td>${investments.interestEarnings}</td>
                    <td>${investments.yearInterest}</td>
                    <td>${investments.investment}</td>
                </tr>
            </c:forEach>
        </table>

        <a href="interestcalc.jsp" title="Calculate Again">Calculate Again</a>
</body>
</html>
