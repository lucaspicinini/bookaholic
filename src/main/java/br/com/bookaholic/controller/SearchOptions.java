package br.com.bookaholic.controller;

import br.com.bookaholic.entry.EntryPoint;
import br.com.bookaholic.model.Book;
import br.com.bookaholic.repository.BookRepository;
import br.com.bookaholic.service.ApiService;
import br.com.bookaholic.service.Mapper;
import br.com.bookaholic.utils.Menu;
import br.com.bookaholic.utils.ScreenClear;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Scanner;

public class SearchOptions {
    private final Scanner scanner = new Scanner(System.in);
    private final ApiService apiService = new ApiService();
    private final Mapper mapper = new Mapper();
    private final String searchMenuOption;
    private final BookRepository bookRepository;
    private final List<Book> books;

    public SearchOptions(String searchMenuOption, BookRepository bookRepository, List<Book> books) {
        this.searchMenuOption = searchMenuOption;
        this.bookRepository = bookRepository;
        this.books = books;
    }

    public void checkOption() {
        switch (searchMenuOption) {
            case "1":
                ScreenClear.clear();
                Menu.saving();

                try {
                    bookRepository.saveAll(books);
                    Menu.saved();
                } catch (DataIntegrityViolationException e) {
                    Menu.alreadySaved();
                }

                EntryPoint.setUserInput("");
                break;
            case "2":
                ScreenClear.clear();
                Menu.askId();
                String bookId = scanner.nextLine();
                ScreenClear.clear();
                Menu.connecting();
                String responseBody = apiService
                        .getResponseBody("https://gutendex.com/books/" + bookId + "/");
                EntryPoint.checkNullResponseBody(responseBody, mapper, bookRepository);
                EntryPoint.setUserInput("");
                break;
            case "0":
                ScreenClear.clear();
                EntryPoint.setUserInput("");
                break;
            default:
                Menu.invalidOption();
                break;
        }
    }
}
