package project.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import project.library.dao.TitleDao;
import project.library.entities.login.User;
import project.library.entities.dto.UserDto;
import project.library.exceptions.LoginException;
import project.library.exceptions.RegistrationException;
import project.library.services.UserService;

import java.lang.management.GarbageCollectorMXBean;
import java.util.Optional;

@EnableWebMvc
@Controller
public class LoginController {


    @Autowired
    private UserService userService;

    @Autowired
    private TitleDao titleDao;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String getTest(Model model) {
        userService.createTestUsers();
        model.addAttribute("titles", titleDao.findAll());
        return "mainPage";
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String getLoginForm() {
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegistrationForm() { return "registration"; }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String createAccount(@ModelAttribute("user") UserDto userDto, Model model, BindingResult errors) {
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
