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
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
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

                    <h1>Your Orders on ${date}</h1>
                    <table id="order-table" class="table table-striped">    
                        <thead>
                            <tr>
                                <th>Order Number</th>
                                <th>Order Name</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <c:forEach items="${orders}" var="order">
                            <tr id="order-row-${order.orderNumber}">
                                <td><a data-order-id="${order.orderNumber}" data-toggle="modal" data-target="#showOrderModal">${order.orderNumber}</td>
                                <td> ${order.customerName}</td>
                                <td><a data-toggle="modal"  data-order-id="${order.orderNumber}"  data-target="#editOrderModal"><i class="glyphicon glyphicon-wrench"></a></td>
                                <td><a class="delete-link"><i data-order-id="${order.orderNumber}" class="glyphicon glyphicon-trash"></i></a></td>
                            </tr>
                        </c:forEach>
                    </table>
                    <h2>${noOrders}</h2>
                </div>

                <%@include file="addOrderForm.jsp"%> 

            </div>
        </div>


        <!-- Placed at the end of the document so the pages load faster -->
        <%@include file="showOrderModal.jsp"%> 
        <%@include file="editOrderModal.jsp"%> 
        <script>
            var contextRoot = '${pageContext.request.contextPath}';
        </script>
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/appOrder.js" ></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <script>
            $(function () {
                $(".datepicker").datepicker();
            });
        </script>

    </body>
</html>

