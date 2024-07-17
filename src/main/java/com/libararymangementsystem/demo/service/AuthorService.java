package com.libararymangementsystem.demo.service;

import com.libararymangementsystem.demo.dtos.AuthorDTO;
import com.libararymangementsystem.demo.dtos.AuthorInfo;
import com.libararymangementsystem.demo.dtos.BookDTO;
import com.libararymangementsystem.demo.dtos.BookWithAuthorDetailsDTO;
import com.libararymangementsystem.demo.entity.Author;
import com.libararymangementsystem.demo.entity.Book;

import java.util.List;

public interface AuthorService {
    List<AuthorDTO> findAll();

    AuthorDTO findById(int authorId);

    AuthorDTO saveAuthor(AuthorDTO authorDTO);

    void deletById(int authorId);

    void addBookToAuthor(int authorId, BookDTO theBookDTO);

    void updateAuthor(AuthorDTO authorDTO);

    BookWithAuthorDetailsDTO findAuthorWithBook(int authorId);
}
