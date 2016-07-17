<%-- 
    Document   : editModal
    Created on : Jun 8, 2016, 9:45:17 AM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <div class="modal fade" id="editNoteModal" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Edit Note Details</h4>
                </div>
                <div class="modal-body">
                    <table class="table table-bordered" id="show-dvd-table">
                        <input type="hidden" id="edit-id"/>
                        <input type="hidden" id="edit-dvd-id"/>
                        <input type="hidden" id="edit-title"/>
                        <input type="hidden" id="edit-note-date"/>

                        <tr>
                            <th>Details</th>
                            <td>
                                <textarea class="form-control" id="edit-notes" name="notes" rows="5"></textarea>
                            </td>
                        </tr> 


                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-danger" id="edit-notes-button">Save</button>
                </div>
            </div>
        </div>
    </div>  
</html>
