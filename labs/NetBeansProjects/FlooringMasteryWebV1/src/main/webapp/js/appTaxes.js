$(document).ready(function () {

    $('#create-tax-submit').on('click', function (e) {

        e.preventDefault();

        var taxesData = JSON.stringify({
            state: $("#state-input").val(),
            taxRate: $("#rate-input").val()
        });

        $.ajax({
            url: contextRoot + "/admin/" + "addtaxrates",
            type: "POST",
            data: taxesData,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {
                console.log(data);
                var tableRow = buildTaxesRow(data);
                $('#taxes-table').append($(tableRow));
                $("#rate-input").val('');
                $("#state-input").val('');

                $('#taxes-state').text(data.state);
                $('#taxes-tax-rate').text(data.taxRate);

                $('#showTaxesModal').modal('show');
            },
            error: function (data, status) {
                var errors = data.responseJSON.errors;

                $.each(errors, function (index, error) {

                    $('#add-taxes-validation-errors').append(error.fieldName + ": " + error.message + "<br/>");

                });
            }


        });

    });

//    $('#showTaxesModal').on('show.bs.modal', function (e) {
//
//        var link = $(e.relatedTarget);
//
//        var taxesType = link.data('taxes-id');
//
//        $.ajax({
//            url: contextRoot + "/admin/" + taxesType,
//            type: 'GET',
//            dataType: 'json',
//            beforeSend: function (xhr) {
//                xhr.setRequestHeader("Accept", "application/json");
//            },
//            success: function (data, status) {
//                $("#taxes-type-input").html(data.taxesType);
//                $("#cost-input").html(data.costPerSqFt);
//                $("#labor-input").html(data.laborCostPerSqFt);
//            },
//            error: function (data, status) {
//
//            }
//
//
//        });
//
//    });

    $('#editTaxesModal').on('show.bs.modal', function (e) {

        var link = $(e.relatedTarget);

        var taxesId = link.data('taxes-type');
        $.ajax({
            url: contextRoot + "/admin/editTax/" + taxesId,
            type: 'GET',
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            },
            success: function (data, status) {
                $('#edit-taxes-state').val(data.state);
                $('#edit-taxes-rate').val(data.taxRate);
                $('#edit-taxes-id').val(data.state);
                console.log($('#edit-taxes-id').val());


            },
            error: function (data, status) {
                var errors = data.responseJSON.errors;

                $.each(errors, function (index, error) {

                    $('#edit-taxes-validation-errors').append(error.fieldName + ": " + error.message + "<br/>");

                });
            }


        });

    });


    $(document).on('click', '#edit-taxes-button', function (e) {

        e.preventDefault();

        var taxesData = JSON.stringify({
            state: $("#edit-taxes-state").val(),
            taxRate: $("#edit-taxes-rate").val()
        });

        console.log($('edit-tax-id').val());

        $.ajax({
            url: contextRoot + "/admin/taxSave" + $('#edit-taxes-id').val(),
            type: "PUT",
            data: taxesData,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {
                $('#editTaxesModal').modal('hide');

                var tableRow = buildTaxesRow(data);

                $('#taxes-row-' + $('#edit-taxes-id').val()).replaceWith($(tableRow));

            },
            error: function (data, status) {
                var errors = data.responseJSON.errors;

                $.each(errors, function (index, error) {

                    $('#edit-taxes-validation-errors').append(error.fieldName + ": " + error.message + "<br/>");

                });
            }

        });

    });




    $(document).on('click', '.delete-tax-link', function (e) {

        e.preventDefault();

        var taxesId = $(e.target).data('taxes-type');


        $.ajax({
            type: "DELETE",
            url: contextRoot + "/admin/deleteTax/" + taxesId,
            success: function (data, status) {

                $('#taxes-row-' + taxesId).remove();

            },
            error: function (data, status) {

            }
        });

    });




    function buildTaxesRow(data) {

        return "<tr id='taxes-row-" + data.state + "'>  \n\
                <td> " + data.state + "</td> \n\
                <td> " + data.taxRate + "</td> \n\
                <td> <a data-taxes-id='" + data.state + "' data-toggle='modal' data-target='#editTaxesModal'><i class='glyphicon glyphicon-wrench'></i></a>  </td>   \n\
                <td> <a class='delete-tax-link'><i data-taxes-type='" + data.state + "' class='glyphicon glyphicon-trash'></i></a>  </td>   \n\
                </tr>  ";


    }


});