package br.com.bookaholic.utils;

import br.com.bookaholic.entry.EntryPoint;
import br.com.bookaholic.model.Book;
import br.com.bookaholic.model.DataIndex;
import br.com.bookaholic.repository.BookRepository;

import java.util.List;
import java.util.Scanner;

public class Catalogue {
    private final Scanner scanner = new Scanner(System.in);
    private final DataIndex dataIndex;
    private final BookRepository bookRepository;
    private Integer apiPageNumber;
    private String apiPage;

    public Catalogue(DataIndex dataIndex, BookRepository bookRepository) {
        this.dataIndex = dataIndex;
        this.bookRepository = bookRepository;
        this.apiPageNumber = EntryPoint.getApiPageNumber();
        this.apiPage = EntryPoint.getApiPage();
    }

    public void load() {
        if (this.dataIndex.invalidPage() == null) {
            List<Book> books = this.dataIndex.books().stream().map(Book::new).toList();
            books.forEach(Book::printBook);
            Menu.page();
            Menu.catalogueMenu();
            String userOption = scanner.nextLine();
            ScreenClear.clear();
            CatalogueOptions catalogueOptions = new CatalogueOptions(userOption, books, bookRepository);
            catalogueOptions.checkOption();
        } else {
            ScreenClear.clear();
            Menu.pageNotFound();
            Menu.backToCatalogue();
            apiPageNumber = 1;
            apiPage = String.valueOf(apiPageNumber);
            EntryPoint.setApiPageNumber(apiPageNumber);
            EntryPoint.setApiPage(apiPage);
        }
    }
}
