package br.com.bookaholic.utils;

public class Menu {
    private static final String mainMenu = """
            
            ***** Bookaholic *****
            
            +++ Escolha uma opção:
            1 - Exibir catálogo
            2 - Buscar por título ou autor
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

    public static void printMainMenu() {
        System.out.println(mainMenu);
    }

    public static void printCatalogueMenu() {
        System.out.println(catalogueMenu);
    }
}
