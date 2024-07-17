package com.libararymangementsystem.demo.service;

import com.libararymangementsystem.demo.dtos.BookDTO;
import com.libararymangementsystem.demo.dtos.BookWithAuthorDetailsDTO;
import com.libararymangementsystem.demo.dtos.BorrowerDTO;
import com.libararymangementsystem.demo.entity.Borrower;

import java.util.List;

public interface BookService {
    BookDTO save(BookDTO book);

    List<BookDTO> findAll();

    BookDTO findById(int bookId);

    void deleteById(int bookId);

    void deleteAll();

    void borrowBook(int bookId, BorrowerDTO theBorrower);

    void borrowingBook(int bookId, Borrower theBorrower);


}