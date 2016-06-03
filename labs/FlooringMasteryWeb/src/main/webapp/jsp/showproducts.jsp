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
                        <li role="presentation"><a href="${pageContext.request.contextPath}/admin/adminhome">Home</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/admin/addproducts/">Add Products</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/admin/addtaxrates/">Add Tax Rate</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}">Return to Flooring Master</a></li>

                    </ul>     
                </div>
            </div>

            <div class="row">

                <div class="col-md-6">

                    <h1>Product Summary - ${product.productType}</h1>
                    <br/>
                    <table class="table table-striped"> 
                        <tr>
                            <td>Cost Per Sq/Ft. ${product.costPerSqFt}</td>
                        </tr>
                        <tr>
                            <td>Labor Cost Per Sq/Ft. ${product.laborCostPerSqFt}</td>
                        </tr>

                    </table>


                    <form method="get" action="${pageContext.request.contextPath}">
                        <fieldset class="form-group">
                            <button class="btn bg-primary" type="submit"/>Go Back</button>
                        </fieldset>
                    </form>
                </div>

                <div class="col-md-6">

                    <h1>Add Another Product</h1>
                    <form method="POST" action="${pageContext.request.contextPath}/admin/addproducts">

                        <div class="form-group">
                            <fieldset class="form-group">
                                <label for="productType">Product Type</label>
                                <input type="text" class="form-control" name="productType" placeholder="Enter Product Type">
                            </fieldset>
                        </div>

                        <div class="form-group">
                            <fieldset class="form-group">
                                <label for="costPerSqFt">Cost per Sq/Ft</label>
                                <input type="text" class="form-control" name="costPerSqFt" placeholder="Enter Cost per Sq/Ft Type">
                            </fieldset>
                        </div>

                        <div class="form-group">
                            <fieldset class="form-group">
                                <label for="laborCostPerSqFt">Labor Cost per Sq/Ft</label>
                                <input type="text" class="form-control" name="laborCostPerSqFt" placeholder="Enter Labor Cost per Sq/Ft Type">
                            </fieldset>
                        </div>

                        <fieldset class="form-group">
                            <input class="btn bg-primary" type="submit"/>
                        </fieldset>
                    </form>

                </div>

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
                                <td><a href="../showproducts/${product.productType}">${product.productType}</td>
                                <td fmt:formatNumber type="currency">$${product.costPerSqFt}</td>
                                <td fmt:formatNumber type="currency">$${product.laborCostPerSqFt}</td>
                                <td><a href="../editproduct/${product.productType}">Edit</a></td>
                                <td><a href="../deleteproducts/${product.productType}">Delete</a></td>


                            </tr>

                        </c:forEach>
                    </table>
                </div>
            </div>

            <!-- Placed at the end of the document so the pages load faster -->
            <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

