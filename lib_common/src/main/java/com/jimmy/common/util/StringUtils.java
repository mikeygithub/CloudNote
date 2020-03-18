package com.jimmy.common.util;

import java.util.Random;

/**
 */
public class StringUtils {

    /**
     * 获取随机字符串
     *
     * @param length 字符串长度
     * @return
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijkmnopqrstuvwxyz0123456789ABCDEFGHJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

}
