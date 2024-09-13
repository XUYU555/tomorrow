package com.xxyy.quandaiban1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing  //开启jpa审计
@SpringBootApplication
public class Quandaiban1Application {

    public static void main(String[] args) {
        SpringApplication.run(Quandaiban1Application.class, args);
    }

}
