package com.literatura.challenge_litura.service;

import com.literatura.challenge_litura.model.Author;
import com.literatura.challenge_litura.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Iterable<Author> getAuthors(){
        return authorRepository.findAll();
    }

    public List<Author> getAuthorsVivosInYear(int year) {
        return authorRepository.getAuthorsVivoInYear(year);
    }

    public Author getAuthorByName(String name){
        return authorRepository
                .findFirstByNameContainingIgnoreCase(name)
                .orElse(null);
    }
}
