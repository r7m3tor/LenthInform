package ua.edu.chdtu.uran.LenthInform;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.edu.chdtu.uran.LenthInform.model.News;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ServiceController {
    @RequestMapping("/getLast")
    public List<News> getLast(HttpSession httpSession) {
        httpSession.setAttribute("Login", "DAKARTA");
        List<News> listNews = new ArrayList<>();
        News news = new News();
        news.setTitle("Title");
        news.setText("Text Text Text Text Text Text Text ");
        News news2 = new News();
        news2.setTitle("Title");
        news2.setText("Text Text Text Text Text Text Text ");
        listNews.add(news);
        listNews.add(news2);
        return listNews;
    }
}
