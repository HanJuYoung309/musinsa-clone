package com.demo.musinsaclone;

import com.demo.musinsaclone.jwt.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EntityScan(basePackages = "com.demo.musinsaclone.domain")
@EnableConfigurationProperties(JwtProperties.class)
public class MusinsaCloneApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusinsaCloneApplication.class, args);
    }

}
