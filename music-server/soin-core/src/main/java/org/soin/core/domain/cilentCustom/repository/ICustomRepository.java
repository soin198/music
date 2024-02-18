package org.soin.core.domain.cilentCustom.repository;

import org.soin.core.domain.cilentCustom.entity.Custom;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:05
 **/
public interface ICustomRepository {

    /**
     * 根据用户名获取用户
     * {@link org.soin.core.domain.cilentCustom.entity.Custom}
     *
     * @param username 用户名
     * @return 用户
     */
    Custom getUserByUserName(String username);

    /**
     * 根据用户名密码获取用户
     * {@link org.soin.core.domain.cilentCustom.entity.Custom}
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户
     */
    Custom getUserByNameAndPassword(String username, String password);

    /**
     * 添加客户端数据
     * {@link java.lang.Boolean}
     *
     * @param custom 添加数据源
     * @return 是否添加成功
     */
    int insert(Custom custom);

    /**
     * 根据人员ID获取用户
     * {@link org.soin.core.domain.cilentCustom.entity.Custom}
     *
     * @param userId 人员ID
     * @return 用户
     */
    Custom getOne(Long userId);

    /**
     * 注销账号
     *
     * @param userId 当前人员ID
     * @return 是否注销成功
     */
    boolean cancel(Long userId);
}
