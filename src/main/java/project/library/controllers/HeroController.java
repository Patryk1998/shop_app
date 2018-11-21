package project.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.library.entities.Hero;
import project.library.services.HeroService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/heroes")
public class HeroController {

    @Autowired
    private HeroService heroService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Hero> getHeroes() {
        return heroService.getHeroes();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Hero getHero(@PathVariable Integer id) {
        return heroService.getHeroById(id);
    }
}
