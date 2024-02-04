package org.soin.client.api;

import org.soin.client.api.dto.RegisterDTO;
import org.soin.core.infrastructure.base.response.GenericResponse;
import org.soin.core.infrastructure.base.constant.BaseConstant;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
     * @return token
     */
    @PostMapping("/login")
    GenericResponse<String> login(@RequestParam(value = "username") String username,
                                  @RequestParam(value = "password") String password);

    /**

     * 注册平台用户
     *
     * @param registerDTO 注册数据源
     * @return 是否注册成功
     */
    @PostMapping("/register")
    GenericResponse<Boolean> register(@RequestBody RegisterDTO registerDTO);

}
