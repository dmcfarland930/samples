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
    </head>
    <body>
        <h1>Welcome to Lucky Sevens!</h1>
        <h2>How much will you put down?</h2>
        <form action="LuckySevensServlet" method="POST">
            Bet: <input type="text" name="myBet"  />    
            <input type="submit" value="Submit"/>
        </form>
        <p>${error}</p>
    </body>
</html>
