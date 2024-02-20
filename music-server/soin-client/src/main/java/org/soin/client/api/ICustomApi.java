package org.soin.client.api;

import org.soin.client.api.dto.RegisterDTO;
import org.soin.core.domain.cilentCustom.vo.CustomVo;
import org.soin.core.domain.cilentCustom.vo.LoginVo;
import org.soin.core.infrastructure.base.response.GenericResponse;
import org.soin.core.infrastructure.base.constant.BaseConstant;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:37
 **/
@RequestMapping(BaseConstant.BASE_SERVICE_URL + "/custom/authority")
public interface ICustomApi {

    /**
     * 前台站点登录接口
     *
     * @param username 用户名
     * @param password 密码
     * @return 当前登录用户信息
     */
    @PostMapping("/login")
    GenericResponse<LoginVo> login(@RequestParam(value = "username") String username,
                                   @RequestParam(value = "password") String password,
                                   @RequestParam(value = "code") String code);

    /**
     * 注册平台用户
     *
     * @param registerDTO 注册数据源
     * @return 是否注册成功
     */
    @PostMapping("/register")
    GenericResponse<Boolean> register(@RequestBody RegisterDTO registerDTO);

    /**
     * 获取用户详情
     *
     * @param userId 当前人员ID
     * @return 当前登录人员详情
     */
    @GetMapping("/detailsQuery")
    GenericResponse<CustomVo> detailsQuery(@RequestParam("userId") Long userId);

    /**
     * 注销账号
     *
     * @param userId 当前人员ID
     * @return 是否注销成功
     */
    @PostMapping("/cancel")
    GenericResponse<Boolean> cancel(@RequestParam("userId") Long userId);

    /**
     * 生成随机图片验证码
     *
     * @return 图形验证码
     **/
    @GetMapping("/graphicCode")
    GenericResponse<Map<String,String>> graphicCode();
}
