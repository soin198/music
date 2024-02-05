package org.soin.core.domain.cilentCustom.service;

import lombok.RequiredArgsConstructor;
import org.soin.core.domain.cilentCustom.bo.CustomBO;
import org.soin.core.domain.cilentCustom.entity.Custom;
import org.soin.core.domain.cilentCustom.repository.ICustomRepository;
import org.soin.core.infrastructure.enums.CommonTimeEnum;
import org.soin.core.infrastructure.enums.FolderEnum;
import org.soin.core.infrastructure.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * 用户服务
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-09 11:50
 **/
@Service(value = "CustomService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomService {

    private final ICustomRepository customRepository;

    /**
     * 用户地址服务
     */
    private final CustomAreaService customAreaService;

    /**
     * 验证登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 是否登录成功
     */
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

    /**
     * 注册客户端账号
     *
     * @param convert 注册参数数据源
     * @return 是否注册成功
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean register(CustomBO convert) {
        Assert.isNull(convert, "请提供注册数据源");
        Custom custom = ConvertUtil.convert(convert, Custom.class);
        customRepository.insert(custom);
        Long userId = custom.getId();
        RunTimeTool.printMethodResponseMsg("customRepository.insert", userId);
        /*Integer province = convert.getProvince();
        Integer city = convert.getCity();
        Integer region = convert.getRegion();
        customAreaService.insert(userId, province, city, region);*/
        return Boolean.TRUE;
    }

}
