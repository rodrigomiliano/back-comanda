package comanda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan(basePackages = {"comanda.service.mapper", "comanda.service", "comanda.controller", "comanda.repository", "comanda.entity"})
@Configuration
public class BackComandaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackComandaApplication.class, args);
	}

}
