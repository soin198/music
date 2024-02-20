package org.soin.core.domain.cilentCustom.service;

import lombok.RequiredArgsConstructor;
import org.soin.core.domain.cilentCustom.bo.CustomBo;
import org.soin.core.domain.cilentCustom.entity.Custom;
import org.soin.core.domain.cilentCustom.repository.ICustomRepository;
import org.soin.core.domain.cilentCustom.vo.CustomVo;
import org.soin.core.domain.cilentCustom.vo.LoginVo;
import org.soin.core.infrastructure.enums.CommonTimeEnum;
import org.soin.core.infrastructure.enums.RegionEnum;
import org.soin.core.infrastructure.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileOutputStream;
import java.io.IOException;
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
     * 校验登陆方法
     *
     * @param username 用户名
     * @param password 密码
     * @return 当前登录用户信息
     */
    private LoginVo getLoginVo(String username, String password) {
        Assert.isBlank(username, "请提供用户名");
        Assert.isBlank(password, "请输入密码");
        Custom byUserNameQuery = customRepository.getUserByUserName(username);
        Assert.isNull(byUserNameQuery, "用户不存在，请更换用户名后重试");
        Custom custom = customRepository.getUserByNameAndPassword(username, password);
        Assert.isNull(custom, "密码错误，请重试");
        LoginVo loginVo = new LoginVo();
        Long customId = custom.getId();
        loginVo.setUserId(customId);
        loginVo.setUsername(custom.getUsername());
        loginVo.setPhoto(ImageUtil.generate(custom.getPhoto()));
        String token = CacheUtil.secureGet(customId, String.class, t -> JwtUtil.generateToken(customId), CommonTimeEnum.SECS_1800.getSecond(), TimeUnit.SECONDS, RegionEnum.CLIENT);
        loginVo.setToken(token);
        return loginVo;
    }

    /**
     * 前台验证码登陆接口
     *
     * @return org.soin.core.domain.cilentCustom.vo.LoginVo
     * @author gjx
     * @date 2024/2/20 16:29
     **/
    public LoginVo login(String username, String password, String code) {
        Assert.isBlank(code, "请输入验证码");
//        Assert.isTrue(!GraphicCodeUtil.getCacheValue(code), "验证码错误，请刷新后重新输出");
        return getLoginVo(username, password);
    }

    /**
     * 验证登陆接口
     *
     * @return org.soin.core.domain.cilentCustom.vo.LoginVo
     * @author gjx
     * @date 2024/2/20 16:29
     **/
    public LoginVo login(String username, String password) {
        return getLoginVo(username, password);
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
    @Transactional(rollbackFor = Exception.class)
    public String graphicCode() {
        String code = "";
        GraphicCodeUtil verifyCode = new GraphicCodeUtil();
        try {
            verifyCode.output(verifyCode.getImage(), new FileOutputStream("code.jpg"));
//            code = verifyCode.setCache();
            code = verifyCode.getText();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;
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
}
