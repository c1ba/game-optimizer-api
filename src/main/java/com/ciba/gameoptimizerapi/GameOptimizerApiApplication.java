package com.ciba.gameoptimizerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {SessionAutoConfiguration.class}, scanBasePackages = {"com.ciba.gameoptimizerapi"})
public class GameOptimizerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameOptimizerApiApplication.class, args);
	}

}
