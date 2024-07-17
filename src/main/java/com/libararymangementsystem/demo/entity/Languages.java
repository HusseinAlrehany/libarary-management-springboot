package com.libararymangementsystem.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="language")
public class Languages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="first_lang")
    private String firstLang;
    @Column(name="second_lang")
    private String secondLang;
    @Column(name="third_lang")
    private String thirdLang;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="book_id")
    private Book book;


    public Languages(){}

    public Languages(String firstLang, String secondLang, String thirdLang) {
        this.firstLang = firstLang;
        this.secondLang = secondLang;
        this.thirdLang = thirdLang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
