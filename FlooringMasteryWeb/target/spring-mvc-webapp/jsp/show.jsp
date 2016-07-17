<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                <div class="navbar">
                    <ul class="nav nav-tabs">
                        <li role="presentation"><a href="${pageContext.request.contextPath}">Home</a></li>
                        <li  role="presentation"><a href="${pageContext.request.contextPath}/adminlogin/">Admin Login</a></li>

                        <form class="form-inline pull-right" method="POST" action="${pageContext.request.contextPath}/order/search/">

                            <fieldset class="form-group">
                                <input type="text" class="form-control" name="date" placeholder="Enter date to find order.">
                            </fieldset>
                            <button class="btn bg-primary" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                        </form>
                    </ul>    
                </div>
            </div>

            <div class="row">
                <div class="col-md-6 col-md-offset-3 order-form">

                    <h1>Order Summary #${order.orderNumber}</h1>
                    <br/>

                    <table class="table table-striped"> 
                        <tr>
                            <td>Date of Order:</td> <td>${date}</td>
                        </tr>
                        <tr>
                            <td>Customer Name:</td> <td>${order.customerName}</td>
                        </tr>
                        <tr>
                            <td>State:</td> <td>${order.state}</td>
                        </tr>
                        <tr>
                            <td>Product:</td> <td>${order.productType}</td>
                        </tr>
                        <tr>
                            <td>Area:</td> <td><fmt:formatNumber type="number" 
                                              maxIntegerDigits="10" value="${order.area}" /> sq/ft</td>
                        </tr>
                    </table>

                    <table class="table table-striped">
                        <tr>
                            <td>Product Cost/SqFt:</td>
                            <td><fmt:formatNumber type="currency" 
                                              maxIntegerDigits="10" value="${order.costPerSqFt}"/></td>
                        </tr>
                        <tr>
                            <td>Labor Cost/SqFt:</td> 
                            <td><fmt:formatNumber type="currency" 
                                              maxIntegerDigits="10" value="${order.laborCostPerSqFt}"/></td>
                        </tr>
                        <tr>
                            <td>Tax Rate:</td> 
                            <td><fmt:formatNumber type="number" 
                                              maxIntegerDigits="10" value="${order.taxRate}"/>%</td>
                        </tr>

                    </table>
                    <table class="table table-striped"> 
                        <tr>
                            <td>Total Product Cost:</td> <td><fmt:formatNumber type="currency" 
                                              maxIntegerDigits="10" value="${order.materialCost}"/></td>
                        </tr>
                        <tr>
                            <td>Total Labor Cost:</td> <td><fmt:formatNumber type="currency" 
                                              maxIntegerDigits="10" value=" ${order.totalLaborCost}"/></td>
                        </tr>
                        <tr>
                            <td>Tax:</td> <td><fmt:formatNumber type="currency" 
                                              maxIntegerDigits="10" value="${order.tax}" /></td>
                        </tr>
                        <tr>
                            <td>Total:</td> <td><fmt:formatNumber type="currency" 
                                              maxIntegerDigits="10" value=" ${order.orderTotal}" /></td>
                        </tr>
                    </table>


                    <a href="${pageContext.request.contextPath}"> <button class="btn bg-primary col-md-12" type="submit"/>Go Back</button></a>
                </div>

            </div>
            <!-- Placed at the end of the document so the pages load faster -->
            <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

