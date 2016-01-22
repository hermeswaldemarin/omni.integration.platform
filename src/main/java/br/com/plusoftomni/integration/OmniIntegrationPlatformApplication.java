package br.com.plusoftomni.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan("br.com.plusoftomni.integration")
public class OmniIntegrationPlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(OmniIntegrationPlatformApplication.class, args);
    }
}
