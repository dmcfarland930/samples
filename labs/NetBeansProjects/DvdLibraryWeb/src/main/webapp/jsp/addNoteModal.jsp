<%-- 
    Document   : addNoteModal
    Created on : Jun 19, 2016, 11:33:33 AM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <div class="modal fade" id="addNoteModal" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Add Note Details</h4>
                </div>
                <div class="modal-body">
                    <table class="table table-bordered" id="show-dvd-table">
                        <input type="hidden" id="dvd-id-input"/>

                        <tr>
                            <th>Details</th>
                            <td>
                                <textarea class="form-control" id="add-note-input" name="notes" rows="5"></textarea>
                            </td>
                        </tr> 


                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-danger" id="create-notes-submit">Create</button>
                </div>
            </div>
        </div>
    </div>  
</html>
