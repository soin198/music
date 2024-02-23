package org.soin.core.infrastructure.repository.manageEmployee;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.soin.core.domain.manageEmployee.entity.Employee;
import org.soin.core.domain.manageEmployee.repository.IEmployeeRepository;
import org.soin.core.infrastructure.mapper.manageEmployee.EmployeeMapper;
import org.soin.core.infrastructure.base.common.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:06
 **/
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeRepository implements IEmployeeRepository {

    private final EmployeeMapper employeeMapper;

    /**
     * 根据用户名获取员工
     *
     * @param username 用户名
     * @return 员工
     */
    @Override
    public Employee byUserNameQuery(String username) {
        Assert.isBlank(username, "请提供用户名");
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return employeeMapper.selectOne(wrapper);
    }

    /**
     * 根据用户名与密码获取员工
     *
     * @param username 用户名
     * @param password 密码
     * @return 员工
     */
    @Override
    public Employee byUserNameAndSecretKeyQuery(String username, String password) {
        Assert.isBlank(username, "请提供用户名");
        Assert.isBlank(password, "请提供密码");
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        wrapper.eq("password", password);
        return employeeMapper.selectOne(wrapper);
    }
}


