package br.com.spring.estudo.estudos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class SpringAwsEstudosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAwsEstudosApplication.class, args);
	}

}
