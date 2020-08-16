package com.leadiro.starter.service.biz.validation;

import com.leadiro.starter.common.model.PostCode;
import com.leadiro.starter.service.biz.ValidateService;
import com.leadiro.starter.service.biz.request.ValidationRequest;
import com.leadiro.starter.service.biz.result.ValidationResult;
import com.leadiro.starter.service.integration.postcode.request.PostCodeQueryRequest;
import com.leadiro.starter.service.integration.postcode.response.PostCodeQueryResponse;
import com.leadiro.starter.service.integration.postcode.service.PostCodeQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PostCodeValidationService implements ValidateService<PostCode, String> {

    @Autowired
    private PostCodeQueryService postCodeQueryService;

    @Override
    public ValidationResult<PostCode> validate(ValidationRequest<String> request) {

        ValidationResult<PostCode> result = new ValidationResult<>();
        PostCodeQueryResponse queryResponse = postCodeQueryService.query(new PostCodeQueryRequest(request.getInput()));

        if (queryResponse.getStatus() == 200) {
            result.setSuccess(true);
            result.setResult(queryResponse.getPostCode());
        } else {
            result.setSuccess(false);
            result.setMessage(queryResponse.getError());
        }

        return result;

    }

}
