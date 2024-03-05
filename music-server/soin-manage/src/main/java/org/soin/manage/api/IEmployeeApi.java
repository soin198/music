package org.soin.manage.api;

import org.soin.core.domain.manageEmployee.vo.LoginVo;
import org.soin.core.infrastructure.base.constant.BaseConstant;
import org.soin.core.infrastructure.base.response.GenericResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:37
 **/
@RequestMapping(BaseConstant.BASE_SERVICE_URL + "/employee")
public interface IEmployeeApi {

    /**
     * 后台登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录参数
     */
    @PostMapping("/login")
    GenericResponse<LoginVo> login(@RequestParam(value = "username") String username,
                                   @RequestParam(value = "password") String password);

}
