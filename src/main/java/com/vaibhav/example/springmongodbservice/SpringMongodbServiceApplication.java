package com.vaibhav.example.springmongodbservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class SpringMongodbServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMongodbServiceApplication.class, args);
    }

}
