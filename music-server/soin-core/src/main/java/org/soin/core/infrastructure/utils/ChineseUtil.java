package org.soin.core.infrastructure.utils;


import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.soin.core.infrastructure.base.common.Assert;

/**
 * 中文转换拼音工具
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-23 15:12
 **/
public class ChineseUtil {

    public static String chineseToPinyin(String value) {
        Assert.isBlank(value, "error convert value is null!");
        if (!RegexExpressionUtil.containsChinese(value)) {
            return value;
        }
        //约定拼音输出格式
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        //输出为小写
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        //不包含声调
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        StringBuilder builder = new StringBuilder();
        char[] chars = value.toCharArray();
        for (char val : chars) {
            boolean isOpen = Character.toString(val).matches("[\\u4e00-\\u9fa5]");
            if (!isOpen) {
                builder.append(val);
                continue;
            }
            String[] pinyinArray = null;
            try {
                pinyinArray = PinyinHelper.toHanyuPinyinStringArray(val, format);
            } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                badHanyuPinyinOutputFormatCombination.printStackTrace();
            }
            if (pinyinArray != null) {
                String pyValue = pinyinArray[0];
                String data = (pyValue.contains(":")) ? pinyinArray[0].replace(":", "") : pyValue;
                builder.append(data);
            } else {
                builder.append(val);
            }
        }
        return builder.toString();
    }

}
