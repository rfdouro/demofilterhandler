package br.rfdouro.demointerceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.rfdouro.demointerceptor.handlers.RequestInterceptor;

@SpringBootApplication
@EnableWebMvc // to be used only if you're using an non-spring boot application
public class DemointerceptorApplication implements WebMvcConfigurer {

	public static Logger logger = LoggerFactory.getLogger(DemointerceptorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemointerceptorApplication.class, args);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new RequestInterceptor());
	}

}
