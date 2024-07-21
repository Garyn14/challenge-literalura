package com.literatura.challenge_litura.service;

import com.literatura.challenge_litura.model.Author;
import com.literatura.challenge_litura.model.Book;
import com.literatura.challenge_litura.repository.AuthorRepository;
import com.literatura.challenge_litura.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Book createBook(Book book){
        // search author in db if not exists we will create one
        Author author =  authorRepository
                .findByName(book.getAuthor().getName())
                .orElseGet(() -> {
                    return authorRepository.save(book.getAuthor());
                });

        book.setAuthor(author);
        return bookRepository.save(book);
    }

    public boolean existsByTitle(String title) {
        return bookRepository.existsByTitle(title);
    }
}
