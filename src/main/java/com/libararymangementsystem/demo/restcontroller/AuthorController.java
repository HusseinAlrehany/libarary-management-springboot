package com.libararymangementsystem.demo.restcontroller;

import com.libararymangementsystem.demo.dtos.AuthorDTO;
import com.libararymangementsystem.demo.dtos.AuthorInfo;
import com.libararymangementsystem.demo.dtos.BookDTO;
import com.libararymangementsystem.demo.dtos.BookWithAuthorDetailsDTO;
import com.libararymangementsystem.demo.entity.Author;
import com.libararymangementsystem.demo.entity.Book;
import com.libararymangementsystem.demo.exceptionhandling.SuccessResponse;
import com.libararymangementsystem.demo.exceptionhandling.SuccessResponseWithEntity;
import com.libararymangementsystem.demo.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthorController {
    @Autowired
    private AuthorService authorService;


    @GetMapping("/authors")
    public List<AuthorDTO> findAll() {
        List<AuthorDTO> authorsdtos = authorService.findAll();
        return authorsdtos;
    }

    @GetMapping("/authors/{authorId}")
    public ResponseEntity<AuthorDTO> findById(@PathVariable int authorId){
        AuthorDTO dbAuthorDTO=authorService.findById(authorId);

        return ResponseEntity.ok(dbAuthorDTO);

    }
    @PostMapping("/authors")
    public ResponseEntity<SuccessResponse> saveAuthor(@RequestBody AuthorDTO authorDTO){
       //set the id to 0 in case a user enters an id in JSON
       // author.setId(0);
        String successMessage="Author Saved Successfully!";
        SuccessResponse response=new SuccessResponse();
        response.setMessage(successMessage);
        authorService.saveAuthor(authorDTO);
        return ResponseEntity.ok(response);

    }
    @PutMapping("/authors")
    public ResponseEntity<SuccessResponseWithEntity<AuthorDTO>> updateAuthor(@RequestBody AuthorDTO authorDTO){
        String successMessage="Author Updated Successfully!";
        SuccessResponseWithEntity<AuthorDTO> response=new SuccessResponseWithEntity<>();
        response.setMessage(successMessage);
        response.setEntity(authorDTO);
        authorService.updateAuthor(authorDTO);

        return ResponseEntity.ok(response) ;
    }

    @DeleteMapping("/authors/{authorId}")
    public ResponseEntity<SuccessResponse> deleteAuthor(@PathVariable int authorId){

       String successMessage="Author Deleted Successfully ID->>" + authorId;
       SuccessResponse response=new SuccessResponse();
       response.setMessage(successMessage);
        authorService.deletById(authorId);

        return ResponseEntity.ok(response);
    }
    //end point for adding a book to an author
   @PostMapping("/authors/{authorId}")
    public ResponseEntity<SuccessResponse> addBookToAuthor(@PathVariable int authorId,
                                  @RequestBody BookDTO theBookDTO){
        String successMessage="Book Added To Author ID ->> " + authorId;
        SuccessResponse response=new SuccessResponse();
        response.setMessage(successMessage);
        authorService.addBookToAuthor(authorId,theBookDTO);

        return ResponseEntity.ok(response);
    }
    //GETTING AN AUTHOR WITH RELATED BOOKS
    @GetMapping("/AuthorBooks/{authorId}")
    public ResponseEntity<BookWithAuthorDetailsDTO> findAuthorWithBook(@PathVariable int authorId){

        BookWithAuthorDetailsDTO bookWithAuthorDetailsDTO=authorService.findAuthorWithBook(authorId);
        return ResponseEntity.ok(bookWithAuthorDetailsDTO);
    }

    //end point for adding an existing book to an existing author
   /* @PostMapping("/authors/{authorId}")
    public String addBookToAuthor(@PathVariable int authorId,
                                   @RequestBody Book theBook) {
        authorService.addBookToAuthor(authorId,theBook);
        return "BOOK ADDED TO AUTHOR ID->> " + authorId;
    }*/


}
