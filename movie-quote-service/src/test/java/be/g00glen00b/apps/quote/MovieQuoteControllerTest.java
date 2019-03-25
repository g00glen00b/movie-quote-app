package be.g00glen00b.apps.quote;

import be.g00glen00b.apps.movie.Movie;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@RunWith(SpringRunner.class)
public class MovieQuoteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MovieQuoteService service;

    @Test
    public void getMovieQuotes_shouldUseFindAll() throws Exception {
        Movie movie = Movie.builder().id(1L).name("Terminator").build();
        when(service.findAll(0, 10)).thenReturn(Lists.newArrayList(
            MovieQuote.builder().id(1L).movie(movie).quote("Hasta la vista, baby").build(),
            MovieQuote.builder().id(2L).movie(movie).quote("I'll be back").build()
        ));
        this.mockMvc
            .perform(get("/api/movie-quote"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[*]", hasSize(2)))
            .andExpect(jsonPath("$[0].id", is(1)))
            .andExpect(jsonPath("$[0].quote", is("Hasta la vista, baby")))
            .andExpect(jsonPath("$[0].movie.id", is(1)))
            .andExpect(jsonPath("$[0].movie.name", is("Terminator")))
            .andExpect(jsonPath("$[1].id", is(2)))
            .andExpect(jsonPath("$[1].quote", is("I'll be back")))
            .andExpect(jsonPath("$[1].movie.id", is(1)))
            .andExpect(jsonPath("$[1].movie.name", is("Terminator")));
    }

    @Test
    public void getMovieQuoteById_shouldUseFindOne() throws Exception {
        Movie movie = Movie.builder().id(1L).name("Terminator").build();
        MovieQuote quote = MovieQuote.builder().id(1L).movie(movie).quote("Hasta la vista, baby").build();
        when(service.findById(1L)).thenReturn(quote);
        this.mockMvc
            .perform(get("/api/movie-quote/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.quote", is("Hasta la vista, baby")))
            .andExpect(jsonPath("$.movie.id", is(1)))
            .andExpect(jsonPath("$.movie.name", is("Terminator")));
    }

    @Test
    public void postMovieQuoteById_shouldUseCreate() throws Exception {
        Movie movie = Movie.builder().id(1L).name("Terminator").build();
        MovieQuote quote = MovieQuote.builder().id(1L).movie(movie).quote("Hasta la vista, baby").build();
        when(service.create(any())).thenReturn(quote);
        this.mockMvc
            .perform(post("/api/movie-quote")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"quote\":\"Hasta la vista, baby\", \"movie\": \"Terminator\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.quote", is("Hasta la vista, baby")))
            .andExpect(jsonPath("$.movie.id", is(1)))
            .andExpect(jsonPath("$.movie.name", is("Terminator")));
        ArgumentCaptor<MovieQuoteInput> anyQuote = forClass(MovieQuoteInput.class);
        verify(service).create(anyQuote.capture());
        assertThat(anyQuote.getValue().getMovie()).isEqualTo("Terminator");
        assertThat(anyQuote.getValue().getQuote()).isEqualTo("Hasta la vista, baby");
    }

    @Test
    public void getMovieQuoteById_shouldReturn404IfNoQuoteExists() throws Exception {
        when(service.findById(1L)).thenThrow(new MovieQuoteNotFoundException(1L));
        this.mockMvc
            .perform(get("/api/movie-quote/1"))
            .andExpect(status().isNotFound())
            .andExpect(content().string("Quote with id '1' was not found"));
    }
}
