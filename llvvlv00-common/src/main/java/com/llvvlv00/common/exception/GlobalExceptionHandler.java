package com.llvvlv00.common.exception;

import com.llvvlv00.common.constant.ApiResponseCode;
import com.llvvlv00.common.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理，将异常转化为ApiResponse
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleException(Exception ex) {
        log.info("Exception: ", ex);

        //返回响应对象
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        Map<String, String> errors = new HashMap<>();
        errors.put(ApiResponseCode.SERVICE_ERROR.getMessage(), ex.getMessage());
        apiResponse.error(ApiResponseCode.SERVICE_ERROR.getCode(), errors);
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ParameterException.class)
    public ResponseEntity<ApiResponse<Object>> apiErrorException(ParameterException ex) {
        //返回响应对象
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        Map<String, String> errors = new HashMap<>();
        errors.put(ApiResponseCode.PARAMETER_INVALID.getMessage(), ex.getMessage());
        apiResponse.error(ApiResponseCode.PARAMETER_INVALID.getCode(), errors);
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<ApiResponse<Object>> apiErrorException(LoginException bizException) {
        //返回响应对象
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        Map<String, String> errors = new HashMap<>();
        errors.put(ApiResponseCode.LOGIN_ERROR.getMessage(), bizException.getMessage());
        apiResponse.error(bizException.getCode(), errors);
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
