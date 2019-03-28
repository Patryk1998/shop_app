package project.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import project.library.services.BookService;
import project.library.services.MyUserDetails;
import project.library.services.RentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;

@EnableWebMvc
@Controller
@RequestMapping(value = "/library")
public class MainController {

    private Long userId;

    @Autowired
    private BookService bookService;

    @Autowired
    private RentService rentService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getTitles(Model model, HttpServletRequest request) {
        userId = rentService.getUserIdFromSession(request);
        model.addAttribute("titles", bookService.getAllTitlesDto(false));
        return "mainPage";
    }

    @RequestMapping(value = "/book/rent/{id}", method = RequestMethod.GET)
    public String rentBook(@PathVariable String titleId, Model model) {
        try {
            rentService.rentBook(Long.parseLong(titleId), userId);
        } catch (Exception e) {
            e.getMessage();
        }
        return "mainPage";
    }

    @RequestMapping(value = "/book/back/{id}", method = RequestMethod.GET)
    public String backBook(@PathVariable String rentId, Model model) {
        rentService.backBook(Long.parseLong(rentId), userId);
        return "mainPage";
    }
}