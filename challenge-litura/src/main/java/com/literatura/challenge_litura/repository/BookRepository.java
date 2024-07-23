package com.literatura.challenge_litura.repository;

import com.literatura.challenge_litura.model.Book;
import com.literatura.challenge_litura.model.StatisticsByLanguage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    boolean existsByTitle(String title);

    @Query("SELECT new com.literatura.challenge_litura.model.StatisticsByLanguage(b.language, COUNT(b)) FROM Book b GROUP BY b.language")
    List<StatisticsByLanguage> getStatisticsByLanguage();

    List<Book> findTop5ByOrderByDownloadCountDesc();
}
