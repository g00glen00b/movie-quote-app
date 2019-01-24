package be.g00glen00b.apps;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieQuoteRepository extends JpaRepository<MovieQuote, Long> {
}
