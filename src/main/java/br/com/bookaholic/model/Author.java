package br.com.bookaholic.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String birthYear;
    private String deathYear;
    @ManyToMany(mappedBy = "authors")
    private List<Book> books = new ArrayList<>();

    public Author() {}

    public Author(AuthorData author) {
        this.name = author.name();
        this.birthYear = author.birthYear();
        this.deathYear = author.deathYear();
    }

    public String getName() {
        return name;
    }

    public void setBooks(Book book) {
        this.books.add(book);
    }
}
