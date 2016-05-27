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
        <title>Tip Calculator</title>
    </head>
    <body>
        <h1>Let's calculate your tip since you can't math.</h1>
            <form action="TipCalculatorServlet" method="POST">
            Enter amount on bill: <input type="text" name="myAmount"  />    
            Enter your gratuity: <input type="text" name="myTip"  />   
            </br>
            <input type="submit" value="Calculate Tip"/>
        </form>
    </body>
</html>
