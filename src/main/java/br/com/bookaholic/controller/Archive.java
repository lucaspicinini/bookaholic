package br.com.bookaholic.controller;

import br.com.bookaholic.model.Book;
import br.com.bookaholic.repository.BookRepository;
import br.com.bookaholic.utils.Menu;
import br.com.bookaholic.utils.ScreenClear;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Scanner;

public class Archive {
    private final Scanner scanner = new Scanner(System.in);
    private final BookRepository bookRepository;
    private String archiveInput = "";
    private static Integer pageNumber = 1;

    public Archive(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void load() {
        while (!archiveInput.equals("0")) {
            Pageable pageable = PageRequest.of(pageNumber - 1, 10);
            Page<Book> page = bookRepository.findAll(pageable);
            page.forEach(Book::printBook);
            Menu.archiveMenu();
            Menu.archiveMenuInfo(pageNumber, page);
            Menu.askOption();
            archiveInput = scanner.nextLine();
            ScreenClear.clear();
            ArchiveOptions archiveOptions = new ArchiveOptions(archiveInput, page, pageNumber);
            archiveOptions.checkOption();
        }

        archiveInput = "";
    }

    public static void setPageNumber(Integer pageNumber) {
        Archive.pageNumber = pageNumber;
    }
}
