package br.com.bookaholic.utils;

import br.com.bookaholic.entry.EntryPoint;
import br.com.bookaholic.model.Book;
import br.com.bookaholic.model.DataIndex;
import br.com.bookaholic.repository.BookRepository;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Scanner;

public class CatalogueOptions {
    private final Scanner scanner = new Scanner(System.in);
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
                apiPageNumber = scanner.nextInt();
                scanner.nextLine();
                apiPage = String.valueOf(apiPageNumber).trim();
                EntryPoint.setApiPageNumber(apiPageNumber);
                EntryPoint.setApiPage(apiPage);
                break;
            case "4":
                ScreenClear.clear();
                System.out.println("Salvando livros...\n");
                try {
                    bookRepository.saveAll(this.books);
                    System.out.println("Livros salvos com sucesso!\n");
                } catch (DataIntegrityViolationException e) {
                    System.out.println("Esses livros já foram salvos anteriormente!\n");
                }
                break;
            case "5":
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
