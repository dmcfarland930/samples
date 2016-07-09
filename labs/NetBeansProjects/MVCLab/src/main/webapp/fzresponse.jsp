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
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/labStyles.css" />
    </head>
    <body>

        <%@include file="header.jsp"%> 

        <div id="content">

            <div class="container">  
                <div class="row">  
                    <div class="col-md-4 col-md-offset-3">  
                        <h1>Factorizer</h1>
                        <h2>${message}</h2>
                        <h2>${message2}</h2>
                        <p>Factors:</p>
                        <c:forEach items="${factors}" var="factors">
                            ${factors}
                            </br>
                        </c:forEach>
                        </br>

                        <form action="Factor" method="POST">
                            Enter your favorite number: 
                            </br>
                            <input type="text" name="myNumber"  />    
                            <br/>
                            <br/>
                            <input type="submit" value="FACTORIZE"/>
                            <p>${error}</p>
                        </form>
                    </div>
                </div>
            </div>

        </div>

        <%@include file="footer.jsp"%>
        
    </body>
</html>
