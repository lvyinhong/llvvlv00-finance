package com.llvvlv00.finance.biz.dto.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class GetBase64CodeForm {
    /**
     * 客户端Id
     */
    @ApiModelProperty(value = "客户端Id", required = true)
    @NotBlank(message = "客户端Id不能为空")
    @Pattern(regexp = "^[0-9a-zA-Z]{32}$", message = "clientId格式不正确")
    private String clientId;

}
