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


        <title>Flooring Master</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

        <!-- External Styling --> 
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <link href='https://fonts.googleapis.com/css?family=Roboto+Slab' rel='stylesheet' type='text/css'>
        <link href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" rel="stylesheet">

    </head>
    <div class="navbar">
        <ul class="nav nav-tabs">
            <li role="presentation"><a href="${pageContext.request.contextPath}">Flooring Master</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/adminlogin/">Admin Login</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/order/vieworders/">View Orders</a></li>        </ul>    
    </div>

    <%@include file="showOrderModal.jsp"%> 
    <%@include file="editOrderModal.jsp"%> 

    <div class="container search-table">
        <body>
<!--            <div id="spin">
                
            </div>-->
            <div id="table-div" class="hidden">
                <table id="searchTable" class="display" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th>Order #</th>
                            <th>Order Placed</th>
                            <th>Order Name</th>
                            <th>Material Cost</th>
                            <th>Labor Cost</th>
                            <th>Tax Total</th>
                            <th>Order Total
                            <th>Edit</th>
                            <th>Delete</th>
                            </th>
                    </thead>

                    <tbody> 
                        <c:forEach items="${orders}" var="order">
                            <tr id="order-row-${order.orderNumber}">
                                <td><a data-order-id="${order.orderNumber}" data-toggle="modal" data-target="#showOrderModal">${order.orderNumber}</td>
                                <td> ${order.orderDateString}</td>
                                <td> ${order.customerName}</td>
                                <td><fmt:formatNumber type="currency" 
                                                  maxIntegerDigits="10" value="${order.materialCost}" /></td>
                                <td><fmt:formatNumber type="currency" 
                                                  maxIntegerDigits="10" value="${order.totalLaborCost}" /> </td>
                                <td><fmt:formatNumber type="currency" 
                                                  maxIntegerDigits="10" value="${order.tax}" /> </td>
                                <td><fmt:formatNumber type="currency" 
                                                  maxIntegerDigits="10" value="${order.orderTotal}" /> </td>
                                <td><a data-toggle="modal"  data-order-id="${order.orderNumber}"  data-target="#editOrderModal"><i class="glyphicon glyphicon-wrench"></a></td>
                                <td><a class="delete-link"><i data-order-id="${order.orderNumber}" class="glyphicon glyphicon-trash"></i></a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
    </div>
    <script>
        var contextRoot = '${pageContext.request.contextPath}';
    </script>
    <script src="https://code.jquery.com/jquery-1.12.3.js"></script>
    <script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js" ></script>
    <script src="${pageContext.request.contextPath}/js/dataTable.js" ></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/appOrder.js" ></script>  
    <!--<script src="${pageContext.request.contextPath}/js/spin.js" ></script>-->  
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <script>
        $(function () {
            $(".datepicker").datepicker();
        });
    </script>
</body>
</html>
