package com.libararymangementsystem.demo.exceptionhandling;

public class SuccessResponseWithEntity <T>{

    private String message;
    private T Entity;

    public SuccessResponseWithEntity(){}

    public SuccessResponseWithEntity(String message, T entity) {
        this.message = message;
        Entity = entity;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getEntity() {
        return Entity;
    }

    public void setEntity(T entity) {
        Entity = entity;
    }
}
