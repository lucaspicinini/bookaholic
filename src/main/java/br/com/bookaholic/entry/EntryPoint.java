package br.com.bookaholic.entry;

import br.com.bookaholic.model.Book;
import br.com.bookaholic.model.DataIndex;
import br.com.bookaholic.utils.Catalogue;
import br.com.bookaholic.utils.CatalogueOptions;
import br.com.bookaholic.utils.Menu;
import br.com.bookaholic.service.ApiService;
import br.com.bookaholic.service.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EntryPoint {
    private final Scanner scanner = new Scanner(System.in);
    private final ApiService apiService = new ApiService();
    private final Mapper mapper = new Mapper();
    private static DataIndex dataIndex;
    private static Integer apiPageNumber = 1;
    private static String apiPage = String.valueOf(apiPageNumber);
    private static String userInput = "";
    private String apiSearchName = "";
    private String responseBody;

    public void init() {
        boolean exitLoop = false;

        while (!exitLoop) {
            if (userInput.isEmpty()) {
                Menu.printMainMenu();
                userInput = scanner.nextLine();
            }

            switch (userInput) {
                case "1":
                    System.out.println("Estabelecendo conexão...\n");
                    responseBody = apiService
                            .getResponseBody("https://gutendex.com/books/?languages=pt&page=" + apiPage);

                    if (responseBody != null) {
                        dataIndex = mapper.getClassFromJson(responseBody, DataIndex.class);
                        Catalogue catalogue = new Catalogue(dataIndex);
                        catalogue.load();
                    }
                    break;
                case "2":
                    System.out.println("Digite um nome para busca: ");
                    apiSearchName = scanner.nextLine().replace(" ", "%20").toLowerCase();
                    responseBody = apiService
                            .getResponseBody("https://gutendex.com/books/?languages=pt&search=" + apiSearchName);
                    dataIndex = mapper.getClassFromJson(responseBody, DataIndex.class);

                    if (!dataIndex.books().isEmpty()) {
                        List<Book> books = dataIndex.books().stream().map(Book::new).toList();
                        books.forEach(Book::printBook);
                    } else {
                        System.out.println("Livro/Autor não encontrado.");
                    }
                    break;
                case "0":
                    System.out.println("Até a próxima :) ...\n");
                    exitLoop = true;
                default:
                    System.out.println("Digite uma opção válida.");
                    userInput = "";
            }
        }
    }

    public static String getApiPage() {
        return apiPage;
    }

    public static void setApiPage(String apiPage) {
        EntryPoint.apiPage = apiPage;
    }

    public static Integer getApiPageNumber() {
        return apiPageNumber;
    }

    public static void setApiPageNumber(Integer apiPageNumber) {
        EntryPoint.apiPageNumber = apiPageNumber;
    }

    public static DataIndex getDataIndex() {
        return dataIndex;
    }

    public static void setUserInput(String userInput) {
        EntryPoint.userInput = userInput;
    }
}
