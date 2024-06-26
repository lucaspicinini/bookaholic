package br.com.bookaholic;

import br.com.bookaholic.entry.EntryPoint;
import br.com.bookaholic.repository.AuthorRepository;
import br.com.bookaholic.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookaholicApplication implements CommandLineRunner {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private AuthorRepository authorRepository;

	public static void main(String[] args) {
		SpringApplication.run(BookaholicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			EntryPoint entryPoint = new EntryPoint(bookRepository, authorRepository);
			entryPoint.init();
		} catch (Exception e) {
            throw new Exception(e);
        }
	}
}
