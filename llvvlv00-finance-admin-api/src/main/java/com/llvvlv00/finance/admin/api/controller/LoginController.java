package com.llvvlv00.finance.admin.api.controller;

import com.llvvlv00.common.dto.ApiResponse;
import com.llvvlv00.finance.biz.dto.form.GetBase64CodeForm;
import com.llvvlv00.finance.biz.service.MemberLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("用户登录模块")
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    final MemberLoginService memberLoginService;

    @ApiOperation(value = "获取客户端ID")
    @GetMapping(value = "/getClientId")
    public ApiResponse<String> getClientId() {
        String clientId = memberLoginService.getClientId();
        return ApiResponse.success(clientId);
    }
    @ApiOperation("获取图形验证码")
    @GetMapping("/getBase64Code")
    public ApiResponse<String> getBase64Code(@Validated @ModelAttribute GetBase64CodeForm form) {
        String baseCode64 = memberLoginService.getBase64Code(form);
        return ApiResponse.success(baseCode64);
    }
}
