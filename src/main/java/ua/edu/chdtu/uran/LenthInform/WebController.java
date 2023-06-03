package ua.edu.chdtu.uran.LenthInform;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/")
    public String lastNews() {
        return "index";
    }
}
