package br.com.bookaholic.repository;

import br.com.bookaholic.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
