package project.library.controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.hibernate.validator.constraints.ModCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import project.library.entities.User;
import project.library.entities.dto.UserDto;
import project.library.exceptions.LoginException;
import project.library.exceptions.RegistrationException;
import project.library.services.UserService;

import java.util.Optional;
import java.util.logging.Logger;

@EnableWebMvc
@Controller
@RequestMapping(value = "/user")
public class LoginController {


    @Autowired
    private UserService userService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String getTest(Model model) {
        userService.createTestUsers();
        Optional<User> user = userService.getUserByUsername("asd");
        model.addAttribute("roles", user.get().getRole());
        return "mainPage";
    }

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public String getTest1(Model model) {
        Optional<User> user = userService.getUserByUsername("asd");
        model.addAttribute("roles", user.get().getRole());
        return "mainPage";
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public String getTest2() {

        return "test";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginForm() {
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegistrationForm() { return "registration"; }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute(name = "user") UserDto userDto, Model model, BindingResult errors) {

        try{
            if(userService.login(userDto)) {
                return "mainPage";   /// w tym miejscu przenosimy sie do bilbioteki. Zastanowić się nad tym czy tego rzucania wyjątków nie da sie lepiej rozwiazac, bo sama logike juz mam
            }
        } catch (LoginException e) {
            model.addAttribute("failureInfo", e.getMessage());
            return "login";
        }
        return "";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String createAccount(@ModelAttribute(name = "user") UserDto userDto, Model model, BindingResult errors) {
        try{
            if(userService.addNewAccount(userDto)) {
                model.addAttribute("ifGood", true);
                return "registration";
            }
        } catch (RegistrationException e){
            model.addAttribute("ifGood", false);
            model.addAttribute("failureInfo", e.getMessage());
            return "registration";
        }
        return "";
    }
}
