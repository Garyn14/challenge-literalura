package com.literatura.challenge_litura.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "books")
@Getter @NoArgsConstructor
@AllArgsConstructor
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    private String language;

    private Long downloadCount;

    public Book(BookApi bookApi) {
        this.title = bookApi.title();
        this.author = new Author(bookApi.getFirstAuthor());
        this.language = bookApi.getFirstLanguage();
        this.downloadCount = bookApi.downloadCount();
    }
}
