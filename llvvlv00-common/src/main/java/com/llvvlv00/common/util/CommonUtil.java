package com.llvvlv00.common.util;

public class CommonUtil {
    /**
     * 随机生成 length 位数字
     * @param length
     * @return
     */
    public static int getRandom(int length) {
        return (int) ((Math.random() * 9 + 1) * Math.pow(10, length - 1));
    }
}
