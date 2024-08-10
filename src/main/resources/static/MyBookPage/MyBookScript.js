$(document).ready(function () {
    $(document).on("click", "#submitDeleteLibrary", function () {
        let id = $(this).attr("data-id");
        $.ajax({
            url: '/deleteLibrary?id=' + id,
            type: 'GET',
            contentType: "application/json; charset=utf-8",
            dataType: "text",
            success: (message) => {
                $("#Modal_del_book").modal("hide");
            },
            error: (err) => {
                console.log(err);
            }
        });
    });

    $(document).on("click", "#refreshLyb", function () {
        let id = $(this).attr("data-id");
        let placeId = $('#select_my_shelf_myb').val();
        let groupId = $('#select_my_class_myb').val();
        let status = $('#select_my_status_myb').val();
        let notes = $('#formGrouptextarea_myb').val();
        let lastPage = $('#lastPage').val();
        let placeInfo = $('#placeInfo').val();
        let hidden = $('#select_hidden_myb').val();

        $.ajax({
            url: '/refreshLibrary?id=' + id + '&placeId=' + placeId + '&groupId=' + groupId + '&status=' + status + '&notes=' + notes + '&lastPage=' + lastPage + '&placeInfo=' + placeInfo + '&hidden=' + hidden,
            type: 'GET',
            contentType: "application/json; charset=utf-8",
            dataType: "text",
            success: (message) => {
                location.reload();
            },
            error: (err) => {
                console.log(err);
            }
        });
    });
});