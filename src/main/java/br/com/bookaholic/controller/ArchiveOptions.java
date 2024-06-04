package br.com.bookaholic.controller;

import br.com.bookaholic.entry.EntryPoint;
import br.com.bookaholic.model.Book;
import br.com.bookaholic.utils.Menu;
import br.com.bookaholic.utils.ScreenClear;
import org.springframework.data.domain.Page;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ArchiveOptions {
    private final Scanner scanner = new Scanner(System.in);
    private String archiveInput;
    private Integer pageNumber;
    Page<Book> page;

    public ArchiveOptions(String archiveInput, Page<Book> page, Integer pageNumber) {
        this.archiveInput = archiveInput;
        this.page = page;
        this.pageNumber = pageNumber;
    }

    public void checkOption() {
        switch (archiveInput) {
            case "1":
                if (!page.isFirst()) {
                    pageNumber--;
                    EntryPoint.setPageNumber(pageNumber);
                }
                break;
            case "2":
                if (!page.isLast()) {
                    pageNumber++;
                    EntryPoint.setPageNumber(pageNumber);
                }
                break;
            case "3":
                boolean checkOption = false;

                while (!checkOption) {
                    Menu.askPage();

                    try {
                        pageNumber = scanner.nextInt();
                        scanner.nextLine();

                        if (pageNumber >= 1 && pageNumber <= page.getTotalPages()) {
                            EntryPoint.setPageNumber(pageNumber);
                            checkOption = true;
                            ScreenClear.clear();
                        } else {
                            Menu.invalidPage(page);
                        }

                    } catch (InputMismatchException e) {
                        Menu.invalidPage(page);
                        break;
                    }

                }
                break;
            case "0":
                archiveInput = "0";
                break;
            default:
                Menu.invalidOption();
                break;
        }
    }

}
