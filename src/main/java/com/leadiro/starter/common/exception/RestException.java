package com.leadiro.starter.common.exception;

import com.leadiro.starter.common.ResultCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RestException extends RuntimeException {

    private ResultCode resultCode;
    private String message;
    private HttpStatus httpStatus;

    public RestException(ResultCode resultCode, String message, HttpStatus httpStatus) {
        this.resultCode = resultCode;
        this.message = message;
        this.httpStatus = httpStatus;
    }

}
