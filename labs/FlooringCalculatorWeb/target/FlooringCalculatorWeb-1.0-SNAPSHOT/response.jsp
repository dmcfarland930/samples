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
    </head>
    <body>
        
        <h1>${message}</h1>
        <h2>${areaMessage}</h2>
        <h2>${costPerTile}</h2>
        <h2>${timeMessage}</h2>
        <h2>Total Cost of Tiles: $${costOfTiles}</h2>
        <h2>Total Cost of Labor: $${costOfLabor}</h2>
        <h2>GrandTotal: $${grandTotal}</h2>
        
        <a href="index.jsp" title="Calculate Again">Calculate Again</a>
    </body>
</html>
