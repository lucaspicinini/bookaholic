package br.com.bookaholic.utils;

import br.com.bookaholic.model.Book;
import br.com.bookaholic.model.BookData;
import br.com.bookaholic.repository.BookRepository;
import br.com.bookaholic.service.Mapper;
import org.springframework.dao.DataIntegrityViolationException;

public class CheckNullResponseBody {
    public static void check(String responseBody, Mapper mapper, BookRepository bookRepository) {
        if (responseBody != null) {
            BookData bookDataById = mapper.getClassFromJson(responseBody, BookData.class);
            ScreenClear.clear();

            if (bookDataById.invalidPage() == null) {
                Book bookById = new Book(bookDataById);
                Menu.saving();

                try {
                    bookRepository.save(bookById);
                    Menu.saved();
                } catch (DataIntegrityViolationException e) {
                    Menu.alreadySaved();
                }

                bookById.printBook();
            } else {
                Menu.notFound();
            }

        }
    }
}
