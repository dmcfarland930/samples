$(document).ready(function () {

    $('#create-submit').on('click', function (e) {

        e.preventDefault();

        var orderData = JSON.stringify({
            customerName: $('#order-name-input').val(),
            productId: $("#order-product-input").val(),
            taxesId: $("#order-tax-input").val(),
            area: $("#order-area-input").val(),
            date: $("#order-date-input").val()

        });

        $.ajax({
            url: contextRoot + "/order/",
            type: "POST",
            data: orderData,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {
                console.log(data);
                var tableRow = buildOrderRow(data);
                $('#order-table').append($(tableRow));
                $("#order-name-input").val('');
                $("#order-state-input").val('');
                $("#order-product-input").val('');
                $("#order-area-input").val('');
                $("#order-date-input").val('');
                $("#order-number").val(data.orderNumber);
                $('#show-header').text("Order Added");

                $('#order-date').text(data.date);
                $('#order-name').text(data.customerName);
                $('#order-state').text(data.state);
                $('#order-product').text(data.productType);
                $('#order-area').text(data.area);

                $('#order-product-cost').text(data.costPerSqFt);
                $('#order-labor-cost').text(data.laborCostPerSqFt);
                $('#order-tax-rate').text(data.taxRate);

                $('#order-product-total').text(data.materialCost);
                $('#order-labor-total').text(data.totalLaborCost);
                $('#order-tax-total').text(data.tax);
                $('#order-grand-total').text(data.orderTotal);

                $('#showOrderModal').modal('show');
            },
            error: function (data, status) {
                var errors = data.responseJSON.errors;
                $('#name-error').empty();
                $('#area-error').empty();

                $.each(errors, function (index, error) {

                    switch (error.fieldName) {
                        case "customerName":
                            $('#name-error').append(error.message);
                            //$('#order-name-input').addClass("") (or .css);
                            break;
                        case "area":
                            $('#area-error').append(error.message);
                            break;
                        default:
                            break;

                    }

                });
            }


        });

    });

    $('#showOrderModal').on('show.bs.modal', function (e) {

        var link = $(e.relatedTarget);

        var orderId = link.data('order-id');
        if (orderId === undefined) {
            orderId = $('#order-number').val();
        }
        console.log(orderId);
        console.log($('#order-number').val());
        $.ajax({
            url: contextRoot + "/order/" + orderId,
            type: 'GET',
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            },
            success: function (data, status) {

                $('#order-date').html(data.date);
                $('#order-name').html(data.customerName);
                $('#order-state').html(data.state);
                $('#order-product').html(data.productType);
                $('#order-area').html(data.area);

                $('#order-product-cost').html(data.costPerSqFt);
                $('#order-labor-cost').html(data.laborCostPerSqFt);
                $('#order-tax-rate').html(data.taxRate);

                $('#order-product-total').html(data.materialCost);
                $('#order-labor-total').html(data.totalLaborCost);
                $('#order-tax-total').html(data.tax);
                $('#order-grand-total').html(data.orderTotal);
            },
            error: function (data, status) {

            }


        });

    });

    $('#editOrderModal').on('show.bs.modal', function (e) {

        var link = $(e.relatedTarget);
        var orderId = link.data('order-id');
        $.ajax({
            url: contextRoot + "/order/" + orderId,
            type: 'GET',
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            },
            success: function (data, status) {
                $('#edit-order-name').val(data.customerName);
                $('#edit-order-area').val(data.area);
                $('#edit-order-product').val(data.product.id);
                $('#edit-order-tax').val(data.taxes.id);
                $('#edit-order-date').val(data.date);
                $('#last-date').val(data.date);
                $('#order-number').val(data.orderNumber);
                $('#edit-id').val(orderId);

            },
            error: function (data, status) {

            }

        });

    });


    $(document).on('click', '#edit-order-button', function (e) {

        e.preventDefault();

        console.log($('#last-date').val());

        var orderData = JSON.stringify({
            orderNumber: $("#edit-id").val(),
            customerName: $("#edit-order-name").val(),
            productId: $("#edit-order-product").val(),
            taxesId: $("#edit-order-tax").val(),
            area: $('#edit-order-area').val(),
            date: $('#edit-order-date').val()
        });

        $.ajax({
            url: contextRoot + "/order/",
            type: "PUT",
            data: orderData,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {

                $('#editOrderModal').modal('hide');

                $('#show-header').text("Order Updated");

                $('#order-date').html(data.date);
                $('#order-name').html(data.customerName);
                $('#order-state').html(data.state);
                $('#order-product').html(data.productType);
                $('#order-area').html(data.area);

                $('#order-product-cost').html(data.costPerSqFt);
                $('#order-labor-cost').html(data.laborCostPerSqFt);
                $('#order-tax-rate').html(data.taxRate);


                $('#order-product-total').html(data.materialCost);
                $('#order-labor-total').html("<fmt:formatNumber type='currency' maxIntegerDigits='10' value='" + data.totalLaborCost + "'/>");
                $('#order-tax-total').html(data.tax);
                $('#order-grand-total').html(data.orderTotal);

                $('#showOrderModal').modal('show');

                console.log(data.date);
                console.log($('#last-date').val());
                if (data.date !== $('#last-date').val()) {

                    $('#order-row-' + data.orderNumber).remove();

                } else {

                    var tableRow = buildOrderRow(data);

                    $('#order-row-' + data.orderNumber).replaceWith($(tableRow));

                }

            },
            error: function (data, status) {
                var errors = data.responseJSON.errors;
                $('#name-edit-error').empty();
                $('#area-edit-error').empty();


                $.each(errors, function (index, error) {

                    switch (error.fieldName) {
                        case "customerName":
                            $('#name-edit-error').append(error.message);
                            //$('#order-name-input').addClass("") (or .css);
                            break;
                        case "area":
                            $('#area-edit-error').append(error.message);
                            break;
                        default:
                            break;
                    }
                });
            }

        });

    });




    $(document).on('click', '.delete-link', function (e) {

        e.preventDefault();

        var orderId = $(e.target).data('order-id');
        console.log(orderId);
        $.ajax({
            type: "DELETE",
            url: contextRoot + "/order/" + orderId,
            success: function (data, status) {

                $('#order-row-' + orderId).remove();

            },
            error: function (data, status) {

            }
        });

    });




    function buildOrderRow(data) {

        return "<tr id='order-row-" + data.orderNumber + "'>  \n\
                <td><a data-order-id='" + data.orderNumber + "' data-toggle='modal' data-target='#showOrderModal'>" + data.orderNumber + "</a></td>  \n\
                <td> " + data.customerName + "</td>    \n\
                <td> <a data-order-id='" + data.orderNumber + "' data-toggle='modal' data-target='#editOrderModal'><i class='glyphicon glyphicon-wrench'></i></a>  </td>   \n\
                <td> <a class='delete-link'><i data-order-id='" + data.orderNumber + "' class='glyphicon glyphicon-trash'></i></a>  </td>   \n\
                </tr>  ";


    }




});