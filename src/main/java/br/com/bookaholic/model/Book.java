package br.com.bookaholic.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Book {
    int id;
    String title;
    // List<Author> authors;
    List<String> subjects = new ArrayList<>();
    boolean copyright;
    int downloadCount;

    public Book(Object book) {
    }
}
