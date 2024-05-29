package br.com.bookaholic.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Author {
    private String name;
    private String birthYear;
    private String deathYear;

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
