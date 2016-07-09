<%-- 
    Document   : header
    Created on : May 30, 2016, 6:39:34 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <style>
        header{

            border-color: #F8F8F8;
            background-color: #F8F8F8;
            border-style: solid;
            border-width: 2px;
            border-radius: 5px;
            margin: 20px;
        }
        h1{
            margin-top: 10px;
        }
        h2{
            margin-bottom: 10px;  
        }
        nav{
            margin: 20px;
        }
    </style>
    <header style="text-align:center">
        <h1>Software Craftmanship Guild Java Cohort</h1>
        <h2>JSP Site Lab</h2>        </header>

    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-center">
                    <li><a href="Home">Home <span class="sr-only">(current)</span></a></li>
                    <li><a href="Lucky">Lucky Sevens</a></li>
                    <li><a href="Factor">Factorizer</a></li>
                    <li><a href="Interest">Interest Calculator</a></li>
                    <li><a href="Flooring">Flooring Calculator</a></li>
                    <li><a href="Tip">Tip Calculator</a></li>
                    <li><a href="Converter">Unit Converter</a></li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>  
</html>
