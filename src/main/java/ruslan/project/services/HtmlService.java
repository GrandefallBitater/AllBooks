package ruslan.project.services;

import org.springframework.stereotype.Service;

@Service
public class HtmlService {
    private final String greetingsPageHtml = "<h5 style=\"border: solid; width: 100%; text-align: center\" class=\"container-fluid\">allBooks - это</h5>\n" +
            "    <div style=\"height: 900px; width: 1300px\">\n" +
            "        <div id=\"carouselExampleCaptions\" class=\"carousel slide\">\n" +
            "            <div class=\"carousel-indicators\">\n" +
            "                <button type=\"button\" data-bs-target=\"#carouselExampleCaptions\" data-bs-slide-to=\"0\" class=\"active\" aria-current=\"true\" aria-label=\"Slide 1\"></button>\n" +
            "                <button type=\"button\" data-bs-target=\"#carouselExampleCaptions\" data-bs-slide-to=\"1\" aria-label=\"Slide 2\"></button>\n" +
            "                <button type=\"button\" data-bs-target=\"#carouselExampleCaptions\" data-bs-slide-to=\"2\" aria-label=\"Slide 3\"></button>\n" +
            "            </div>\n" +
            "            <div class=\"carousel-inner\">\n" +
            "                <div class=\"carousel-item active\">\n" +
            "                    <img src=\"images/первый.jpg\" class=\"d-block w-100\" alt=\"описание интерфейса\">\n" +
            "                    <div class=\"carousel-caption d-none d-md-block\">\n" +
            "                        <h5>Приятный инерфейс</h5>\n" +
            "                        <p>согласен</p>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <div class=\"carousel-item\">\n" +
            "                    <img src=\"images/второй.jpg\" class=\"d-block w-100\" alt=\"описание приоритетов\">\n" +
            "                    <div class=\"carousel-caption d-none d-md-block\">\n" +
            "                        <h5>Приятный инерфейс</h5>\n" +
            "                        <p>согласен</p>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <div class=\"carousel-item\">\n" +
            "                    <img src=\"images/третий.jpg\" class=\"d-block w-100\" alt=\"описание функционала\">\n" +
            "                    <div class=\"carousel-caption d-none d-md-block\">\n" +
            "                        <h5>Приятный инерфейс</h5>\n" +
            "                        <p>согласен</p>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "            <button class=\"carousel-control-prev\" type=\"button\" data-bs-target=\"#carouselExampleCaptions\" data-bs-slide=\"prev\">\n" +
            "                <span class=\"carousel-control-prev-icon\" aria-hidden=\"true\"></span>\n" +
            "                <span class=\"visually-hidden\">Предыдущий</span>\n" +
            "            </button>\n" +
            "            <button class=\"carousel-control-next\" type=\"button\" data-bs-target=\"#carouselExampleCaptions\" data-bs-slide=\"next\">\n" +
            "                <span class=\"carousel-control-next-icon\" aria-hidden=\"true\"></span>\n" +
            "                <span class=\"visually-hidden\">Следующий</span>\n" +
            "            </button>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "    <div style=\"height: auto\">\n" +
            "        <div sec:authorize=\"isAuthenticated()\">\n" +
            "            <h5 style=\"text-align: center; border: black; width: auto\">Вы успешно вошли как: <span sec:authentication=\"name\"></span></h5>\n" +
            "            <button type=\"button\" class=\"btn btn-primary\" id=\"goToLibraryButton\">Управление библиотекой</button>\n" +
            "        </div>\n" +
            "        <div sec:authorize=\"hasRole('ROLE_ANONYMOUS')\" style=\"margin-top: 20px\">\n" +
            "            <h5 style=\"text-align: center; border: black; width: auto\">Для того, чтобы начать учёт своей библиотеки войдите в учётную запись</h5>\n" +
            "            <form th:action=\"@{/login}\" method=\"post\">\n" +
            "                <div><label> User Name : <input type=\"text\" name=\"username\"/> </label></div>\n" +
            "                <div><label> Password: <input type=\"password\" name=\"password\"/> </label></div>\n" +
            "                <button type=\"submit\" class=\"btn btn-primary\" id=\"loginButton\">Войти</button>\n" +
            "            </form>\n" +
            "            <div style=\"display: inline-block\">\n" +
            "                <p>Не имеете учётной записи? Создайте её!</p>\n" +
            "                <button type=\"button\" class=\"btn btn-secondary\" id=\"registerButton\">Зарегестрироваться</button>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </div>";

    public String getGreetingsPageHtml() {;
        return greetingsPageHtml;
    }
}
