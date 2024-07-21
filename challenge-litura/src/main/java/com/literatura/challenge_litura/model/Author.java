package com.literatura.challenge_litura.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "authors")
@Getter @NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;

    private Integer birthYear;
    private Integer deathYear;

    public Author(PersonApi firstAuthor) {
        if (firstAuthor!= null){
            this.name = firstAuthor.name();
            this.birthYear = firstAuthor.birthYear();
            this.deathYear = firstAuthor.deathYear();
        }
    }
}
