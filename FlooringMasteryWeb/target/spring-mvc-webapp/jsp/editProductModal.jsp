<%-- 
    Document   : editModal
    Created on : Jun 8, 2016, 9:45:17 AM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <div class="modal fade" id="editProductModal" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Edit Product Details</h4>
                </div>

                <div class="modal-body">
                    <table class="table table-bordered" id="show-product-table">
                        <input type="hidden" id="edit-id"/>
                        <tr>
                            <th>Product Type</th>
                            <td id="product-div">
                                <input class="form-control" type="text" id="edit-product-type"/>
                                <div id="type-edit-error" class="col-md-8 error-message">
                                </div>
                            </td>
                        </tr> 

                        <tr>
                            <th>Cost/SqFt</th>
                            <td id="cost-div">
                                <input class="form-control" type="text" id="edit-product-cost"/>
                                <div id="cost-edit-error" class="col-md-8 error-message">
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <th>Labor Cost/SqFt</th>
                            <td id="labor-div">
                                <input class="form-control" type="text" id="edit-product-labor"/>
                                <div id="labor-edit-error" class="col-md-8 error-message">
                                </div>
                            </td>
                        </tr>


                    </table>
                </div>
                <div id="edit-product-validation-errors">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-danger" id="edit-product-button">Save</button>
                </div>
            </div>
        </div>
    </div>  
</html>
