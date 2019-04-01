package project.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import project.library.services.BookService;
import project.library.services.RentService;

import javax.servlet.http.HttpServletRequest;

@EnableWebMvc
@Controller
@RequestMapping(value = "/library")
public class UserMainController {
    //DELETE  FROM USERS where USER_ID = 25
    private Long userId;

    @Autowired
    private BookService bookService;

    @Autowired
    private RentService rentService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getTitles(Model model, HttpServletRequest request) {
        if(userId==null) userId = rentService.getUserIdFromSession(request);
        model.addAttribute("what", "books");
        model.addAttribute("titles", bookService.getAllTitlesDto(false));
        model.addAttribute("userId", userId);
        return "userPage";
    }

    @RequestMapping(value = "/rents", method = RequestMethod.GET)
    public String backBook(Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("what", "rents");
        model.addAttribute("rents", rentService.getUserRentsDto(userId));
        return "userPage";
    }

    @RequestMapping(value = "/book/rent/{id}", method = RequestMethod.GET)
    public String rentBook(@PathVariable String id, Model model) {
        try {
            rentService.rentBook(Long.parseLong(id), userId);
        } catch (Exception e) {
            e.getMessage();
        }
        return "redirect:/library";
    }

    @RequestMapping(value = "/book/back/{id}", method = RequestMethod.GET)
    public String backBook(@PathVariable String id, Model model) {
        userId = rentService.backBook(Long.parseLong(id), userId);
        return "redirect:/library/rents";
    }
}