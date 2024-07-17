package com.libararymangementsystem.demo.exceptionhandling;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    //TO HANDLE NOT FOUND EXCEPTIONS TYPE
    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> notFoundExceptionHandler(NotFoundException exc,WebRequest request){



        return new ResponseEntity<Object>(new Response(exc.getMessage(),HttpStatus.NOT_FOUND, LocalDateTime.now()),HttpStatus.NOT_FOUND);
    }

    //TO HANDLE OTHER TYPES OF EXCEPTIONS
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<Object> genericExceptionHandling(Exception exc,WebRequest request){



        return new ResponseEntity<Object>(new Response(exc.getMessage(), HttpStatus.BAD_REQUEST,LocalDateTime.now()),HttpStatus.BAD_REQUEST);

    }


    //TO HANDLE REQUESTS THAT DO NOT HAVE A HANDLER METHODS
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {


        return new ResponseEntity<Object>(new Response(ex.getMessage(),HttpStatus.NOT_FOUND,LocalDateTime.now()),HttpStatus.NOT_FOUND) ;
    }
}
