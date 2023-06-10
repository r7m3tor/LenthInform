package ua.edu.chdtu.uran.LenthInform;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
    @GetMapping("/")
    public String lastNews() {
        return "index";
    }

    @RequestMapping("/topic/{tag}")
    public String topic(@PathVariable("tag") String tag, Model model) {
        model.addAttribute("type", tag);
        return "topic";
    }

    @GetMapping("/post")
    public String post() {
        return "post";
    }
}
