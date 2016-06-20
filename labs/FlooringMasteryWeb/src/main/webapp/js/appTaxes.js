$(document).ready(function () {

    $('#create-tax-submit').on('click', function (e) {

        e.preventDefault();

        var taxesData = JSON.stringify({
            state: $("#state-input").val(),
            taxRate: $("#rate-input").val()
        });

        $('#rate-div').removeClass('has-error');

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

                $('#taxes-state').text(data.state);
                $('#taxes-tax-rate').text(data.taxRate);

                $('#showTaxesModal').modal('show');
            },
            error: function (data, status) {
                var errors = data.responseJSON.errors;
                $('rate-error').empty();

                $.each(errors, function (index, error) {

                    switch (error.fieldName) {
                        case "taxRate":
                            $('#rate-error').append(error.message);
                            $('#rate-div').addClass('has-error');
                            break;
                        default:
                            break;
                    }

                });
            }


        });

    });


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
                $('#edit-taxes-id').val(data.id);
                console.log($('#edit-taxes-id').val());


            },
            error: function (data, status) {

            }


        });

    });


    $(document).on('click', '#edit-taxes-button', function (e) {

        e.preventDefault();

        var taxesData = JSON.stringify({
            id: $('#edit-taxes-id').val(),
            state: $("#edit-taxes-state").val(),
            taxRate: $("#edit-taxes-rate").val()
        });

        $('#rate-div').removeClass('has-error');

        console.log($('#edit-taxes-id').val());

        $.ajax({
            url: contextRoot + "/admin/taxSave/" + $('#edit-taxes-id').val(),
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
                $('rate-error').empty();

                $.each(errors, function (index, error) {

                    switch (error.fieldName) {
                        case "taxRate":
                            $('#rate-error').append(error.message);
                            $('#rate-div').addClass('has-error');
                            break;
                        default:
                            break;
                    }

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
                
                $('#errorModal').modal('show');
            }
        });

    });




    function buildTaxesRow(data) {

        return "<tr id='taxes-row-" + data.id + "'>  \n\
                <td> " + data.state + "</td> \n\
                <td> " + data.taxRate + "</td> \n\
                <td> <a data-taxes-id='" + data.id + "' data-toggle='modal' data-target='#editTaxesModal'><i class='glyphicon glyphicon-wrench'></i></a>  </td>   \n\
                <td> <a class='delete-tax-link'><i data-taxes-type='" + data.id + "' class='glyphicon glyphicon-trash'></i></a>  </td>   \n\
                </tr>  ";


    }


});