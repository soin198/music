package org.soin.manage.contorller;

import org.soin.core.infrastructure.base.response.GenericResponse;
import org.springframework.web.bind.annotation.*;


/**
 * 后台登录接口
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-08 15:07
 **/
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @PostMapping("/login")
    public GenericResponse<?> login(@RequestParam(value = "username") String username,
                                    @RequestParam(value = "password") String password) {
        return GenericResponse.builder().success(true);

    }
}
