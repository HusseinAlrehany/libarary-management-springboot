package com.libararymangementsystem.demo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="title")
    private String title;
    @Column(name="")
    private int quantity;

    @ManyToOne(cascade ={CascadeType.DETACH,
                         CascadeType.MERGE,
                         CascadeType.PERSIST,
                         CascadeType.REFRESH})
    @JoinColumn(name = "author-id")
    private Author author;
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private List<Languages> languages;
    @ManyToMany(cascade = {CascadeType.DETACH,
                           CascadeType.MERGE,
                           CascadeType.PERSIST,
                           CascadeType.REFRESH})
    @JoinTable(name = "book-borrower",
    joinColumns = @JoinColumn(name="book_id"),
    inverseJoinColumns = @JoinColumn(name="borrower_id"))
    private List<Borrower> borrowers;

    public Book(){}

    public Book(String title, int quantity) {
        this.title = title;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Languages> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Languages> languages) {
        this.languages = languages;
    }

    public List<Borrower> getBorrowers() {
        return borrowers;
    }

    public void setBorrowers(List<Borrower> borrowers) {
        this.borrowers = borrowers;
    }

    //ASSOCIATION FUNCTION TO ADD LANGUAGE TO A BOOK
    public void addLang(Languages lang){
         if(languages==null){
             languages=new ArrayList<>();
         }
         languages.add(lang);
         lang.setBook(this);
    }

    //BREAKING DOWN THE ASSOCIATION TO REMOVE A LANGUAGE
    public void removeLang(Languages lang){
        if(languages!=null){
            lang.setBook(null);
            languages.remove(lang);
        }
    }
}
