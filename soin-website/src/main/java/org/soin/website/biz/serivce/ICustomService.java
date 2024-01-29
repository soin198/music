package org.soin.website.biz.serivce;

/**
 * 客户接口
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-09 11:45
 **/
public interface ICustomService {

    /**
     * 验证登录
     *
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    String login(String username, String password);
}
