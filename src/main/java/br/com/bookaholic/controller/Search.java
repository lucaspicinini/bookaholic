package br.com.bookaholic.controller;

import br.com.bookaholic.entry.EntryPoint;
import br.com.bookaholic.model.Book;
import br.com.bookaholic.model.DataIndex;
import br.com.bookaholic.repository.BookRepository;
import br.com.bookaholic.utils.Menu;
import br.com.bookaholic.utils.ScreenClear;

import java.util.List;
import java.util.Scanner;

public class Search {
    private final Scanner scanner = new Scanner(System.in);
    private final DataIndex dataIndex;
    private final BookRepository bookRepository;

    public Search(BookRepository bookRepository) {
        this.dataIndex = EntryPoint.getDataIndex();
        this.bookRepository = bookRepository;
    }

    public void load() {
        if (!dataIndex.books().isEmpty()) {
            ScreenClear.clear();
            List<Book> books = dataIndex.books().stream().map(Book::new).toList();
            books.forEach(Book::printBook);
            Menu.searchMenu();
            String searchMenuOption = scanner.nextLine();
            SearchOptions searchOptions = new SearchOptions(searchMenuOption, bookRepository, books);
            searchOptions.checkOption();
        } else {
            ScreenClear.clear();
            Menu.notFound();
        }
    }
}
