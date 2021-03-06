<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Flooring Master - Admin</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

        <!-- External Styling --> 
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />

    </head>
    <body>
        <div class="container">
            <h1>Flooring Master - Admin Mode ${test}</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/admin/adminhome">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/admin/addproducts/">Add Products</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/admin/addtaxrates/">Add Tax Rate</a></li>
                    <a href="${pageContext.request.contextPath}"> <button class="btn bg-primary col-md-2 pull-right" type="submit"/>Return to Flooring Master</button></a>


                </ul>    
            </div>

            <div class="row">

                <div class="col-md-6">

                    <h1>Product Info - ${productShow.productType}</h1>
                    <br/>
                    <table class="table table-striped"> 
                        <tr>
                            <td>Cost Per Sq/Ft.: $${productShow.costPerSqFt}</td>
                        </tr>
                        <tr>
                            <td>Labor Cost Per Sq/Ft.: $${productShow.laborCostPerSqFt}</td>
                        </tr>

                    </table>

                </div>

                <div class="col-md-6 formDiv">


                    <h1>Edit Product</h1>
                    <br/>
                    <form:form commandName="product" method="POST" action="${pageContext.request.contextPath}/admin/editproduct/${productShow.productType}">

                        <fieldset class="form-group">
                            <label class="col-md-4" for="productType">Product Type:</label>
                            <div class="col-md-8">
                                <form:input path="productType" class="form-control" value="${product.productType}"></form:input>
                                <form:errors path="productType"/>
                            </div>
                        </fieldset>
                        <fieldset class="form-group">
                            <label class="col-md-4" for="costPerSqFt">Cost Per Sq/Ft:</label>
                            <div class="col-md-8">
                                <form:input path="costPerSqFt" class="form-control" value="${product.costPerSqFt}"></form:input>
                                <form:errors path="costPerSqFt"/>
                            </div>
                        </fieldset>
                        <fieldset class="form-group">
                            <label class="col-md-4" for="laborCostPerSqFt">Labor Cost Per Sq/Ft:</label>
                            <div class="col-md-8">
                                <form:input path="laborCostPerSqFt" class="form-control" value="${product.laborCostPerSqFt}"></form:input>
                                <form:errors path="laborCostPerSqFt"/>
                            </div>
                        </fieldset>

                        <div id="button">
                            <fieldset class="form-group">
                                <input class="btn bg-primary button-size pull-right" value="Edit Product" type="submit"/>
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

