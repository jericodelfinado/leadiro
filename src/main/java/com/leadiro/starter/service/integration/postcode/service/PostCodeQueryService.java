package com.leadiro.starter.service.integration.postcode.service;

import com.leadiro.starter.service.integration.postcode.request.PostCodeQueryRequest;
import com.leadiro.starter.service.integration.postcode.response.PostCodeQueryResponse;

public interface PostCodeQueryService {

    PostCodeQueryResponse query(PostCodeQueryRequest request);
}
