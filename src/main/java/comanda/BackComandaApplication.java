package comanda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"comanda.service.mapper"})
public class BackComandaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackComandaApplication.class, args);
	}

}
