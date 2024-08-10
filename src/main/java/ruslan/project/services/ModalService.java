package ruslan.project.services;

import org.springframework.stereotype.Service;

@Service
public class ModalService {
    private String errorModal = "<div class=\"modal fade\" id=\"errorModalCenter\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"errorModalCenterTitle\" aria-hidden=\"true\">\n" +
            "  <div class=\"modal-dialog modal-dialog-centered\" role=\"document\">\n" +
            "    <div class=\"modal-content\">\n" +
            "      <div class=\"modal-header\">\n" +
            "        <h5 class=\"modal-title\" id=\"errorModalLongTitle\">ошибка</h5>\n" +
            "        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\" id=\"errorModalCenterCloseButton\">\n" +
            "          <span aria-hidden=\"true\">&times;</span>\n" +
            "        </button>\n" +
            "      </div>\n" +
            "      <div class=\"modal-body\">\n" +
            "        <p id=\"errorMessage\">%s</p>\n" +
            "      </div>\n" +
            "      <div class=\"modal-footer\">\n" +
            "        <button type=\"button\" class=\"btn btn-primary\" id=\"errorModalCenterCloseButton\">ok</button>\n" +
            "      </div>\n" +
            "    </div>\n" +
            "  </div>\n" +
            "</div>";

    private String registerModal = "<div class=\"modal fade\" id=\"registerModal\" tabindex=\"-1\" aria-labelledby=\"registerModalLabel\" aria-hidden=\"true\">\n" +
            "        <div class=\"modal-dialog\">\n" +
            "            <div class=\"modal-content\">\n" +
            "                <div class=\"modal-header\">\n" +
            "                    <h1 class=\"modal-title fs-5\" id=\"registerModalLabel\">Регистрация</h1>\n" +
            "                    <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" id=\"closeRegisterModal\" aria-label=\"Закрыть\"></button>\n" +
            "                </div>\n" +
            "                <div class=\"modal-body\">\n" +
            "                    <form>\n" +
            "                        <div class=\"mb-3\">\n" +
            "                            <label for=\"registerUsername\" class=\"col-form-label\">Логин:</label>\n" +
            "                            <input type=\"text\" class=\"form-control\" id=\"registerUsername\">\n" +
            "                        </div>\n" +
            "                        <div class=\"mb-3\">\n" +
            "                            <label for=\"registerPassword\" class=\"col-form-label\">Пароль:</label>\n" +
            "                            <input type=\"text\" class=\"form-control\" id=\"registerPassword\">\n" +
            "                        </div>\n" +
            "                    </form>\n" +
            "                </div>\n" +
            "                <div class=\"modal-footer\">\n" +
            "                    <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\" id=\"closeRegisterModal\">Закрыть</button>\n" +
            "                    <button type=\"button\" class=\"btn btn-primary\">Зарегестрироваться</button>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </div>";

