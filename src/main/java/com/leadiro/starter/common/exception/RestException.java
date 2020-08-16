package com.leadiro.starter.common.exception;

import com.leadiro.starter.common.ResultCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RestException extends RuntimeException {

    private ResultCode resultCode;
    private HttpStatus httpStatus;

    public RestException(ResultCode resultCode, String message, HttpStatus httpStatus) {
        super(message);
        this.resultCode = resultCode;
        this.httpStatus = httpStatus;
    }

    public RestException(String message) {
        super(message);
        this.resultCode = ResultCode.UNKNOWN_ERROR;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
