package com.bookaroom.web.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(MultipartException.class)
    public String handleError1(MultipartException e)
    {
        e.printStackTrace();

        return "Error uploading file";
    }
}
