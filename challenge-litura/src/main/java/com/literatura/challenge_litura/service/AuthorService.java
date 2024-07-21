package com.literatura.challenge_litura.service;

import com.literatura.challenge_litura.model.Author;
import com.literatura.challenge_litura.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Iterable<Author> getAuthors(){
        return authorRepository.findAll();
    }
}
