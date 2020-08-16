package com.leadiro.starter.service.biz.result;

import lombok.Data;

@Data
public class ValidationResult<T> {

    private boolean success;
    private String message;
    private T result;

}
