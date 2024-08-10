$(document).ready(function () {
    $(document).on("click", "#createBookModalSubmit", function () {
        let bookId = $(this).attr("data-bookId");
        let placeId = $('#select_my_shelf' + bookId).val();
        let groupId = $('#select_my_class' + bookId).val();
        let status = $('#select_my_status' + bookId).val();
        console.log(bookId);

        if(placeId === "none" && groupId === "none") {
            createErrorModal("книга должна принадлежать либо полке либо группе!");
        }

        $.ajax({
            url: '/createLibrary?placeId=' + placeId + '&groupId=' + groupId + '&status=' + status + '&bookId=' + bookId,
            type: 'GET',
            contentType: "application/json; charset=utf-8",
            dataType: "text",
            success: (message) => {
                $('#add_book_myLib' + bookId).modal("hide");
            },
            error: (err) => {
                console.log(err);
            }
        });
    });

    $(document).on("click", "#createBookModalSubmit1", function () {
        let bookId = $(this).attr("data-id");
        let placeId = $('#select_my_shelfr').val();
        let groupId = $('#select_my_classr').val();
        let status = $('#select_my_statusr').val();
        console.log(bookId);

        if(placeId === "none" && groupId === "none") {
            createErrorModal("книга должна принадлежать либо полке либо группе!");
        }

        $.ajax({
            url: '/createLibrary?placeId=' + placeId + '&groupId=' + groupId + '&status=' + status + '&bookId=' + bookId,
            type: 'GET',
            contentType: "application/json; charset=utf-8",
            dataType: "text",
            success: (message) => {
                $('#add_book_myLib' + bookId).modal("hide");
            },
            error: (err) => {
                console.log(err);
            }
        });
    });

    $(document).on("click", "#createCommentSubmit", function () {
        if($('#exampleFormControlTextarea1').val() === "") {
            createErrorModal("комментарий пустой!");
        }else {
            let text = $('#exampleFormControlTextarea1').val();
            let id  = $('#exampleFormControlTextarea1').attr("data-id");
            $.ajax({
                url: '/createComment?text=' + text + '&bookId=' + id,
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
        }
    });

    $(document).on("click", "#createRequest", function () {
        let name = $('#formGroupExampleInput').val();
        let descr = $('#formGrouptextarea').val();
        let image;
        let formData = new FormData();
        if (window.FormData === undefined) {
            alert('В вашем браузере FormData не поддерживается')
            return;
        } else {
            image = $("#formGroupExampleInputimage")[0].files[0];
            formData.append('image', image);
        }
        let genres = $('#genres').val();
        let year = $('#year').val();
        let capacity = $('#capacity').val();
        let authors = $('#authors').val();
        let publisned = $('#publisned').val();
        let isbn = $('#isbn').val();
        if(isbn === "" || publisned === "" || authors === "" || capacity === "" || year === "" || genres === "" || descr === "" || name === "" || image === undefined) {
            createErrorModal("все поля должны быть заполнены!")
            return;
        }else {
            formData.append('isbn', isbn);
            formData.append('publisned', publisned);
            formData.append('authors', authors);
            formData.append('capacity', capacity);
            formData.append('year', year);
            formData.append('genres', genres);
            formData.append('descr', descr);
            formData.append('name', name);

            $.ajax({
                url: '/createRequest',
                type: 'POST',
                contentType: false,
                data: formData,
                processData: false,
                dataType: "text",
                success: (message) => {
                    $('#static_create_request_add').modal('hide');
                },
                error: (err) => {
                    console.log(err);
                }
            });
        }
    });

    $(document).on("click", "#ByISBNSubmit", function () {
        let isbn = $('#formGroupExampleInputIsbn').val();
        if(isbn === "") {
            createErrorModal("Введите ISBN!");
        }else {
            $.ajax({
                url: 'https://www.googleapis.com/books/v1/volumes?q=isbn:' + isbn,
                type: 'GET',
                contentType: "application/json; charset=utf-8",
                dataType: "text",
                success: (data) => {
                    console.log(JSON.parse(data));
                    let items = JSON.parse(data).items[0];
                    let value = items.volumeInfo;
                    console.log(value);

                    let author = value.authors[0];
                    let genres = value.categories[0];
                    let name = value.title;
                    let publisher = value.publisher;
                    let year = value.publishedDate;
                    let descr = value.description;
                    let capacity = value.pageCount;
                    let imageLink = value.imageLinks.thumbnail;
                    console.log(genres);
                    console.log(author);
                    console.log(name);
                    console.log(publisher);
                    console.log(year);
                    console.log(descr);
                    console.log(capacity);
                    console.log(imageLink);

                    $.ajax({
                        url: '/createByISBN?isbn=' + isbn + '&author=' + author + '&genres=' + genres + '&name=' + name + '&publisher=' + publisher + '&year=' + year + '&descr=' + descr + '&capacity=' + capacity,
                        type: 'POST',
                        contentType: "application/json; charset=utf-8",
                        data: JSON.stringify({"message": imageLink}),
                        dataType: "text",
                        success: (message) => {
                            console.log(JSON.parse(message).message);
                            if(JSON.parse(message).message === "Книга с таким ISBN уже существует!") {
                                createErrorModal("книга с таким ISBN уже есть в системе!");
                            }else {
                                location.reload();
                            }
                        },
                        error: (err) => {
                            console.log(err);
                        }
                    });
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