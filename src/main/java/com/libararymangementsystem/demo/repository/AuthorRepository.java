package com.libararymangementsystem.demo.repository;

import com.libararymangementsystem.demo.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author,Integer> {

    public Author findByEmail(String email);

}
