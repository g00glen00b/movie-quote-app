package be.g00glen00b.apps.quote;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import static java.text.MessageFormat.format;

@NoArgsConstructor
@AllArgsConstructor
public class MovieQuoteNotFoundException extends RuntimeException {
    private Long id;

    @Override
    public String getMessage() {
        return format("Quote with id '{0}' was not found", id);
    }
}
