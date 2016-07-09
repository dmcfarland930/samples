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
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/labStyles.css" />
    </head>
    <body>

        <%@include file="header.jsp"%> 

        <div id="content">

            <div class="container">  
                <div class="row">  
                    <div class="col-md-4 col-md-offset-3">  
                        <h1>Interest Calculator</h1>
                        <h2></h2>
                        <form action="Interest" method="POST">
                            Enter your investment:<br/>
                            <input type="text" name="myInvestment"  /> 
                            </br>
                            Enter your annual interest rate: 
                            <br/>
                            <input type="text" name="myRate"  />  
                            </br>
                            How long will you keep your investment (years):
                            <br/>
                            <input type="text" name="myLength"  /> 
                            </br>  

                            <input type="radio" name="compound" value="daily"> Daily
                            <input type="radio" name="compound" value="monthly"> Monthly
                            <input type="radio" name="compound" value="yearly"> Yearly
                            </br><br/> 

                            <input type="submit" value="Calculate"/>
                        </form>
                        <p>${error}</p>
                    </div>
                </div>
            </div>

        </div>

        <%@include file="footer.jsp"%>
    </body>
</html>
