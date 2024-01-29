package org.soin.core.biz.mapper;

import org.apache.ibatis.annotations.Param;
import org.soin.core.biz.entity.Custom;

/**
 * 用户接口
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-09 14:02
 **/
public interface CustomMapper {

    /**
     * 根据用户名与密码获取账户信息
     *
     * @param username 用户名
     * @param password 密码
     * @return 当前人员
     */
    Custom getUserByNameAndPassword(@Param("username") String username,
                                    @Param("password") String password);
}
