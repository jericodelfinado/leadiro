package com.leadiro.starter.service;

import com.leadiro.starter.service.request.ValidationRequest;
import com.leadiro.starter.service.result.ValidationResult;

public interface ValidateService<T, S> {

    ValidationResult<T> validate(ValidationRequest<S> request);
}
