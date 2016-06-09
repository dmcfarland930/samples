$(document).ready(function () {

    $('#create-submit').on('click', function (e) {

        e.preventDefault();

        var dvdData = JSON.stringify({
            title: $("#title-input").val(),
            rating: $("#rating-input").val(),
            director: $("#director-input").val(),
            studio: $("#studio-input").val(),
            date: $("#date-input").val(),
            notes: $("#notes-input").val()

        });

        $.ajax({
            url: contextRoot + "/dvd/",
            type: "POST",
            data: dvdData,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {
                console.log(data);
                var tableRow = buildDvdRow(data);
                $('#dvd-table').append($(tableRow));
                $("#title-input").val('');
                $("#director-input").val('');
                $("#rating-input").val('');
                $("#studio-input").val('');
                $("#date-input").val('');
                $("#notes-input").val('');
            },
            error: function (data, status) {
                alert("error");
            }


        });

    });

    $('#showDvdModal').on('show.bs.modal', function (e) {

        var link = $(e.relatedTarget);

        var dvdId = link.data('dvd-id');

        $.ajax({
            url: contextRoot + "/dvd/" + dvdId,
            type: 'GET',
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            },
            success: function (data, status) {
                $('#dvd-title').html(data.title);
                $('#dvd-director').html(data.director);
                $('#dvd-rating').html(data.rating);
                $('#dvd-studio').html(data.studio);
                $('#dvd-date').html(data.date);
                $('#dvd-notes').html(data.notes);
            },
            error: function (data, status) {

            }


        });

    });

    $('#editDvdModal').on('show.bs.modal', function (e) {

        var link = $(e.relatedTarget);

        var dvdId = link.data('dvd-id');

        $.ajax({
            url: contextRoot + "/dvd/" + dvdId,
            type: 'GET',
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            },
            success: function (data, status) {
                $('#edit-dvd-title').val(data.title);
                $('#edit-dvd-director').val(data.director);
                $('#edit-dvd-rating').val(data.rating);
                $('#edit-dvd-studio').val(data.studio);
                $('#edit-dvd-date').val(data.date);
                $('#edit-dvd-notes').val(data.notes);
                $('#edit-id').val(data.id);
            },
            error: function (data, status) {

            }


        });

    });


    $(document).on('click', '#edit-dvd-button', function (e) {

        e.preventDefault();

        var dvdData = JSON.stringify({
            id: $("#edit-id").val(),
            title: $("#edit-dvd-title").val(),
            director: $("#edit-dvd-director").val(),
            rating: $("#edit-dvd-rating").val(),
            studio: $("#edit-dvd-studio").val(),
            date: $("#edit-dvd-date").val(),
            notes: $("#edit-dvd-notes").val()
        });


        $.ajax({
            url: contextRoot + "/dvd/",
            type: "PUT",
            data: dvdData,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {

                $('#editDvdModal').modal('hide');

                var tableRow = buildDvdRow(data);

                $('#dvd-row-' + data.id).replaceWith($(tableRow));

            },
            error: function (data, status) {
                alert("error");
            }

        });

    });




    $(document).on('click', '.delete-link', function (e) {

        e.preventDefault();

        var dvdId = $(e.target).data('dvd-id');

        $.ajax({
            type: "DELETE",
            url: contextRoot + "/dvd/" + dvdId,
            success: function (data, status) {

                $('#dvd-row-' + dvdId).remove();

            },
            error: function (data, status) {

            }
        });

    });




    function buildDvdRow(data) {

        return "<tr id='dvd-row-" + data.id + "'>  \n\
                <td><a data-dvd-id='" + data.id + "' data-toggle='modal' data-target='#showDvdModal'>" + data.title + "</a></td>  \n\
                <td> " + data.director + "</td>    \n\
                <td> <a data-dvd-id='" + data.id + "' data-toggle='modal' data-target='#editDvdModal'>Edit</a>  </td>   \n\
                <td> <a data-dvd-id='" + data.id + "' class='delete-link'>Delete</a>  </td>   \n\
                </tr>  ";


    }


});