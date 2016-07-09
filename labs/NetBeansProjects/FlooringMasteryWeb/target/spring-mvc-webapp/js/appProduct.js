$(document).ready(function () {

    $('#create-submit').on('click', function (e) {

        e.preventDefault();

        var productData = JSON.stringify({
            productType: $("#product-type-input").val(),
            costPerSqFt: $("#cost-input").val(),
            laborCostPerSqFt: $("#labor-input").val()

        });


        $('#product-div').removeClass('has-error');
        $('#cost-div').removeClass('has-error');
        $('#labor-div').removeClass('has-error');
        $('type-error').empty();
        $('cost-error').empty();
        $('labor-error').empty();

        $.ajax({
            url: contextRoot + "/admin/" + "addproducts",
            type: "POST",
            data: productData,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {
                console.log(data);
                var tableRow = buildProductRow(data);
                $('#product-table').append($(tableRow));
                $("#product-type-input").val('');
                $("#cost-input").val('');
                $("#labor-input").val('');

                $('#product-product-type').text(data.productType);
                $('#product-costPerSqFt').text(data.costPerSqFt);
                $('#product-laborCostPerSqFt').text(data.laborCostPerSqFt);

                $('#showProductModal').modal('show');
            },
            error: function (data, status) {
                var errors = data.responseJSON.errors;
                $('type-error').empty();
                $('cost-error').empty();
                $('labor-error').empty();

                $.each(errors, function (index, error) {

                    switch (error.fieldName) {
                        case "productType":
                            $('#type-error').append(error.message);
                            $('#product-div').addClass('has-error');

                            break;
                        case "costPerSqFt":
                            $('#cost-error').append(error.message);
                            $('#cost-div').addClass('has-error');
                            break;
                        case "laborCostPerSqFt":
                            $('#labor-error').append(error.message);
                            $('#labor-div').addClass('has-error');
                            break;
                        default:
                            break;
                    }

                });
            }


        });

    });

    $('#editProductModal').on('show.bs.modal', function (e) {

        var link = $(e.relatedTarget);

        var productId = link.data('product-id');

        $.ajax({
            url: contextRoot + "/admin/editProduct/" + productId,
            type: 'GET',
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            },
            success: function (data, status) {
                $('#edit-id').val(data.id);
                $('#edit-product-type').val(data.productType);
                $('#edit-product-cost').val(data.costPerSqFt);
                $('#edit-product-labor').val(data.laborCostPerSqFt);
            },
            error: function (data, status) {
                alert("error");
            }

        });

    });


    $(document).on('click', '#edit-product-button', function (e) {

        e.preventDefault();

        var productData = JSON.stringify({
            id: $('#edit-id').val(),
            productType: $("#edit-product-type").val(),
            costPerSqFt: $("#edit-product-cost").val(),
            laborCostPerSqFt: $("#edit-product-labor").val()
        });

        $('#product-div').removeClass('has-error');
        $('#cost-div').removeClass('has-error');
        $('#labor-div').removeClass('has-error');
        $('type-error').empty();
        $('cost-error').empty();
        $('labor-error').empty();

        $.ajax({
            url: contextRoot + "/admin/productSave/" + $("#edit-id").val(),
            type: "PUT",
            data: productData,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {

                $('#editProductModal').modal('hide');

                var tableRow = buildProductRow(data);

                $('#product-row-' + $("#edit-id").val()).replaceWith($(tableRow));

            },
            error: function (data, status) {
                var errors = data.responseJSON.errors;
                $('type-edit-error').empty();
                $('cost-edit-error').empty();
                $('labor-edit-error').empty();

                $.each(errors, function (index, error) {

                    switch (error.fieldName) {
                        case "productType":
                            $('#type-edit-error').append(error.message);
                            $('#product-div').addClass('has-error');
                            break;
                        case "costPerSqFt":
                            $('#cost-edit-error').append(error.message);
                            $('#cost-div').addClass('has-error');
                            break;
                        case "laborCostPerSqFt":
                            $('#labor-edit-error').append(error.message);
                            $('#labor-div').addClass('has-error');
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

        var productId = $(e.target).data('product-id');
        console.log(productId);
        $.ajax({
            type: "DELETE",
            url: contextRoot + "/admin/deleteProduct/" + productId,
            success: function (data, status) {

                $('#product-row-' + productId).remove();

            },
            error: function (data, status) {


            }
        });

    });




    function buildProductRow(data) {

        return "<tr id='product-row-" + data.id + "'>  \n\
                <td> " + data.productType + "</a></td>  \n\
                <td> " + "$" + data.costPerSqFt + "</td> \n\
                <td> " + "$" + data.laborCostPerSqFt + "</td> \n\
                <td> <a data-product-id='" + data.productType + "' data-toggle='modal' data-target='#editProductModal'><i class='glyphicon glyphicon-wrench'></i></a>  </td>   \n\
                <td> <a class='delete-link'><i data-product-id='" + data.id + "' class='glyphicon glyphicon-trash'></i></a>  </td>   \n\
                </tr>  ";


    }


});