package com.leadiro.starter.common.exception;

import com.leadiro.starter.controller.api.response.GenericResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice("com.leadiro.starter.controller.api")
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(RestException.class)
    public ResponseEntity<Object> handleRestException(RestException ex, WebRequest request) {
        GenericResponse<Void> response = new GenericResponse<>();
        response.setCode(ex.getResultCode().toString());
        response.setMessage(ex.getMessage());
        return new ResponseEntity(response, ex.getHttpStatus());
    }

}
