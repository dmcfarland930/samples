<%-- 
    Document   : home
    Created on : May 30, 2016, 5:04:46 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/labStyles.css" />
    </head>
    <body>

        <%@include file="header.jsp"%> 

        <div id="content">

            <div class="container">  
                <div class="row">  
                    <div class="col-md-4 col-md-offset-3">  
                        <h1>Welcome to the Calculator Portal</h1>
                        <img src="img/hippo.jpg" alt="Hippo">
                    </div>
                </div>
            </div>
        </div>
        <%@include file="footer.jsp"%>
    </body>
</html>
