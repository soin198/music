package org.soin.core.domain.cilentCustom.service;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.soin.core.domain.cilentCustom.bo.CustomBo;
import org.soin.core.domain.cilentCustom.entity.Custom;
import org.soin.core.domain.cilentCustom.repository.ICustomRepository;
import org.soin.core.domain.cilentCustom.vo.CustomVo;
import org.soin.core.domain.cilentCustom.vo.LoginVo;
import org.soin.core.domain.database.service.ImageDataBaseService;
import org.soin.core.domain.manageEmployee.params.CustomParams;
import org.soin.core.infrastructure.base.common.Assert;
import org.soin.core.infrastructure.base.common.Page;
import org.soin.core.infrastructure.base.common.RunTimeTool;
import org.soin.core.infrastructure.enums.CommonTimeEnum;
import org.soin.core.infrastructure.enums.RegionEnum;
import org.soin.core.infrastructure.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
     * 图片库服务
     */
    private final ImageDataBaseService imageDataBaseService;

    /**
     * 前台登录站点
     *
     * @param username 用户名
     * @param password 密码
     * @return 当前登录用户信息
     */
    public LoginVo login(String username, String password) {
        Assert.isBlank(username, "请提供用户名");
        Assert.isBlank(password, "请输入密码");
        Custom byUserNameQuery = customRepository.getUserByUserName(username);
        Assert.isNull(byUserNameQuery, "用户不存在，请更换用户名后重试");
        Custom custom = customRepository.getUserByNameAndPassword(username, password);
        Assert.isNull(custom, "密码错误，请重试");
        LoginVo loginVo = new LoginVo();
        Long customId = custom.getId();
        Long imageId = custom.getImageId();
        loginVo.setUserId(customId);
        loginVo.setUsername(custom.getNickname());
        String path = imageDataBaseService.pathQuery(imageId);
        loginVo.setPhoto(ImageUtil.generate(path));
        String token = CacheUtil.secureGet(customId, String.class, t -> JwtUtil.generateToken(customId), CommonTimeEnum.SECS_1800.getSecond(), TimeUnit.SECONDS, RegionEnum.CLIENT);
        loginVo.setToken(token);
        return loginVo;
    }

    /**
     * 注册客户端账号
     *
     * @param convert 注册参数数据源
     * @return 是否注册成功
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean register(CustomBo convert) {
        Assert.isNull(convert, "请提供注册数据源");
        Custom custom = ConvertUtil.convert(convert, Custom.class);
        customRepository.insert(custom);
        Long userId = custom.getId();
        RunTimeTool.printMethodResponseMsg("customRepository.insert", userId);
        Integer province = convert.getProvince();
        Integer city = convert.getCity();
        Integer region = convert.getRegion();
        customAreaService.insert(userId, province, city, region);
        return Boolean.TRUE;
    }

    /**
     * 根据人员ID获取用户
     *
     * @param userId 人员ID
     * @return 用户
     */
    public CustomVo getOne(Long userId) {
        Assert.isNull(userId, "请提供userId");
        Custom custom = customRepository.getOne(userId);
        return ConvertUtil.convert(custom, CustomVo.class);
    }

    /**
     * 注销账号
     *
     * @param userId 当前人员ID
     * @return 是否注销成功
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean cancel(Long userId) {
        Assert.isNull(userId, "请提供人员ID");
        boolean isOpen = customRepository.cancel(userId);
        Assert.isTrue(!isOpen, "注销失败，请稍后重试");
        boolean remove = CacheUtil.remove(userId, RegionEnum.CLIENT);
        RunTimeTool.printMethodResponseMsg("remove", remove);
        return Boolean.TRUE;
    }

    /**
     * 获取图形验证码
     *
     * @return java.lang.String
     * @author gjx
     * @date 2024/2/20 15:30
     **/
    public Map<String, String> generateValidateCode() {
        //通过工具类生成图片验证码
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150, 48, 4, 20);
        //验证码的值
        String codeValue = circleCaptcha.getCode();
        //将图片进行base64编码，并返回
        String imageBase64 = circleCaptcha.getImageBase64();
        String key = UUID.randomUUID().toString().replaceAll("-", "");
        CacheUtil.put("code:validate:" + key, codeValue, CommonTimeEnum.SECS_30.getSecond(), TimeUnit.SECONDS, RegionEnum.CLIENT);
        return Map.of("key", key, "imageBase64", "data:image/png;base64," + imageBase64);
    }


    /**
     * 根据手机号码获取用户
     *
     * @param phone 手机号码
     * @return 用户信息
     */
    public Custom phoneQuery(String phone) {
        return customRepository.getUserByPhone(phone);
    }

    /**
     * 获取获取列表分页
     *
     * @param params 查询数据源
     * @return 客户分页列表
     */
    public Page<CustomVo> page(CustomParams params) {
        int rows = customRepository.count(params);
        List<CustomVo> list = (rows > 0) ? customRepository.list(params) : Lists.newArrayList();
        list = list.stream().peek(item -> item.setPhoto(ImageUtil.generate(item.getPhoto()))).collect(Collectors.toList());
        return new Page<>(rows, list);
    }
}
