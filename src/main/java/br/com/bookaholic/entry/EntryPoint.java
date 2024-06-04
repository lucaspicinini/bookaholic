package br.com.bookaholic.entry;

import br.com.bookaholic.controller.ArchiveOptions;
import br.com.bookaholic.model.Book;
import br.com.bookaholic.model.DataIndex;
import br.com.bookaholic.repository.BookRepository;
import br.com.bookaholic.controller.Catalogue;
import br.com.bookaholic.utils.Menu;
import br.com.bookaholic.service.ApiService;
import br.com.bookaholic.service.Mapper;
import br.com.bookaholic.utils.ScreenClear;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Scanner;

public class EntryPoint {
    private final Scanner scanner = new Scanner(System.in);
    private final ApiService apiService = new ApiService();
    private final Mapper mapper = new Mapper();
    private final BookRepository bookRepository;
    private static DataIndex dataIndex;
    private static Integer apiPageNumber = 1;
    private static String apiPage = String.valueOf(apiPageNumber);
    private static String userInput = "";
    private static String archiveInput = "";
    private static Integer pageNumber = 1;
    private String responseBody;

    public EntryPoint(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void init() {
        ScreenClear.clear();
        boolean exitLoop = false;
        
        while (!exitLoop) {
            
            if (userInput.isEmpty()) {
                Menu.mainMenu();
                userInput = scanner.nextLine();
                ScreenClear.clear();
            }

            switch (userInput) {
                case "1":
                    Menu.connecting();
                    responseBody = apiService
                            .getResponseBody("https://gutendex.com/books/?languages=pt&page=" + apiPage);
                    
                    if (responseBody != null) {
                        dataIndex = mapper.getClassFromJson(responseBody, DataIndex.class);
                        Catalogue catalogue = new Catalogue(dataIndex, bookRepository);
                        catalogue.load();
                    }
                    
                    break;
                case "2":
                    Menu.askName();
                    String apiSearchName = scanner.nextLine().replace(" ", "%20").toLowerCase();
                    responseBody = apiService
                            .getResponseBody("https://gutendex.com/books/?languages=pt&search=" + apiSearchName);
                    dataIndex = mapper.getClassFromJson(responseBody, DataIndex.class);
                    
                    if (!dataIndex.books().isEmpty()) {
                        ScreenClear.clear();
                        List<Book> books = dataIndex.books().stream().map(Book::new).toList();
                        books.forEach(Book::printBook);
                    } else {
                        ScreenClear.clear();
                        Menu.notFound();
                    }

                    Menu.mainMenu();
                    userInput = scanner.nextLine();
                    ScreenClear.clear();
                    break;
                case "3":
                    while (!archiveInput.equals("0")) {
                        Pageable pageable = PageRequest.of(pageNumber - 1, 10);
                        Page<Book> page = bookRepository.findAll(pageable);
                        page.forEach(Book::printBook);
                        Menu.archiveMenu();
                        Menu.archiveMenuInfo(pageNumber, page);
                        Menu.askOption();
                        archiveInput = scanner.nextLine();
                        ScreenClear.clear();
                        ArchiveOptions archiveOptions = new ArchiveOptions(archiveInput, page, pageNumber);
                        archiveOptions.load();
                    }

                    archiveInput = "";
                    userInput = "";
                    break;
                case "0":
                    Menu.exit();
                    exitLoop = true;
                    break;
                default:
                    Menu.invalidOption();
                    userInput = "";
                    break;
            }
        }
    }

    public static void checkNullResponseBody(String responseBody, Mapper mapper, BookRepository bookRepository) {
        if (responseBody != null) {
            BookData bookDataById = mapper.getClassFromJson(responseBody, BookData.class);
            ScreenClear.clear();

            if (bookDataById.invalidPage() == null) {
                Book bookById = new Book(bookDataById);
                Menu.saving();

                try {
                    bookRepository.save(bookById);
                    Menu.saved();
                } catch (DataIntegrityViolationException e) {
                    Menu.alreadySaved();
                }

                bookById.printBook();
            } else {
                Menu.notFound();
            }

        }
    }

    public static void setPageNumber(Integer pageNumber) {
        EntryPoint.pageNumber = pageNumber;
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
