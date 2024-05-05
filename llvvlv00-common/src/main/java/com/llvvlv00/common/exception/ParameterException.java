package com.llvvlv00.common.exception;
import com.llvvlv00.common.constant.ApiResponseCode;
import java.util.Map;

/**
 * 参数异常
 */
public class ParameterException extends BaseException {
    private static final long serialVersionUID = 2612992235262400823L;

    private Map<String, String> fieldErrors;

    public ParameterException(String message) {
        super(ApiResponseCode.PARAMETER_INVALID.getCode(), message);
    }

    public ParameterException(int code, String message) {
        super(code, message);
    }

    public ParameterException(String key, String value) {
        super(ApiResponseCode.PARAMETER_INVALID.getCode(), ApiResponseCode.PARAMETER_INVALID.getMessage());
        fieldErrors.put(key,value);
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }
    public ParameterException(String message, Throwable t) {
        super(message, t);
    }
}
