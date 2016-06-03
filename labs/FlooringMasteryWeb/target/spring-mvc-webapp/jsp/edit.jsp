<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- 
    Document   : edit
    Created on : May 31, 2016, 6:55:31 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Contact List</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <div class="container">
            <h1>Flooring Master - Edit ${test}</h1>
            <hr/>
            <div class="navbar">
                <div class="navbar">
                    <ul class="nav nav-tabs">
                        <li role="presentation"><a href="${pageContext.request.contextPath}">Home</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/order/search/">Search Orders</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/adminlogin/">Admin Login</a></li>
                    </ul>    
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">

                    <h1>Current Order Details #${order.orderNumber}</h1>
                    <table class="table table-striped"> 
                        <tr>
                            <td>Date of Order: ${date}</td>
                        </tr>
                        <tr>
                            <td>Customer Name: ${order.customerName}</td>
                        </tr>
                        <tr>
                            <td>Product: ${order.productType}</td>
                        <tr>
                            <td>State: ${order.state}</td></tr>
                        </tr>
                        <tr>
                            <td>Area: ${order.area} sq/ft</td>
                        </tr>
                    </table>

                    <table class="table table-striped"> 
                        <tr>
                            <td>Product Cost per Sq/Ft: $${order.costPerSqFt}</td>
                        </tr>
                        <tr>
                            <td>Labor Cost/SqFt: $${order.laborCostPerSqFt}</td>
                        </tr>
                        <tr>
                            <td>Tax Rate: ${order.taxRate}%</td>
                        </tr>
                    </table>

                    <table class="table table-striped"> 
                        <tr>
                            <td>Total Product Cost: $${order.materialCost}</td>
                        </tr>
                        <tr>
                            <td>Total Labor Cost: $${order.totalLaborCost}</td>
                        </tr>
                        <tr>
                            <td>Tax: $${order.tax}</td>
                        </tr>
                        <tr>
                            <td>Total: $${order.orderTotal}</td>
                        </tr>
                    </table>

                </div>

                <div class="col-md-6">

                    <h1>Edit Order</h1>
                    <form method="POST" action="../edit/${order.orderNumber}">
                        <fieldset class="form-group">
                            <label for="customerName">Name</label>
                            <input type="text" class="form-control" name="customerName" placeholder="${order.customerName}">
                        </fieldset>
                        <div class="form-group">
                            <label for="productSelect">Choose Product</label>
                            <select class="form-control" name="productType" id="productSelect">
                                <c:forEach items="${products}" var="productType">
                                    <option value="${productType.productType}" ${productType.productType == order.productType ? 'selected' : ''}>${productType.productType}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="taxSelect">Choose State Tax</label>
                            <select class="form-control" name="state" id="taxSelect">
                                <c:forEach items="${taxes}" var="state">
                                    <option value="${state.state}" ${state.state == order.state ? 'selected' : ''}>${state.state}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <fieldset class="form-group">
                            <label for="area">Area</label>
                            <input type="text" class="form-control" name="area" placeholder="${order.area}">
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="date">Date</label>
                            <input type="text" class="form-control" name="orderDate" placeholder="ex. 02/14/1993">
                        </fieldset>

                        <input class="btn bg-primary" type="submit"/>
                    </form>

                </div>
            </div>
        </div>

    </div>
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>
