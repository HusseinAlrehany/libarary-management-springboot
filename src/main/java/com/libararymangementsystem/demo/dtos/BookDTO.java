package com.libararymangementsystem.demo.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class BookDTO implements Serializable {

    private int id;
    @JsonProperty("BookTitle")
    private String title;
    @JsonProperty("BookQuantity")
    private int quantity;
    @JsonProperty("AuthorDetail")
    private AuthorInfo authorInfo;

    public BookDTO(){}

    public BookDTO(String title, int quantity,AuthorInfo authorInfo) {
        this.title = title;
        this.quantity = quantity;
        this.authorInfo=authorInfo;

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

    public AuthorInfo getAuthorInfo() {
        return authorInfo;
    }

    public void setAuthorInfo(AuthorInfo authorInfo) {
        this.authorInfo = authorInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
