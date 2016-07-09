<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
            <h1>Contact List</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}">Home</a></li>
                </ul>    
            </div>

            <div class="row">
                <div class="col-md-6">

                    <h1>Your List</h1>
                    <table class="table table-striped" id="contact-table">    
                        <thead>
                            <tr>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Edit</th>
                                <th>Remove</th>
                            </tr>
                        </thead>
                        <c:forEach items="${contacts}" var="contact">
                            <tr id="contact-row-${contact.id}">
                                <td><a data-contact-id="${contact.id}" data-toggle="modal" data-target="#showContactModal">${contact.firstName}</td>
                                <td>${contact.lastName}</td>
                                <td><a data-contact-id="${contact.id}" data-toggle="modal" data-target="#editContactModal">Edit</a></td>
                                <td><a data-contact-id="${contact.id}" class="delete-link">Delete</a></td>

                                <!--create edit and delete in controller-->

                            </tr>

                        </c:forEach>
                    </table>

                </div>
                <div class="col-md-6">

                    <h1>Enter Contact Info</h1>
                    <br/>
                    <form method="POST" class="form-horizontal">
                        <fieldset class="form-group">
                            <label class="col-md-3" for="firstName">First Name</label>
                            <div class="col-md-9">
                                <input type="text" id="first-name-input" class="form-control"/>
                            </div>
                        </fieldset>
                        <fieldset class="form-group">
                            <label class="col-md-3" for="lastName">Last Name</label>
                            <div class="col-md-9">
                                <input type="text" id="last-name-input" class="form-control"/>
                            </div>
                        </fieldset>
                        <fieldset class="form-group">
                            <label class="col-md-3" for="company">Company</label>
                            <div  class="col-md-9" >
                                <input type="text" id="company-input" class="form-control"/></div> 
                        </fieldset>
                        <fieldset class="form-group">
                            <label class="col-md-3" for="email">Email</label>
                            <div class="col-md-9">
                                <input type="text" id="email-input" class="form-control"/>
                            </div>
                        </fieldset>
                        <fieldset class="form-group">
                            <label class="col-md-3" for="phone">Phone</label>
                            <div class="col-md-9">
                                <input type="text" id="phone-input" class="form-control"/>
                            </div>
                        </fieldset>
                        <fieldset class="form-group">
                            <label class="col-md-3" for="lastContacted">Last Contacted: </label>
                            <div class="col-md-9">
                                <input type="text" id="last-contacted-input" class="form-control"/>
                            </div>
                        </fieldset>
                        
                        
                        <div id="add-contact-validation-errors">
                        </div>
                        
                        
                        
                        <input id="create-submit" class="btn btn-default pull-right" type="submit"/>

                    </form>

                </div>
            </div>

        </div>

        <div class="modal fade" id="showContactModal" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">Contact Details</h4>
                    </div>
                    <div class="modal-body">
                        <table class="table table-bordered" id="show-contact-table">

                            <tr>
                                <th>First Name:</th>
                                <td id="contact-first-name"></td>
                            </tr> 

                            <tr>
                                <th>Last Name:</th>
                                <td id="contact-last-name"></td>
                            </tr>

                            <tr>
                                <th>Company:</th>
                                <td id="contact-company"></td>
                            </tr>

                            <tr>
                                <th>Email:</th>
                                <td id="contact-email"></td>
                            </tr>

                            <tr>
                                <th>Phone:</th>
                                <td id="contact-phone"></td>
                            </tr>

                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>  

        <div class="modal fade" id="editContactModal" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">Edit Contact Details</h4>
                    </div>
                    <div class="modal-body">
                        <table class="table table-bordered" id="show-contact-table">
                            <input type="hidden" id="edit-id"/>
                            <tr>
                                <th>First Name</th>
                                <td>
                                    <input type="text" id="edit-contact-first-name"/>
                                </td>
                            </tr> 

                            <tr>
                                <th>Last Name</th>
                                <td>
                                    <input type="text" id="edit-contact-last-name"/>
                                </td>
                            </tr>

                            <tr>
                                <th>Company</th>
                                <td>
                                    <input type="text" id="edit-contact-company"/>
                                </td>
                            </tr>

                            <tr>
                                <th>Email</th>
                                <td>
                                    <input type="text" id="edit-contact-email"/>
                                </td>
                            </tr>

                            <tr>
                                <th>Phone</th>
                                <td>
                                    <input type="text" id="edit-contact-phone"/>
                                </td>
                            </tr>

                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-danger" id="edit-contact-button">Save</button>
                    </div>
                </div>
            </div>
        </div>  
        <script>
            var contextRoot = '${pageContext.request.contextPath}';
        </script>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/app.js"></script>
    </body>
</html>

