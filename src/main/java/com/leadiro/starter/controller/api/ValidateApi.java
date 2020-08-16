package com.leadiro.starter.controller.api;

import com.leadiro.starter.controller.api.response.GenericResponse;
import org.springframework.http.HttpEntity;

import java.util.Map;

public interface ValidateApi {

    /**
     * Validate Email Pattern
     *
     * @param email email to be validated
     * @return response
     */
    GenericResponse<Void> validateEmail(String email);

    /**
     * Validate post code through https://postcodes.io/
     *
     * @param postCode post code to be validated
     * @return region if valid
     */
    <T> GenericResponse<T> validatePostCode(String postCode);
}
