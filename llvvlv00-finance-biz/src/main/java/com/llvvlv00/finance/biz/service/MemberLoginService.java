package com.llvvlv00.finance.biz.service;

import com.llvvlv00.finance.biz.dto.form.GetBase64CodeForm;

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

}
