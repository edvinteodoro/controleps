package gt.edu.cunoc.controleps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class}) 
public class ControlEpsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControlEpsApplication.class, args);
	}

}
