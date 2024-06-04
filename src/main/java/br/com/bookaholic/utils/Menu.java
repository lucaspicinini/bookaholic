package br.com.bookaholic.utils;

import br.com.bookaholic.entry.EntryPoint;
import br.com.bookaholic.model.Book;
import org.springframework.data.domain.Page;

public class Menu {
    private static final String mainMenu = """
            
            ***** Bookaholic *****
            
            +++ Escolha uma opção:
            1 - Exibir catálogo online
            2 - Buscar por título ou autor
            3 - Consultar livros salvos
            0 - Sair
            """;
    private static final String searchMenu = """
            +++ Escolha uma opção:
            1 - Salvar todos os livros desta página
            2 - Salvar livro por id
            0 - Voltar ao menu principal
            """;
    private static final String catalogueMenu = """
            +++ Menu do Catálogo:
            
            1 - Página anterior
            2 - Próxima página
            3 - Buscar página pelo número
            4 - Salvar todos os livros desta página
            5 - Salvar livro por id
            0 - Voltar ao menu principal
            """;
    private static final String archiveMenu = """
            +++ Escolha uma opção:
            1 - Página anterior
            2 - Pŕoxima página
            3 - Buscar pelo número da página
            0 - Voltar ao menu principal
            """;

    // EntryPoint
    public static void mainMenu() {
        System.out.println(mainMenu);
    }
    // EntryPoint
    public static void searchMenu() {
        System.out.println(searchMenu);
    }
    // EntryPoint
    public static void archiveMenu() { System.out.println(archiveMenu); }
    // EntryPoint
    public static void askName() { System.out.println("Digite um nome para busca: "); }
    // EntryPoint
    public static void askOption() { System.out.println("Selecione uma opção: "); }
    // EntryPoint
    public static void exit() { System.out.println("Até a próxima :) ...\n"); }
    // EntryPoint
    public static void archiveMenuInfo (int pageNumber, Page<Book> page) {
        System.out.println("\nExibindo página " + pageNumber + " de " + page.getTotalPages());
        System.out.println("Livros nesta página: " + page.getNumberOfElements());
        System.out.println("Total de Livros Armazenados: " + page.getTotalElements() + "\n");
    }
    public static void invalidPage (Page<Book> page) {
        System.out.println("Digite uma página entre 1 e " + page.getTotalPages() + ".");
    }

    // Catalogue
    public static void pageNotFound() { System.out.println("Página: " + EntryPoint.getApiPage() + " não encontrada"); }
    // Catalogue
    public static void page() { System.out.println("***** Página: " + EntryPoint.getApiPage() + " *****\n"); }
    // Catalogue
    public static void backToCatalogue() { System.out.println("Voltando ao catálogo...\n"); }

    // CatalogueOptions
    public static void askId() { System.out.println("Digite o id do livro: "); }
    // CatalogueOptions
    public static void saving() { System.out.println("Salvando...\n"); }
    // CatalogueOptions
    public static void saved() { System.out.println("Salvo com sucesso!\n"); }
    // CatalogueOptions
    public static void alreadySaved() { System.out.println("Livro/Livros já armazenado(s) anteriormente!\n"); }

    // EntryPoint and Catalogue
    public static void catalogueMenu() {
        System.out.println(catalogueMenu);
    }
    // EntryPoint and CatalogueOptions
    public static void connecting() { System.out.println("Estabelecendo conexão...\n"); }
    // EntryPoint and CatalogueOptions
    public static void invalidOption() { System.out.println("Digite uma opção válida."); }
    // EntryPoint and CatalogueOptions
    public static void notFound() { System.out.println("Livro/Autor não encontrado."); }
    // EntryPoint and CatalogueOptions
    public static void askPage() { System.out.println("Digite o número da página para busca: "); }

}
