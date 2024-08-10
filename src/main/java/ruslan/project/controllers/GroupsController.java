package ruslan.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ruslan.project.json.StringJson;
import ruslan.project.services.GroupService;

@Controller
public class GroupsController {
    private GroupService groupService;

    @Autowired
    public GroupsController(GroupService groupService) {
        this.groupService = groupService;
    }

    @RequestMapping(value = "/Groups", method = RequestMethod.GET)
    public String mainPageView(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        model.addAttribute("groups", groupService.getGroups(currentPrincipalName));
        return "groupsPage";
    }

    @RequestMapping("/createGroup")
    public @ResponseBody String createGroup(@RequestBody StringJson groupName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return String.format("{\"message\": \"%s\"}", groupService.createGroup(groupName.getMessage(), currentPrincipalName));
    }

    @RequestMapping("/changeGroup")
    public @ResponseBody String changeGroup(@RequestBody StringJson newName, @RequestParam("id") String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return String.format("{\"message\": \"%s\"}", groupService.changeGroup(currentPrincipalName, id, newName.getMessage()));
    }

    @RequestMapping(value = "/deleteGroup", method = RequestMethod.GET)
    public @ResponseBody String deleteGroup(@RequestParam("id") String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return String.format("{\"message\": \"%s\"}", groupService.deleteGroup(id, currentPrincipalName));
    }
}
