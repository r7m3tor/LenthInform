package ua.edu.chdtu.uran.LenthInform.db;


import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ua.edu.chdtu.uran.LenthInform.model.News;
import ua.edu.chdtu.uran.LenthInform.model.User;

@Repository
public interface UserRepository extends ReactiveNeo4jRepository<User, Long> {

    Flux<User> findAllByLoginLike(String login);
}