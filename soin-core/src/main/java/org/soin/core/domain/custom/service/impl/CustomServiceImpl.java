package org.soin.core.domain.custom.service.impl;

import io.jsonwebtoken.lang.Assert;
import lombok.RequiredArgsConstructor;
import org.soin.core.domain.custom.entity.Custom;
import org.soin.core.domain.custom.repository.ICustomRepository;
import org.soin.core.domain.custom.service.ICustomService;
import org.soin.core.infrastructure.enums.CommonTimeEnum;
import org.soin.core.infrastructure.enums.FolderEnum;
import org.soin.core.infrastructure.utils.CacheUtil;
import org.soin.core.infrastructure.utils.CustomAssert;
import org.soin.core.infrastructure.utils.JwtUtil;
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

    private final ICustomRepository customRepository;

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
        Custom custom = customRepository.getUserByNameAndPassword(username, password);
        Assert.notNull(custom, "很抱歉，该用户名不存在");
        Long customId = custom.getId();
        final String token = JwtUtil.generateToken(customId);
        Assert.hasText(token, "token is null");
        CacheUtil.put(customId, token, CommonTimeEnum.SECS_300.getSecond(), TimeUnit.SECONDS, FolderEnum.CLIENT);
        return token;
    }

}
