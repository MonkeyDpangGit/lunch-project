package com.lunch.operation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession;

/**
 * OperationApp
 *
 * @author torrisli
 * @date 2023/2/14
 * @Description: OperationApp
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.lunch.*")
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = "com.lunch.*.repository")
@EnableMongoHttpSession(maxInactiveIntervalInSeconds = 3600)
public class OperationApp {

    public static void main(String[] args) {
        SpringApplication.run(OperationApp.class, args);
    }
}
