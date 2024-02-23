package org.soin.core.infrastructure.utils;


import org.soin.core.infrastructure.base.common.Assert;
import org.soin.core.infrastructure.base.common.RunTimeTool;
import org.soin.core.infrastructure.base.constant.BaseConstant;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 时间日期处理工具
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-08 15:12
 **/
@Component
public class FileUtil {

    /**
     * 转换文件名称
     *
     * @param fileName 文件名称
     * @return 转换后的文件名称
     */
    public static String coverFileName(String fileName) {
        Assert.isBlank(fileName, "fileName is null");
        return ImageUtil.generateImageName(fileName);
    }

    /**
     * 确认创建文件夹
     *
     * @param rootNode 根目录
     * @param fileName 文件夹名称
     */
    public static void ensureGet(String rootNode, String fileName) {
        Assert.isBlank(rootNode, "node is null");
        Assert.isBlank(fileName, "params is null");
        if (include(rootNode, fileName)) {
            return;
        }
        create(rootNode, fileName);
    }

    /**
     * 是否包含包名
     *
     * @param rootNode 根节点
     * @param fileName 包名
     * @return 是否包含包名
     */
    private static boolean include(String rootNode, String fileName) {
        Path path = Paths.get(rootNode, fileName);
        return Files.exists(path) && Files.isDirectory(path);
    }

    /**
     * 创建文件夹
     *
     * @param rootNode 根路径
     * @param fileName 文件夹名称
     */
    private static void create(String rootNode, String fileName) {
        try {
            Path path = Files.createDirectories(Paths.get((rootNode + BaseConstant.JOIN + fileName)));
            String newPath = path.toAbsolutePath().toString();
            RunTimeTool.printMethodResponseMsg("ensureGet", String.format("文件夹%s创建成功", newPath));
        } catch (IOException ioException) {
            RunTimeTool.printError("创建文件夹失败....");
            ioException.printStackTrace();
        }
    }

    /**
     * 移动文件
     *
     * @param before 之前的文件路径
     * @param after  之后的文件夹路径
     * @return 是否移动成功
     */
    public static boolean move(String before, String after) {
        Path beforeVal = Paths.get(before);
        Path afterVal = Paths.get(after);
        try {
            Files.move(beforeVal, afterVal.resolve(beforeVal.getFileName()));
        } catch (IOException ioException) {
            ioException.printStackTrace();
            RunTimeTool.printMethodResponseMsg("move", "文件移动失败");
        }
        return Boolean.TRUE;
    }

}


