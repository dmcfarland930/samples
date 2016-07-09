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
        <title>Speed Converter</title>
    </head>
    <body>
        <h1>Speed Converter</h1>
        <form name="speedForm" method="post" action="SpeedServlet">
            <input type="text" name="mySpeed">
            <select name="unit">
                <option value="mph">Mph to Kph</option>
                <option value="kph">Kph to Mph</option>
            </select>
            <input type="submit" value="Convert"/>
        </form>


        <h2>${error}</h2>
        <h2>${speedConvert}</h2>

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
