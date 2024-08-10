package ruslan.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ruslan.project.services.HtmlService;

@Controller
public class MainPageController {
    private final HtmlService HtmlService;

    @Autowired
    public MainPageController(HtmlService HtmlService) {
        this.HtmlService = HtmlService;
    }

    @RequestMapping("/")
    public String mainPageView(Model model) {
        return "mainPage";
    }

    @RequestMapping(value = "/greetingsPageHtml")
    public @ResponseBody String getGreetingsPageHtml() {
        return HtmlService.getGreetingsPageHtml();
    }
}
