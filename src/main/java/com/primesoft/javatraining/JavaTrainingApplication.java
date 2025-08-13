package com.primesoft.javatraining;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.primesoft.*")
public class JavaTrainingApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaTrainingApplication.class, args);
    }

}
