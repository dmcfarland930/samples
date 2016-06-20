<%-- 
    Document   : addOrderForm
    Created on : Jun 9, 2016, 10:28:04 AM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <div class="col-md-6 formDiv">

        <h1>Add Order</h1>
        <br/>
        <form method="POST">

            <fieldset class="form-group">
                <label class="col-md-4" for="customerName">Name on Order:</label>
                <div id="name-div" class="col-md-8">
                    <input type="text" id="order-name-input" class="form-control"/>
                </div>
                <div class="error-message" id="name-error" class="col-md-8">
                </div>
            </fieldset>

            <fieldset class="form-group">
                <label class="col-md-4" for="productSelect">Choose Product:</label>
                <div div class="col-md-8">
                    <select class="form-control" name="productType" id="order-product-input">
                        <c:forEach items="${products}" var="productType">
                            <option value="${productType.id}">${productType.productType}</option>
                        </c:forEach>
                    </select>
                </div>
            </fieldset>

            <fieldset class="form-group">
                <label class="col-md-4" for="taxSelect">Choose State Tax:</label>
                <div div class="col-md-8">
                    <select class="form-control" name="state" id="order-tax-input">
                        <c:forEach items="${taxes}" var="state">
                            <option value="${state.id}">${state.state}</option>
                        </c:forEach>
                    </select>
                </div>
            </fieldset>

            <fieldset class="form-group">
                <label class="col-md-4" for="area">Area:</label>
                <div id="area-div" class="col-md-8">
                    <input type="text" id="order-area-input" class="form-control ${hasError}"/>
                </div>
                <div class="error-message" id="area-error" class="col-md-8">
                </div>
            </fieldset>

            <fieldset class="form-group">
                <label class="col-md-4" for="date">Date:</label>
                <div class="col-md-8">
                    <input id="order-date-input" path="date" class="form-control datepicker" value="${date}" readonly></input>
                </div>
            </fieldset>
            <div id="add-order-validation-errors">
            </div>

            <div id="button">
                <fieldset class="form-group ">
                    <input id="create-submit" class="btn bg-primary pull-right button-size" type="submit" value="Submit Order"/>
                </fieldset>
            </div>
            <input type="hidden" id="last-date" value="${date}"/>
            <input type="hidden" id="order-number"/>
        </form>

    </div>
</html>
