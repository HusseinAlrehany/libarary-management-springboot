package com.libararymangementsystem.demo.dtos;

public class BookWithAuthorDetailsDTO {

    private String book_Title;

    private int book_Quantity;

    private String author_FirstName;
    private String author_LastName;

    private String AuthorEmail;

    public BookWithAuthorDetailsDTO(){}

    public BookWithAuthorDetailsDTO(String book_Title, int book_Quantity, String author_FirstName, String author_LastName, String authorEmail) {
        this.book_Title = book_Title;
        this.book_Quantity = book_Quantity;
        this.author_FirstName = author_FirstName;
        this.author_LastName = author_LastName;
        AuthorEmail = authorEmail;
    }

    public String getBook_Title() {
        return book_Title;
    }

    public void setBook_Title(String book_Title) {
        this.book_Title = book_Title;
    }

    public int getBook_Quantity() {
        return book_Quantity;
    }

    public void setBook_Quantity(int book_Quantity) {
        this.book_Quantity = book_Quantity;
    }

    public String getAuthor_FirstName() {
        return author_FirstName;
    }

    public void setAuthor_FirstName(String author_FirstName) {
        this.author_FirstName = author_FirstName;
    }

    public String getAuthor_LastName() {
        return author_LastName;
    }

    public void setAuthor_LastName(String author_LastName) {
        this.author_LastName = author_LastName;
    }

    public String getAuthorEmail() {
        return AuthorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        AuthorEmail = authorEmail;
    }
}
