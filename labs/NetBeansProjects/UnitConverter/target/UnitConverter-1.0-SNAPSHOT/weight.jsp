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
        <title>Weight Converter</title>
    </head>
    <body>
        <h1>Weight Converter</h1>
        <form name="weightForm" method="post" action="WeightServlet">
            <input type="text" name="myWeight">
            <select name="unit">
                <option value="pounds">Lbs to Kgs</option>
                <option value="kilos">Kgs to Lbs</option>
            </select>
            <input type="submit" value="Convert"/>
        </form>


        <h2>${error}</h2>
        <h2>${weightConvert}</h2>

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
