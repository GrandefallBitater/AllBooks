package ruslan.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ruslan.project.json.UserJson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class loginController {

    @RequestMapping(value = "/loginForModal")
    public @ResponseBody String createSignModal(@RequestBody UserJson user,
                                                    HttpServletRequest request, HttpServletResponse response) {
        try {
            request.login(user.getUsername(), user.getPassword());
            return "{\"status\": true}";
        } catch (Exception e) {
            return "{\"status\": false, \"error\": \"Bad Credentials\"}";
        }
    }
}
