package com.programming.springboot.exception;

import lombok.Getter;

public class EmployeeInfoException extends Exception{

    @Getter private String responseMessage;
    @Getter private int statusCode;
    @Getter private String messageCode;

    public EmployeeInfoException(int statusCode, String messageCode, String responseMessage) {
        super(messageCode);
        this.responseMessage = responseMessage;
        this.statusCode = statusCode;
        this.messageCode = messageCode;
    }
}
