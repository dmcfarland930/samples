<%-- 
    Document   : showProductModal
    Created on : Jun 8, 2016, 10:14:14 AM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <div class="modal fade" id="showTaxesModal" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Tax Rate Added</h4>
                </div>
                <div class="modal-body">
                    <table class="table table-bordered" id="show-taxes-table">

                        <tr>
                            <th>State</th>
                            <td id="taxes-state"></td>
                        </tr> 

                        <tr>
                            <th>Tax Rate</th>
                            <td id="taxes-tax-rate"></td>
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
