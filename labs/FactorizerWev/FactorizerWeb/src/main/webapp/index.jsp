<%-- 
    Document   : index
    Created on : May 26, 2016, 7:14:57 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Factorizer</title>
    </head>
    <body>
        <h1>Welcome to the Factorizer app! We're gonna have some fun!</h1>
            <form action="FactorizerServlet" method="POST">
            Enter your favorite number: <input type="text" name="myNumber"  />    
            <input type="submit" value="FACTORIZE"/>
            <p>${error}</p>
        </form>
    </body>
</html>
