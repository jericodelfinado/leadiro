package com.leadiro.starter.controller.api.validate;

import com.leadiro.starter.common.ResultCode;
import com.leadiro.starter.common.util.ParamCheckUtil;
import com.leadiro.starter.controller.api.ValidateApi;
import com.leadiro.starter.controller.api.response.GenericResponse;
import com.leadiro.starter.controller.api.response.VoidGenericResponse;
import com.leadiro.starter.service.biz.ValidateService;
import com.leadiro.starter.service.biz.request.ValidationRequest;
import com.leadiro.starter.service.biz.result.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validate")
public class ValidateApiController implements ValidateApi {

    private ValidateService<Void, String> emailValidationService;

    private ConversionService conversionService;

    @Override
    @GetMapping("/email")
    public GenericResponse<Void> validateEmail(String email) {

        ParamCheckUtil.assertNotNull(email, "email", ResultCode.PARAM_MISSING);
        ParamCheckUtil.assertNotBlank(email, "email", ResultCode.PARAM_ILLEGAL);

        ValidationResult<Void> validationResult = emailValidationService.validate(new ValidationRequest<>(email));

        return conversionService.convert(validationResult, VoidGenericResponse.class);
    }

    @Override
    @GetMapping("/postcode")
    public <T> GenericResponse<T> validatePostCode(String postCode) {
        return null;
    }


    @Autowired
    public void setEmailValidationService(
            ValidateService<Void, String> emailValidationService) {
        this.emailValidationService = emailValidationService;
    }

    @Autowired
    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }
}
