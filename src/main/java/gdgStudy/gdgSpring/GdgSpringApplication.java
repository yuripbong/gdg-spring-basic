package gdgStudy.gdgSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GdgSpringApplication {

	public static void main(String[] args) {

		SpringApplication.run(GdgSpringApplication.class, args);
	}
}
