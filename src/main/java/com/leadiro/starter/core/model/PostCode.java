package com.leadiro.starter.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PostCode {

    @JsonProperty("postcode")
    private String code;
    private String region;
}
