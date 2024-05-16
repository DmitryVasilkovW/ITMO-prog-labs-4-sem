package org.lab4.app.myExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class RoleNotSuitableException extends RuntimeException
{
    public RoleNotSuitableException(String message)
    {
        super(message);
    }
}
