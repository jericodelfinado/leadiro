package com.leadiro.starter.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PostCode {

    @JsonProperty("postcode")
    private String code;
    private String region;
}
