$(document).ready(function () {

    $('#addNoteModal').on('show.bs.modal', function (e) {

        var dvdId = $("#dvd-id-note").val();

        $.ajax({
            url: contextRoot + "/dvd/addnote/" + dvdId,
            type: 'GET',
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            },
            success: function (data, status) {
                $('#dvd-id-input').val(dvdId);
            },
            error: function (data, status) {

            }


        });

    });


    $('#create-notes-submit').on('click', function (e) {

        e.preventDefault();

        var noteData = JSON.stringify({
            note: $("#add-note-input").val(),
            dvdId: $("#dvd-id-input").val()
        });

        $.ajax({
            url: contextRoot + "/dvd/createnote/",
            type: "POST",
            data: noteData,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {
                console.log(data);
                
                $('#addNoteModal').modal('hide');
                $('.note-row').remove();
                var notes = data.noteList;

                for (var i = 0; i < data.noteList.length; i++) {
                    var noteRow = buildNoteRow(notes[i]);
                    $('#show-note-table').append($(noteRow));

                }
            },
            error: function (data, status) {
                var errors = data.responseJSON.errors;

                $.each(errors, function (index, error) {

                    $('#create-dvd-validation-errors').append(error.fieldName + ": " + error.message + "<br/>");

                });
            }


        });

    });

    $('#showNoteModal').on('show.bs.modal', function (e) {

        var dvdId = $("#dvd-id-note").val();

        $.ajax({
            url: contextRoot + "/dvd/showNote/" + dvdId,
            type: 'GET',
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            },
            success: function (data, status) {

                $('.note-row').remove();
                var notes = data.noteList;

                for (var i = 0; i < data.noteList.length; i++) {
                    var noteRow = buildNoteRow(notes[i]);
                    $('#show-note-table').append($(noteRow));

                }
            },
            error: function (data, status) {

            }


        });

    });

    $('#editNoteModal').on('show.bs.modal', function (e) {

        var link = $(e.relatedTarget);

        var noteId = link.data('note-id');

        $.ajax({
            url: contextRoot + "/dvd/editnote/" + noteId,
            type: 'GET',
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            },
            success: function (data, status) {
                $('#edit-notes').val(data.note);
                $('#edit-id').val(noteId);
                $('#edit-dvd-id').val(data.dvdId);
                $('#edit-note-date').val(data.noteDate);
            },
            error: function (data, status) {

            }


        });

    });


    $(document).on('click', '#edit-notes-button', function (e) {

        e.preventDefault();

        var noteData = JSON.stringify({
            noteId: $("#edit-id").val(),
            dvdId: $("#edit-dvd-id").val(),
            note: $("#edit-notes").val(),
            noteDate: $("#edit-note-date").val()
        });


        $.ajax({
            url: contextRoot + "/dvd/editnote/",
            type: "PUT",
            data: noteData,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {

                $('#editNoteModal').modal('hide');

                var tableRow = buildNoteRow(data);

                $('#note-row-' + data.noteId).replaceWith($(tableRow));

            },
            error: function (data, status) {
                var errors = data.responseJSON.errors;

                $.each(errors, function (index, error) {

                    $('#edit-dvd-validation-errors').append(error.fieldName + ": " + error.message + "<br/>");

                });
            }

        });

    });




    $(document).on('click', '.delete-note-link', function (e) {

        e.preventDefault();

        var noteId = $(e.target).data('note-id');

        $.ajax({
            type: "DELETE",
            url: contextRoot + "/dvd/deletenote/" + noteId,
            success: function (data, status) {

                $('#note-row-' + noteId).remove();

            },
            error: function (data, status) {

            }
        });

    });




    function buildNoteRow(notes) {

        return"<tr class='note-row' id='note-row-" + notes.noteId + "'>\n\
                <td>" + notes.noteDate + "</td>\n\
                <td>" + notes.note + "</td>\n\
                <td> <a data-note-id='" + notes.noteId + "' data-toggle='modal' data-target='#editNoteModal'>Edit</a>  </td>   \n\
                <td> <a data-note-id='" + notes.noteId + "' class='delete-note-link'>Delete</a>  </td>   \n\
                </td>";

    }


});