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

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

        <!-- External Styling --> 
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" /> 
        <link href='https://fonts.googleapis.com/css?family=Roboto+Slab' rel='stylesheet' type='text/css'>


    </head>
    <body>
        <div class="navbar">
            <ul class="nav nav-tabs">
                <li role="presentation"><a href="${pageContext.request.contextPath}/admin/adminhome">Admin Panel</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/admin/addproducts/">Add Products</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/admin/addtaxrates/">Add Tax Rate</a></li>
                <a href="${pageContext.request.contextPath}"> <button class="return-button btn bg-primary col-md-2 pull-right" type="submit"/>Return to Flooring Master</button></a>
            </ul>    
        </div>
        <div class="container">

            <div class="row">
                <div class="col-md-6">
                    <h1>Products</h1>
                    <br/>
                    <table  id="product-table" class="table table-striped">    
                        <thead>
                            <tr>
                                <th>Product Name</th>
                                <th>Cost/SqFt</th>   
                                <th>Labor Cost/SqFt</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <c:forEach items="${products}" var="product">
                            <tr id="product-row-${product.id}">
                                <td>${product.productType}</td>
                                <td><fmt:formatNumber type="currency" value="${product.costPerSqFt}"/></td>
                                <td><fmt:formatNumber type="currency" value="${product.laborCostPerSqFt}"/></td>
                                <td><a data-toggle="modal"  data-product-id="${product.id}"  data-target="#editProductModal"><i class="glyphicon glyphicon-wrench"></a></td>
                                <td><a class="delete-link"><i data-product-id="${product.id}" class="glyphicon glyphicon-trash"></i></a></td>
                            </tr>

                        </c:forEach>
                    </table>

                    <%@include file="editProductModal.jsp"%> 


                </div>
                <div class="col-md-6">

                    <h1>Tax Rates</h1>
                    <br/>
                    <table id="taxes-table" class="table table-striped">    
                        <thead>
                            <tr>
                                <th>State</th>
                                <th>Tax Rate</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${taxes}" var="tax">
                                <tr id="taxes-row-${tax.id}">
                                    <td>${tax.state}</td>
                                    <td> <fmt:formatNumber type="number" value="${tax.taxRate}"/>%</td>
                                    <td><a data-toggle="modal"  data-taxes-type="${tax.id}"  data-target="#editTaxesModal"><i class="glyphicon glyphicon-wrench"></a></td>
                                    <td><a class="delete-tax-link"><i data-taxes-type="${tax.id}" class="glyphicon glyphicon-trash"></i></a></td>
                                </tr>

                            </c:forEach>
                        </tbody>
                    </table>

                    <%@include file="editTaxesModal.jsp"%> 
                </div>
            </div>
        </div>
        <%@include file="errorModal.jsp"%> 
        <!-- Placed at the end of the document so the pages load faster -->
        <script>
            var contextRoot = '${pageContext.request.contextPath}';
        </script>

        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/appProduct.js" ></script>
        <script src="${pageContext.request.contextPath}/js/appTaxes.js" ></script>

    </body>
</html>

