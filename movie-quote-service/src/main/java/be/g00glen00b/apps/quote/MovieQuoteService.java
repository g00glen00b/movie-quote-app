package be.g00glen00b.apps.quote;

import java.util.List;

public interface MovieQuoteService {
    List<MovieQuote> findAll(int page, int pageSize);

    Long count();

    List<MovieQuote> findByMovie(String movie);

    MovieQuote findRandom();

    MovieQuote findById(Long id);

    MovieQuote create(MovieQuoteInput input);

    MovieQuote update(Long id, MovieQuoteInput input);

    void delete(Long id);
}
