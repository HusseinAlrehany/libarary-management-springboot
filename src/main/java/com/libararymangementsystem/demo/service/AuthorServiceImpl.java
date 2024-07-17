package com.libararymangementsystem.demo.service;

import com.libararymangementsystem.demo.converter.EntityToDTOAndViceVersa;
import com.libararymangementsystem.demo.dtos.AuthorDTO;
import com.libararymangementsystem.demo.dtos.AuthorInfo;
import com.libararymangementsystem.demo.dtos.BookDTO;
import com.libararymangementsystem.demo.dtos.BookWithAuthorDetailsDTO;
import com.libararymangementsystem.demo.entity.Author;
import com.libararymangementsystem.demo.entity.Book;
import com.libararymangementsystem.demo.exceptionhandling.NotFoundException;
import com.libararymangementsystem.demo.repository.AuthorRepository;
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
public class AuthorServiceImpl implements AuthorService{
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AuthorDTO> findAll() {
        List<Author> authors=authorRepository.findAll();
        if(authors.isEmpty()){
            throw new NotFoundException("NO AUTHORS FOUND!");
        }
        List<AuthorDTO> authorDTOS=modelMapper.map(authors,new TypeToken<List<AuthorDTO>>(){}.getType());
        return authorDTOS;
    }

    @Override
    public AuthorDTO findById(int authorId) {
        Optional<Author> result=authorRepository.findById(authorId);
        Author dbAuthor=null;
        if(result.isPresent()){
            dbAuthor=result.get();
        }else{
            throw new NotFoundException("Author Not Found ID >> " + authorId);
        }
                AuthorDTO authorDTO=modelMapper.map(dbAuthor,AuthorDTO.class);
           return authorDTO;

    }

    @Override
    public AuthorDTO saveAuthor(AuthorDTO authorDTO) {
             Author author=modelMapper.map(authorDTO,Author.class);
             Author result=authorRepository.findByEmail(author.getEmail());
             if(result!=null){
                 throw new NotFoundException("Email is Already taken -> " + author.getEmail());
             }
             author.setId(0);
             author=authorRepository.save(author);

           return modelMapper.map(author,AuthorDTO.class);
       }


    @Override
    public void deletById(int authorId) {
        Optional<Author> result=authorRepository.findById(authorId);
        Author dbAuthor=null;
        if(result.isEmpty()){
            throw new RuntimeException("Author Not Found ID >> " + authorId);
        }
        dbAuthor=result.get();
        List<Book> books=dbAuthor.getBooks();
        for(Book book:books){
            book.setAuthor(null);
        }
        authorRepository.deleteById(authorId);
    }


    //ADDING A NEW BOOK TO EXISTING CREATED AUTHOR
    @Override
    public void addBookToAuthor(int authorId, BookDTO theBookDTO) {
        Book theBook=modelMapper.map(theBookDTO,Book.class);
        List<Book> books=bookRepository.findByTitle(theBook.getTitle());
        if(!books.isEmpty()){
            throw new NotFoundException("Book Title Already Exist  ->> " + theBook.getTitle());
        }

        Optional<Author> result=authorRepository.findById(authorId);
        Author dbAuthor=null;
        if(result.isPresent()){
            dbAuthor=result.get();
            dbAuthor.addBook(theBook);
        }
        else{
            throw new NotFoundException("Author Not Found Id >> " + authorId);
        }
    }

    @Override
    public void updateAuthor(AuthorDTO authorDTO) {
        Author author=modelMapper.map(authorDTO,Author.class);
        Optional<Author> result=authorRepository.findById(author.getId());
        if(result.isEmpty()){
            throw new NotFoundException("Author Not Found Id ->> " + author.getId() );
        }
        authorRepository.save(author);


       /* Author dbAuthor=authorRepository.findByEmail(author.getEmail());
        if(dbAuthor!=null){
            dbAuthor.setFirstName(author.getFirstName());
            dbAuthor.setLastName(author.getLastName());
            dbAuthor.setEmail(author.getEmail());

            authorRepository.save(dbAuthor);
        }else{
            authorRepository.save(author);
        }*/


    }


    @Override
    public BookWithAuthorDetailsDTO findAuthorWithBook(int authorId) {
        Optional<Author> result=authorRepository.findById(authorId);
       List<Book>theBook=bookRepository.findByAuthorId(authorId);
       if(result.isEmpty()){
           throw new NotFoundException("No Author Found Id-> " + authorId);
       }
       if(theBook.isEmpty()){
           throw new NotFoundException("No Books Found For Author ID-> " + authorId);
       }

        BookWithAuthorDetailsDTO bookWithAuthorDetailsDTO=new BookWithAuthorDetailsDTO();
        bookWithAuthorDetailsDTO.setAuthor_FirstName(result.get().getFirstName());
        bookWithAuthorDetailsDTO.setAuthor_LastName(result.get().getLastName());
        bookWithAuthorDetailsDTO.setAuthorEmail(result.get().getEmail());
        for(Book books:theBook){
            bookWithAuthorDetailsDTO.setBook_Title(books.getTitle());
            bookWithAuthorDetailsDTO.setBook_Quantity(books.getQuantity());
        }

        return bookWithAuthorDetailsDTO;
    }
}
