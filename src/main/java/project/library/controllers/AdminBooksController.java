package project.library.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import project.library.entities.dto.TitleDtoGet;
import project.library.entities.dto.TitleDtoPost;
import project.library.services.BookService;

@EnableWebMvc
@Controller
@RequestMapping(value = "/console")
public class AdminBooksController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String getTitles(Model model) {
        model.addAttribute("what", "books");
        model.addAttribute("books", bookService.getAllTitlesDto(true));
        return "adminPage";
    }

    @RequestMapping(value = "/book/add", method = RequestMethod.GET)
    public String getAddBookForm(Model model) {
        model.addAttribute("what", "bookForm");
        model.addAttribute("types", bookService.getAllTypes());
        return "adminPage";
    }

    @RequestMapping(value = "/book/save", method = RequestMethod.POST)
    public String getAddBookForm(@ModelAttribute("title") TitleDtoPost titleDto, Model model) {
        bookService.addTitle(titleDto);
        return "redirect:/console/books";
    }

    @RequestMapping(value = "/book/edit/{id}", method = RequestMethod.GET)
    public String getBook(@PathVariable String id, Model model) {
        model.addAttribute("what", "bookEditForm");
        model.addAttribute("book", bookService.getTitleDtoById(Long.parseLong(id)));
        model.addAttribute("types", bookService.getAllTypes());
        return "adminPage";
    }

    @RequestMapping(value = "/book/change/{id}", method = RequestMethod.POST)
    public String saveBook(@ModelAttribute("title") TitleDtoPost titleDto, @PathVariable String id, Model model) {
        bookService.changeBook(Long.parseLong(id), titleDto);
        return "redirect:/console/books";
    }

    @RequestMapping(value = "/book/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable String id, Model model) {
        bookService.deleteTitleById(Long.parseLong(id));
        return "redirect:/console/books";
    }

    @RequestMapping(value = "/book/addPiece/{id}", method = RequestMethod.GET)
    public String addPiece(@PathVariable String id, Model model) {
        bookService.addPieceToTitle(Long.parseLong(id));
        return "redirect:/console/books";
    }


}
