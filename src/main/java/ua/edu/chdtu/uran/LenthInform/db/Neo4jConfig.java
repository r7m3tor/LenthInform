package ua.edu.chdtu.uran.LenthInform.db;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.AbstractReactiveNeo4jConfig;
import org.springframework.data.neo4j.repository.config.EnableReactiveNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ua.edu.chdtu.uran.LenthInform.model.News;
import ua.edu.chdtu.uran.LenthInform.model.User;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableReactiveNeo4jRepositories
@EnableTransactionManagement
public class Neo4jConfig extends AbstractReactiveNeo4jConfig {

    @Bean
    public Driver driver() {
        return GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "12345678"));
    }

    @Override
    protected Collection<String> getMappingBasePackages() {
        ArrayList<String> list = new ArrayList<>();
        list.add(News.class.getPackage().getName());
        list.add(User.class.getPackage().getName());
        return list;
    }
}