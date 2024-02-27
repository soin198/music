package org.soin.core.infrastructure.utils;

import java.util.regex.Pattern;

/**
 * 正则验证工具
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2023-07-13 16:14
 **/
public class RegexExpressionUtil {

    /**
     * 校验是否是手机号
     */
    public static boolean isMobile(String mobile) {
        int length = mobile.length();
        String regex = "1\\d{10}";
        return Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(mobile).matches() && (length == 11);
    }

    /**
     * 验证 6 - 18位密码
     */
    public static boolean isPassword(String password) {
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$";
        return Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(password).matches();
    }


    /**
     * 校验是否是数字类型
     */
    public static boolean isNumber(String str) {
        String regex = "^(\\-|\\+)?\\d+(\\.\\d+)?$";
        return str.matches(regex);
    }

    /**
     * 校验是否是身份证号
     */
    public static boolean isIdentityCard(String idCard) {
        String regex = "^\\d{15}|\\d{18}$";
        return Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(idCard).matches();
    }

    /**
     * 是否是qq号
     */
    public static boolean isTencentQQ(String qq) {
        String regex = "[1-9][0-9]{4,}";
        return Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(qq).matches();
    }

    /**
     * 是否是正确邮箱地址
     */
    public static boolean isEmail(String email) {
        String regex = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        return Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(email).matches();
    }

    /**
     * 验证是否是金额
     *
     * @param money 验证数据
     * @return 是否是金额
     */
    public static boolean isMoney(String money) {
        String regex = "^[\\+\\-]?[\\d]*\\.?[\\d]*$";
        return Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(money).matches();
    }

    /**
     * 验证金额是否为零
     *
     * @param money 验证金额
     * @return 是否匹配成功
     */
    public static boolean mateZero(String money) {
        String regex = "^[\\+\\-]?0?\\.?0*$";
        return Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(money).matches();
    }

    /**
     * 验证金额是否为小数
     *
     * @param param 验证金额
     * @return 是否匹配成功
     */
    public static boolean isUniqueMoney(String param) {
        String regex = "^([1-9][0-9]*){1,3}$";
        return Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(param).matches();
    }

    /**
     * 判断字符串是否包含中文
     * @param param 验证字符串
     * @return 是否包含中文
     */
    public static boolean containsChinese(String param) {
        String regex = "[\\u4e00-\\u9fa5]";
        return Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(param).find();
    }

}
