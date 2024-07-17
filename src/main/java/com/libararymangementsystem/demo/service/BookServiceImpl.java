package com.libararymangementsystem.demo.service;

import com.libararymangementsystem.demo.dtos.BookDTO;
import com.libararymangementsystem.demo.dtos.BookWithAuthorDetailsDTO;
import com.libararymangementsystem.demo.dtos.BorrowerDTO;
import com.libararymangementsystem.demo.entity.Author;
import com.libararymangementsystem.demo.entity.Book;
import com.libararymangementsystem.demo.entity.Borrower;
import com.libararymangementsystem.demo.exceptionhandling.NotFoundException;
import com.libararymangementsystem.demo.repository.AuthorRepository;
import com.libararymangementsystem.demo.repository.BarrowerRepository;
import com.libararymangementsystem.demo.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BarrowerRepository barrowerRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public BookDTO save(BookDTO book) {
        List<Book> result=bookRepository.findByTitle(book.getTitle());
        if(!result.isEmpty()){
            throw new NotFoundException("Book Title Is Already Exist->> " + book.getTitle());
        }
        Book dbBook=modelMapper.map(book,Book.class);
        bookRepository.save(dbBook);
        return modelMapper.map(dbBook,BookDTO.class);
    }
    @Override
    public List<BookDTO> findAll() {
        List<Book> books=bookRepository.findAll();
        if(books.isEmpty()){
            throw new NotFoundException("OOPS! No BOOKS FOUND!!");
        }
        List<BookDTO> theBooksDTO=modelMapper.map(books,new TypeToken<List<BookDTO>>(){}.getType());
        return theBooksDTO;
    }
    @Override
    public BookDTO findById(int bookId) {
       Optional<Book> result=bookRepository.findById(bookId);
       Book dbBook=null;
       if(result.isPresent()){
           dbBook=result.get();
       }else{
           throw new NotFoundException("Book Not Found Id >> " + bookId);
       }
        BookDTO bookDTO=modelMapper.map(dbBook,BookDTO.class);


        return bookDTO;
    }
    @Override
    public void deleteById(int bookId) {
        Optional<Book> book=bookRepository.findById(bookId);
        if(!book.isPresent()){
            throw new NotFoundException("Book Not Found ID >> " + bookId);
        }
        bookRepository.deleteById(bookId);
    }

    @Override
    public void deleteAll() {
        List<Book> books=bookRepository.findAll();
        if(books.isEmpty()){
            throw new NotFoundException("No Books Found!!");
        }
        bookRepository.deleteAll();
    }

    //BORROWING A BOOK BY NEW CREATED BORROWER AND UPDATING THE BOOK QUANTITY
    @Override
    public void borrowBook(int bookId, BorrowerDTO theBorrower ) {
        Optional<Book> book=bookRepository.findById(bookId);
        Borrower baro=barrowerRepository.findByEmail(theBorrower.getEmail());
        Book dbBook=null;
        Borrower borrower;
        if(book.isEmpty()){
            throw new NotFoundException("Book Not Found Id-> " + bookId);
        } else if (baro!=null) {
            throw new NotFoundException("Email Is Already Exists " + theBorrower.getEmail());

        } else{
            dbBook=book.get();
            borrower=modelMapper.map(theBorrower, Borrower.class);
            dbBook.getBorrowers().add(borrower);
        }

         if (dbBook.getQuantity()<=0) {
            throw new NotFoundException("Book is out of stock");
        }
         //this part of code will throw null pointer exception
        //since borrow.getBooks() is still null
         /*else if (borrower.getBooks().size()>=3) {
            throw new NotFoundException("Borrower has reached the borrowing Limit");
            
        }*/
        dbBook.setQuantity(dbBook.getQuantity()-1);

    }

    @Override
    public void borrowingBook(int bookId, Borrower theBorrower) {
        Optional<Book> result=bookRepository.findById(bookId);
         Optional<Borrower> result2=barrowerRepository.findById(theBorrower.getId());
        Book theBook=null;
        Borrower borr=null;
        if(result.isPresent()&&result2.isPresent()){
            theBook=result.get();
            borr=result2.get();
            theBook.getBorrowers().add(borr);
        }
        theBook.setQuantity(theBook.getQuantity()-1);
        if(borr.getBooks().size()>=3){
            throw new NotFoundException("Only 3 Books Is Allowed For Booking");
        }
    }

}
