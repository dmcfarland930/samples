<%-- 
    Document   : editModal
    Created on : Jun 8, 2016, 9:45:17 AM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
        <div class="modal fade" id="editDvdModal" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">Edit Dvd Details</h4>
                    </div>
                    <div class="modal-body">
                        <table class="table table-bordered" id="show-dvd-table">
                            <input type="hidden" id="edit-id"/>
                            <tr>
                                <th>Title</th>
                                <td>
                                    <input type="text" id="edit-dvd-title"/>
                                </td>
                            </tr> 

                            <tr>
                                <th>Director</th>
                                <td>
                                    <input type="text" id="edit-dvd-director"/>
                                </td>
                            </tr>

                            <tr>
                                <th>MPAA Rating</th>
                                <td>
                                    <input type="text" id="edit-dvd-rating"/>
                                </td>
                            </tr>

                            <tr>
                                <th>Studio</th>
                                <td>
                                    <input type="text" id="edit-dvd-studio"/>
                                </td>
                            </tr>

                            <tr>
                                <th>Date</th>
                                <td>
                                    <input type="text" id="edit-dvd-date"/>
                                </td>
                            </tr>


                        </table>
                    </div>
                    <div id="edit-dvd-validation-errors">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-danger" id="edit-dvd-button">Save</button>
                    </div>
                </div>
            </div>
        </div>  
</html>
