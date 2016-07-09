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
        <title>Dollars Converter</title>
    </head>
    <body>
        <h1>Dollars Converter</h1>
        <form name="dollarsForm" method="post" action="DollarsServlet">
            <input type="text" name="myMoney">
            <select name="unit">
                <option value="euros">to Euros</option>
                <option value="pesos">to Pesos</option>
                <option value="pounds">to Pounds</option>
                <option value="yen">to Yen</option>
            </select>
            <input type="submit" value="Convert"/>
        </form>


        <h2>${error}</h2>
        <h2>${dollarConvert}</h2>

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
