package com.libararymangementsystem.demo.dtos;

public class AuthorInfo {

    private String AuthorFirstName;
    private String AuthorLastName;

    private String AuthorEmail;

    public AuthorInfo(){}

    public AuthorInfo(String authorFirstName, String authorLastName, String authorEmail) {
        AuthorFirstName = authorFirstName;
        AuthorLastName = authorLastName;
        AuthorEmail = authorEmail;
    }

    public String getAuthorFirstName() {
        return AuthorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        AuthorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return AuthorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        AuthorLastName = authorLastName;
    }

    public String getAuthorEmail() {
        return AuthorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        AuthorEmail = authorEmail;
    }
}
