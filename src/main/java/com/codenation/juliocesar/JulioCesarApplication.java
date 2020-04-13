package com.codenation.juliocesar;

import com.codenation.controller.MainExecutionController;
import com.codenation.model.ProgrammerSingleton;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootApplication
public class JulioCesarApplication {

    public static void main(String[] args) {
        SpringApplication.run(JulioCesarApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
			ProgrammerSingleton singleton = ProgrammerSingleton.getInstance();
			MainExecutionController mainExecutionController = new MainExecutionController();
			mainExecutionController.executeChallenge(restTemplate, singleton);
        };
    }
}
