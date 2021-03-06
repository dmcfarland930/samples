<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%-- 
    Document   : edit
    Created on : May 31, 2016, 6:55:31 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Address Book</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <div class="container">
            <h1>Search</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}">Home</a></li>
                    <li class="active"  role="presentation"><a href="${pageContext.request.contextPath}/address/search/">Search</a></li>
                </ul>    
            </div>

            <div class="row">
                <div class="col-md-6">

                    <h1>Your Address Book</h1>
                    <table class="table table-striped">    
                        <thead>
                            <tr>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                        </thead>

                        <c:forEach items="${addresses}" var="address">
                            <tr id="address-row-${address.id}">
                                <td><a data-address-id="${address.id}" data-toggle="modal" data-target="#showAddressModal">${address.firstName}</td>
                                <td>${address.lastName}</td>
                                <td><a data-address-id="${address.id}" data-toggle="modal" data-target="#editAddressModal">Edit</a></td>
                                <td><a data-address-id="${address.id}" class="delete-link">Delete</a></td>
                                <!--create edit and delete in controller-->

                            </tr>

                        </c:forEach>
                    </table>

                </div>
                <div class="col-md-6">

                    <h1>Search</h1>
                    <br/><br/>
                    <form class="form-inline" method="POST" action="${pageContext.request.contextPath}/address/search/">

                        <div class="form-group">
                            <!--<label for="searchBy">Search by:</label>-->
                            <select class="form-control" name="selection" id="searchBy">
                                <option value="lastName">Last Name</option>
                                <option value="city">City</option>
                                <option value="state">State</option>
                                <option value="zip">Zip</option>
                            </select>
                        </div>

                        <fieldset class="form-group">
                            <input type="text" class="form-control" name="entry" placeholder="Enter Query">
                        </fieldset>
                        <input class="btn btn-default" type="submit"/>
                    </form>
                </div>
            </div>

        </div>

        <div class="modal fade" id="showAddressModal" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">Address Details</h4>
                    </div>
                    <div class="modal-body">
                        <table class="table table-bordered" id="show-address-table">

                            <tr>
                                <th>First Name:</th>
                                <td id="address-first-name"></td>
                            </tr> 

                            <tr>
                                <th>Last Name:</th>
                                <td id="address-last-name"></td>
                            </tr>

                            <tr>
                                <th>Street:</th>
                                <td id="address-street"></td>
                            </tr>

                            <tr>
                                <th>City:</th>
                                <td id="address-city"></td>
                            </tr>

                            <tr>
                                <th>State:</th>
                                <td id="address-state"></td>
                            </tr>
                            <tr>
                                <th>Zip Code:</th>
                                <td id="address-zip"></td>
                            </tr>

                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>  

        <div class="modal fade" id="editAddressModal" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">Edit Address Details</h4>
                    </div>
                    <div class="modal-body">
                        <table class="table table-bordered" id="show-address-table">
                            <input type="hidden" id="edit-id"/>
                            <tr>
                                <th>First Name</th>
                                <td>
                                    <input type="text" id="edit-address-first-name"/>
                                </td>
                            </tr> 

                            <tr>
                                <th>Last Name</th>
                                <td>
                                    <input type="text" id="edit-address-last-name"/>
                                </td>
                            </tr>

                            <tr>
                                <th>Street Number</th>
                                <td>
                                    <input type="text" id="edit-address-street-number"/>
                                </td>
                            </tr>

                            <tr>
                                <th>Street Name</th>
                                <td>
                                    <input type="text" id="edit-address-street-name"/>
                                </td>
                            </tr>

                            <tr>
                                <th>City</th>
                                <td>
                                    <input type="text" id="edit-address-city"/>
                                </td>
                            </tr>

                            <tr>
                                <th>State</th>
                                <td>
                                    <input type="text" id="edit-address-state"/>
                                </td>
                            </tr>

                            <tr>
                                <th>Zip</th>
                                <td>
                                    <input type="text" id="edit-address-zip"/>
                                </td>
                            </tr>

                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-danger" id="edit-address-button">Save</button>
                    </div>
                </div>
            </div>
        </div>  
        <!-- Placed at the end of the document so the pages load faster -->
        
        <script>
            var contextRoot = '${pageContext.request.contextPath}';
        </script>
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/app.js"></script>

    </body>
</html>
