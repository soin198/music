package org.soin.core.domain.manageEmployee.service;

import lombok.RequiredArgsConstructor;
import org.soin.core.domain.manageEmployee.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 后台实现
 *
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
        return null;
    }
}
