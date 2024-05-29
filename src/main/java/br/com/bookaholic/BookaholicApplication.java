package br.com.bookaholic;

import br.com.bookaholic.entry.EntryPoint;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookaholicApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BookaholicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			EntryPoint entryPoint = new EntryPoint();
			entryPoint.init();
		} catch (Exception e) {
            throw new Exception(e);
        }
	}
}
