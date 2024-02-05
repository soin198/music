package org.soin.core.domain.manageEmployee.repository;

import org.soin.core.domain.manageEmployee.entity.Employee;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:05
 **/
public interface IEmployeeRepository {

    /**
     * 根据用户名获取员工
     *
     * @param username 用户名
     * @return 员工
     */
    Employee byUserNameQuery(String username);

    /**
     * 根据用户名与密码获取员工
     *
     * @param username 用户名
     * @param password 密码
     * @return 员工
     */
    Employee byUserNameAndSecretKeyQuery(String username, String password);
}
