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
    </head>
    <body>
        <h1>Unit Converter</h1>
        <form name="form" method="POST" action="ConverterServlet">
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
    </body>
</html>
