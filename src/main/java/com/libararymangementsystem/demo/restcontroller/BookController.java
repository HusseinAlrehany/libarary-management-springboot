package com.libararymangementsystem.demo.restcontroller;

import com.libararymangementsystem.demo.dtos.BookDTO;
import com.libararymangementsystem.demo.dtos.BookWithAuthorDetailsDTO;
import com.libararymangementsystem.demo.dtos.BorrowerDTO;
import com.libararymangementsystem.demo.entity.Borrower;
import com.libararymangementsystem.demo.exceptionhandling.SuccessResponse;
import com.libararymangementsystem.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping("/books")
    public ResponseEntity<List<BookDTO>> findAll(){

        return ResponseEntity.ok(bookService.findAll());
    }
    @GetMapping("/books/{bookId}")
    public ResponseEntity<BookDTO> findById(@PathVariable int bookId){
        BookDTO bookDTO=bookService.findById(bookId);
        return ResponseEntity.ok(bookDTO);
    }
    //GETTING A BOOK WITH AUTHOR DETAILS
    @PostMapping("/books")
    public ResponseEntity<SuccessResponse> addBook(@RequestBody BookDTO book){
        //setting the id to 0 in case a user enters an id in ..JSON
        book.setId(0);
        String message="Book Saved Successfully";
        SuccessResponse response=new SuccessResponse();
        response.setMessage(message);
        bookService.save(book);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/books")
    public ResponseEntity<BookDTO> updateBook(@RequestBody BookDTO book){
        BookDTO dbBook=bookService.save(book);

        return ResponseEntity.ok(dbBook);
    }
    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<SuccessResponse> deleteBook(@PathVariable int bookId){
        String message="Book Deleted Successfully!! ID -> " + bookId ;
        SuccessResponse response=new SuccessResponse();
        response.setMessage(message);
        bookService.deleteById(bookId);
        return ResponseEntity.ok(response);
    }

   @DeleteMapping("/books")
    public ResponseEntity<SuccessResponse> deleteAll(){
        String message="ALL BOOKS DELETED SUCCESSFULLY";
        SuccessResponse response=new SuccessResponse();
        response.setMessage(message);
        bookService.deleteAll();
        return ResponseEntity.ok(response);
   }

   //BORROWING A BOOK BY NEW CREATED BORROWER AND UPDATING THE BOOK QUANTITY
   @PostMapping("/borrower/{bookId}")
    public ResponseEntity<SuccessResponse> borrowBook(@PathVariable int bookId,
                                                      @RequestBody BorrowerDTO theBorrower){
        theBorrower.setBorrowingDate(LocalDateTime.now());
         bookService.borrowBook(bookId,theBorrower);
         String successMessage="Borrowing Book Is Successful " + bookId;
         SuccessResponse response=new SuccessResponse();
         response.setMessage(successMessage);

         return ResponseEntity.ok(response);
   }
    //BORROWING A CREATED BOOK BY CREATED BORROWER AND UPDATING THE BOOK QUANTITY
    @PostMapping("/borrowBook/{bookId}")
    public ResponseEntity<SuccessResponse> borrowingBook(@PathVariable int bookId,
                                                         @RequestBody Borrower theBorrower){
        theBorrower.setBorrowingDate(LocalDateTime.now());
        bookService.borrowingBook(bookId,theBorrower);
        String message="Borrowing Book Is Done Successfully " + bookId;
        SuccessResponse response=new SuccessResponse();
        response.setMessage(message);

        return ResponseEntity.ok(response);

    }

}
