package project.library.controllers;


import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import project.library.dao.UserDao;
import project.library.entities.dto.UserDto;
import project.library.entities.login.User;
import project.library.mapper.Mapper;
import project.library.services.UserService;

@EnableWebMvc
@Controller
@RequestMapping(value = "/console")
public class AdminAccountsController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getAdminPage() {
        return "adminPage";
    }

    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public String getAccounts(Model model) {
        model.addAttribute("what", "accounts");
        model.addAttribute("accounts", userService.getUsers());
        return "adminPage";
    }

    @RequestMapping(value = "/accounts/edit/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable String id, Model model) {
        model.addAttribute("what", "accountForm");
        model.addAttribute("account", userService.getUserById(Long.parseLong(id)));
        return "adminPage";
    }

    @RequestMapping(value = "/account/change/{id}", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") UserDto userDto, @PathVariable String id, Model model) {
        userService.changeUser(Long.parseLong(id), userDto);
        return "redirect:/console/accounts";
    }

    @RequestMapping(value = "/account/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable String id, Model model) {
        userService.deleteUserById(Long.parseLong(id));
        return "redirect:/console/accounts";
    }
}
