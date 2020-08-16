package com.leadiro.starter.service.result;

import lombok.Data;

@Data
public class ValidationResult<T> {

    private boolean success;
    private T result;

}
