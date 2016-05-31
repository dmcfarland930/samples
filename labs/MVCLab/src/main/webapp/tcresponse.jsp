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
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/labStyles.css" />
    </head>
    <body>

        <%@include file="header.jsp"%> 

        <div id="content">

            <div class="container">  
                <div class="row">  
                    <div class="col-md-4 col-md-offset-3">  
                        <h2>Amount on bill: $${myAmount}</h2>
                        <h2>Your gratuity: ${myTip}%</h2>
                        <h2>Tip amount: $${tip}</h2>
                        <h2>Total: $${total}</h2>
                        </br>
                        <a href="tipcalc.jsp" title="Calculate Again">Calculate Again</a>

                    </div>
                </div>
            </div>

        </div>
        <%@include file="footer.jsp"%>
    </body>
</html>
