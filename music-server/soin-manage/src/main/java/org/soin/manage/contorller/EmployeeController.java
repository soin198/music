package org.soin.manage.contorller;

import lombok.RequiredArgsConstructor;
import org.soin.core.domain.manageEmployee.service.EmployeeService;
import org.soin.core.infrastructure.base.response.GenericResponse;
import org.soin.core.infrastructure.base.common.RunTimeTool;
import org.soin.manage.api.IEmployeeApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-08 15:07
 **/
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeController implements IEmployeeApi {

    private final EmployeeService employeeService;

    /**
     * 后台登录
     *
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    @Override
    public GenericResponse<String> login(String username, String password) {
        RunTimeTool.printMethodMsg("login", "后台员工登录", username, password);
        String token = employeeService.login(username, password);
        RunTimeTool.printMethodResponseMsg("login", token);
        return GenericResponse.builder().success(token);
    }
}
