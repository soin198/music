package org.soin.core.infrastructure.utils;


import org.soin.core.infrastructure.base.constant.BaseConstant;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2023-12-13 11:27
 **/
@SuppressWarnings("all")
public class ImageUtil {

    /**
     * 生成base64图片
     *
     * @param path 图片路径
     * @return base64
     */
    public static String generate(String path) {
        int index = path.lastIndexOf("/") + 1;
        String imageName = path.substring(index);
        File file = new File((BaseConstant.IMAGE_URL + "/" + imageName));
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

}
