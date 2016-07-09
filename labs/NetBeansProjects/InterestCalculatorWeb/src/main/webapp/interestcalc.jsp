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
        <title>Interest Calculator</title>
    </head>
    <body>
        <h1>Welcome to the Interest Calculator</h1>
        <h2></h2>
        <form action="InterestCalculatorServlet" method="POST">
            Enter your investment: <input type="text" name="myInvestment"  /> 
            </br>
            Enter your annual interest rate: <input type="text" name="myRate"  />  
            </br>
            How long will you keep your investment (years): <input type="text" name="myLength"  /> 
            </br>  

            <input type="radio" name="compound" value="daily"> Daily
            <input type="radio" name="compound" value="monthly"> Monthly
            <input type="radio" name="compound" value="yearly"> Yearly
            </br>

            <input type="submit" value="Calculate"/>
        </form>
        <p>${error}</p>
    </body>
</html>
