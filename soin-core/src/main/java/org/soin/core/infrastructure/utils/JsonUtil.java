package org.soin.core.infrastructure.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.soin.core.infrastructure.utils.RunTimeTool;

import java.io.IOException;

/**
 * JSON相关工具
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2023-06-28 10:02
 **/
@Slf4j
public class JsonUtil {

    /**
     * 把目标对象转换为JSON字符串
     *
     * @param value 目标数据源
     * @return JSON串
     */
    public static <P> String byObjectToJson(P value) {
        if (null == value) {
            return null;
        }
        String r = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            r = objectMapper.writeValueAsString(value);
        } catch (IOException ioException) {
            RunTimeTool.printError(ioException);
            ioException.printStackTrace();
        }
        return r;
    }

    /**
     * 反序列化JSON串
     *
     * @param json json字符串
     * @return 反序列化后的参数
     */
    public static <R> R byStringToObject(String json, Class<R> classes) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        R result = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            result = objectMapper.readValue(json, classes);
        } catch (IOException ioException) {
            RunTimeTool.printError(ioException);
            ioException.printStackTrace();
        }
        return result;
    }

}
