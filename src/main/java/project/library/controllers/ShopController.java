package project.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import project.library.services.ShopService;

@EnableWebMvc
@Controller
@RequestMapping(value = "/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @RequestMapping(value = "/shop", method = RequestMethod.GET)
    public String getReadOnlyShop(Model model) {
        model.addAttribute("categories", shopService.getCategories());
        model.addAttribute("products", shopService.getProducts());
        return "mainPage";
    }
}
