package ruslan.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ruslan.project.json.StringJson;
import ruslan.project.services.ModalService;


@Controller
public class ModalController {

    private final ModalService modalService;

    @Autowired
    public ModalController(ModalService modalService) {
        this.modalService = modalService;
    }


    @RequestMapping(value = "/errorModal")
    public @ResponseBody String createErrorModal(@RequestBody StringJson message) {
        return modalService.createErrorModal(message.getMessage());
    }

    @RequestMapping(value = "/registerModal")
    public @ResponseBody String createRegisterModal() {
        return modalService.createRegisterModal();
    }

    @RequestMapping(value = "/loginModal")
    public @ResponseBody String createSignModal() {
        return modalService.createSignModal();
    }

    @RequestMapping(value = "/showCreateUserModal")
    public @ResponseBody String showCreateUserModal() {
        return modalService.createUserModal();
    }

    @RequestMapping(value = "/showDeleteUserModal")
    public @ResponseBody String showDeleteUserModal() {
        return modalService.createDeleteUserModal("вы точно хотите удалить этого пользователя?");
    }

    @RequestMapping(value = "/showChangeUserModal")
    public @ResponseBody String showChangeUserModal() {
        return modalService.createChangeUserModal();
    }

    @RequestMapping(value = "/showCreateClassModal")
    public @ResponseBody String showCreateClassModal() {
        return modalService.showCreateClassModal();
    }
}
