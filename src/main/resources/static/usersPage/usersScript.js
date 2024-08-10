$(document).ready(function () {
    let activeUsername = "";
    $(document).on("click", "#allRadioButton", function () {
        $("#tableBody").html("");
        $.ajax({
            url: '/getAllUsers',
            type: 'GET',
            contentType: "application/json; charset=utf-8",
            dataType: "text",
            success: (users) => {
                changeTableData(users);
            },
            error: (err) => {
                console.log(err);
            }
        });
    });

    $(document).on("click", "#usersRadioButton", function () {
        $("#tableBody").html("");
        $.ajax({
            url: '/getUsers',
            type: 'GET',
            contentType: "application/json; charset=utf-8",
            dataType: "text",
            success: (users) => {
                changeTableData(users);
            },
            error: (err) => {
                console.log(err);
            }
        });
    });

    $(document).on("click", "#adminRadioButton", function () {
        $("#tableBody").html("");
        $.ajax({
            url: '/getAdmins',
            type: 'GET',
            contentType: "application/json; charset=utf-8",
            dataType: "text",
            success: (users) => {
                changeTableData(users);
            },
            error: (err) => {
                console.log(err);
            }
        });
    });

    function changeTableData(users) {
        let UsersJSON = JSON.parse(users);
        for (let i = 0; i < UsersJSON.length; i++){
            let UserInfo = "<tr><td id=\"viewUserName\">" + UsersJSON[i].username + "</td>\n" +
                "          <td id=\"viewPassword\">" + UsersJSON[i].password + "</td>\n" +
                "          <td id=\"viewEnabled\" data-status = \"" + UsersJSON[i].authority + "\">" + UsersJSON[i].enabled + "</td>\n" +
                "          <td>\n" +
                "            <button class=\"btn btn-light\" type=\"button\" id=\"changeUserButton\">изменить</button>\n" +
                "          </td>\n" +
                "          <td>\n" +
                "            <button class=\"btn btn-light\" type=\"button\" id=\"deleteUserButton\">удалить</button>\n" +
                "          </td>"
            $("#tableBody").append(UserInfo);
        }
    }

    // create user
    $(document).on("click", "#createUser", function () {
        $.ajax({
            url: '/showCreateUserModal',
            type: 'GET',
            contentType: "application/json; charset=utf-8",
            dataType: "text",
            success: (modal) => {
                $('#localModal').html(modal);
                $("#createUserModal").modal("show");
            },
            error: (err) => {
                console.log(err);
            }
        });
    });

    $(document).on("click", "#closeCreateUserModal", function () {
        activeUsername = "";
        $("#createUserModal").modal("hide");
        $('#localModal').html("");
    });

    $(document).on("click", "#submitCreateUserModal", function () {
        let username = $("#createUserUsername").val();
        let password = $("#createUserPassword").val();
        if(username === "" || password === "") {
            $("#createUserModal").modal("hide");
            $('#localModal').html("");
            let errorMessage = {
                "message": "все поля должны быть заполнены!"
            }
            $.ajax({
                url: '/errorModal',
                type: 'POST',
                data: JSON.stringify(errorMessage),
                contentType: "application/json; charset=utf-8",
                dataType: "text",
                success: (message) => {
                    $('#localModal').html(message);
                    $("#errorModalCenter").modal("show");
                },
                error: (err) => {
                    console.log(err);
                }
            });
        }else {
            let user = {
                "username": username,
                "password": password,
                "enabled": $("#createUserEnabled").val(),
                "authority": $("#createUserStatus").val()
            }
            $.ajax({
                url: '/createUser',
                type: 'POST',
                contentType: "application/json; charset=utf-8",
                dataType: "text",
                data: JSON.stringify(user),
                success: (data) => {
                    $("#createUserModal").modal("hide");
                    $('#localModal').html("");
                },
                error: (err) => {
                    console.log(err);
                }
            });
        }
    });

    //change user
    $(document).on("click", "#changeUserButton", function () {
        let tr = $(this).parent().parent();
        $.ajax({
            url: '/showChangeUserModal',
            type: 'GET',
            contentType: "application/json; charset=utf-8",
            dataType: "text",
            success: (modal) => {
                $('#localModal').html(modal);
                activeUsername = tr.children("#viewUserName").text()
                $("#changeUserUsername").val(activeUsername);
                $("#changeUserPassword").val(tr.children("#viewPassword").text());
                let enabled = tr.children("#viewEnabled").text();
                $("#changeUserEnabled").val(enabled);
                let status = tr.children("#viewEnabled").attr("data-status");
                console.log(status);
                switch (status) {
                    case "ROLE_USER":
                        $('#changeUserStatus option:contains("Пользователь")').prop('selected', true);
                        break;
                    case "ROLE_MANAGER":
                        $('#changeUserStatus option:contains("Менеджер")').prop('selected', true);
                        break;
                    case "ROLE_ADMIN":
                        $('#changeUserStatus option:contains("Администратор")').prop('selected', true);
                        break;
                }
                $("#changeUserModal").modal("show");
            },
            error: (err) => {
                console.log(err);
            }
        });
    });

    $(document).on("click", "#closeChangeUserModal", function () {
        activeUsername = "";
        $("#changeUserModal").modal("hide");
        $('#localModal').html("");
    });

    $(document).on("click", "#submitChangeUserModal", function () {
        let username = $("#changeUserUsername").val();
        let password = $("#changeUserPassword").val();
        if(username === "" || password === "") {
            $("#changeUserModal").modal("hide");
            $('#localModal').html("");
            let errorMessage = {
                "message": "все поля должны быть заполнены!"
            }
            $.ajax({
                url: '/errorModal',
                type: 'POST',
                data: JSON.stringify(errorMessage),
                contentType: "application/json; charset=utf-8",
                dataType: "text",
                success: (message) => {
                    $('#localModal').html(message);
                    $("#errorModalCenter").modal("show");
                },
                error: (err) => {
                    console.log(err);
                }
            });
        }else {
            let user = {
                "username": username,
                "password": password,
                "enabled": $("#changeUserEnabled").val(),
                "authority": $("#changeUserStatus").val()
            }
            $.ajax({
                url: '/changeUser?oldUsername=' + activeUsername,
                type: 'POST',
                contentType: "application/json; charset=utf-8",
                dataType: "text",
                data: JSON.stringify(user),
                success: (data) => {
                    activeUsername = "";
                    $("#changeUserModal").modal("hide");
                    $('#localModal').html("");
                    location.reload();
                },
                error: (err) => {
                    console.log(err);
                }
            });
        }
    });

    // delete user
    $(document).on("click", "#deleteUserButton", function () {
        $.ajax({
            url: '/showDeleteUserModal',
            type: 'GET',
            contentType: "application/json; charset=utf-8",
            dataType: "text",
            success: (modal) => {
                let tr = $(this).parent().parent();
                activeUsername = tr.children("#viewUserName").text();
                $('#localModal').html(modal);
                $("#messageModalCenter").modal("show");
            },
            error: (err) => {
                console.log(err);
            }
        });
    });

    $(document).on("click", "#messageModalCenterCloseButton", function () {
        activeUsername = "";
        $("#messageModalCenter").modal("hide");
        $('#localModal').html("");
    });

    $(document).on("click", "#messageModalCenterSubmitButton", function () {
        $.ajax({
            url: '/deleteUser?username=' + activeUsername,
            type: 'GET',
            contentType: "application/json; charset=utf-8",
            dataType: "text",
            success: (data) => {
                activeUsername = "";
                $("#messageModalCenter").modal("hide");
                $('#localModal').html("");
                location.reload();
            },
            error: (err) => {
                console.log(err);
            }
        });
    });
});