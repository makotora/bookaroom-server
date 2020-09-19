package com.bookaroom.web.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalDefaultExceptionHandler extends ResponseEntityExceptionHandler
{

    private static Logger log = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> defaultErrorHandler(final WebRequest request, Exception e)
    {

        log.debug("Preparing error response", e);

        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        ResponseStatus annotation = AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class);
        if (annotation != null) {
            httpStatus = annotation.value();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String result = "{\"error\": \"" + e.toString() + "\", \"message\": \"" + e.getMessage() + "\"}";

        log.debug("Response: {}", result);

        return handleExceptionInternal(e, result, headers, httpStatus, request);
    }
}