    private String signModal = "<div class=\"modal fade\" id=\"loginModal\" tabindex=\"-1\" aria-labelledby=\"loginModalLabel\" aria-hidden=\"true\">\n" +
            "        <div class=\"modal-dialog\">\n" +
            "            <div class=\"modal-content\">\n" +
            "                <div class=\"modal-header\">\n" +
            "                    <h1 class=\"modal-title fs-5\" id=\"loginModalLabel\">Войти</h1>\n" +
            "                    <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" id=\"closeLoginModal\" aria-label=\"Закрыть\"></button>\n" +
            "                </div>\n" +
            "                <div class=\"modal-body\">\n" +
            "                    <form>\n" +
            "                        <div class=\"mb-3\">\n" +
            "                            <label for=\"loginUsername\" class=\"col-form-label\">Логин:</label>\n" +
            "                            <input type=\"text\" class=\"form-control\" id=\"loginUsername\">\n" +
            "                        </div>\n" +
            "                        <div class=\"mb-3\">\n" +
            "                            <label for=\"loginPassword\" class=\"col-form-label\">Пароль:</label>\n" +
            "                            <input type=\"password\" class=\"form-control\" id=\"loginPassword\">\n" +
            "                        </div>\n" +
            "                    </form>\n" +
            "                </div>\n" +
            "                <div class=\"modal-footer\">\n" +
            "                    <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\" id=\"closeLoginModal\">Закрыть</button>\n" +
            "                    <button type=\"button\" class=\"btn btn-primary\" id=\"submitLoginModal\">Войти</button>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </div>";
    private String createUserModal = "<div class=\"modal fade\" id=\"createUserModal\" tabindex=\"-1\" aria-labelledby=\"createUserModalLabel\" aria-hidden=\"true\">\n" +
            "        <div class=\"modal-dialog\">\n" +
            "            <div class=\"modal-content\">\n" +
            "                <div class=\"modal-header\">\n" +
            "                    <h1 class=\"modal-title fs-5\" id=\"createUserModalLabel\">Создать пользователя</h1>\n" +
            "                    <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" id=\"closeCreateUserModal\" aria-label=\"Закрыть\"></button>\n" +
            "                </div>\n" +
            "                <div class=\"modal-body\">\n" +
            "                    <form>\n" +
            "                        <div class=\"mb-3\">\n" +
            "                            <label for=\"createUserUsername\" class=\"col-form-label\">Логин:</label>\n" +
            "                            <input type=\"text\" class=\"form-control\" id=\"createUserUsername\">\n" +
            "                        </div>\n" +
            "                        <div class=\"mb-3\">\n" +
            "                            <label for=\"createUserPassword\" class=\"col-form-label\">Пароль:</label>\n" +
            "                            <input type=\"text\" class=\"form-control\" id=\"createUserPassword\">\n" +
            "                        </div>\n" +
                                        "<div class=\"mb-3\">\n" +
            "                            <label for=\"createUserEnabled\" class=\"col-form-label\">Активен:</label>\n" +
                                        "<select id= \"createUserEnabled\">\n" +
                                            "<option value=\"true\" selected>Да</option>\n" +
                                            "<option value=\"false\">Нет</option>\n" +
                                        "</select>\n" +
            "                        </div>\n" +
                                        "<div class=\"mb-3\">\n" +
            "                            <label for=\"createUserStatus\" class=\"col-form-label\">Роль:</label>\n" +
                                        "<select id= \"createUserStatus\">\n" +
                                            "<option value=\"user\" selected>Пользователь</option>\n" +
                                            "<option value=\"manager\">Менеджер</option>\n" +
                                            "<option value=\"admin\">Администратор</option>\n" +
                                        "</select>\n" +
            "                        </div>\n" +
            "                    </form>\n" +
            "                </div>\n" +
            "                <div class=\"modal-footer\">\n" +
            "                    <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\" id=\"closeCreateUserModal\">Закрыть</button>\n" +
            "                    <button type=\"button\" class=\"btn btn-primary\" id=\"submitCreateUserModal\">Создать</button>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </div>";

    private String changeUserModal = "<div class=\"modal fade\" id=\"changeUserModal\" tabindex=\"-1\" aria-labelledby=\"changeUserModalLabel\" aria-hidden=\"true\">\n" +
            "        <div class=\"modal-dialog\">\n" +
            "            <div class=\"modal-content\">\n" +
            "                <div class=\"modal-header\">\n" +
            "                    <h1 class=\"modal-title fs-5\" id=\"changeUserModalLabel\">Изменить пользователя</h1>\n" +
            "                    <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" id=\"closeChangeUserModal\" aria-label=\"Закрыть\"></button>\n" +
            "                </div>\n" +
            "                <div class=\"modal-body\">\n" +
            "                    <form>\n" +
            "                        <div class=\"mb-3\">\n" +
            "                            <label for=\"changeUserUsername\" class=\"col-form-label\">Логин:</label>\n" +
            "                            <input type=\"text\" class=\"form-control\" id=\"changeUserUsername\">\n" +
            "                        </div>\n" +
            "                        <div class=\"mb-3\">\n" +
            "                            <label for=\"changeUserPassword\" class=\"col-form-label\">Пароль:</label>\n" +
            "                            <input type=\"text\" class=\"form-control\" id=\"changeUserPassword\">\n" +
            "                        </div>\n" +
                                        "<div class=\"mb-3\">\n" +
                                        "<label for=\"changeUserEnabled\" class=\"col-form-label\">Активен:</label>\n" +
                                            "<select id= \"changeUserEnabled\">\n" +
                                                "<option value=\"true\" selected>Да</option>\n" +
                                                "<option value=\"false\">Нет</option>\n" +
                                            "</select>\n" +
                                        "                        </div>\n" +
                                        "<div class=\"mb-3\">\n" +
                                        "<label for=\"changeUserStatus\" class=\"col-form-label\">Роль:</label>\n" +
                                            "<select id= \"changeUserStatus\">\n" +
                                                "<option value=\"user\" selected>Пользователь</option>\n" +
                                                "<option value=\"manager\">Менеджер</option>\n" +
                                                "<option value=\"admin\">Администратор</option>\n" +
                                            "</select>\n" +
            "                        </div>\n" +
            "                    </form>\n" +
            "                </div>\n" +
            "                <div class=\"modal-footer\">\n" +
            "                    <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\" id=\"closeChangeUserModal\">Закрыть</button>\n" +
            "                    <button type=\"button\" class=\"btn btn-primary\" id=\"submitChangeUserModal\">Изменить</button>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </div>";

