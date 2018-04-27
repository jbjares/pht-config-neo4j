package de.difuture.ekut.pht.config;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * Configuration class for the Neo4j instance running
 * on the de.NBI cloud for the Personal Health APITrain Project
 *
 */
@Configuration
@EnableTransactionManagement
@Profile("denbi")
public class Neo4jDenbiConfiguration {

    @Bean
    public org.neo4j.ogm.config.Configuration configuration() {

        return new org.neo4j.ogm.config.Configuration.Builder()
                .uri("bolt://193.196.20.91:7687")
                .build();
    }

    @Bean
    public SessionFactory sessionFactory(org.neo4j.ogm.config.Configuration configuration) {

        return new SessionFactory(
                configuration,
                "de.difuture.ekut.pht.train.router.repository.traindestination",
                "de.difuture.ekut.pht.lib.core.neo4j.entity");
      }

    @Bean("transactionManager")
    public Neo4jTransactionManager neo4jTransactionManager(SessionFactory sessionFactory) {

        return new Neo4jTransactionManager(sessionFactory);
    }
}
