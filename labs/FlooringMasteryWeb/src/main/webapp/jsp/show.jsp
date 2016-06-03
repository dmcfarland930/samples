<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
                        <li role="presentation"><a href="${pageContext.request.contextPath}">Home</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/order/search/">Search Orders</a></li>
                        <li  role="presentation"><a href="${pageContext.request.contextPath}/adminlogin/">Admin Login</a></li>
                    </ul>    
                </div>
            </div>
            <div class="row">

                <div class="col-md-6 col-md-offset-3">

                    <h1>Order Summary #${order.orderNumber}</h1>
                    <br/>
                    <table class="table table-striped"> 
                        <tr>
                            <td>Date of Order: ${order.orderDate}</td>
                        </tr>
                        <tr>
                            <td>Customer Name: ${order.customerName}</td>
                        </tr>
                        <tr>
                            <td>State: ${order.state}</td>
                        </tr>
                        <tr>
                            <td>Product: ${order.productType}</td>
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


                    <a href="${pageContext.request.contextPath}"> <button class="btn bg-primary" type="submit"/>Go Back</button></a>
                </div>




            </div>
            <!-- Placed at the end of the document so the pages load faster -->
            <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

