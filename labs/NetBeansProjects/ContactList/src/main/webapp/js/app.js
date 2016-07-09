$(document).ready(function () {

    $('#create-submit').on('click', function (e) {

        e.preventDefault();

        var contactData = JSON.stringify({
            firstName: $("#first-name-input").val(),
            lastName: $("#last-name-input").val(),
            company: $("#company-input").val(),
            email: $("#email-input").val(),
            phone: $("#phone-input").val()

        });


        $.ajax({
            url: contextRoot + "/contact/",
            type: "POST",
            data: contactData,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {
                console.log(data);
                var tableRow = buildContactRow(data);
                $('#contact-table').append($(tableRow));
                $("#first-name-input").val('');
                $("#last-name-input").val('');
                $("#company-input").val('');
                $("#email-input").val('');
                $("#phone-input").val('');
                $("#last-contacted-input").val('');
                $('#add-contact-validation-errors').remove;
                $('#add-contact-validation-errors').html("<p>Good job!</p>");
            },
            error: function (data, status) {
                var errors = data.responseJSON.errors;

                $.each(errors, function (index, error) {

                    $('#add-contact-validation-errors').append(error.fieldName + ": " + error.message + "<br/>");

                });


            }
        });
    });

    $('#showContactModal').on('show.bs.modal', function (e) {

        var link = $(e.relatedTarget);

        var contactId = link.data('contact-id');

        $.ajax({
            url: contextRoot + "/contact/" + contactId,
            type: 'GET',
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            },
            success: function (data, status) {
                $('#contact-first-name').html(data.firstName);
                $('#contact-last-name').html(data.lastName);
                $('#contact-company').html(data.company);
                $('#contact-email').html(data.email);
                $('#contact-phone').html(data.phone);
            },
            error: function (data, status) {

            }


        });

    });


    $('#editContactModal').on('show.bs.modal', function (e) {

        var link = $(e.relatedTarget);

        var contactId = link.data('contact-id');

        $.ajax({
            url: contextRoot + "/contact/" + contactId,
            type: 'GET',
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            },
            success: function (data, status) {
                $('#edit-contact-first-name').val(data.firstName);
                $('#edit-contact-last-name').val(data.lastName);
                $('#edit-contact-company').val(data.company);
                $('#edit-contact-email').val(data.email);
                $('#edit-contact-phone').val(data.phone);
                $('#edit-id').val(data.id);
            },
            error: function (data, status) {

            }


        });

    });


    $('#edit-contact-button').on('click', function (e) {

        e.preventDefault();

        var contactData = JSON.stringify({
            id: $("#edit-id").val(),
            firstName: $("#edit-contact-first-name").val(),
            lastName: $("#edit-contact-last-name").val(),
            company: $("#edit-contact-company").val(),
            email: $("#edit-contact-email").val(),
            phone: $("#edit-contact-phone").val()


        });


        $.ajax({
            url: contextRoot + "/contact/",
            type: "PUT",
            data: contactData,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {

                $('#editContactModal').modal('hide');

                var tableRow = buildContactRow(data);

                $('#contact-row-' + data.id).replaceWith($(tableRow));

            },
            error: function (data, status) {
                alert("error");
            }

        });

    });


    $(document).on('click', '.delete-link', function (e) {

        e.preventDefault();

        var contactId = $(e.target).data('contact-id');

        $.ajax({
            type: "DELETE",
            url: contextRoot + "/contact/" + contactId,
            success: function (data, status) {

                $('#contact-row-' + contactId).remove();

            },
            error: function (data, status) {

            }
        });

    });

    function buildContactRow(data) {

        return "<tr id='contact-row-" + data.id + "'>  \n\
                <td><a data-contact-id='" + data.id + "' data-toggle='modal' data-target='#showContactModal'>" + data.firstName + "</a></td>  \n\
                <td> " + data.lastName + "</td>    \n\
                <td> <a data-contact-id='" + data.id + "' data-toggle='modal' data-target='#editContactModal'>Edit</a>  </td>   \n\
                <td> <a data-contact-id='" + data.id + "' class='delete-link'>Delete</a>  </td>   \n\
                </tr>  ";

    }

});