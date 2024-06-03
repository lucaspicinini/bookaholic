package br.com.bookaholic.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "livros")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private Integer idBook;
    @Column(nullable = false, length = 510)
    private String title;
    private Boolean copyright;
    @Transient
    private String copyrightText;
    private Integer downloadCount;
    @Transient
    private List<String> subjects;
    @Transient
    private List<Author> authors;

    protected Book() {}

    public Book(BookData book) {
        this.idBook = book.idBook();
        this.title = book.title();
        this.copyright = book.copyright();
        this.copyrightText = this.copyright ? "Sim" : "Não";
        this.downloadCount = book.downloadCount();
        this.subjects = book.subjects();
        this.authors = book.authors().stream().map(Author::new).toList();
    }

    public void printBook() {
        System.out.println("**** Livro: " + this.title + " ****");
        System.out.println("--- Id: " + this.idBook);
        this.authors.forEach(author -> System.out.println("--- Autores: " + author.getName()));
        System.out.println("--- Possui copyright? " + this.copyrightText);
        System.out.println("--- Número de downloads: " + this.downloadCount);
        System.out.println("--- Temas: ");
        this.subjects.forEach(subject -> System.out.println("+ " + subject));
        System.out.println();
    }

    public Integer getIdBook() {
        return idBook;
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
