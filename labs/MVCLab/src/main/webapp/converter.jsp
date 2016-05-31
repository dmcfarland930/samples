<%-- 
    Document   : converter
    Created on : May 27, 2016, 2:46:02 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unit Converter</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/labStyles.css" />
    </head>
    <body>

        <%@include file="header.jsp"%> 

        <div id="content">

            <div class="container">  
                <div class="row">  
                    <div class="col-md-4 col-md-offset-3">  
                        <h1>Unit Converter</h1>
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
