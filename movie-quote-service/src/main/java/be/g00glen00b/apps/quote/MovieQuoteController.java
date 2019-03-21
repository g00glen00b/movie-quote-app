package be.g00glen00b.apps.quote;

import be.g00glen00b.apps.movie.MovieNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/movie-quote")
public class MovieQuoteController {
    private MovieQuoteService service;

    @GetMapping
    public List<MovieQuote> findAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int pageSize) {
        return service.findAll(page, pageSize);
    }

    @GetMapping("/count")
    public Long count() {
        return service.count();
    }

    @GetMapping("/@random")
    public MovieQuote findRandom() {
        return service.findRandom();
    }

    @GetMapping("/{id}")
    public MovieQuote findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/by-movie/{movie}")
    public List<MovieQuote> findByMovie(@PathVariable String movie) {
        return service.findByMovie(movie);
    }

    @PostMapping
    public MovieQuote create(@RequestBody MovieQuoteInput input) {
        return service.create(input);
    }

    @PutMapping("/{id}")
    public MovieQuote update(@PathVariable Long id, @RequestBody MovieQuoteInput input) {
        return service.update(id, input);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @ExceptionHandler({MovieQuoteNotFoundException.class, MovieNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound(RuntimeException ex) {
        return ex.getMessage();
    }
}
