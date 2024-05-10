package com.llvvlv00.finance.biz.service;

import com.llvvlv00.finance.biz.dto.form.GetBase64CodeForm;
import com.llvvlv00.finance.biz.dto.form.GetSmsCodeForm;

public interface MemberLoginService {
    /**
     * 获取客户端id
     * @return
     */
    String getClientId();

    /**
     * 获取图形验证码
     * @param form
     * @return
     */
    String getBase64Code(GetBase64CodeForm form);

    /**
     * 获取短信验证码
     * @param form
     * @return
     */
    void sendSmsCode(GetSmsCodeForm form);

    /**
     * 校验图形验证码
     * @param clientId
     * @param code
     * @return
     */
    boolean checkBase64Code(String clientId, String code);
}
