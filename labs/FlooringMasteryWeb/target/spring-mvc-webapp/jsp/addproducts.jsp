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

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

        <!-- External Styling --> 
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
        <link href='https://fonts.googleapis.com/css?family=Roboto+Slab' rel='stylesheet' type='text/css'>

    </head>
    <body>
        <div class="container">
            <h1>Flooring Master - Admin Mode ${test}</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/admin/adminhome">Home</a></li>
                    <li class="active" role="presentation"><a href="${pageContext.request.contextPath}/admin/addproducts/">Add Products</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/admin/addtaxrates/">Add Tax Rate</a></li>  
                    <a href="${pageContext.request.contextPath}"> <button class="btn bg-primary col-md-2 pull-right" type="submit"/>Return to Flooring Master</button></a>
                </ul>    
            </div>
            <div class="row">
                <div class="col-md-6">
                    <h1>Products</h1>
                    <br />
                    <table id="product-table" class="table table-striped">    
                        <thead>
                            <tr id="product-row-${product.productType}">
                                <th>Product Name</th>
                                <th>Cost/SqFt</th>   
                                <th>Labor Cost/SqFt</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <c:forEach items="${products}" var="product">
                            <tr id="product-row-${product.productType}">
                                <td>${product.productType}</td>
                                <td><fmt:formatNumber type="currency" value="${product.costPerSqFt}"/></td>
                                <td><fmt:formatNumber type="currency" value="${product.laborCostPerSqFt}"/></td>
                                <td><a data-toggle="modal"  data-product-type="${product.productType}"  data-target="#editProductModal"><i class="glyphicon glyphicon-wrench"></a></td>
                                <td><a class="delete-link"><i data-product-type="${product.productType}" class="glyphicon glyphicon-trash"></i></a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div class="col-md-6 formDiv">
                    <h1>Add Product</h1>
                    <br/>
                    <form commandName="product" method="POST" id="product-form">

                        <fieldset class="form-group">
                            <label class="col-md-4" for="productType">Product Type:</label>
                            <div class="col-md-8">                                
                                <input type="text" id="product-type-input" class="form-control"/>
                            </div>
                        </fieldset>
                        <fieldset class="form-group">
                            <label class="col-md-4" for="costPerSqFt">Cost Per Sq/Ft:</label>
                            <div class="col-md-8">
                                <input type="text" id="cost-input" class="form-control"/>
                            </div>
                        </fieldset>
                        <fieldset class="form-group">
                            <label class="col-md-4" for="laborCostPerSqFt">Labor Cost Per Sq/Ft:</label>
                            <div class="col-md-8">
                                <input type="text" id="labor-input" class="form-control"/>
                            </div>
                        </fieldset>

                        <div id="button">
                            <fieldset class="form-group">
                                <input id="create-submit" class="btn bg-primary button-size pull-right" value="Add Product" type="submit"/>
                            </fieldset>
                        </div>
                    </form>
                    <%@include file="editProductModal.jsp"%> 
                    <%@include file="showProductModal.jsp"%> 
                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->

        <script>
            var contextRoot = '${pageContext.request.contextPath}';
        </script>

        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/appProduct.js" ></script>

    </body>
</html>

