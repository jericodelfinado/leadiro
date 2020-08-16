package com.leadiro.starter.service.integration.postcode.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.leadiro.starter.common.model.PostCode;
import lombok.Data;

@Data
public class PostCodeQueryResponse {
    private int status;
    @JsonProperty("result")
    private PostCode postCode;
    private String error;
}
