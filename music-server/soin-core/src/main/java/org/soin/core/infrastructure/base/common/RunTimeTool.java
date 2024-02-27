package org.soin.core.infrastructure.base.common;

import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Date;

/**
 * 运行时日志
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2023-11-28 15:07
 **/
@SuppressWarnings("all")
public class RunTimeTool {

    /**
     * Logger_Bean
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RunTimeTool.class);

    /**
     * 默认输出时间格式
     */
    public static final FastDateFormat FORMAT = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

    /**
     * 暂停当前线程
     *
     * @param sleepTime 睡眠时间
     */
    public static void sleepThread(Long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * 输出INFO级别日志
     *
     * @param message 输出文本
     */
    public static <T> void printInfo(T... message) {
        LOGGER.info("执行内容：{}", Arrays.toString(message));
    }

    /**
     * 输出DEBUG级别日志
     *
     * @param message 输出文本
     */
    public static <T> void printDebug(T... message) {
        if (!LOGGER.isDebugEnabled()) {
            return;
        }
        LOGGER.debug("执行内容：{}", Arrays.toString(message));
    }

    /**
     * 输出WARN级别日志
     *
     * @param message 输出文本
     */
    public static <T> void printWarn(T... message) {
        LOGGER.warn("执行内容：{}", Arrays.toString(message));
    }

    /**
     * 输出ERROR级别日志
     *
     * @param message 输出文本
     */
    public static <T> void printError(T... message) {
        LOGGER.error("执行内容：{}", Arrays.toString(message));
    }

    /**
     * 输出执行方法入口信息
     *
     * @param methodName 方法名
     * @param message    执行信息
     */
    public static void printMethodMsg(String methodName, String message) {
        LOGGER.info("请求方法：[{}]，[{}]", methodName, message);
    }

    /**
     * 输出执行方法入口信息包含入参参数
     *
     * @param methodName 方法名
     * @param message    执行信息
     * @param params     入参参数
     */
    public static <TYPE> void printMethodMsg(String methodName, String message, Object... params) {
        LOGGER.info("请求方法：[{}]，[{}]，请求参数：[{}]", methodName, message, JSONObject.toJSONString(params));
    }

    /**
     * 输出方法响应体
     *
     * @param methodName 方法名
     * @param params     响应参数
     */
    public static void printMethodResponseMsg(String methodName, Object... params) {
        LOGGER.info("响应方法：[{}]，响应参数：[{}]", methodName, JSONObject.toJSONString(params));
    }

    /**
     * 日志输出执行器开始执行信息
     *
     * @param executorName 执行器名词
     * @param executorMsg  执行器处理信息
     * @param startTime    执行器开始执行时间
     */
    public static void printTaskStartMsg(String executorName, String executorMsg, Date startTime) {
        LOGGER.info("开始执行定时器：[{}]，[{}]，开始执行时间：[{}]", executorName, executorMsg, FORMAT.format(startTime));
    }

    /**
     * 日志输出执行器结束时间 & 执行器执行耗时时间
     *
     * @param executorName 执行器名词
     * @param startTime    执行器开始时间
     */
    public static void printTaskEndMsg(String executorName, Date startTime) {
        Date now = new Date();
        LOGGER.info("定时器：[{}]执行完成，完成时间：[{}]，耗时[{}]完成",
                executorName,
                FORMAT.format(now),
                DateUtil.formatBetween(startTime, now, BetweenFormatter.Level.MILLISECOND));
    }

    /**
     * 日志输出执行器响应数据
     *
     * @param executorName 执行器名词
     * @param params       执行器响应参数
     */
    public static <TYPE> void printTaskResponse(String executorName, TYPE... params) {
        LOGGER.info("定时器：[{}]执行完成，响应值[{}]", executorName, JSONObject.toJSONString(params));
    }

}
