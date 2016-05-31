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
        <title>Flooring Calculator</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/labStyles.css" />
    </head>

    <body>

        <%@include file="header.jsp"%> 

        <div id="content">

            <div class="container">  
                <div class="row">  
                    <div class="col-md-4 col-md-offset-3">  
                        <h1>Flooring Calculator</h1>
                        <h2>Pricing</h2>
                        <p>We charge $86 per hour. $21.50 per quarter hour.</p>
                        
                        <h3>Flooring Information</h3>

                        <form action="Flooring" method="POST">
                            Enter the width of your flooring unit:
                            <br/> 
                            <input type="text" name="myWidthTile"  />  
                            <br/>
                            Enter the length of your flooring unit:
                            <br/> 
                            <input type="text" name="myLengthTile"  />    
                            <br/>
                            Enter the cost of your flooring unit: 
                            <br/> 
                            <input type="text" name="myCostTile"  />    
                            <br/> 
                            <h3>Room Information</h3>
                            Enter the width of your room:
                            <br/> 
                            <input type="text" name="myWidthRoom"  />     
                            <br/>
                            Enter the length of your room:
                            <br/> 
                            <input type="text" name="myLengthRoom"  />    
                            <br/><br/> 
                            <input type="submit" value="Submit"/>
                        </form>
                        <p>${error}</p>

                    </div>
                </div>
            </div>

        </div>
        <%@include file="footer.jsp"%>
    </body>
</html>
