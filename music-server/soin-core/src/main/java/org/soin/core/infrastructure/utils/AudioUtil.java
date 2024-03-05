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
 * @date 2023-02-29 11:27
 **/
public class AudioUtil {

    /**
     * 平台支持音频类型
     */
    public static final List<String> AUDIO_TYPE = List.of("mp3");

    /**
     * 生成base64音频流
     *
     * @param path 图片路径
     * @return base64
     */
    @SuppressWarnings("all")
    public static String generate(String path) {
        Assert.isBlank(path, "path is null");
        File file = new File(BaseConstant.AUDIO_URL + "/" + path);
        if (!file.exists()) {
            return null;
        }
        String result = null;
        try {
            byte[] bytes = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(bytes);
            fis.close();
            result = "data:audio/mpeg;base64," + Base64.getEncoder().encodeToString(bytes);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return result;
    }

    /**
     * 生成唯一音频名称
     *
     * @param audioName 音频名称
     * @return 音频名称
     */
    public static String generateName(String audioName) {
        int index = audioName.indexOf('.');
        String suffix = audioName.substring(index + 1);
        boolean contains = AUDIO_TYPE.contains(suffix);
        Assert.isTrue(!contains, "暂不支持此文件类型");
        String secureId = SecureUtil.generateRandomHash();
        return (secureId + "." + suffix);
    }

}
