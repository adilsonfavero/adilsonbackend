package com.example.demo.resources.exceptions;

import java.util.List;

public class ValidationError extends  StandardError{

    private List<FieldMessage> errors;


    public ValidationError(int status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);

    }


    public List<FieldMessage> getErrors() {
        return errors;
    }


    public void addError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }


    
}