<%-- 
    Document   : editModal
    Created on : Jun 8, 2016, 9:45:17 AM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <div class="modal fade" id="editOrderModal" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Edit Order Details</h4>
                </div>

                <div class="modal-body">
                    <table class="table table-bordered" id="show-product-table">
                        <input type="hidden" id="edit-id"/>
                        <tr>
                            <th>Name on Order:</th>
                            <td>
                                <input type="text" id="edit-order-name"/>
                            </td>
                        </tr>                   
                        <tr>
                            <th>Product:</th>
                            <td>
                                <select class="form-control" name="productType" id="edit-order-product">
                                    <c:forEach items="${products}" var="productType">
                                        <option value="${productType.productType}">${productType.productType}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>State Tax:</th>
                            <td>
                                <select class="form-control" name="state" id="edit-order-tax">
                                    <c:forEach items="${taxes}" var="state">
                                        <option value="${state.state}">${state.state}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>Area:</th>
                            <td>
                                <input type="text" id="edit-order-area"/>
                            </td>
                        </tr>    
                        <tr>
                            <th>Date:</th>
                            <td>
                                <input id="edit-order-date" path="date" class="form-control datepicker" value="${date}"></input>
                            </td>
                        </tr>       
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-danger" id="edit-order-button">Save</button>
                </div>
            </div>
        </div>
    </div>  
</html>
