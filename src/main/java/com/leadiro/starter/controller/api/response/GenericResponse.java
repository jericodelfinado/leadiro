package com.leadiro.starter.controller.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericResponse<T> {
    private String code;
    private String message;
    private T data;
}
