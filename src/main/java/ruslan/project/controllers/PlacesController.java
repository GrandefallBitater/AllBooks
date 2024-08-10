package ruslan.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ruslan.project.json.StringJson;
import ruslan.project.services.GroupService;
import ruslan.project.services.PlaceService;

@Controller
public class PlacesController {
    private PlaceService placeService;

    @Autowired
    public PlacesController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @RequestMapping("/Places")
    public String mainPageView(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        model.addAttribute("places", placeService.getPlaces(currentPrincipalName));
        return "placesPage";
    }

    @RequestMapping("/createPlace")
    public @ResponseBody String createGroup(@RequestBody StringJson placeName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return String.format("{\"message\": \"%s\"}", placeService.createPlace(placeName.getMessage(), currentPrincipalName));
    }

    @RequestMapping("/changePlace")
    public @ResponseBody String changeGroup(@RequestBody StringJson newName, @RequestParam("id") String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return String.format("{\"message\": \"%s\"}", placeService.changePlace(currentPrincipalName, id, newName.getMessage()));
    }

    @RequestMapping(value = "/deletePlace", method = RequestMethod.GET)
    public @ResponseBody String deleteGroup(@RequestParam("id") String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return String.format("{\"message\": \"%s\"}", placeService.deletePlace(id, currentPrincipalName));
    }
}
