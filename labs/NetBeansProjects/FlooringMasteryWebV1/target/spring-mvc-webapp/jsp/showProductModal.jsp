<%-- 
    Document   : showProductModal
    Created on : Jun 8, 2016, 10:14:14 AM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <div class="modal fade" id="showProductModal" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Product Added</h4>
                </div>
                <div class="modal-body">
                    <table class="table table-bordered" id="show-product-table">
                        <tr>
                            <th>Product:</th>
                            <td id="product-product-type"></td>
                        </tr> 

                        <tr>
                            <th>Cost/SqFt:</th>
                            <td id="product-costPerSqFt"></td>
                        </tr>

                        <tr>
                            <th>Cost of Labor/Sqft:</th>
                            <td id="product-laborCostPerSqFt"></td>
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
