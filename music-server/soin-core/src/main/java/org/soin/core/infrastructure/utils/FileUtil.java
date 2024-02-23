package org.soin.core.infrastructure.utils;


import org.soin.core.infrastructure.base.common.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * 时间日期处理工具
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-08 15:12
 **/
public class FileUtil {

    /**
     * 覆盖文件名称
     *
     * @param file 文件
     * @return 覆盖后的文件
     */
    public static File coverFileName(File file) {
        Assert.isNull(file, "file is null");
        File parentFile = file.getParentFile();
        String newFileName = ImageUtil.generateImageName(file.getName());
        return new File(parentFile, newFileName);
    }

    /**
     * 获取指定路径下的所有文件（包括子目录中的文件）。
     *
     * @param directoryPath 要遍历的目录路径
     * @return 包含所有文件对象的列表
     * @throws IOException 如果发生I/O错误
     */
    public static List<File> getAllFiles(String directoryPath) throws IOException {
        List<File> allFiles = new ArrayList<>();
        Path startingDir = Paths.get(directoryPath);

        Files.walkFileTree(startingDir, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                allFiles.add(file.toFile()); // 将Path转换为File对象
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) {
                // 忽略访问失败的文件
                return FileVisitResult.CONTINUE;
            }
        });

        return allFiles;
    }


    public static void main(String[] args) {
        try {
            List<File> allFiles = getAllFiles("D:\\image");
            for (File file : allFiles) {
                System.out.println(file.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