    private String questionModal = "<div class=\"modal fade\" id=\"messageModalCenter\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"messageModalCenterTitle\" aria-hidden=\"true\">\n" +
            "  <div class=\"modal-dialog modal-dialog-centered\" role=\"document\">\n" +
            "    <div class=\"modal-content\">\n" +
            "      <div class=\"modal-header\">\n" +
            "        <h5 class=\"modal-title\" id=\"messageModalLongTitle\">Подождите!</h5>\n" +
            "        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\" id=\"messageModalCenterCloseButton\">\n" +
            "          <span aria-hidden=\"true\">&times;</span>\n" +
            "        </button>\n" +
            "      </div>\n" +
            "      <div class=\"modal-body\">\n" +
            "        <p id=\"Message\">%s</p>\n" +
            "      </div>\n" +
            "      <div class=\"modal-footer\">\n" +
            "        <button type=\"button\" class=\"btn btn-danger\" id=\"messageModalCenterCloseButton\">Отмена</button>\n" +
            "        <button type=\"button\" class=\"btn btn-primary\" id=\"messageModalCenterSubmitButton\">Да</button>\n" +
            "      </div>\n" +
            "    </div>\n" +
            "  </div>\n" +
            "</div>";

    private String createClassModal = "<div class=\"modal fade\" id=\"createClassModal\" tabindex=\"-1\" aria-labelledby=\"createClassModalLabel\" aria-hidden=\"true\">\n" +
            "        <div class=\"modal-dialog\">\n" +
            "            <div class=\"modal-content\">\n" +
            "                <div class=\"modal-header\">\n" +
            "                    <h1 class=\"modal-title fs-5\" id=\"createClassModalLabel\">Создать группу</h1>\n" +
            "                    <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" id=\"closeCreateClassModal\" aria-label=\"Закрыть\"></button>\n" +
            "                </div>\n" +
            "                <div class=\"modal-body\">\n" +
            "                    <form>\n" +
            "                        <div class=\"mb-3\">\n" +
            "                            <label for=\"createClassName\" class=\"col-form-label\">Название:</label>\n" +
            "                            <input type=\"text\" class=\"form-control\" id=\"createClassName\">\n" +
            "                        </div>\n" +
            "                    </form>\n" +
            "                </div>\n" +
            "                <div class=\"modal-footer\">\n" +
            "                    <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\" id=\"closeCreateClassModal\">Закрыть</button>\n" +
            "                    <button type=\"button\" class=\"btn btn-primary\" id=\"submitCreateClassModal\">Создать</button>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </div>";
    public String createErrorModal(String message) {;
        return String.format(errorModal,message);
    }

    public String createRegisterModal() {;
        return registerModal;
    }

    public String createSignModal() {;
        return signModal;
    }

    public String createUserModal() {;
        return createUserModal;
    }

    public String createChangeUserModal() {;
        return changeUserModal;
    }

    public String createDeleteUserModal(String message) {;
        return String.format(questionModal, message);
    }

    public String showCreateClassModal() {
        return createClassModal;
    }
}
