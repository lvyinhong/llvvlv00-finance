package com.llvvlv00.finance.biz.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.llvvlv00.common.constant.RedisKeyConstant;
import com.llvvlv00.common.exception.BizException;
import com.llvvlv00.common.exception.ParameterException;
import com.llvvlv00.common.util.CommonUtil;
import com.llvvlv00.finance.biz.dto.form.GetBase64CodeForm;
import com.llvvlv00.finance.biz.dto.form.GetSmsCodeForm;
import com.llvvlv00.finance.biz.dto.form.SmsCodeResult;
import com.llvvlv00.finance.biz.enums.SmsCodeTypeEnum;
import com.llvvlv00.finance.biz.service.MemberBindPhoneService;
import com.llvvlv00.finance.biz.service.MemberLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.llvvlv00.common.util.DateUtil;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberLoginServiceImpl implements MemberLoginService {
    //依赖注入
    final RedisTemplate<String, Object> redisTemplate;
    private MemberBindPhoneService memberBindPhoneService;

    /**
     * 获取客户端id
     *
     * @return
     */
    @Override
    public String getClientId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获取图形验证码
     *
     * @return
     */
    @Override
    public String getBase64Code(GetBase64CodeForm form) {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(300, 192, 4, 1000);
        String code = lineCaptcha.getCode();
        redisTemplate.opsForValue().set(RedisKeyConstant.buildGraphicVerificationCodeKey(form.getClientId()), code,15, TimeUnit.MINUTES);
        return lineCaptcha.getImageBase64();
    }

    /**
     * 获取短信验证码
     *
     * @param form
     * @return
     */
    @Override
    public void sendSmsCode(GetSmsCodeForm form) {
        checkBase64Code(form.getClientId(), form.getCode());
        String smsVerificationCodeKey = RedisKeyConstant.buildSmsVerificationCodeKey(form.getSmsCodeType(), form.getPhone());
        SmsCodeResult smsCodeResult = (SmsCodeResult) redisTemplate.opsForValue().get(smsVerificationCodeKey);
        if (smsCodeResult!=null){
            Duration duration = DateUtil.getDuration(smsCodeResult.getDateTime(), DateUtil.getSystemTime());

            if (duration.getSeconds() < 60) {
                throw new BizException("验证码获取太频繁，请稍后重试");
            }
        }
//         memberBindPhone = memberBindPhoneService.getMemberByPhone(form.getPhone());
//        if (form.getSmsCodeType().equals(SmsCodeTypeEnum.REG.getCode()) && memberBindPhone != null) {
//            throw new ParameterException("phone", "该手机号已注册!");
//        }
//        if (form.getSmsCodeType().equals(SmsCodeTypeEnum.LOGIN.getCode()) && memberBindPhone == null) {
//            throw new ParameterException("phone", "该手机号未注册!");
//        }
        int smsCode = CommonUtil.getRandom(6);
        smsCodeResult = new SmsCodeResult();
        smsCodeResult.setCode(String.valueOf(smsCode));
        smsCodeResult.setDateTime(DateUtil.getSystemTime());
        redisTemplate.opsForValue().set(smsVerificationCodeKey, smsCodeResult, 5, TimeUnit.MINUTES);
        log.info("客户端id:{},手机号:{},短信验证码:{}", form.getClientId(), form.getPhone(), smsCode);
        // todo 调用第三方短信发送接口
    }

    /**
     * 校验图形验证码
     * @param clientId
     * @param code
     * @return
     */
    @Override
    public boolean checkBase64Code(String clientId, String code) {
        String cacheCode = (String) redisTemplate.opsForValue().get(RedisKeyConstant.buildGraphicVerificationCodeKey(clientId));
        redisTemplate.delete(RedisKeyConstant.buildGraphicVerificationCodeKey(clientId));
        if (!code.equalsIgnoreCase(cacheCode)) {
            throw new ParameterException("code", "图形验证码错误");
        }
        return true;
    }

}
