package id.co.nds.gadai_2022_07_04;

import javax.annotation.PreDestroy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import id.co.nds.gadai_2022_07_04.schedulers.DbParamScheduler;

@SpringBootApplication
@EnableScheduling
public class Gadai20220704Application {

	static final Logger logger = LogManager.getLogger(Gadai20220704Application.class);
	public static void main(String[] args) {
		SpringApplication.run(Gadai20220704Application.class, args);
	}

	@PreDestroy
	public void destroy() {
		// System.out.println("STOP");

		logger.info("");
		logger.info("Stopping configuration for System. . . .");

		logger.info("Stopping custom DB scheduler. . . .");
		DbParamScheduler.shutdownScheduler();

		logger.info("");
		logger.info("Finish Stopping Configuration for System. . . .");

	}
}
