package be.g00glen00b.apps.movie;

import lombok.AllArgsConstructor;

import static java.text.MessageFormat.format;

@AllArgsConstructor
public class MovieNotFoundException extends RuntimeException {
    private String name;

    @Override
    public String getMessage() {
        return format("Movie with name '{0}' was not found", name);
    }
}
