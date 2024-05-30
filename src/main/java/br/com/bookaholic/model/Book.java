package br.com.bookaholic.model;

import java.util.List;

public class Book {
    private final Integer id;
    private final String title;
    private final Boolean copyright;
    private final String copyrightText;
    private final Integer downloadCount;
    private final List<String> subjects;
    private final List<Author> authors;

    public Book(BookData book) {
        this.id = book.id();
        this.title = book.title();
        this.copyright = book.copyright();
        this.copyrightText = this.copyright ? "Sim" : "Não";
        this.downloadCount = book.downloadCount();
        this.subjects = book.subjects();
        this.authors = book.authors().stream().map(Author::new).toList();
    }

    public void printBook() {
        System.out.println("**** Livro: " + this.title + " ****");
        System.out.println("--- Id: " + this.id);
        this.authors.forEach(author -> System.out.println("--- Autores: " + author.getName()));
        System.out.println("--- Possui copyright? " + this.copyrightText);
        System.out.println("--- Número de downloads: " + this.downloadCount);
        System.out.println("--- Temas: ");
        this.subjects.forEach(subject -> System.out.println("+ " + subject));
        System.out.println();
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getCopyright() {
        return copyright;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public List<Author> getAuthors() {
        return authors;
    }
}
