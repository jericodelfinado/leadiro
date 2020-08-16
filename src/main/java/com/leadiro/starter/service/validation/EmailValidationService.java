package com.leadiro.starter.service.validation;

import com.leadiro.starter.common.ResultCode;
import com.leadiro.starter.common.exception.BadRequestException;
import com.leadiro.starter.service.ValidateService;
import com.leadiro.starter.service.request.ValidationRequest;
import com.leadiro.starter.service.result.ValidationResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class EmailValidationService implements ValidateService<Void, String> {


    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    @Override
    public ValidationResult<Void> validate(ValidationRequest<String> request) {

        ValidationResult<Void> result = new ValidationResult<>();

        if (StringUtils.isEmpty(request.getInput())) {
            throw new BadRequestException(ResultCode.PARAM_MISSING, ResultCode.PARAM_MISSING.getDescription());
        }

        result.setSuccess(request.getInput().matches(EMAIL_REGEX));

        return result;
    }
}
