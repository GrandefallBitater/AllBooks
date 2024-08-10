$(document).ready(function () {
    //createGroup
    $(document).on("click", "#createGroupSubmit", function () {
        let groupName = $('#InputGroupName').val();
        if(groupName === "") {
            $("#add_new_groups").modal("hide");
            createErrorModal("все поля должны быть заполнены!");
        }else {
            let groupNameJson = {
                "message": groupName
            }
            $.ajax({
                url: '/createGroup',
                type: 'POST',
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(groupNameJson),
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

    //changeGroup
    $(document).on("click", "#changeGroupSubmit", function () {
        let groupId = $(this).attr('data-id');
        let groupNewName = $('#formGroupchange_groups' + groupId).val();
        if(groupNewName === "") {
            createErrorModal("все поля должны быть заполнены!");
        }else {
            console.log(groupId, groupNewName);
            let name = {
                "message": groupNewName
            }
            $.ajax({
                url: '/changeGroup?id=' + groupId,
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

    //delete
    $(document).on("click", "#deleteGroupSubmit", function () {
        let groupId = $(this).attr('data-id');
        $.ajax({
            url: '/deleteGroup?id=' + groupId,
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