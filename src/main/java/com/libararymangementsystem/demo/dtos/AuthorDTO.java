package com.libararymangementsystem.demo.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.libararymangementsystem.demo.entity.Book;

import java.io.Serializable;
import java.util.List;

public class AuthorDTO implements Serializable {

    private int id;

    @JsonProperty(value = "AuthorFirstName")
    private String firstName;

    @JsonProperty(value = "AuthorLastName")
    private String lastName;

    @JsonProperty(value = "AuthorUserName")
    private String email;

    @JsonProperty("AuthorBooks")
    private List<BookInfo> books;
    public AuthorDTO(){

    }

    public AuthorDTO(String firstName, String lastName, String email,List<BookInfo> books) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.books=books;
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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<BookInfo> getBooks() {
        return books;
    }

    public void setBooks(List<BookInfo> books) {
        this.books = books;
    }
}
