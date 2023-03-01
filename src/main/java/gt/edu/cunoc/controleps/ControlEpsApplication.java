package gt.edu.cunoc.controleps;

import gt.edu.cunoc.controleps.config.StorageConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageConfig.class)
public class ControlEpsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControlEpsApplication.class, args);
	}

}
