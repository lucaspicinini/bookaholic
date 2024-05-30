package br.com.bookaholic.utils;

import br.com.bookaholic.entry.EntryPoint;
import br.com.bookaholic.model.Book;
import br.com.bookaholic.model.DataIndex;

import java.util.List;
import java.util.Scanner;

public class Catalogue {
    private final Scanner scanner = new Scanner(System.in);
    private final DataIndex dataIndex;
    private Integer apiPageNumber;
    private String apiPage;

    public Catalogue(DataIndex dataIndex) {
        this.dataIndex = dataIndex;
        this.apiPageNumber = EntryPoint.getApiPageNumber();
        this.apiPage = EntryPoint.getApiPage();
    }

    public void load() {
        if (this.dataIndex.invalidPage() == null) {
            List<Book> books = this.dataIndex.books().stream().map(Book::new).toList();
            books.forEach(Book::printBook);
            System.out.println("***** Página: " + apiPage + " *****\n");
            Menu.printCatalogueMenu();
            String userOption = scanner.nextLine();
            ScreenClear.clear();
            CatalogueOptions catalogueOptions = new CatalogueOptions(userOption);
            catalogueOptions.checkOption();
        } else {
            ScreenClear.clear();
            System.out.println("Página: " + apiPage + " não encontrada");
            System.out.println("Voltando ao catálogo...\n");
            apiPageNumber = 1;
            apiPage = String.valueOf(apiPageNumber);
            EntryPoint.setApiPageNumber(apiPageNumber);
            EntryPoint.setApiPage(apiPage);
        }
    }
}
