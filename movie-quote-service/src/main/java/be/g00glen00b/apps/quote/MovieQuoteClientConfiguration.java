package be.g00glen00b.apps.quote;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Stream;

@Slf4j
@Configuration
public class MovieQuoteClientConfiguration {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
            .rootUri("http://localhost:8080/api")
            .build();
    }

    @Bean
    @Profile("dev")
    public CommandLineRunner initializer(MovieQuoteClient client) {
        return args -> Stream.of(
            MovieQuoteInput.builder().movie("Gone With The Wind").quote("Frankly, my dear, I don't give a damn").build(),
            MovieQuoteInput.builder().movie("Star Wars").quote("May the Force be with you").build(),
            MovieQuoteInput.builder().movie("E.T. The Extra-Terrestrial").quote("E.T. phone home").build(),
            MovieQuoteInput.builder().movie("James Bond").quote("A martini. Shaken, not stirred").build(),
            MovieQuoteInput.builder().movie("The Terminator").quote("I'll be back").build(),
            MovieQuoteInput.builder().movie("Casablanca").quote("We'll always have Paris").build(),
            MovieQuoteInput.builder().movie("Apollo 13").quote("Houston, we have a problem").build(),
            MovieQuoteInput.builder().movie("The Shining").quote("Here's Johnny!").build(),
            MovieQuoteInput.builder().movie("The Lord of the Rings").quote("My precious").build(),
            MovieQuoteInput.builder().movie("The Dark Knight").quote("Why so serious?").build(),
            MovieQuoteInput.builder().movie("Harry Potter").quote("I solemnly swear that I am up to no good").build(),
            MovieQuoteInput.builder().movie("300").quote("This is Sparta!").build(),
            MovieQuoteInput.builder().movie("Spider-Man").quote("With great power comes great responsiblity").build(),
            MovieQuoteInput.builder().movie("Matrix").quote("Human beings are a disease, cancer of this planet.").build(),
            MovieQuoteInput.builder().movie("V for Vendetta").quote("Beneath this mask there is an idea. And ideas are bulletproof.").build(),
            MovieQuoteInput.builder().movie("Forrest Gump").quote("Run Forrest run!").build(),
            MovieQuoteInput.builder().movie("The Godfather").quote("I'll make him an offer he can't refuse").build(),
            MovieQuoteInput.builder().movie("Forrest Gump").quote("Mama always said life is like a box of chocolates.").build(),
            MovieQuoteInput.builder().movie("James Bond").quote("Bond. James Bond").build(),
            MovieQuoteInput.builder().movie("Taxi Driver").quote("You talking to me?").build(),
            MovieQuoteInput.builder().movie("Jaws").quote("You're gonna need a bigger boat").build(),
            MovieQuoteInput.builder().movie("Die Hard").quote("Yippe-ki-yaya, Motherf*cker").build(),
            MovieQuoteInput.builder().movie("Scarface").quote("Say hello to my little friend!").build(),
            MovieQuoteInput.builder().movie("Wizard of Oz").quote("There's no place like home").build(),
            MovieQuoteInput.builder().movie("T2").quote("Hasta la vista, baby").build(),
            MovieQuoteInput.builder().movie("Wizard of Oz").quote("I've got a feeling we're not in Kansas anymore").build(),
            MovieQuoteInput.builder().movie("The Princess Bride").quote("My name is Inigo Montoya. You killed my father. Prepare to die").build(),
            MovieQuoteInput.builder().movie("The Empire Strikes Back").quote("No, I am your father!").build(),
            MovieQuoteInput.builder().movie("The treasure of the Sierra Madre").quote("We ain't got no badges").build(),
            MovieQuoteInput.builder().movie("Raiders of the Lost Ark").quote("Why'd it have to be snakes?").build(),
            MovieQuoteInput.builder().movie("The Martian").quote("I'm gonna have to science the sh*t outtta this").build(),
            MovieQuoteInput.builder().movie("Gladiator").quote("Are you not entertained!").build(),
            MovieQuoteInput.builder().movie("Toy Story").quote("To Infinity and Beyond!").build(),
            MovieQuoteInput.builder().movie("The Usual Suspects").quote("The greatest trick the Devil ever pulled was convincing the world he didn't exist").build(),
            MovieQuoteInput.builder().movie("Titanic").quote("I'm the king of the world!").build(),
            MovieQuoteInput.builder().movie("The Big Lebowski").quote("You got the wrong guy. I'm the Dude, man.").build(),
            MovieQuoteInput.builder().movie("Forrest Gump").quote("Stupid is as stupid does").build(),
            MovieQuoteInput.builder().movie("Office Space").quote("I believe you have my stapler...").build())
            .map(client::create)
            .forEach(quote -> log.info("Created {}", quote));
    }
}
