package com.leadiro.starter.service.integration.postcode.service;

import com.leadiro.starter.common.exception.RestException;
import com.leadiro.starter.config.integration.PostCodeIntegrationConfig;
import com.leadiro.starter.service.integration.postcode.request.PostCodeQueryRequest;
import com.leadiro.starter.service.integration.postcode.response.PostCodeQueryResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;

@Service
@Slf4j
public class PostCodeQueryServiceImpl implements PostCodeQueryService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PostCodeIntegrationConfig postCodeIntegrationConfig;

    @Override
    public PostCodeQueryResponse query(PostCodeQueryRequest request) {

        try {
            log.info("Querying {} from {}", request.getPostCode(), postCodeIntegrationConfig.getBasePath());
            ResponseEntity<PostCodeQueryResponse> responseEntity = restTemplate.getForEntity(
                    MessageFormat.format("{0}/{1}", postCodeIntegrationConfig.getBasePath(), request.getPostCode()),
                    PostCodeQueryResponse.class);
            log.info("Post code query response: {}", responseEntity);
            return responseEntity.getBody();
        } catch (HttpClientErrorException e) {
            log.error("Error querying post code: {} - {}", e.getRawStatusCode(), e.getResponseBodyAsString(), e);
            JSONObject jsonObject = new JSONObject(e.getResponseBodyAsString());
            PostCodeQueryResponse queryResponse = new PostCodeQueryResponse();
            queryResponse.setStatus(e.getRawStatusCode());
            queryResponse.setError(jsonObject.getString("error"));
            return queryResponse;
        } catch (Exception e) {
            log.error("Error querying post code", e);
            throw new RestException(e.getMessage());
        }

    }
}
