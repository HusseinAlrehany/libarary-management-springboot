package com.libararymangementsystem.demo.repository;

import com.libararymangementsystem.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {

    public List<Book> findByTitle(String title);

    List<Book> findByAuthorId(int authorId);
}
