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
        <title>Temperature Convert</title>
    </head>
    <body>
        <h1>Temperature Converter</h1>
        <form name="tempform" method="post" action="TempServlet">
            <input type="text" name="myTemp">
            <select name="unit">
                <option value="fahrenheit">&deg;F to &deg;C</option>
                <option value="celcius">&deg;C to &deg;F</option>
            </select>
            <input type="submit" value="Convert"/>
        </form>


        <h2>${error}</h2>
        <h2>${tempConvert}</h2>

        </br>
        <p>Convert Something Else</p>
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
            <input type="submit" name ="button" value="Home" onclick="{
                        document.form.button.value = this.value;
                        document.form.submit();
                    }"/>
        </form>

    </body>
</html>
