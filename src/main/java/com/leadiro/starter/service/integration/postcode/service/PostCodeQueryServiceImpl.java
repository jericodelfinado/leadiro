package com.leadiro.starter.service.integration.postcode.service;

import com.leadiro.starter.service.integration.postcode.request.PostCodeQueryRequest;
import com.leadiro.starter.service.integration.postcode.response.PostCodeQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;

@Service
public class PostCodeQueryServiceImpl implements PostCodeQueryService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public PostCodeQueryResponse query(PostCodeQueryRequest request) {

        return restTemplate.getForObject(
                        MessageFormat.format("http://api.postcodes.io/postcodes/{0}", request.getPostCode()),
                        PostCodeQueryResponse.class);

    }
}
