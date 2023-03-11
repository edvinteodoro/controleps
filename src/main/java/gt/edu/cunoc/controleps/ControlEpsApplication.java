package gt.edu.cunoc.controleps;

import gt.edu.cunoc.controleps.config.StorageConfig;
import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.unit.DataSize;

@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties(StorageConfig.class)
public class ControlEpsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControlEpsApplication.class, args);
	}
        
        @Bean
	MultipartConfigElement multipartConfigElement() {
	    MultipartConfigFactory factory = new MultipartConfigFactory();
	    factory.setMaxFileSize(DataSize.ofBytes(512000000L));
	    factory.setMaxRequestSize(DataSize.ofBytes(512000000L));
	    return factory.createMultipartConfig();
	}

}
