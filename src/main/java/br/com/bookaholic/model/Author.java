package br.com.bookaholic.model;

public class Author {
    private final String name;
    private final String birthYear;
    private final String deathYear;

    public Author(AuthorData author) {
        this.name = author.name();
        this.birthYear = author.birthYear();
        this.deathYear = author.deathYear();
    }

    public String getName() {
        return name;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public String getDeathYear() {
        return deathYear;
    }
}
