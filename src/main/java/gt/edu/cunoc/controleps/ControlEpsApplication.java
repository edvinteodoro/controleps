package gt.edu.cunoc.controleps;

import gt.edu.cunoc.controleps.service.WordManagerService;
import gt.edu.cunoc.controleps.service.imp.WordManagerServiceImp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ControlEpsApplication {

	public static void main(String[] args) {

		SpringApplication.run(ControlEpsApplication.class, args);

		WordManagerServiceImp wordManagerService = new WordManagerServiceImp();
		wordManagerService.replaceBookMarks();
	}

}
