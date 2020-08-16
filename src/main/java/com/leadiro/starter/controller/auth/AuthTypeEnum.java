package com.leadiro.starter.controller.auth;

import lombok.Getter;

@Getter
public enum AuthTypeEnum {

    BASIC("basic", "Basic Auth"),
    JWT("jwt", "JSON Web Token"),
    ;

    /** Auth type code */
    private final String code;

    /** Auth type description */
    private final String description;

    AuthTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
