package com.libararymangementsystem.demo.service;

import com.libararymangementsystem.demo.dtos.BookDTO;
import com.libararymangementsystem.demo.dtos.BorrowerDTO;
import com.libararymangementsystem.demo.entity.Book;
import com.libararymangementsystem.demo.entity.Borrower;

import java.util.List;

public interface BarrowerService {
    BorrowerDTO save(BorrowerDTO theBarrower);


    List<BorrowerDTO> findAll();

    BorrowerDTO findById(int barrowerId);

    void deleteById(int barrowerId);

    void deleteAll();

    BorrowerDTO saveUpdate(BorrowerDTO theBorrower);

    void addBookToBorrower(int borrowerId, BookDTO theBook);

    void addExBookToExBarrower(int borrowerId, Book theBook);
}
