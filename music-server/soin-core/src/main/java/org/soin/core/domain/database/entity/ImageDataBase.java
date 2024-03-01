package org.soin.core.domain.database.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.soin.core.infrastructure.base.common.BaseEntity;

import java.io.Serializable;

/**
 * 图片库
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-23 16:39
 **/
@Data
@TableName(value = "t_image_database")
@EqualsAndHashCode(callSuper = true)
public class ImageDataBase extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -6183879821881831534L;

    /**
     * 图片存储路径
     */
    private String path;

    /**
     * 图片名称
     */
    private String name;

    /**
     * 图片大小(B)
     */
    private Long size;

    /**
     * 图片类型
     */
    private Type type;

    /**
     * 支持的图片类型
     */
    public enum Type {

        /**
         * JPG
         */
        JPG,

        /**
         * PNG
         */
        PNG,

        /**
         * JPEG
         */
        JPEG;
    }

    /**
     * 获取URL完整路径
     *
     * @return 图片完整路径
     */
    @SuppressWarnings("all")
    public String getURL() {
        return (this.path + "/" + this.name);
    }


}
