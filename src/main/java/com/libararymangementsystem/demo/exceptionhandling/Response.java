package com.libararymangementsystem.demo.exceptionhandling;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class Response {

    private HttpStatus status;
    private String message;
    private LocalDateTime timeStamp;
    //private int statusCode;


    public Response(){}

    public Response(String message,HttpStatus status,LocalDateTime timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
       // this.statusCode=statusCode;
    }




    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
