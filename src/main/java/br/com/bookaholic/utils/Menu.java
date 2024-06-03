package br.com.bookaholic.utils;

import br.com.bookaholic.entry.EntryPoint;

public class Menu {
    private static final String mainMenu = """
            
            ***** Bookaholic *****
            
            +++ Escolha uma opção:
            1 - Exibir catálogo online
            2 - Buscar por título ou autor
            3 - Consultar livros salvos
            0 - Sair
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

    // EntryPoint
    public static void mainMenu() {
        System.out.println(mainMenu);
    }
    // EntryPoint
    public static void askName() { System.out.println("Digite um nome para busca: "); }
    // EntryPoint
    public static void exit() { System.out.println("Até a próxima :) ...\n"); }

    // Catalogue
    public static void pageNotFound() { System.out.println("Página: " + EntryPoint.getApiPage() + " não encontrada"); }
    // Catalogue
    public static void page() { System.out.println("***** Página: " + EntryPoint.getApiPage() + " *****\n"); }
    // Catalogue
    public static void backToCatalogue() { System.out.println("Voltando ao catálogo...\n"); }

    // CatalogueOptions
    public static void askPage() { System.out.println("Digite o número da página para busca: "); }
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

}
