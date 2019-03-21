package be.g00glen00b.apps.quote;

import java.util.function.Function;

public final class MovieQuoteExtractors {
    public static Function<MovieQuote, Object> quote() {
        return MovieQuote::getQuote;
    }

    public static Function<MovieQuote, Object> movieName() {
        return quote -> quote.getMovie().getName();
    }

    public static Function<MovieQuote, Object> movieId() {
        return quote -> quote.getMovie().getId();
    }

    public static Function<MovieQuote, Object> id() {
        return MovieQuote::getId;
    }
}
