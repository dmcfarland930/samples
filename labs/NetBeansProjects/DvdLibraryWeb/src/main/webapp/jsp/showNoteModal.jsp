<%-- 
    Document   : showNoteModal
    Created on : Jun 18, 2016, 7:44:07 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <div class="modal fade" id="showNoteModal" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Note Details</h4>
                </div>
                <div class="modal-body">
                    <table class="table table-bordered" id="show-note-table">
                        <thead>
                        <th>Date Added</th>
                        <th>Details</th>
                        </thead>


                    </table>
                </div>
                <div class="modal-footer">
                     <button id="dvd-id-note" type="button" class="btn btn-default" data-toggle="modal" data-target="#addNoteModal">Add Notes</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
</html>
