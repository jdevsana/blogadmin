package com.sana.blogadmin.exception;

import com.sana.blogadmin.model.ErrorMsg;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorMsg error = new ErrorMsg(LocalDateTime.now(), status.value(), status.getReasonPhrase(), e.getMessage());
        return ResponseEntity.status(status).body(error);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException e,
                                                                     HttpHeaders headers, HttpStatus status, WebRequest request) {
        HttpHeaders header = new HttpHeaders();
        header.add("error", "Media type not supported");
        ErrorMsg error = new ErrorMsg(LocalDateTime.now(), status.value(), status.getReasonPhrase(), e.getMessage());
        return ResponseEntity.status(status).headers(headers).body(error);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException e, HttpHeaders headers,
                                                               HttpStatus status, WebRequest request) {
        HttpHeaders header = new HttpHeaders();
        header.add("error", "Missing Path Variable");
        ErrorMsg error = new ErrorMsg(LocalDateTime.now(), status.value(), status.getReasonPhrase(), e.getMessage());
        return ResponseEntity.status(status).headers(headers).body(error);

    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException e,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {
        HttpHeaders header = new HttpHeaders();
        header.add("error", "Missing RequestParam");
        ErrorMsg error = new ErrorMsg(LocalDateTime.now(), status.value(), status.getReasonPhrase(), e.getMessage());
        return ResponseEntity.status(status).headers(headers).body(error);

    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException e, HttpHeaders headers,
                                                        HttpStatus status, WebRequest request) {
        HttpHeaders header = new HttpHeaders();
        header.add("error", "Input mismatch");
        ErrorMsg error = new ErrorMsg(LocalDateTime.now(), status.value(), status.getReasonPhrase(), e.getMessage());
        return ResponseEntity.status(status).headers(headers).body(error);
    }


    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String reason = HttpStatus.NOT_FOUND.getReasonPhrase();
        HttpHeaders header = new HttpHeaders();
        header.add("error", "User not found");
        //ErrorMessage error = new ErrorMessage(status.value(), e.getMessage(), status.getReasonPhrase(), LocalDateTime.now());
        ErrorMsg error = new ErrorMsg(LocalDateTime.now(), status.value(), reason, exception.getMessage());
        return ResponseEntity.status(status).headers(header).body(error);
    }

    @ExceptionHandler(PostNotFoundException.class)
    protected ResponseEntity<Object> handlePostNotFoundException(PostNotFoundException exception) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String reason = HttpStatus.NOT_FOUND.getReasonPhrase();
        HttpHeaders header = new HttpHeaders();
        header.add("error", "Post not found");
        //ErrorMessage error = new ErrorMessage(status.value(), e.getMessage(), status.getReasonPhrase(), LocalDateTime.now());
        ErrorMsg error = new ErrorMsg(LocalDateTime.now(), status.value(), reason, exception.getMessage());
        return ResponseEntity.status(status).headers(header).body(error);
    }

}
