$(document).ready(function () {

    $('#create-submit').on('click', function (e) {

        e.preventDefault();

        var addressData = JSON.stringify({
            firstName: $("#first-name-input").val(),
            lastName: $("#last-name-input").val(),
            streetNumber: $("#street-number-input").val(),
            streetName: $("#street-name-input").val(),
            city: $("#city-input").val(),
            state: $("#state-input").val(),
            zip: $("#zip-code-input").val()

        });

        $.ajax({
            url: contextRoot + "/address/",
            type: "POST",
            data: addressData,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {
                console.log(data);
                var tableRow = buildAddressRow(data);
                $('#address-table').append($(tableRow));
                $("#first-name-input").val('');
                $("#last-name-input").val('');
                $("#street-number-input").val('');
                $("#street-name-input").val('');
                $("#city-input").val('');
                $("#state-input").val('');
                $("#zip-code-input").val('');
            },
            error: function (data, status) {
                var errors = data.responseJSON.errors;

                $.each(errors, function (index, error) {

                    $('#add-address-validation-errors').append(error.fieldName + ": " + error.message + "<br/>");

                });
            }
        });
    });


    $('#showAddressModal').on('show.bs.modal', function (e) {

        var link = $(e.relatedTarget);

        var addressId = link.data('address-id');

        $.ajax({
            url: contextRoot + "/address/" + addressId,
            type: 'GET',
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            },
            success: function (data, status) {
                $('#address-first-name').html(data.firstName);
                $('#address-last-name').html(data.lastName);
                $('#address-street').html(data.streetNumber + " " + data.streetName);
                $('#address-city').html(data.city);
                $('#address-state').html(data.state);
                $('#address-zip').html(data.zip);
                document.getElementById('map').src = "//www.google.com/maps/embed/v1/place?q="+data.streetNumber+"+"+data.streetName+"+"+data.city+"+"+data.state+"+"+data.zip+"&zoom=17&key=AIzaSyBtKEaTaZeu1AxsAYLXNzfwssoql2Z-BT4";

            },
            error: function (data, status) {

            }


        });

    });

    $('#editAddressModal').on('show.bs.modal', function (e) {

        var link = $(e.relatedTarget);

        var addressId = link.data('address-id');

        $.ajax({
            url: contextRoot + "/address/" + addressId,
            type: 'GET',
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            },
            success: function (data, status) {
                $('#edit-address-first-name').val(data.firstName);
                $('#edit-address-last-name').val(data.lastName);
                $('#edit-address-street-number').val(data.streetNumber);
                $('#edit-address-street-name').val(data.streetName);
                $('#edit-address-city').val(data.city);
                $('#edit-address-state').val(data.state);
                $('#edit-address-zip').val(data.zip);
                $('#edit-id').val(data.id);
            },
            error: function (data, status) {

            }


        });

    });


    $(document).on('click', '#edit-address-button', function (e) {

        e.preventDefault();

        var addressData = JSON.stringify({
            id: $("#edit-id").val(),
            firstName: $("#edit-address-first-name").val(),
            lastName: $("#edit-address-last-name").val(),
            streetNumber: $("#edit-address-street-number").val(),
            streetName: $("#edit-address-street-name").val(),
            city: $("#edit-address-city").val(),
            state: $("#edit-address-state").val(),
            zip: $("#edit-address-zip").val()
        });


        $.ajax({
            url: contextRoot + "/address/",
            type: "PUT",
            data: addressData,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {

                $('#editAddressModal').modal('hide');

                var tableRow = buildAddressRow(data);

                $('#address-row-' + data.id).replaceWith($(tableRow));

            },
            error: function (data, status) {
                var errors = data.responseJSON.errors;

                $.each(errors, function (index, error) {

                    $('#edit-address-validation-errors').append(error.fieldName + ": " + error.message + "<br/>");

                });
            }

        });

    });




    $(document).on('click', '.delete-link', function (e) {

        e.preventDefault();

        var addressId = $(e.target).data('address-id');

        $.ajax({
            type: "DELETE",
            url: contextRoot + "/address/" + addressId,
            success: function (data, status) {

                $('#address-row-' + addressId).remove();

            },
            error: function (data, status) {

            }
        });

    });




    function buildAddressRow(data) {

        return "<tr id='address-row-" + data.id + "'>  \n\
                <td><a data-address-id='" + data.id + "' data-toggle='modal' data-target='#showAddressModal'>" + data.firstName + "</a></td>  \n\
                <td> " + data.lastName + "</td>    \n\
                <td> <a data-address-id='" + data.id + "' data-toggle='modal' data-target='#editAddressModal'>Edit</a>  </td>   \n\
                <td> <a data-address-id='" + data.id + "' class='delete-link'>Delete</a>  </td>   \n\
                </tr>  ";


    }

});