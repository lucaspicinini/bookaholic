package br.com.bookaholic.utils;

import br.com.bookaholic.entry.EntryPoint;

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

    public static void mainMenu() {
        System.out.println(mainMenu);
    }

    public static void catalogueMenu() {
        System.out.println(catalogueMenu);
    }

    public static void connecting() { System.out.println("Estabelecendo conexão...\n"); }

    public static void askName() { System.out.println("Digite um nome para busca: "); }

    public static void askPage() { System.out.println("Digite o número da página para busca: "); }

    public static void notFound() { System.out.println("Livro/Autor não encontrado."); }

    public static void pageNotFound() { System.out.println("Página: " + EntryPoint.getApiPage() + " não encontrada"); }

    public static void page() { System.out.println("***** Página: " + EntryPoint.getApiPage() + " *****\n"); }

    public static void invalidOption() { System.out.println("Digite uma opção válida."); }

    public static void backToCatalogue() { System.out.println("Voltando ao catálogo...\n"); }

    public static void exit() { System.out.println("Até a próxima :) ...\n"); }
}
