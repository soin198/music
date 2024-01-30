package org.soin.website.biz.serivce.impl;

import io.jsonwebtoken.lang.Assert;
import lombok.RequiredArgsConstructor;
import org.soin.core.biz.common.enums.FolderEnum;
import org.soin.core.biz.entity.Custom;
import org.soin.core.biz.mapper.CustomMapper;
import org.soin.core.util.CacheUtil;
import org.soin.core.util.CustomAssert;
import org.soin.core.util.JwtUtil;
import org.soin.website.biz.serivce.ICustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 用户接口实现
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-09 11:50
 **/
@Service("iCustomService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomServiceImpl implements ICustomService {

    private final CustomMapper customMapper;

    /**
     * 验证登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 是否登录成功
     */
    @Override
    public String login(String username, String password) {
        CustomAssert.isTrueAndString(username, "请提供用户名");
        CustomAssert.isTrueAndString(password, "请输入密码");
        Custom custom = customMapper.getUserByNameAndPassword(username, password);
        Assert.notNull(custom, "很抱歉，该用户名不存在");
        final String token = JwtUtil.generateToken(100L);
        Assert.hasText(token, "token is null");
        CacheUtil.put(custom.getId(), token, 1800L, TimeUnit.SECONDS, FolderEnum.CLIENT);
        return token;
    }
}
