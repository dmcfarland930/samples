<%-- 
    Document   : temp
    Created on : May 27, 2016, 3:48:38 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Temperature Converter</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/labStyles.css" />
    </head>
    <body>

        <%@include file="header.jsp"%> 

        <div id="content">

            <div class="container">  
                <div class="row">  
                    <div class="col-md-4 col-md-offset-3">  
                        <h1>Temperature Converter</h1>
                        <form name="tempform" method="post" action="Temp">
                            <input type="text" name="myTemp">
                            <select name="unit">
                                <option value="fahrenheit">&deg;F to &deg;C</option>
                                <option value="celcius">&deg;C to &deg;F</option>
                            </select>
                            <input type="submit" value="Convert"/>
                        </form>


                        <h2>${error}</h2>
                        <h2>${tempConvert}</h2>

                        <p>Convert Something Else</p>
                        <form name="form" method="POST" action="Converter">
                            <input type="submit" name ="button" value="Temperature" onclick="{
                                        document.form.button.value = this.value;
                                        document.form.submit();
                                    }"/>
                            <input type="submit" name ="button" value="Weight"  onclick="{
                                        document.form.button.value = this.value;
                                        document.form.submit();
                                    }"/>
                            <input type="submit" name ="button" value="Speed"  onclick="{
                                        document.form.button.value = this.value;
                                        document.form.submit();
                                    }"/>
                            <input type="submit" name ="button" value="Dollars" onclick="{
                                        document.form.button.value = this.value;
                                        document.form.submit();
                                    }"/>
                        </form>
        <br/>
                    </div>
                </div>
            </div>

        </div>

        <%@include file="footer.jsp"%>
    </body>
</html>
