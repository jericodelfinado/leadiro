package com.leadiro.starter.common.util;

import com.leadiro.starter.common.ResultCode;
import com.leadiro.starter.controller.api.response.GenericResponse;

public class ResponseUtil {


    public static GenericResponse<Void> successResponse(String message) {
        GenericResponse<Void> response = new GenericResponse<>();
        response.setCode(ResultCode.SUCCESS.toString());
        response.setMessage(message);
        return response;
    }


    private ResponseUtil() {}
}
