package com.leadiro.starter.common.common;

import com.leadiro.starter.common.ResultCode;
import com.leadiro.starter.controller.api.response.VoidGenericResponse;
import com.leadiro.starter.service.result.ValidationResult;
import org.springframework.core.convert.converter.Converter;

public class ValidationResultConverter implements Converter<ValidationResult<Void>, VoidGenericResponse> {

    @Override
    public VoidGenericResponse convert(ValidationResult<Void> validationResult) {

        VoidGenericResponse response = new VoidGenericResponse();

        if (validationResult.isSuccess()) {
            response.setCode(ResultCode.SUCCESS.toString());
            response.setMessage("Valid");
        } else {
            response.setCode(ResultCode.RESULT_INVALID.toString());
            response.setMessage("Invalid");
        }

        return response;
    }
}
