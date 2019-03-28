package project.library.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@RequestMapping(value = "/aaa")
@EnableWebMvc
public class Example {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String home() {
        return "hello";
    }
}
