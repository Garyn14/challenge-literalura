package com.literatura.challenge_litura.repository;

import com.literatura.challenge_litura.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    boolean existsByTitle(String title);
}
