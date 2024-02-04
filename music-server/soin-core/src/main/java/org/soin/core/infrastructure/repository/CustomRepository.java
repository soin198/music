package org.soin.core.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.soin.core.domain.custom.entity.Custom;
import org.soin.core.domain.custom.repository.ICustomRepository;
import org.soin.core.infrastructure.mappers.mapper.ICustomMapper;
import org.soin.core.infrastructure.utils.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:06
 **/
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomRepository implements ICustomRepository {

    private final ICustomMapper customMapper;

    /**
     * 根据用户名获取用户
     *
     * @param username 用户名
     * @return 用户
     */
    @Override
    public Custom getUserByUserName(String username) {
        QueryWrapper<Custom> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username.trim());
        return customMapper.selectOne(wrapper);
    }

    /**
     * 根据用户名密码获取用户
     * {@link Custom}
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户
     */
    @Override
    public Custom getUserByNameAndPassword(String username, String password) {
        Assert.isBlank(username, "username is null");
        Assert.isBlank(password, "password is null");
        QueryWrapper<Custom> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        wrapper.eq("password", password);
        return customMapper.selectOne(wrapper);
    }

    /**
     * 添加客户端数据
     * {@link java.lang.Boolean}
     *
     * @param custom 添加数据源
     * @return 是否添加成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(Custom custom) {
        Assert.isNull(custom, "请提供客户端数据源");
        String username = custom.getUsername().trim();
        Assert.isBlank(username, "请输入用户名");
        Custom userByUserName = this.getUserByUserName(username);
        Assert.isNotNull(userByUserName, "用户名重复，请更换一个吧!");
        String password = custom.getPassword().trim();
        Assert.isBlank(password, "请输入密码");
        Custom.GenderEnum sex = custom.getSex();
        Assert.isNull(sex, "请选择性别");
        String phone = custom.getPhone();
        Assert.isBlank(phone, "请提供手机号码");
        String email = custom.getEmail();
        Assert.isBlank(email, "请提供邮箱");
        LocalDate birth = custom.getBirth();
        Assert.isNull(birth, "请选择您的生日");
        String resume = custom.getResume();
        Assert.isBlank(resume, "请输入您的个性签名");
        return customMapper.insert(custom);
    }

}


