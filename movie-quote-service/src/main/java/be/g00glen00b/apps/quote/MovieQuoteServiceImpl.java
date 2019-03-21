package be.g00glen00b.apps.quote;

import be.g00glen00b.apps.movie.Movie;
import be.g00glen00b.apps.movie.MovieNotFoundException;
import be.g00glen00b.apps.movie.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieQuoteServiceImpl implements MovieQuoteService {
    private MovieQuoteRepository movieQuoteRepository;
    private MovieRepository movieRepository;

    @Override
    public List<MovieQuote> findAll(int page, int pageSize) {
        return movieQuoteRepository.findAll(PageRequest.of(page, pageSize)).getContent();
    }

    @Override
    public Long count() {
        return movieQuoteRepository.count();
    }

    @Override
    public List<MovieQuote> findByMovie(String movie) {
        return movieRepository.findByName(movie)
            .map(Movie::getQuotes)
            .orElseThrow(() -> new MovieNotFoundException(movie));
    }

    @Override
    public MovieQuote findRandom() {
        int random = (int) Math.floor(Math.random() * movieQuoteRepository.count());
        return movieQuoteRepository
            .findAll(PageRequest.of(random, 1))
            .stream()
            .findAny()
            .orElseThrow(MovieQuoteNotFoundException::new);
    }

    @Override
    public MovieQuote findById(Long id) {
        return movieQuoteRepository.findById(id).orElseThrow(() -> new MovieQuoteNotFoundException(id));
    }

    @Override
    public MovieQuote create(MovieQuoteInput input) {
        return movieQuoteRepository.saveAndFlush(MovieQuote.builder()
            .movie(findOrCreate(input.getMovie()))
            .quote(input.getQuote())
            .build());
    }

    @Override
    public MovieQuote update(Long id, MovieQuoteInput input) {
        MovieQuote quote = findById(id);
        quote.setMovie(findOrCreate(input.getMovie()));
        quote.setQuote(input.getQuote());
        return quote;
    }

    @Override
    public void delete(Long id) {
        movieQuoteRepository.delete(findById(id));
    }

    private Movie findOrCreate(String movie) {
        return movieRepository
            .findByName(movie)
            .orElseGet(() -> movieRepository.saveAndFlush(Movie.builder().name(movie).build()));
    }
}
