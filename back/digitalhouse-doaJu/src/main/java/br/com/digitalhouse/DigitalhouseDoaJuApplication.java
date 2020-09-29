package br.com.digitalhouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"br.com.digitalhouse.model"})
public class DigitalhouseDoaJuApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalhouseDoaJuApplication.class, args);
	}

}
