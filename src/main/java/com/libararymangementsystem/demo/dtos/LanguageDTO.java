package com.libararymangementsystem.demo.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.libararymangementsystem.demo.entity.Book;

import java.io.Serializable;

public class LanguageDTO implements Serializable {

    private int id;
    @JsonProperty("First-Lang")
    private String firstLang;
    @JsonProperty("Second-Lang")
    private String secondLang;
    @JsonProperty("Third-Lang")
    private String thirdLang;


    private Book books;

    public LanguageDTO(){}

    public LanguageDTO(String firstLang, String secondLang, String thirdLang,Book books) {
        this.firstLang = firstLang;
        this.secondLang = secondLang;
        this.thirdLang = thirdLang;
        this.books=books;
    }

    public String getFirstLang() {
        return firstLang;
    }

    public void setFirstLang(String firstLang) {
        this.firstLang = firstLang;
    }

    public String getSecondLang() {
        return secondLang;
    }

    public void setSecondLang(String secondLang) {
        this.secondLang = secondLang;
    }

    public String getThirdLang() {
        return thirdLang;
    }

    public void setThirdLang(String thirdLang) {
        this.thirdLang = thirdLang;
    }

    public Book getBooks() {
        return books;
    }

    public void setBooks(Book books) {
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
