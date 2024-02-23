package org.soin.core.domain.manageEmployee.service;

import lombok.RequiredArgsConstructor;
import org.soin.core.domain.manageEmployee.entity.Employee;
import org.soin.core.domain.manageEmployee.repository.IEmployeeRepository;
import org.soin.core.infrastructure.enums.CommonTimeEnum;
import org.soin.core.infrastructure.enums.RegionEnum;
import org.soin.core.infrastructure.base.common.Assert;
import org.soin.core.infrastructure.utils.CacheUtil;
import org.soin.core.infrastructure.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-04 13:05
 **/
@Service(value = "EmployeeService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeService {

    private final IEmployeeRepository iEmployeeRepository;

    /**
     * 后台登录
     *
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    public String login(String username, String password) {
        Assert.isBlank(username, "请输入用户名");
        Assert.isBlank(password, "请输入密码");
        Employee userNameEmp = iEmployeeRepository.byUserNameQuery(username);
        Assert.isNull(userNameEmp, "用户名不存在");
        Employee passWordEmp = iEmployeeRepository.byUserNameAndSecretKeyQuery(username, password);
        Assert.isNull(passWordEmp, "密码错误，请重新输入");
        Long employeeId = passWordEmp.getId();
        return CacheUtil.secureGet(employeeId, String.class, t -> JwtUtil.generateToken(employeeId), CommonTimeEnum.SECS_1800.getSecond(), TimeUnit.SECONDS, RegionEnum.MANAGE);
    }
}
