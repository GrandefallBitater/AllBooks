$(document).ready(function () {
    $(document).on("click", "#deleteBookSubmit", function () {
        let id = $(this).attr("data-id");
        $.ajax({
            url: '/denyRequest?id=' + id,
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

    $(document).on("click", "#AddBookSubmit", function () {
        let id = $(this).attr("data-id");
        let name = $('#formGroupExampleInput' + id).val();
        let isbn = $('#isbnAddBook' + id).val();
        let descr = $('#formGrouptextarea' + id).val();
        let genres = $('#genresAddBook' + id).val();
        let year = $('#yearAddBook' + id).val();
        let capacity = $('#capacityAddBook' + id).val();
        let author = $('#authorAddBook' + id).val();
        let published = $('#publishedAddBook' + id).val();
        $.ajax({
            url: '/AcceptRequest?id=' + id + '&name=' + name + '&isbn=' + isbn + '&descr=' + descr + '&genres=' + genres + '&year=' + year + '&capacity=' + capacity + '&author=' + author + '&published=' + published,
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

    $(document).on("click", "#ByISBNSubmit", function () {
        let isbn = $('#formGroupExampleInputIsbn').val();
        console.log(1);
        if(isbn === "") {
            createErrorModal("Введите ISBN!");
        }else {
            $.ajax({
                url: '/createByISBN?isbn=' + isbn,
                type: 'GET',
                contentType: "application/json; charset=utf-8",
                dataType: "text",
                success: (message) => {
                    if(JSON.parse(message).message === "copy") {
                        createErrorModal("книга с таким ISBN уже есть в системе!");
                    }else {
                        location.reload();
                    }
                },
                error: (err) => {
                    console.log(err);
                }
            });
        }
    });

    function createErrorModal(message) {
        let errorMessage = {
            "message": message
        }
        $.ajax({
            url: '/errorModal',
            type: 'POST',
            data: JSON.stringify(errorMessage),
            contentType: "application/json; charset=utf-8",
            dataType: "text",
            success: (message) => {
                $('body').append(message);
                $("#errorModalCenter").modal("show");
            },
            error: (err) => {
                console.log(err);
            }
        });
    }
});