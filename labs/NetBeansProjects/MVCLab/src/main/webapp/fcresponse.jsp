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
                        <h1>${message}</h1>
                        <h2>${areaMessage}</h2>
                        <h2>${costPerTile}</h2>
                        <h2>${timeMessage}</h2>
                        <h2>Total Cost of Tiles: $${costOfTiles}</h2>
                        <h2>Total Cost of Labor: $${costOfLabor}</h2>
                        <h2>GrandTotal: $${grandTotal}</h2>

                        <form action="Flooring">
                            <input type="submit" value="Calculate Again">
                        </form>
                        </div>
                </div>
            </div>

        </div>

        <%@include file="footer.jsp"%>
    </body>
</html>
