package be.g00glen00b.apps.quote;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static be.g00glen00b.apps.quote.MovieQuoteExtractors.id;
import static be.g00glen00b.apps.quote.MovieQuoteExtractors.movieId;
import static be.g00glen00b.apps.quote.MovieQuoteExtractors.movieName;
import static be.g00glen00b.apps.quote.MovieQuoteExtractors.quote;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.queryParam;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest({MovieQuoteClient.class, MovieQuoteClientConfiguration.class})
public class MovieQuoteClientTest {
    @Autowired
    private MovieQuoteClient client;
    @Autowired
    private MockRestServiceServer server;

    @Test
    public void findAll_callsGetMovieQuotes() {
        server
            .expect(once(), requestTo(startsWith("/movie-quote")))
            .andExpect(method(HttpMethod.GET))
            .andExpect(queryParam("page", "0"))
            .andExpect(queryParam("pageSize", "10"))
            .andRespond(withSuccess(new ClassPathResource("movie-quotes.json"), MediaType.APPLICATION_JSON));
        assertThat(client.findAll(0, 10))
            .extracting(id(), movieId(), movieName(), quote())
            .containsOnlyOnce(
                tuple(1L, 1L, "Terminator", "Hasta la vista, baby"),
                tuple(2L, 1L, "Terminator", "I'll be back"));
    }

    @Test
    public void findById_callsGetMovieQotesById() {
        server
            .expect(once(), requestTo(startsWith("/movie-quote/1")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withSuccess(new ClassPathResource("movie-quote.json"), MediaType.APPLICATION_JSON));
        assertThat(client.findById(1L))
            .extracting(id(), movieId(), movieName(), quote())
            .containsExactly(1L, 1L, "Terminator", "Hasta la vista, baby");
    }

    @Test
    public void create_callsPostMovieQuotes() {
        server
            .expect(once(), requestTo(startsWith("/movie-quote")))
            .andExpect(method(HttpMethod.POST))
            .andExpect(jsonPath("$.movie", is("Terminator")))
            .andExpect(jsonPath("$.quote", is("I'll be back")))
            .andRespond(withSuccess(new ClassPathResource("movie-quote.json"), MediaType.APPLICATION_JSON));
        assertThat(client.create(MovieQuoteInput.builder().movie("Terminator").quote("I'll be back").build()))
            .extracting(id(), movieId(), movieName(), quote())
            .containsExactly(1L, 1L, "Terminator", "Hasta la vista, baby");
    }
}
