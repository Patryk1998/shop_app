package project.library.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import project.library.entities.dto.UserDto;
import project.library.services.UserService;


@EnableWebMvc
@Controller
@RequestMapping(value = "/library")
public class UserAccountController {

    @Autowired
    private UserService userService;

    private Long userId;

    @RequestMapping(value = "/account/password/{id}", method = RequestMethod.GET)
    public String getPasswordForm(@PathVariable String id, Model model) {
        userId = Long.parseLong(id);
        model.addAttribute("what", "changePassword");
        return "userPage";
    }

    @RequestMapping(value = "/account/password", method = RequestMethod.POST)
    public String changePassword(@ModelAttribute("user") UserDto userDto) {
        try {
            userService.changePassword(userId, userDto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/library";
    }

}
