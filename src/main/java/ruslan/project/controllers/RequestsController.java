package ruslan.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import ruslan.project.models.RequestsModel;
import ruslan.project.services.RequestsService;

import java.util.List;

@Controller
public class RequestsController {
    private RequestsService requestsService;

    @Autowired
    public RequestsController(RequestsService requestsService) {
        this.requestsService = requestsService;
    }

    @RequestMapping("/createRequest")
    public @ResponseBody String createRequest(@RequestParam("isbn") String isbn,
                                              @RequestParam("publisned") String publisned,
                                              @RequestParam("authors") String authors,
                                              @RequestParam("capacity") String capacity,
                                              @RequestParam("year") String year,
                                              @RequestParam("genres") String genres,
                                              @RequestParam("descr") String descr,
                                              @RequestParam("name") String name,
                                              @RequestParam("image")MultipartFile image) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return String.format("{\"message\": \"%s\"}", requestsService.createRequest(isbn, publisned, authors, capacity,
                year, genres, descr, name, image, currentPrincipalName));
    }

    @RequestMapping("/getRequestsForUser")
    public String getRequestsForUser(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        model.addAttribute("requests", requestsService.getRequestsByUsername(currentPrincipalName));
        return "profilePage";
    }

    @RequestMapping("/getAllRequests")
    public String getAllRequests(Model model) {
        model.addAttribute("requests", requestsService.getAllRequests());
        return "requestsPage";
    }

    @RequestMapping("/denyRequest")
    public @ResponseBody String denyRequest(@RequestParam("id") String id) {
        return String.format("{\"message\": \"%s\"}", requestsService.deleteRequest(id));
    }

    @RequestMapping("/AcceptRequest")
    public @ResponseBody String AcceptRequest(@RequestParam("isbn") String isbn,
                                              @RequestParam("published") String published,
                                              @RequestParam("author") String authors,
                                              @RequestParam("capacity") String capacity,
                                              @RequestParam("year") String year,
                                              @RequestParam("genres") String genres,
                                              @RequestParam("descr") String descr,
                                              @RequestParam("name") String name,
                                              @RequestParam("id") String id) {
        return String.format("{\"message\": \"%s\"}", requestsService.createBookFromRequest(isbn, published, authors,
                capacity, year, genres, descr, name, id));
    }
}
