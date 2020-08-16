package com.leadiro.starter.service.integration.postcode.request;


import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class PostCodeQueryRequest {

    private final String postCode;

    public PostCodeQueryRequest(String postCode) {
        this.postCode = StringUtils.trim(postCode);
    }
}
