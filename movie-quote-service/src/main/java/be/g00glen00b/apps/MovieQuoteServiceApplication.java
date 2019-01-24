package be.g00glen00b.apps;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@SpringBootApplication
public class MovieQuoteServiceApplication {

	@Bean
	public ApplicationRunner applicationRunner(MovieQuoteRepository repository) {
		return args -> {
			repository.deleteAll();
			repository.saveAll(Arrays.asList(
				new MovieQuote(null, "Frankly, my dear, I don't give a damn", "Gone With The Wind"),
				new MovieQuote(null, "May the Force be with you", "Star Wars"),
				new MovieQuote(null, "E.T. phone home", "E.T. The Extra-Terrestrial"),
				new MovieQuote(null, "A martini. Shaken, not stirred", "James Bond"),
				new MovieQuote(null, "I'll be back", "The Terminator"),
				new MovieQuote(null, "We'll always have Paris", "Casablanca"),
				new MovieQuote(null, "Houston, we have a problem", "Apollo 13"),
				new MovieQuote(null, "Here's Johnny!", "The Shining"),
				new MovieQuote(null, "My previous", "The Lord of the Rings"),
				new MovieQuote(null, "Why so serious?", "The Dark Knight"),
				new MovieQuote(null, "I solemnly swear that I am up to no good", "Harry Potter"),
				new MovieQuote(null, "This is Sparta!", "300"),
				new MovieQuote(null, "With great power comes great responsiblity", "Spider-Man")
			));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(MovieQuoteServiceApplication.class, args);
	}

}

