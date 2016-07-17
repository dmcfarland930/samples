<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
        <title>Flooring Master</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">


        <!-- External Styling --> 
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
        <link href='https://fonts.googleapis.com/css?family=Roboto+Slab' rel='stylesheet' type='text/css'>
        
    </head>
    <body>
        <div class="container">
            <h1>Flooring Master ${test}</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/adminlogin/">Admin Login</a></li>
                    <form class="form-inline pull-right" method="POST" action="${pageContext.request.contextPath}/order/search/">

                        <fieldset class="form-group">
                            <input type="text" class="form-control" name="date" placeholder="Enter date to find order.">
                        </fieldset>
                        <button class="btn bg-primary" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                    </form>
                </ul>    
            </div>

            <div class="row">

                <div class="col-md-6">

                    <h1>Current Order Details #${order.orderNumber}</h1>
                    <br/>
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

                <div class="col-md-6 formDiv">

                    <h1>Edit Order</h1>
                    <br/>
                    <form:form commandName="orderCommand" method="POST" action="${pageContext.request.contextPath}/order/edit/${order.orderNumber}">

                        <fieldset class="form-group">
                            <label class="col-md-4" for="customerName">Name on Order</label>
                            <div class="col-md-8">
                                <form:input path="customerName" class="form-control" value="${order.customerName}"></form:input>
                                <form:errors path="customerName"/>
                            </div>
                        </fieldset>

                        <fieldset class="form-group">
                            <label class="col-md-4" for="productSelect">Choose Product</label>
                            <div div class="col-md-8">
                                <select class="form-control" name="productType" id="productSelect">
                                    <c:forEach items="${products}" var="productType">
                                        <option value="${productType.productType}">${productType.productType}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </fieldset>

                        <fieldset class="form-group">
                            <label class="col-md-4" for="taxSelect">Choose State Tax</label>
                            <div div class="col-md-8">
                                <select class="form-control" name="state" id="taxSelect">
                                    <c:forEach items="${taxes}" var="state">
                                        <option value="${state.state}">${state.state}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </fieldset>

                        <fieldset class="form-group">
                            <label class="col-md-4" for="area">Area</label>
                            <div class="col-md-8">
                                <form:input path="area" class="form-control" value="${order.area}"></form:input>
                                <form:errors path="area"/>
                            </div>
                        </fieldset>

                        <fieldset class="form-group">
                            <label class="col-md-4" for="date">Date</label>
                            <div class="col-md-8">
                                <form:input path="date" class="form-control" value="${date}"></form:input>
                                <form:errors path="date"/>
                            </div>
                        </fieldset>

                        <div id="button">
                            <fieldset class="form-group">
                                <input class="btn bg-primary pull-right button-size" value="Edit" type="submit"/>
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





