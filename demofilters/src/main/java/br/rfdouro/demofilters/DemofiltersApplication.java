package br.rfdouro.demofilters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class DemofiltersApplication {

	public static Logger logger = LoggerFactory.getLogger(DemofiltersApplication.class);

	public static void main(String[] args) {
		DemofiltersApplication.logger.info("aplicacao iniciando");
		SpringApplication.run(DemofiltersApplication.class, args);
		DemofiltersApplication.logger.info("aplicacao iniciada");
	}

}
