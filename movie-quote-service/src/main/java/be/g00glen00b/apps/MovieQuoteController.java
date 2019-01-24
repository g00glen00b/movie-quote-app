package be.g00glen00b.apps;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movie-quote")
@AllArgsConstructor
public class MovieQuoteController {
    private MovieQuoteRepository repository;

    @GetMapping("/@random")
    public ResponseEntity<MovieQuote> findRandom() {
        int random = (int) Math.floor(Math.random() * repository.count());
        return ResponseEntity.of(repository.findAll(PageRequest.of(random, 1)).get().findAny());
    }
}
