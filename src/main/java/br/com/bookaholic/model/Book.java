package br.com.bookaholic.model;

import java.util.List;

public class Book {
    private Integer id;
    private String title;
    private Boolean copyright;
    private Integer downloadCount;
    private List<String> subjects;
    private List<Author> authors;

    public Book(BookData book) {
        this.id = book.id();
        this.title = book.title();
        this.copyright = book.copyright();
        this.downloadCount = book.downloadCount();
        this.subjects = book.subjects();
        this.authors = book.authors().stream().map(Author::new).toList();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCopyright() {
        return copyright;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
