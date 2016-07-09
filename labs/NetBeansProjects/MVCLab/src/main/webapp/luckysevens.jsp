<%-- 
    Document   : index
    Created on : May 26, 2016, 3:50:22 PM
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
                    <div class="col-md-6 col-md-offset-2">  
                        <h1>Lucky Sevens</h1>
                        <h2>How much will you put down?</h2>
                        <p>${error}</p>
                    </div>
                </div>

                <div class="row">  
                    <div class="col-md-2 col-md-offset-4">  
                        <form action="Lucky" method="POST">
                            Bet: 
                            </br>
                            <input type="text" name="myBet"  />    
                            <br/><br/>
                            <input type="submit" value="Submit"/>
                        </form>
                    </div>
                </div>
                    
            </div>
        </div>

        <%@include file="footer.jsp"%>
    </body>
</html>
