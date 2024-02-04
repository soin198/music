package org.soin.core.domain.cilentCustom.repository;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:05
 **/
public interface ICustomAreaRepository {

    /**
     * 添加用户人员地址
     *
     * @param userId   人员ID
     * @param province 省份
     * @param city     市区
     * @param region   区县
     * @return 是否添加成功
     */
    boolean insert(Long userId, Integer province, Integer city, Integer region);
}
