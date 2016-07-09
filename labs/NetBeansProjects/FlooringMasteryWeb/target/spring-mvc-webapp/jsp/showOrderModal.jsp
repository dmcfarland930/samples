<%-- 
    Document   : showProductModal
    Created on : Jun 8, 2016, 10:14:14 AM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <div class="modal fade" id="showOrderModal" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Order Details</h4>
                </div>
                <div class="modal-body">
                    <table class="table table-striped" id="show-order-table"> 

                        <tr>
                            <td>Date of Order:</td> 
                            <td id="order-date"></td>
                        </tr>
                        <tr>
                            <td>Customer Name:</td> 
                            <td id="order-name"></td>

                        </tr>
                        <tr>
                            <td>State:</td> 
                            <td id="order-state"></td>
                        </tr>
                        <tr>
                            <td>Product:</td> 
                            <td id="order-product"></td>
                        </tr>
                        <tr>
                            <td>Area:</td> 
                            <td id='order-area'></td>
                        </tr>
                    </table>

                    <table class="table table-striped">
                        <tr>
                            <td>Product Cost/SqFt:</td>
                            <td id='order-product-cost'></td>
                        </tr>
                        <tr>
                            <td>Labor Cost/SqFt:</td> 
                            <td id='order-labor-cost'></td>
                        </tr>
                        <tr>
                            <td>Tax Rate:</td> 
                            <td id='order-tax-rate'></td>
                        </tr>

                    </table>
                    <table class="table table-striped"> 
                        <tr>
                            <td>Total Product Cost:</td> 
                            <td id='order-product-total'></td>
                        </tr>
                        <tr>
                            <td>Total Labor Cost:</td>
                            <td id='order-labor-total'></td>
                        </tr>
                        <tr>
                            <td>Tax:</td>
                            <td id='order-tax-total'></td>
                        </tr>
                        <tr>
                            <td>Grand Total:</td>
                            <td id='order-grand-total'></td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
</html>
