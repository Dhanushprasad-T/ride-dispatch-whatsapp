package com.dispatchwhatsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.dispatchwhatsapp"
})
@EnableJpaRepositories(basePackages = "com.dispatchwhatsapp.repository")
@EntityScan(basePackages = "com.dispatchwhatsapp.model")
public class RideDispatchWhatsappApplication {

    public static void main(String[] args) {
        SpringApplication.run(RideDispatchWhatsappApplication.class, args);
    }
}




