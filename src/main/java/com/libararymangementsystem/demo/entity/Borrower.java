package com.libararymangementsystem.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="borrower")
public class Borrower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="email")
    private String email;
    @Column(name="mobil")
    private String mobil;
    @Column(name="borrowing-date")
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    //@JsonDeserialize(using = LocalDateTimeDeserializer.class)
   // private Date borrowingDate;
   private LocalDateTime borrowingDate;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name="book-borrower",
    joinColumns = @JoinColumn(name="borrower_id"),
    inverseJoinColumns = @JoinColumn(name="book_id"))
    private List<Book> books;

    public Borrower(){}

    public Borrower(String name, String email, String mobil, LocalDateTime borrowingDate) {
        this.name = name;
        this.email = email;
        this.mobil = mobil;
        this.borrowingDate = borrowingDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobil() {
        return mobil;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    public LocalDateTime getBorrowingDate() {
        return borrowingDate;
    }

    public void setBorrowingDate(LocalDateTime borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    //association method for booking a book by a borrower
    public void addBookByborrower(Book book){
        if(books==null){
            books=new ArrayList<>();
        }
        books.add(book);

    }
}
