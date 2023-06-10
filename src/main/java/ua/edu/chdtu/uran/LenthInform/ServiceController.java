package ua.edu.chdtu.uran.LenthInform;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.edu.chdtu.uran.LenthInform.db.NewsRepository;
import ua.edu.chdtu.uran.LenthInform.model.News;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ServiceController {

    private final NewsRepository repository;

    public ServiceController(NewsRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/addNew")
    @PutMapping
    public String addNew(HttpSession httpSession, @RequestBody News news) {
        if (httpSession.getAttribute("Login") != null) {
            repository.save(news).block();
        } else {
            throw new RuntimeException("Need login");
        }
        return "{}";
    }

    @RequestMapping("/getLast")
    public List<News> getLast(HttpSession httpSession) {
        httpSession.setAttribute("Login", "DAKARTA");
        return repository
                .findAll()
                .toStream()
                .sorted((o1, o2) -> o2.getId().compareTo(o1.getId()))
                .limit(6)
                .collect(Collectors.toList());
    }
}
