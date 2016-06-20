<%-- 
    Document   : deleteError
    Created on : Jun 19, 2016, 9:42:35 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <div class="modal fade" id="errorModal" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="warning modal-title"><i class="glyphicon glyphicon-exclamation-sign"></i> Delete Error</h4>
                </div>
                <div style="margin: 0;" class="alert alert-danger">
                    <strong>Alert!</strong> This tax rate cannot be deleted. An order has been placed using this tax rate. 
                </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
            </div>
        </div>  
    </div
</html>
