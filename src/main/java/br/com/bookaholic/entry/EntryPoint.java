package br.com.bookaholic.entry;

import br.com.bookaholic.model.Book;
import br.com.bookaholic.model.DataIndex;
import br.com.bookaholic.service.ApiService;
import br.com.bookaholic.service.Mapper;

import java.util.List;
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
    private String apiSearchName = "";
    private String apiSearch = "&search=";
    private DataIndex dataIndex;

    public void init() {
        boolean exitLoop = false;

        while (!exitLoop) {
            System.out.println("***** Bookaholic *****");
            System.out.println("Escolha uma opção: ");
            System.out.println("1 - Exibir catálogo");
            System.out.println("2 - Buscar por título ou autor");
            System.out.println();
            System.out.println("0 - Sair");
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "0":
                    System.out.println("Até a próxima :) ...");
                    exitLoop = true;
                case "1":
                    responseBody = apiService.getResponseBody(apiUrl + apiLang + apiPagination);

                    if (responseBody != null) {
                        dataIndex = mapper.getClassFromJson(responseBody, DataIndex.class);
                        List<Book> books = dataIndex.books().stream().map(Book::new).toList();
                        books.forEach(book -> System.out.println(book.getTitle()));



//                        books.forEach(book -> book.getAuthors().forEach(author -> System.out.println(author.getName())));
//                        System.out.println("***** Catálogo Bookaholic *****");
//                        System.out.println(dataIndex.books());
                    }
                    break;
                case "2":
                    System.out.println("Digite um nome para busca: ");
                    apiSearchName = scanner.nextLine().replace(" ", "%20").toLowerCase();
                    responseBody = apiService.getResponseBody(apiUrl + apiLang + apiSearch + apiSearchName);
                    dataIndex = mapper.getClassFromJson(responseBody, DataIndex.class);
                    if (!dataIndex.books().isEmpty()) {
                        System.out.println(dataIndex.books());
                    } else {
                        System.out.println("Livro/Autor não encontrado.");
                    }
                    break;
                default:
                    System.out.println("Digite uma opção válida.");
            }
        }
    }
}
