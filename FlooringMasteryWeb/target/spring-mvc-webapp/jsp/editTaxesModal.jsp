<%-- 
    Document   : editModal
    Created on : Jun 8, 2016, 9:45:17 AM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <div class="modal fade" id="editTaxesModal" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Edit Tax Rate Details</h4>
                </div>

                <div class="modal-body">
                    <table class="table table-bordered" id="show-product-table">
                        <input type="hidden" id="edit-taxes-id"/>
                        <tr>
                            <th>State Type</th>
                            <td>
                                <input class="form-control" type="text" id="edit-taxes-state" readonly/>
                            </td>
                        </tr> 
                        <tr>
                            <th>Tax Rate</th>
                            <td id="rate-div">
                                <input class="form-control" type="text" id="edit-taxes-rate"/>
                                <div id="rate-edit-error" class="col-md-8 error-message">
                                </div>
                            </td>
                        </tr>

                    </table>
                </div>
                <div id="edit-taxes-validation-errors">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-danger" id="edit-taxes-button">Save</button>
                </div>
            </div>
        </div>
    </div>  
</html>
