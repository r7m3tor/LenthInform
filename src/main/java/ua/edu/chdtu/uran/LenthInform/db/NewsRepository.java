package ua.edu.chdtu.uran.LenthInform.db;


import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ua.edu.chdtu.uran.LenthInform.model.News;

@Repository
public interface NewsRepository extends ReactiveNeo4jRepository<News, Long> {

    Flux<News> findAllByTitleLike(String title);
}