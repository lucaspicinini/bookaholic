package br.com.bookaholic.utils;

import br.com.bookaholic.entry.EntryPoint;
import br.com.bookaholic.model.DataIndex;

import java.util.Scanner;

public class CatalogueOptions {
    private final Scanner scanner = new Scanner(System.in);
    private final DataIndex dataIndex;
    private final String userOption;
    private String apiPage;
    private Integer apiPageNumber;

    public CatalogueOptions(String userOption) {
        this.dataIndex = EntryPoint.getDataIndex();
        this.userOption = userOption;
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
                ScreenClear.clear();
                System.out.println("Digite o número da página para busca: ");
                apiPageNumber = scanner.nextInt();
                scanner.nextLine();
                apiPage = String.valueOf(apiPageNumber).trim();
                EntryPoint.setApiPageNumber(apiPageNumber);
                EntryPoint.setApiPage(apiPage);
                break;
            case "4":
                break;
            case "5":
                break;
            case "0":
                EntryPoint.setUserInput("");
                break;
            default:
                System.out.println("Selecione uma opção válida.");
        }
    }
}
