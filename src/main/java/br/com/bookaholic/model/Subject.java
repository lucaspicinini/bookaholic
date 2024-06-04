package br.com.bookaholic.model;

import jakarta.persistence.*;

@Entity
@Table(name = "temas")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private Book book;

    public Subject() {}

    public Subject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
