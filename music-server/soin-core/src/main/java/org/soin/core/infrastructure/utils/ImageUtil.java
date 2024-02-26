package org.soin.core.infrastructure.utils;


import org.soin.core.infrastructure.base.common.Assert;
import org.soin.core.infrastructure.base.constant.BaseConstant;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2023-12-13 11:27
 **/
public class ImageUtil {

    /**
     * 平台支持图片类型
     */
    public static final List<String> IMAGE_TYPE = List.of("jpg", "png", "jpeg");

    /**
     * 生成base64图片
     *
     * @param path 图片路径
     * @return base64
     */
    @SuppressWarnings("all")
    public static String generate(String path) {
        Assert.isBlank(path, "path is null");
        File file = new File(BaseConstant.IMAGE_URL + "/" + path);
        if (!file.exists()) {
            return null;
        }
        String result = null;
        try {
            byte[] bytes = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(bytes);
            fis.close();
            result = "data:image/jpg;base64," + Base64.getEncoder().encodeToString(bytes);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return result;
    }

    /**
     * 生成唯一图片名称
     *
     * @param imageName 图片名称
     * @return 图片名称
     */
    public static String generateImageName(String imageName) {
        int index = imageName.indexOf('.');
        String suffix = imageName.substring(index + 1);
        boolean contains = IMAGE_TYPE.contains(suffix);
        Assert.isTrue(!contains, "暂不支持此文件类型");
        String secureId = SecureUtil.generateRandomHash();
        return (secureId + "." + suffix);
    }

}
