package com.leadiro.starter.controller.api.validate;

import com.leadiro.starter.common.ResultCode;
import com.leadiro.starter.controller.api.BaseApiTest;
import org.junit.Test;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

public class ValidateApiControllerTest extends BaseApiTest {

    private static final String VALIDATE_EMAIL_PATH = "/validate/email";

    @Test
    public void testCorrectEmailPattern() throws Exception {

        mockMvc.perform(get(VALIDATE_EMAIL_PATH)
                .servletPath(VALIDATE_EMAIL_PATH)
                .with(httpBasic("admin", "password123"))
                .param("email", "jerico.delfinado@gmail.com"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.code", is(ResultCode.SUCCESS.toString())));

    }

    @Test
    public void testInvalidEmailPattern() throws Exception {

        mockMvc.perform(get(VALIDATE_EMAIL_PATH)
                .servletPath(VALIDATE_EMAIL_PATH)
                .with(httpBasic("admin", "password123"))
                .param("email", "jerico.delfinado.gmail.com"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.code", is(ResultCode.RESULT_INVALID.toString())));

    }

    @Test
    public void testBlankEmail() throws Exception {

        mockMvc.perform(get(VALIDATE_EMAIL_PATH)
                .servletPath(VALIDATE_EMAIL_PATH)
                .with(httpBasic("admin", "password123"))
                .param("email", ""))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(ResultCode.PARAM_ILLEGAL.toString())));

    }

    @Test
    public void testNullEmail() throws Exception {

        mockMvc.perform(get(VALIDATE_EMAIL_PATH)
                .servletPath(VALIDATE_EMAIL_PATH)
                .with(httpBasic("admin", "password123")))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(ResultCode.PARAM_MISSING.toString())));

    }

}