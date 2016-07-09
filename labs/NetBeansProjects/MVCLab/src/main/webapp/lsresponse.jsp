<%-- 
    Document   : response
    Created on : May 26, 2016, 3:48:25 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lucky Sevens</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/labStyles.css" />
    </head>
    <body>

        <%@include file="header.jsp"%> 

        <div id="content">

            <div class="container">  

                <div class="row">  
                    <div class="col-md-6 col-md-offset-3">  
                        <h1>${message}</h1>
                        <h2>${message2}</h2>
                        <h2>${message3}</h2>
                    </div>
                </div>


                <div class="row">  
                    <div class="col-md-2 col-md-offset-5"> 
                        <form action="luckysevens.jsp">
                            <input type="submit" value="Play Again"/>
                        </form>
                    </div>
                </div>

            </div>

        </div>


        <%@include file="footer.jsp"%>
    </body>
</html>
