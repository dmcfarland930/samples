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

    </head>
    <body>
        <div class="container">
            <h1>Flooring Master ${test}</h1>
            <hr/>
            <div class="navbar">
                <div class="navbar">
                    <ul class="nav nav-tabs">
                        <li role="presentation"><a href="${pageContext.request.contextPath}/admin/adminhome">Home</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/admin/addproducts/">Add Products</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/admin/addtaxrates/">Add Tax Rate</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}">Return to Flooring Master</a></li>

                    </ul>     
                </div>
            </div>

            <div class="row">

                <div class="col-md-6">

                    <h1>Product Summary - ${product.productType}</h1>
                    <br/>
                    <table class="table table-striped"> 
                        <tr>
                            <td>Cost Per Sq/Ft. ${product.costPerSqFt}</td>
                        </tr>
                        <tr>
                            <td>Labor Cost Per Sq/Ft. ${product.laborCostPerSqFt}</td>
                        </tr>

                    </table>


                    <form method="get" action="${pageContext.request.contextPath}">
                        <fieldset class="form-group">
                            <button class="btn bg-primary" type="submit"/>Go Back</button>
                        </fieldset>
                    </form>
                </div>

                <div class="col-md-6">

                    <h1>Add Another Product</h1>
                    <br />

                    <form:form commandName="product" method="POST" action="${pageContext.request.contextPath}/admin/addproducts">

                        <fieldset class="form-group">
                            <label class="col-md-4" for="productType">Product Type</label>
                            <div class="col-md-8">
                                <form:input path="productType" class="form-control" placeholder="Enter Product Name"></form:input>
                                <form:errors path="productType"/>
                            </div>
                        </fieldset>
                        <fieldset class="form-group">
                            <label class="col-md-4" for="costPerSqFt">Cost Per Sq/Ft</label>
                            <div class="col-md-8">
                                <form:input path="costPerSqFt" class="form-control" placeholder="Enter Cost Per Sq/Ft"></form:input>
                                <form:errors path="costPerSqFt"/>
                            </div>
                        </fieldset>
                        <fieldset class="form-group">
                            <label class="col-md-4" for="laborCostPerSqFt">Labor Cost Per Sq/Ft</label>
                            <div class="col-md-8">
                                <form:input path="laborCostPerSqFt" class="form-control" placeholder="Enter Labor Cost Per Sq/Ft"></form:input>
                                <form:errors path="laborCostPerSqFt"/>
                            </div>
                        </fieldset>

                        <fieldset class="form-group">
                            <input class="btn bg-primary" type="submit"/>
                        </fieldset>
                    </form:form>

                </div>

            </div>

            <!-- Placed at the end of the document so the pages load faster -->
            <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

