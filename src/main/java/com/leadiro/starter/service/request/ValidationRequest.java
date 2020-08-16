package com.leadiro.starter.service.request;

import lombok.Data;

@Data
public class ValidationRequest<T> {
    private T input;

    public ValidationRequest(T input) {
        this.input = input;
    }
}
