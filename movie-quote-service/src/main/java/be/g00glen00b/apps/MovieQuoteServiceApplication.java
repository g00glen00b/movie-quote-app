package be.g00glen00b.apps;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@SpringBootApplication
@EnableConfigurationProperties(CorsConfigurationProperties.class)
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
				new MovieQuote(null, "My precious", "The Lord of the Rings"),
				new MovieQuote(null, "Why so serious?", "The Dark Knight"),
				new MovieQuote(null, "I solemnly swear that I am up to no good", "Harry Potter"),
				new MovieQuote(null, "This is Sparta!", "300"),
				new MovieQuote(null, "With great power comes great responsiblity", "Spider-Man"),
				new MovieQuote(null, "Human beings are a disease, cancer of this planet.", "Matrix"),
				new MovieQuote(null, "Beneath this mask there is an idea. And ideas are bulletproof.", "V for Vendetta"),
				new MovieQuote(null, "Run Forrest run!", "Forrest Gump"),
				new MovieQuote(null, "I'll make him an offer he can't refuse", "The Godfather"),
				new MovieQuote(null, "Mama always said life is like a box of chocolates.", "Forrest Gump"),
				new MovieQuote(null, "Bond. James Bond", "James Bond"),
				new MovieQuote(null, "You talking to me?", "Taxi Driver"),
				new MovieQuote(null, "You're gonna need a bigger boat", "Jaws"),
				new MovieQuote(null, "Yippe-ki-yaya, Motherf*cker", "Die Hard"),
				new MovieQuote(null, "Say hello to my little friend!", "Scarface"),
				new MovieQuote(null, "There's no place like home", "Wizard of Oz"),
				new MovieQuote(null, "Hasta la vista, baby", "T2"),
				new MovieQuote(null, "I've got a feeling we're not in Kansas anymore", "Wizard of Oz"),
				new MovieQuote(null, "My name is Inigo Montoya. You killed my father. Prepare to die", "The Princess Bride"),
				new MovieQuote(null, "No, I am your father!", "The Empire Strikes Back"),
				new MovieQuote(null, "We ain't got no badges", "The treasure of the Sierra Madre"),
				new MovieQuote(null, "Why'd it have to be snakes?", "Raiders of the Lost Ark"),
				new MovieQuote(null, "I'm gonna have to science the sh*t outtta this", "The Martian"),
				new MovieQuote(null, "Are you not entertained!", "Gladiator"),
				new MovieQuote(null, "To Infinity and Beyond!", "Toy Story"),
				new MovieQuote(null, "The greatest trick the Devil ever pulled was convincing the world he didn't exist", "The Usual Suspects"),
				new MovieQuote(null, "I'm the king of the world!", "Titanic"),
				new MovieQuote(null, "You got the wrong guy. I'm the Dude, man.", "The Big Lebowski"),
				new MovieQuote(null, "Stupid is as stupid does", "Forrest Gump"),
				new MovieQuote(null, "I believe you have my stapler...", "Office Space")
			));
		};
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

	public static void main(String[] args) {
		SpringApplication.run(MovieQuoteServiceApplication.class, args);
	}

}

