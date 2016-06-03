<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Flooring Master - Admin</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <div class="container">
            <h1>Flooring Master - Admin Mode ${test}</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li class="active" role="presentation"><a href="${pageContext.request.contextPath}/admin/adminhome">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/admin/addproducts/">Add Products</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/admin/addtaxrates/">Add Tax Rate</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}">Return to Flooring Master</a></li>

                </ul>    
            </div>

            <div class="row">
                <div class="col-md-6">

                    <h1>Products</h1>
                    <table class="table table-striped">    
                        <thead>
                            <tr>
                                <th>Product Name</th>
                                <th>Cost per SqFt</th>   
                                <th>Labor Cost per SqFt</th>
                            </tr>
                        </thead>
                        <c:forEach items="${products}" var="product">
                            <tr>
                                <td><a href="../admin/showproducts/${product.productType}">${product.productType}</td>
                                <td fmt:formatNumber type="currency">$${product.costPerSqFt}</td>
                                <td fmt:formatNumber type="currency">$${product.laborCostPerSqFt}</td>
                                <td><a href="../admin/editproduct/${product.productType}">Edit</a></td>
                                <td><a href="../admin/deleteproducts/${product.productType}">Delete</a></td>


                            </tr>

                        </c:forEach>
                    </table>

                </div>
                <div class="col-md-6">

                    <h1>Tax Rates</h1>
                    <table class="table table-striped">    
                        <thead>
                            <tr>
                                <th>State</th>
                                <th>Tax Rate</th>
                            </tr>
                        </thead>
                        <c:forEach items="${taxes}" var="tax">
                            <tr>
                                <td><a href="../admin/showtaxes/${tax.state}">${tax.state}</td>
                                <td fmt:formatNumber type="percent">${tax.taxRate}%</td>
                                <td><a href="../admin/edittaxrate/${tax.state}">Edit</a></td>
                                <td><a href="../admin/deletetaxes/${tax.state}">Delete</a></td>


                            </tr>

                        </c:forEach>
                    </table>

                </div>
            </div>
<!--(${tax.taxRate}%)-->
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

