<%-- 
    Document   : showModal
    Created on : Jun 8, 2016, 9:42:07 AM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <div class="modal fade" id="showDvdModal" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">DVD Details</h4>
                </div>
                <div class="modal-body">
                    <table class="table table-bordered" id="show-dvd-table">

                        <tr>
                            <th>Title:</th>
                            <td id="dvd-title"></td>
                        </tr> 

                        <tr>
                            <th>Director:</th>
                            <td id="dvd-director"></td>
                        </tr>

                        <tr>
                            <th>Rating:</th>
                            <td id="dvd-rating"></td>
                        </tr>

                        <tr>
                            <th>Studio:</th>
                            <td id="dvd-studio"></td>
                        </tr>

                        <tr>
                            <th>Date:</th>
                            <td id="dvd-date"></td>
                        </tr>
                        <tr>
                            <th>Notes:</th>                        
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
