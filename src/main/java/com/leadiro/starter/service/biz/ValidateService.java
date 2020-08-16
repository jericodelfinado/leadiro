package com.leadiro.starter.service.biz;

import com.leadiro.starter.service.biz.request.ValidationRequest;
import com.leadiro.starter.service.biz.result.ValidationResult;

public interface ValidateService<T, S> {

    ValidationResult<T> validate(ValidationRequest<S> request);
}
