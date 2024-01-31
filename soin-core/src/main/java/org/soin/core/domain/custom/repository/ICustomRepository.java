package org.soin.core.domain.custom.repository;

import org.soin.core.domain.custom.entity.Custom;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:05
 **/
public interface ICustomRepository {

    /**
     * 根据用户名密码获取用户
     * {@link Custom}
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户
     */
    Custom getUserByNameAndPassword(String username, String password);
}