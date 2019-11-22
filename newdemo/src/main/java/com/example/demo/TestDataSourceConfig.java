package com.example.demo;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableMongoRepositories(basePackages = "com.example.demo.MDrepository")
@PropertySource("classpath:application.properties")
public class TestDataSourceConfig extends AbstractMongoConfiguration {

    @Autowired private Environment environment;

    @Override
    protected String getDatabaseName() {
        return environment.getRequiredProperty("spring.data.mongodb.database");
    }

    @Override
    public MongoClient mongoClient() {
        return null;
    }

    @Bean
    public Mongo mongo () throws Exception{
        ServerAddress serverAddress = new ServerAddress(environment.getRequiredProperty("spring.data.mongodb.host"));
        List<MongoCredential> credentials = new ArrayList<>();
        return new MongoClient(serverAddress , credentials);
    }
}
