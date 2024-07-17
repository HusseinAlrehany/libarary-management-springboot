package com.libararymangementsystem.demo.dtos;

public class BookInfo {

    private String bookTitle;
    private int bookQuantity;

    public BookInfo(){}

    public BookInfo(String bookTitle, int bookQuantity) {
        this.bookTitle = bookTitle;
        this.bookQuantity = bookQuantity;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getBookQuantity() {
        return bookQuantity;
    }

    public void setBookQuantity(int bookQuantity) {
        this.bookQuantity = bookQuantity;
    }
}
