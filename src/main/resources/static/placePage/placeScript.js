$(document).ready(function () {
    //createPlace
    $(document).on("click", "#createPlaceSubmit", function () {
        let placeName = $('#formGroupExampleInputshelf').val();
        if(placeName === "") {
            $("#add_new_shelf").modal("hide");
            createErrorModal("все поля должны быть заполнены!");
        }else {
            let placeNameJson = {
                "message": placeName
            }
            $.ajax({
                url: '/createPlace',
                type: 'POST',
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(placeNameJson),
                dataType: "text",
                success: (res) => {
                    if(JSON.parse(res).message === 'false') {
                        createErrorModal("Такая группа уже существует");
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

    //changePlace
    $(document).on("click", "#changePlaceSubmit", function () {
        let placeId = $(this).attr('data-id');
        let placeNewName = $('#formGroupchange_shelf' + placeId).val();
        if(placeNewName === "") {
            createErrorModal("все поля должны быть заполнены!");
        }else {
            console.log(placeId, placeNewName);
            let name = {
                "message": placeNewName
            }
            $.ajax({
                url: '/changePlace?id=' + placeId,
                type: 'POST',
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(name),
                dataType: "text",
                success: (res) => {
                    if(JSON.parse(res).message === 'false') {
                        createErrorModal("Такое имя уже существует!");
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

    //deletePlace
    $(document).on("click", "#deletePlaceSubmit", function () {
        let placeId = $(this).attr('data-id');
        $.ajax({
            url: '/deletePlace?id=' + placeId,
            type: 'GET',
            contentType: "application/json; charset=utf-8",
            dataType: "text",
            success: (res) => {
                if(JSON.parse(res).message === 'false') {
                    createErrorModal("Группа не удалена");
                }else {
                    location.reload();
                }
            },
            error: (err) => {
                console.log(err);
            }
        });
    });

    //errorModal
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