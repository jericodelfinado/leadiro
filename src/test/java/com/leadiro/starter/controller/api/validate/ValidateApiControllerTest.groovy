package com.leadiro.starter.controller.api.validate

import com.leadiro.starter.common.ResultCode
import com.leadiro.starter.controller.api.BaseApiTest
import org.junit.Test
import org.mockserver.model.MediaType

import static org.hamcrest.CoreMatchers.is
import static org.mockserver.model.HttpRequest.request
import static org.mockserver.model.HttpResponse.response
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ValidateApiControllerTest extends BaseApiTest {

    private static final String VALIDATE_EMAIL_PATH = "/validate/email";
    private static final String VALIDATE_POSTCODE_PATH = "/validate/postcode";
    private static final String ADMIN = "admin";
    private static final String PASSWORD = "password123";

    @Test
    void testCorrectEmailPattern() throws Exception {

        mockMvc.perform(get(VALIDATE_EMAIL_PATH)
                .servletPath(VALIDATE_EMAIL_PATH)
                .with(httpBasic("admin", "password123"))
                .param("email", "jerico.delfinado@gmail.com"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("\$.code", is(ResultCode.SUCCESS.toString())));

    }

    @Test
    void testInvalidEmailPattern() throws Exception {

        mockMvc.perform(get(VALIDATE_EMAIL_PATH)
                .servletPath(VALIDATE_EMAIL_PATH)
                .with(httpBasic(ADMIN, PASSWORD))
                .param("email", "jerico.delfinado.gmail.com"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("\$.code", is(ResultCode.RESULT_INVALID.toString())));

    }

    @Test
    void testBlankEmail() throws Exception {

        mockMvc.perform(get(VALIDATE_EMAIL_PATH)
                .servletPath(VALIDATE_EMAIL_PATH)
                .with(httpBasic(ADMIN, PASSWORD))
                .param("email", ""))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("\$.code", is(ResultCode.PARAM_ILLEGAL.toString())));

    }

    @Test
    void testNullEmail() throws Exception {

        mockMvc.perform(get(VALIDATE_EMAIL_PATH)
                .servletPath(VALIDATE_EMAIL_PATH)
                .with(httpBasic(ADMIN, PASSWORD)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("\$.code", is(ResultCode.PARAM_MISSING.toString())));

    }

    @Test
    void testValidPostCode() throws Exception {
        String postCode = "VALIDCODE";
        mockServer.when(request("/postcodes/" + postCode).withMethod("GET"))
                .respond(response()
                        .withStatusCode(200)
                        .withContentType(MediaType.APPLICATION_JSON_UTF_8)
                        .withBody("""{
                                  "status":200, 
                                  "result": { 
                                        "region": "London",
                                        "postcode": "${postCode}"
                                  } 
                                }"""));

        mockMvc.perform(get(VALIDATE_POSTCODE_PATH)
                .servletPath(VALIDATE_POSTCODE_PATH)
                .with(httpBasic(ADMIN, PASSWORD))
                .param("postCode", postCode))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("\$.code", is(ResultCode.SUCCESS.toString())))
                .andExpect(jsonPath("\$.data.region", is("London")));
    }

    @Test
    void testInValidPostCode() throws Exception {
        String postCode = "INVALIDCODE";
        mockServer.when(request("/postcodes/" + postCode).withMethod("GET"))
                .respond(response()
                        .withStatusCode(404)
                        .withContentType(MediaType.APPLICATION_JSON_UTF_8)
                        .withBody("{\"status\":404, \"error\":\"Invalid postcode\"}"));

        mockMvc.perform(get(VALIDATE_POSTCODE_PATH)
                .servletPath(VALIDATE_POSTCODE_PATH)
                .with(httpBasic(ADMIN, PASSWORD))
                .param("postCode", postCode))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("\$.code", is(ResultCode.RESULT_INVALID.toString())))
                .andExpect(jsonPath("\$.message", is("Invalid postcode")));
    }


}