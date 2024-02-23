package org.soin.core.infrastructure.utils;


/**
 * 字符串处理工具
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-09 14:03
 **/
public class StringUtil {

    /**
     * 验证字符是否为空
     *
     * @param charSequence 待验证字符串
     * @return 是否为空
     */
    public static boolean isBlank(final CharSequence charSequence) {
        int strLen;
        if (charSequence == null || (strLen = charSequence.length()) == 0) {
            return Boolean.TRUE;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(charSequence.charAt(i))) {
                return Boolean.FALSE;
            }
        }
        return Boolean.FALSE;
    }

}
