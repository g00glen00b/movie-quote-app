package be.g00glen00b.apps.quote;

import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@AllArgsConstructor
public class MovieQuoteClient {
    private RestTemplate restTemplate;

    public List<MovieQuote> findAll(int page, int pageSize) {
        return restTemplate
            .exchange("/movie-quote?page={page}&pageSize={pageSize}", HttpMethod.GET, null, new ListReference(), page, pageSize)
            .getBody();
    }

    public MovieQuote create(MovieQuoteInput input) {
        return restTemplate.postForObject("/movie-quote", input, MovieQuote.class);
    }

    public MovieQuote findById(Long id) {
        return restTemplate.getForObject("/movie-quote/{id}", MovieQuote.class, id);
    }


    private class ListReference extends ParameterizedTypeReference<List<MovieQuote>> { }
}
