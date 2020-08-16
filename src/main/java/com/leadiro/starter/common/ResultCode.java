package com.leadiro.starter.common;


import lombok.Getter;

@Getter
public enum ResultCode {

    SUCCESS("000", "S", "Success"),
    RESULT_INVALID("001", "S", "Invalid"),

    /* error result */

    PARAM_ILLEGAL("100", "F", "Param illegal"),
    PARAM_MISSING("101", "F", "Param missing"),
    UNKNOWN_ERROR("999", "U", "Unknown error"),
    ;


    private String code;
    private String status;
    private String description;

    ResultCode(String code, String status, String description) {
        this.code = code;
        this.status = status;
        this.description = description;
    }

    @Override
    public String toString() {
        return status + code;
    }
}
