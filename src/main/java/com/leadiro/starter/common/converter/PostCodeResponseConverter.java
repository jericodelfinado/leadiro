package com.leadiro.starter.common.converter;

import com.leadiro.starter.common.ResultCode;
import com.leadiro.starter.common.model.PostCode;
import com.leadiro.starter.controller.api.response.PostCodeGenericResponse;
import com.leadiro.starter.service.biz.result.ValidationResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

public class PostCodeResponseConverter implements Converter<ValidationResult<PostCode>, PostCodeGenericResponse> {

    @Override
    public PostCodeGenericResponse convert(ValidationResult<PostCode> result) {

        PostCodeGenericResponse response = new PostCodeGenericResponse();

        if (result.isSuccess()) {
            response.setCode(ResultCode.SUCCESS.toString());
            response.setMessage(ResultCode.SUCCESS.getDescription());
            response.setData(result.getResult());
        } else {
            response.setCode(ResultCode.RESULT_INVALID.toString());
            response.setMessage(StringUtils.defaultString(result.getMessage(), "Invalid post code"));
        }

        return response;
    }
}
