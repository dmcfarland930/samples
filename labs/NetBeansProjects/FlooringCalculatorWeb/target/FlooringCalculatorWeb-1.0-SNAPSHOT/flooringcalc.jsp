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
    </head>
    <body>
        <h1>Welcome to the Flooring Calculator</h1>
        <h2>We charge $86 per hour. $21.50 per quarter hour.</h2>
        <h3>Flooring Information</h3>
        <form action="FlooringCalculatorServlet" method="POST">
            Enter the width of your flooring unit: <input type="text" name="myWidthTile"  />  
            <br/>
            <form action="FlooringCalculatorServlet" method="POST">
                Enter the length of your flooring unit: <input type="text" name="myLengthTile"  />    
                <br/>
                <form action="FlooringCalculatorServlet" method="POST">
                    Enter the cost of your flooring unit: <input type="text" name="myCostTile"  />    
                    <br/> 
                    <h3>Room Information</h3>
                    <form action="FlooringCalculatorServlet" method="POST">
                        Enter the width of your room: <input type="text" name="myWidthRoom"  />     
                        <br/>
                        <form action="FlooringCalculatorServlet" method="POST">
                            Enter the length of your room: <input type="text" name="myLengthRoom"  />    
                            <br/>
                            <input type="submit" value="Submit"/>
                        </form>
                        <p>${error}</p>
                        </body>
                        </html>
