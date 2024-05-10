package com.llvvlv00.common.constant;

public class RedisKeyConstant {

    private RedisKeyConstant() {
        // 防止实例化
    }

    // 图形验证码相关的键前缀
    public static final String GRAPHIC_VERIFICATION_CODE_PREFIX = "graphic:verification:code:";

    //短信验证码前缀
    public static final String SMS_VERIFICATION_CODE_PREFIX = "sms:code:";

    // 用户相关的键前缀
    public static final String USER_PREFIX = "user:";

    // 商品信息的键前缀
    public static final String PRODUCT_PREFIX = "product:";

    // 用户购物车的键模板，使用占位符{}表示动态部分
    public static final String CART_KEY_TEMPLATE = USER_PREFIX + "{userId}:cart";

    // 单个商品的键模板
    public static final String SINGLE_PRODUCT_KEY_TEMPLATE = PRODUCT_PREFIX + "{productId}";

    // 示例方法：构造用户购物车的完整键名
    public static String buildCartKey(String userId) {
        return CART_KEY_TEMPLATE.replace("{userId}", userId);
    }

    // 示例方法：构造单个商品的完整键名
    public static String buildProductKey(String productId) {
        return SINGLE_PRODUCT_KEY_TEMPLATE.replace("{productId}", productId);
    }

    // 构造图形验证码的完整键名，这里使用用户ID作为标识
    public static String buildGraphicVerificationCodeKey(String userId) {
        return GRAPHIC_VERIFICATION_CODE_PREFIX + userId;
    }

    //构造短信验证码的完整键名，这里使用用户ID作为标识
    public static String buildSmsVerificationCodeKey(String smsCodeType, String phone) {
        return SMS_VERIFICATION_CODE_PREFIX + smsCodeType + ":" + phone;
    }
}
