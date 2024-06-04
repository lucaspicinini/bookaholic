package br.com.bookaholic.controller;

import br.com.bookaholic.entry.EntryPoint;
import br.com.bookaholic.model.Book;
import br.com.bookaholic.model.BookData;
import br.com.bookaholic.model.DataIndex;
import br.com.bookaholic.repository.BookRepository;
import br.com.bookaholic.service.ApiService;
import br.com.bookaholic.service.Mapper;
import br.com.bookaholic.utils.Menu;
import br.com.bookaholic.utils.ScreenClear;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CatalogueOptions {
    private final Scanner scanner = new Scanner(System.in);
    private final ApiService apiService = new ApiService();
    private final Mapper mapper = new Mapper();
    private final DataIndex dataIndex;
    private final String userOption;
    private final List<Book> books;
    private final BookRepository bookRepository;
    private String apiPage;
    private Integer apiPageNumber;


    public CatalogueOptions(String userOption, List<Book> books, BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.dataIndex = EntryPoint.getDataIndex();
        this.userOption = userOption;
        this.books = books;
        this.apiPage = EntryPoint.getApiPage();
        this.apiPageNumber = EntryPoint.getApiPageNumber();
    }

    public void checkOption() {
        switch (this.userOption) {
            case "1":
                if (dataIndex.previousPage() != null) {
                    apiPageNumber--;
                    apiPage = String.valueOf(apiPageNumber);
                    EntryPoint.setApiPageNumber(apiPageNumber);
                    EntryPoint.setApiPage(apiPage);
                }
                break;
            case "2":
                if (dataIndex.nextPage() != null) {
                    apiPageNumber++;
                    apiPage = String.valueOf(apiPageNumber);
                    EntryPoint.setApiPageNumber(apiPageNumber);
                    EntryPoint.setApiPage(apiPage);
                }
                break;
            case "3":
                Menu.askPage();

                try {
                    apiPageNumber = scanner.nextInt();
                    scanner.nextLine();
                    apiPage = String.valueOf(apiPageNumber).trim();
                    EntryPoint.setApiPageNumber(apiPageNumber);
                    EntryPoint.setApiPage(apiPage);
                } catch (InputMismatchException e) {
                    System.out.println("A busca deve ser por número.");
                    break;
                }
                break;
            case "4":
                ScreenClear.clear();
                Menu.saving();

                try {
                    bookRepository.saveAll(books);
                    Menu.saved();
                } catch (DataIntegrityViolationException e) {
                    Menu.alreadySaved();
                }

                break;
            case "5":
                Menu.askId();
                String bookId = scanner.nextLine();
                ScreenClear.clear();
                Menu.connecting();
                String responseBody = apiService
                        .getResponseBody("https://gutendex.com/books/" + bookId + "/");
                EntryPoint.checkNullResponseBody(responseBody, mapper, bookRepository);
                break;
            case "0":
                EntryPoint.setUserInput("");
                break;
            default:
                Menu.invalidOption();
                break;
        }
    }
}
