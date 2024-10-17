package com.libararymangementsystem.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="first_name")
    private String firstName;
   @Column(name="last_name")
    private String lastName;

   @Column(name = "email")
    private String email;
   @OneToMany(mappedBy = "author" , cascade ={CascadeType.DETACH,CascadeType.MERGE,
                                               CascadeType.PERSIST,CascadeType.REFRESH})
   private List<Book> books;

    public Author(){}

    public Author( String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

   //creating association method to add the book to that author
    //and to set the author in the book class to that current instance of author
    public void addBook(Book theBook){
        if(books==null){
            books=new ArrayList<>();
        }
        books.add(theBook);
        theBook.setAuthor(this);

    }
}
