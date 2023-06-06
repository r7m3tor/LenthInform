package ua.edu.chdtu.uran.LenthInform;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/")
    public String lastNews() {
        return "index";
    }

    @GetMapping("/post")
    public String post() {
        return "post";
    }
}
