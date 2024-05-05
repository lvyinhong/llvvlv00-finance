package com.llvvlv00.common.dto;

import com.llvvlv00.common.constant.ApiResponseCode;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ApiResponse <T>{
    private T data;
    private Integer code=0;
    private String codeMessage;
    private Map<String, String> errorMessage;
    private Boolean success = true;

    public static <T>ApiResponse<T> success() {
        return success(null);
    }

    public static <T>ApiResponse<T> success(T data) {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setData(data);
        apiResponse.setCode(ApiResponseCode.SUCCESS.getCode());
        apiResponse.setCodeMessage(ApiResponseCode.SUCCESS.getMessage());
        apiResponse.setSuccess(true);
        return apiResponse;
    }

    public static <T> ApiResponse<T> error(String error) {
        Map<String, String> errors = new HashMap<>();
        errors.put(error, error);
        return error(errors);
    }

    public static <T> ApiResponse<T> error(Map<String, String> errors) {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(false);
        apiResponse.setCode(ApiResponseCode.SERVICE_ERROR.getCode());
        apiResponse.setCodeMessage(ApiResponseCode.SERVICE_ERROR.getMessage());
        apiResponse.setErrorMessage(errors);
        return apiResponse;
    }

    public  ApiResponse<T> error(String msg, T data) {
        this.setData(data);
        this.setSuccess(false);
        this.setCode(ApiResponseCode.SERVICE_ERROR.getCode());
        this.setCodeMessage(ApiResponseCode.SERVICE_ERROR.getMessage());
        return this;
    }
    public ApiResponse<T> error(Integer code,Map<String, String> errors) {
        this.setCode(code);
        this.setErrorMessage(errors);
        this.setData(data);
        this.setSuccess(false);
        return this;
    }
}
