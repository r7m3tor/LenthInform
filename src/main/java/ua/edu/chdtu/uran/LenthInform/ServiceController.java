package ua.edu.chdtu.uran.LenthInform;

import jakarta.servlet.http.HttpSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.edu.chdtu.uran.LenthInform.db.NewsRepository;
import ua.edu.chdtu.uran.LenthInform.model.News;

@RestController
@RequestMapping("/api")
public class ServiceController {

    private final NewsRepository repository;

    public ServiceController(NewsRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/addNew")
    @Transactional
    @PutMapping
    public Mono<News> addNew(HttpSession httpSession, @RequestBody News news) {
        if (httpSession.getAttribute("Login") != null) {
            return repository.save(news);
        } else {
            throw new RuntimeException("Need login");
        }
    }

    @RequestMapping("/getLast")
    @Transactional
    public Flux<News> getLast(HttpSession httpSession) {
        httpSession.setAttribute("Login", "DAKARTA");
        return repository.findAll();
    }
}
