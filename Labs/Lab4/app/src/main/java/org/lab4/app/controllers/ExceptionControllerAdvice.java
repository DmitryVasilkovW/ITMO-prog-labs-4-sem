package org.lab4.app.controllers;

import org.lab4.app.myExceptions.RoleNotSuitableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionControllerAdvice
{

    @ExceptionHandler(RoleNotSuitableException.class)
    public ResponseEntity<?> handleRoleNotSuitableException(RoleNotSuitableException e)
    {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", new Date());
        errorDetails.put("status", HttpStatus.FORBIDDEN.value());
        errorDetails.put("error", "Forbidden");
        errorDetails.put("message", e.getMessage());

        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }

}