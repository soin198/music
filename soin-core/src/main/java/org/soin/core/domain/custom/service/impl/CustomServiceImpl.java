package org.soin.core.domain.custom.service.impl;

import lombok.RequiredArgsConstructor;
import org.soin.core.domain.custom.entity.Custom;
import org.soin.core.domain.custom.repository.ICustomRepository;
import org.soin.core.domain.custom.service.ICustomService;
import org.soin.core.infrastructure.enums.CommonTimeEnum;
import org.soin.core.infrastructure.enums.FolderEnum;
import org.soin.core.infrastructure.utils.Assert;
import org.soin.core.infrastructure.utils.CacheUtil;
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
        Assert.isBlank(username, "请提供用户名");
        Assert.isBlank(password, "请输入密码");
        Custom byUserNameQuery = customRepository.getUserByUserName(username);
        Assert.isNull(byUserNameQuery, "用户不存在，请更换用户名后重试");
        Custom custom = customRepository.getUserByNameAndPassword(username, password);
        Assert.isNull(custom, "密码错误，请重试");
        Long customId = custom.getId();
        return CacheUtil.secureGet(customId, String.class, t -> JwtUtil.generateToken(customId), CommonTimeEnum.SECS_300.getSecond(), TimeUnit.SECONDS, FolderEnum.CLIENT);
    }

}
