package br.com.bookaholic.entry;

import br.com.bookaholic.model.DataIndex;
import br.com.bookaholic.service.ApiService;
import br.com.bookaholic.service.Mapper;

import java.util.Scanner;

public class EntryPoint {
    private Scanner scanner = new Scanner(System.in);
    private ApiService apiService = new ApiService();
    private Mapper mapper = new Mapper();
    private String responseBody;
    private int apiPage = 1;
    private String apiUrl = "https://gutendex.com/books/?languages=";
    private String apiLang = "pt";
    private String apiPagination = "&page=" + apiPage;
    private String apiSearch = "&search=";
    private DataIndex dataIndex;

    public void init() {
        System.out.println("***** Bookaholic *****");
        System.out.println("Escolha uma opção: ");
        System.out.println("1 - Exibir catálogo");
        System.out.println("2 - Buscar por título ou autor");
        String userInput = scanner.nextLine();

        switch (userInput) {
            case "1":
                responseBody = apiService.getResponseBody(apiUrl + apiLang + apiPagination);
                dataIndex = mapper.getClassFromJson(responseBody, DataIndex.class);
                System.out.println(responseBody);
                System.out.println(dataIndex.books());
                break;
            case "2":
                System.out.println("Digite um nome para busca: ");
                String apiSearchName = scanner.nextLine();
                System.out.println(apiUrl + apiLang + apiSearch);
                responseBody = apiService.getResponseBody(apiUrl + apiLang + apiSearch + apiSearchName);
                dataIndex = mapper.getClassFromJson(responseBody, DataIndex.class);
                System.out.println(responseBody);
                System.out.println(dataIndex.books());
                break;
            default:
                System.out.println("Digite uma opção válida.");
        }
    }
}
