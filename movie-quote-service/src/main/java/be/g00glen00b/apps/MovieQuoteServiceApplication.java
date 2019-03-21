package be.g00glen00b.apps;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.ant;
import static springfox.documentation.builders.RequestHandlerSelectors.any;

@Slf4j
@SpringBootApplication
@EnableSwagger2
@EnableConfigurationProperties(CorsConfigurationProperties.class)
public class MovieQuoteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieQuoteServiceApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(any())
			.paths(ant("/api/**"))
			.build();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer(CorsConfigurationProperties properties) {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**").allowedOrigins(properties.getAllowedOrigins());
			}
		};
	}
}
