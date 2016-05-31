<%-- 
    Document   : weight
    Created on : May 28, 2016, 11:33:47 AM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Speed Convertor</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/labStyles.css" />
    </head>
    <body>

        <%@include file="header.jsp"%> 

        <div id="content">

            <div class="container">  
                <div class="row">  
                    <div class="col-md-4 col-md-offset-3">  
                        <h1>Speed Converter</h1>
                        <form name="speedForm" method="post" action="Speed">
                            <input type="text" name="mySpeed">
                            <select name="unit">
                                <option value="mph">Mph to Kph</option>
                                <option value="kph">Kph to Mph</option>
                            </select>
                            <input type="submit" value="Convert"/>
                        </form>


                        <h2>${error}</h2>
                        <h2>${speedConvert}</h2>

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
