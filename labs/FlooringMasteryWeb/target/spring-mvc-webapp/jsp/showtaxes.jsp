<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Flooring Master</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

        <!-- External Styling --> 
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
        <link href='https://fonts.googleapis.com/css?family=Roboto+Slab' rel='stylesheet' type='text/css'>

    </head>
    <body>
        <div class="container">
            <h1>Flooring Master - Admin Mode ${test}</h1>
            <hr/>
            <div class="navbar">
                <div class="navbar">
                    <ul class="nav nav-tabs">
                        <li role="presentation"><a href="${pageContext.request.contextPath}/admin/adminhome">Home</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/admin/addproducts/">Add Products</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/admin/addtaxrates/">Add Tax Rate</a></li>
                        <a href="${pageContext.request.contextPath}"> <button class="btn bg-primary col-md-2 pull-right" type="submit"/>Return to Flooring Master</button></a>

                    </ul>    
                </div>
            </div>
            <div class="row">

                <div class="col-md-6">

                    <h1>Tax Summary - ${taxes.state}</h1>
                    <br/>
                    <table class="table table-striped"> 
                        <tr>
                            <td>Tax Rate: ${taxes.taxRate}%</td>
                    </table>
                    <a href="${pageContext.request.contextPath}/admin/adminhome"> <button class="col-md-12 btn bg-primary" type="submit"/>Go Back</button></a>

                </div>

                <div class="col-md-6">

                    <h1>Add Another Tax Rate</h1>
                    <br />

                    <form:form commandName="taxes" method="POST" action="${pageContext.request.contextPath}/admin/addtaxrates">

                        <fieldset class="form-group">
                            <label class="col-md-4" for="state">State:</label>
                            <div class="col-md-8">
                                <form:input path="state" class="form-control" placeholder="Enter State"></form:input>
                                <form:errors path="state"/>
                            </div>
                        </fieldset>
                        <fieldset class="form-group">
                            <label class="col-md-4" for="taxRate">Tax Rate:</label>
                            <div class="col-md-8">
                                <form:input path="taxRate" class="form-control" placeholder="Enter Tax Rate"></form:input>
                                <form:errors path="taxRate"/>
                            </div>
                        </fieldset>


                        <div id="button">
                            <fieldset class="form-group">
                                <input class="btn bg-primary pull-right button-size" value="Add Tax Rate" type="submit"/>
                            </fieldset>
                        </div>
                    </form:form>

                </div>

            </div>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

