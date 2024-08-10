$(document).ready(function () {

    let status = checkPageStatus();
    replaceHtmlForStatus(status);

    //error
    $(document).on("click", "#errorModalCenterCloseButton", function () {
        $("#errorModalCenter").modal("hide");
        $('#localModal').html("");
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
                $('#localModal').html(message);
                $("#errorModalCenter").modal("show");
            },
            error: (err) => {
                console.log(err);
            }
        });
    }

    //register
    $(document).on("click", "#registerButton", function () {
        $('#localModal').html("");
        createRegisterModal();
    });

    $(document).on("click", "#closeRegisterModal", function () {
        $("#registerModal").modal("hide");
        $('#localModal').html("");
    });

    function createRegisterModal() {
        $.ajax({
            url: '/registerModal',
            type: 'GET',
            contentType: "application/json; charset=utf-8",
            dataType: "text",
            success: (modal) => {
                $('#localModal').html(modal);
                $("#registerModal").modal("show");
            },
            error: (err) => {
                console.log(err);
            }
        });
    }

    //sign
    $(document).on("click", "#loginButton", function () {
        $('#localModal').html("");
        createLoginModal();
    });

    $(document).on("click", "#closeLoginModal", function () {
        $("#loginModal").modal("hide");
        $('#localModal').html("");
    });

    function createLoginModal() {
        $.ajax({
            url: '/loginModal',
            type: 'GET',
            contentType: "application/json; charset=utf-8",
            dataType: "text",
            success: (modal) => {
                $('#localModal').html(modal);
                $("#loginModal").modal("show");
            },
            error: (err) => {
                console.log(err);
            }
        });
    }

    $(document).on("click", "#submitLoginModal", function () {
        let user = {
            "username": $("#loginUsername").val(),
            "password": $("#loginPassword").val()
        }
        $.ajax({
            url: '/loginForModal',
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(user),
            dataType: "text",
            success: (status) => {
                let login = JSON.parse(status);
                if(login.status === false){
                    $("#loginModal").modal("hide");
                    $('#localModal').html("");
                    createErrorModal("Ведены неверные данные или не все поля заполнены")
                }else {
                    location.reload();
                }
            },
            error: (err) => {
                console.log(err);
            }
        });
    });

    //check status url and replace Result block
    function checkPageStatus() {
        let params = (new URL(document.location)).searchParams;
        let status = params.get("status");
        return status;
    }

    function replaceHtmlForStatus(status) {
        switch (status){
            case null:
                break;
            case "test":
                createErrorModal("test");
                break;
        }
    }

    //usersTableScript
    $(window).on("load resize ", function() {
        var scrollWidth = $('.tbl-content').width() - $('.tbl-content table').width();
        $('.tbl-header').css({'padding-right':scrollWidth});
    }).resize();

    $(document).on("click", "#goToLibraryButton", function () {
        location.replace("/myLibrary");
    });
});