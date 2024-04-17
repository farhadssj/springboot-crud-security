package com.programming.springboot.exception;

import com.programming.springboot.model.RestApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmployeeInfoException.class)
    public ResponseEntity<Object> handleEmployeeInfoException(EmployeeInfoException exception) {
        return RestApiResponse.buildResponseWithError(exception.getStatusCode(), exception.getMessageCode(),
                exception.getResponseMessage() + "\n Exception Catch from handleEmployeeInfoException");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception exception) {
        return RestApiResponse.buildResponseWithError(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage(),
                exception.getMessage() + "\n Exception Catch from handleGlobalException");
    }
}
