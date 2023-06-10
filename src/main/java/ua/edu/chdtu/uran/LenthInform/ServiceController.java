package ua.edu.chdtu.uran.LenthInform;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.edu.chdtu.uran.LenthInform.db.NewsRepository;
import ua.edu.chdtu.uran.LenthInform.db.UserRepository;
import ua.edu.chdtu.uran.LenthInform.model.LoginRequest;
import ua.edu.chdtu.uran.LenthInform.model.News;
import ua.edu.chdtu.uran.LenthInform.model.Role;
import ua.edu.chdtu.uran.LenthInform.model.User;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ServiceController {

    private final NewsRepository newsRepository;
    private final UserRepository userRepository;

    public ServiceController(NewsRepository repository, UserRepository userRepository) {
        this.newsRepository = repository;
        this.userRepository = userRepository;
    }

    @RequestMapping("/addNew")
    @PutMapping
    public String addNew(HttpSession httpSession, @RequestBody News news) {
//        String login = httpSession.getAttribute("Login").toString();
//        User user = userRepository.findAllByLoginLike(login).blockFirst();
//        if (user != null && user.getRole() == Role.ADMIN) {
            newsRepository.save(news).block();
//        } else {
//            throw new RuntimeException("Bad user");
//        }
        return "{}";
    }

    @RequestMapping("/getLast")
    public List<News> getLast(HttpSession httpSession) {
        return newsRepository
                .findAll()
                .toStream()
                .sorted((o1, o2) -> o2.getId().compareTo(o1.getId()))
                .limit(6)
                .collect(Collectors.toList());
    }

    @RequestMapping("/topic/{tag}")
    public List<News> getTopic(HttpSession httpSession, @PathVariable("tag") String tag) {
        return newsRepository
                .findAll()
                .toStream()
                .sorted((o1, o2) -> o2.getId().compareTo(o1.getId()))
                .filter(news -> news.getTags().contains(tag))
                .collect(Collectors.toList());
    }

    @RequestMapping("/registration")
    @PutMapping
    public String registration(HttpSession httpSession, @RequestBody User user) {
        userRepository.save(user).block();
        httpSession.setAttribute("Login", user.getLogin());
        return "{}";
    }

    @RequestMapping("/login")
    @PutMapping
    public String login(HttpSession httpSession, @RequestBody LoginRequest request) {
        User user = userRepository.findAllByLoginLike(request.getLogin()).blockFirst();
        if (user != null && user.getPassword().equals(request.getPassword())) {
            httpSession.setAttribute("Login", user.getLogin());
        } else {
            throw new RuntimeException("Need login");
        }
        return "{}";
    }
}
