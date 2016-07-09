<%-- 
    Document   : entry
    Created on : May 26, 2016, 3:15:36 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        
        <h1>I'm having a party. Can you attend?</h1>
        <form action="RSVPServlet" method="POST">
            Answer:<input type="text" name="myAnswer"  /></br>          
            Reason:<input type="text" name=""myReason" />
            <input type="submit" value="Submit"/>
        </form>


    </body>
</html>
