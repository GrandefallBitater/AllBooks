package ruslan.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ruslan.project.customClasses.UserForAdminCustom;
import ruslan.project.services.UserService;

import java.util.ArrayList;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/users")
    public String getUsersPage(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "usersPage";
    }

    @RequestMapping("/getAllUsers")
    public @ResponseBody ArrayList<UserForAdminCustom> getAllUsers() {
        return userService.findAllUsers();
    }

    @RequestMapping("/getUsers")
    public @ResponseBody ArrayList<UserForAdminCustom> getUsers() {
        return userService.findUsers();
    }

    @RequestMapping("/getAdmins")
    public @ResponseBody ArrayList<UserForAdminCustom> getAdmins() {
        return userService.findAdmins();
    }

    @RequestMapping("/deleteUser")
    public @ResponseBody String deleteUser(@RequestParam("username") String username) {
        return userService.deleteUser(username);
    }

    @RequestMapping("/changeUser")
    public @ResponseBody String changeUser(@RequestBody UserForAdminCustom user, @RequestParam("oldUsername") String oldUsername)  {
        return userService.changeUser(user, oldUsername);
    }

    @RequestMapping("/createUser")
    public @ResponseBody String createUser(@RequestBody UserForAdminCustom user) {
        return userService.createUser(user);
    }

    @RequestMapping("/friends")
    public String getFriendsPage() {
        return "friendsPage";
    }
}
