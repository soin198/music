package org.soin.client.controller;

import lombok.RequiredArgsConstructor;
import org.soin.client.api.ICustomApi;
import org.soin.client.api.dto.RegisterDTO;
import org.soin.core.domain.cilentCustom.bo.CustomBo;
import org.soin.core.domain.cilentCustom.service.CustomService;
import org.soin.core.domain.cilentCustom.vo.CustomVo;
import org.soin.core.domain.cilentCustom.vo.LoginVo;
import org.soin.core.infrastructure.base.response.GenericResponse;
import org.soin.core.infrastructure.utils.ConvertUtil;
import org.soin.core.infrastructure.utils.Assert;
import org.soin.core.infrastructure.utils.RunTimeTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制层
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-09 11:43
 **/
@ResponseBody
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomController implements ICustomApi {

    private final CustomService customService;

    /**
     * 前台站点登录接口
     *
     * @param username 用户名
     * @param password 密码
     * @return 当前登录用户信息
     */
    @Override
    public GenericResponse<LoginVo> login(String username, String password) {
        RunTimeTool.printMethodMsg("login", "开始验证登录", username, password);
        Assert.isBlank(username, "请输入用户名");
        Assert.isBlank(username, "请输入密码");
        LoginVo vo = customService.login(username, password);
        RunTimeTool.printMethodResponseMsg("login", vo);
        return GenericResponse.builder().success(vo);
    }

    /**
     * 注册平台用户
     *
     * @param register 注册数据源
     * @return 是否注册成功
     */
    @Override
    public GenericResponse<Boolean> register(RegisterDTO register) {
        RunTimeTool.printMethodMsg("register", "注册平台用户", register);
        Assert.isNull(register, "请提供注册数据");
        CustomBo convert = ConvertUtil.convert(register, CustomBo.class);
        boolean isOpen = customService.register(convert);
        RunTimeTool.printMethodResponseMsg("register", isOpen);
        return GenericResponse.builder().success(isOpen);
    }

    /**
     * 获取用户详情
     *
     * @param userId 当前人员ID
     * @return 当前登录人员详情
     */
    @Override
    public GenericResponse<CustomVo> detailsQuery(Long userId) {
        RunTimeTool.printMethodMsg("detailsQuery", "获取用户信息", userId);
        Assert.isNull(userId, "登录失效，请重新登录");
        CustomVo customVo = customService.getOne(userId);
        RunTimeTool.printMethodResponseMsg("detailsQuery", customVo);
        return GenericResponse.builder().success(customVo);
    }

    /**
     * 注销账号
     *
     * @param userId 当前人员ID
     * @return 是否注销成功
     */
    @Override
    public GenericResponse<Boolean> cancel(Long userId) {
        RunTimeTool.printMethodMsg("cancel", "注销账户", userId);
        Assert.isNull(userId, "请提供人员ID");
        boolean isOpen = customService.cancel(userId);
        RunTimeTool.printMethodResponseMsg("cancel", isOpen);
        return GenericResponse.builder().success(isOpen);
    }

}
