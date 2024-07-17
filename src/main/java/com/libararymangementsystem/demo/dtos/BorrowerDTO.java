package com.libararymangementsystem.demo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BorrowerDTO implements Serializable {

    private int id;
    @JsonProperty("Borrower-Name")
    private String name;
    @JsonProperty("Borrower-Email")
    private String email;
    @JsonProperty("Borrower-Mobil")
    private String mobil;
    @JsonProperty("Borrowing-Date")
    private LocalDateTime borrowingDate;
    @JsonProperty("Borrowed-Books")
    private List<BookInfo> borrowedBooks;

    public  BorrowerDTO(){}

    public BorrowerDTO(String name, String email, String mobil, LocalDateTime borrowingDate,List<BookInfo> borrowedBooks) {
        this.name = name;
        this.email = email;
        this.mobil = mobil;
        this.borrowingDate = borrowingDate;
        this.borrowedBooks=borrowedBooks;
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

    public void setBorrowedBooks(List<BookInfo> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}
