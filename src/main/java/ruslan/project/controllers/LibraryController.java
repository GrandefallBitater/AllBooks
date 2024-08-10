package ruslan.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ruslan.project.services.libraryService;

@Controller
public class LibraryController {
    private final libraryService libraryService;

    @Autowired
    public LibraryController(libraryService libraryService) {
        this.libraryService = libraryService;
    }

    @RequestMapping("/createLibrary")
    public @ResponseBody String createLibrary(@RequestParam("placeId") String placeId,
                                            @RequestParam("groupId") String groupId,
                                            @RequestParam("status") String status,
                                            @RequestParam("bookId") String bookId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return String.format("{\"message\": \"%s\"}", libraryService.createLibrary(currentPrincipalName, placeId, groupId, status, bookId));
    }

    @RequestMapping("/deleteLibrary")
    public @ResponseBody String deleteLibrary(@RequestParam("id") String id) {
        return String.format("{\"message\": \"%s\"}", libraryService.deleteLibrary(Long.parseLong(id)));
    }

    @RequestMapping("/refreshLibrary")
    public @ResponseBody String refreshLibrary(@RequestParam("placeId") String placeId,
                                               @RequestParam("groupId") String groupId,
                                               @RequestParam("status") String status,
                                               @RequestParam("id") String id,
                                               @RequestParam("notes") String notes,
                                               @RequestParam("lastPage") String lastPage,
                                               @RequestParam("placeInfo") String placeInfo,
                                               @RequestParam("hidden") String hidden) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return String.format("{\"message\": \"%s\"}", libraryService.refreshLibrary(placeId, groupId, status,
                id, notes, lastPage, placeInfo, currentPrincipalName, hidden));
    }
}
