package com.leadiro.starter.common.exception;

import com.leadiro.starter.common.ResultCode;
import org.springframework.http.HttpStatus;

public class BadRequestException extends RestException {

    public BadRequestException(ResultCode resultCode, String message) {
        super(resultCode, message, HttpStatus.BAD_REQUEST);
    }
}
