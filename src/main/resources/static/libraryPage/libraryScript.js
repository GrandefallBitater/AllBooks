$(document).ready(function () {
    $('#dropdownMenuButton1').dropdown();
    //create Class
    $(document).on("click", "#addRadioButton", function () {
        $.ajax({
            url: '/showCreateClassModal',
            type: 'GET',
            contentType: "application/json; charset=utf-8",
            dataType: "text",
            success: (modal) => {
                console.log(1);
                $('#localModal').html(modal);
                $("#createClassModal").modal("show");
            },
            error: (err) => {
                console.log(err);
            }
        });
    });

    $(document).on('click' , "#closeCreateClassModal", function () {
        $("#createClassModal").modal("hide");
        $('#localModal').html("");
    });

    $(document).on('click' , "#submitCreateClassModal", function () {
        let name = $('#createClassName').val();
        if (name === "") {
            $("#createClassModal").modal("hide");
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
            let className = {
                "message": name
            }
            $.ajax({
                url: '/createClass',
                type: 'POST',
                data: JSON.stringify(className),
                contentType: "application/json; charset=utf-8",
                dataType: "text",
                success: (message) => {
                    console.log(JSON.stringify({"message": message}));
                    if(message === "false") {
                        let errorMessage = {
                            "message": "такая группа уже существует"
                        }
                        $.ajax({
                            url: '/errorModal',
                            type: 'POST',
                            data: JSON.stringify(errorMessage),
                            contentType: "application/json; charset=utf-8",
                            dataType: "text",
                            success: (message) => {
                                $("#createClassModal").modal("hide");
                                $('#localModal').html(message);
                                $("#errorModalCenter").modal("show");
                            },
                            error: (err) => {
                                console.log(err);
                            }
                        });
                    } else {
                        location.reload();
                    }
                },
                error: (err) => {
                    console.log(err);
                }
            });
        }
    });

    //load classes
    $.ajax({
        url: '/getClasses',
        type: 'GET',
        contentType: "application/json; charset=utf-8",
        dataType: "text",
        success: (classesData) => {
            let classes = JSON.parse(classesData);
            $('#classeslist').append("<div><button id=\"All\">Все</button>");
            for(let i = 0; i < classes.length; i++) {
                let str = "<div><button id=\'classButton\'>" + classes[i] + "</button>"
                $('#classeslist').append(str);
            }
        },
        error: (err) => {
            console.log(err);
        }
    });

    $(document).on('click', "#classButton", function () {
        viewBooksForClass($(this).val());
    });

    function viewBooksForClass(ClassName) {
        $.ajax({
            url: '/getLibraresForClass',
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify("{message: " + ClassName + "}"),
            dataType: "text",
            success: (classesData) => {
                $('.tbl-content').html("");
                console.log(JSON.parse(classesData));
                // $('.tbl-content').append("<div class=\"card\" style=\"width: 18rem; display: inline-block\">\n" +
                //     "            <img src="userImages/ "" + "class=\"card-img-top\" th:alt=\"${book.getBookId().getName()}\">\n" +
                //     "            <div class=\"card-body\">\n" +
                //     "                <h5 class=\"card-title\" th:text = \"${book.getBookId().getName()}\"></h5>\n" +
                //     "                <a href=\"#\" id=\"moreAboutBook\" th:data-bookId = \"${book.getId()}\" class=\"btn btn-primary\">Подробней</a>\n" +
                //     "            </div>\n" +
                //     "        </div>");
            },
            error: (err) => {
                console.log(err);
            }
        });
    }
});