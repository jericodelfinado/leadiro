package com.leadiro.starter.common.util;

import com.leadiro.starter.common.ResultCode;
import com.leadiro.starter.common.exception.BadRequestException;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;

public class ParamCheckUtil {

    public static void assertNotNull(Object param, String paramName, ResultCode code) {
        if (null != param) {
            return;
        }
        throw new BadRequestException(code, MessageFormat.format("{0} cannot be null", paramName));
    }

    public static void assertNotBlank(String param, String paramName, ResultCode code) {
        if (!StringUtils.isBlank(param)) {
            return;
        }
        throw new BadRequestException(code, MessageFormat.format("{0} cannot be blank", paramName));
    }
}
