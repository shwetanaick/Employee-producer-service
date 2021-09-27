//package com.benzassignment.file.service.util;
//
//import com.benzassignment.file.service.model.FileContents;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MissingRequestHeaderException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//@ControllerAdvice
//public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
//@Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(
//            MissingRequestHeaderException ex,
//            HttpHeaders headers,
//            HttpStatus status,
//            FileContents request) {
//        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
//    }
//}
