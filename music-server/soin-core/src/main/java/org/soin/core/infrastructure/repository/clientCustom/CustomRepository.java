package org.soin.core.infrastructure.repository.clientCustom;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.soin.core.domain.cilentCustom.entity.Custom;
import org.soin.core.domain.cilentCustom.repository.ICustomRepository;
import org.soin.core.domain.manageEmployee.params.CustomParams;
import org.soin.core.infrastructure.mapper.clientCustom.CustomMapper;
import org.soin.core.infrastructure.base.common.Assert;
import org.soin.core.infrastructure.utils.RegexExpressionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:06
 **/
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomRepository implements ICustomRepository {

    private final CustomMapper customMapper;

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
        wrapper.eq("deleted", Boolean.FALSE);
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
        wrapper.eq("deleted", Boolean.FALSE);
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
        boolean userNameCondition = (username.length() > 19);
        Assert.isTrue(userNameCondition, "用户名最多支持19个字符");
        Custom userByUserName = this.getUserByUserName(username);
        Assert.isNotNull(userByUserName, "用户名重复啦");
        String password = custom.getPassword().trim();
        Assert.isBlank(password, "请输入密码");
        boolean passwordCondition = (password.length() > 19);
        Assert.isTrue(passwordCondition, "密码最多支持19个字符");
        Custom.Gender sex = custom.getSex();
        Assert.isNull(sex, "请选择性别");
        String phone = custom.getPhone();
        Assert.isBlank(phone, "请提供手机号码");
        boolean mobile = RegexExpressionUtil.isMobile(phone);
        Assert.isTrue(!mobile, "手机号无效，请重新输入");
        String email = custom.getEmail();
        Assert.isBlank(email, "请提供邮箱");
        boolean isEmail = RegexExpressionUtil.isEmail(email);
        Assert.isTrue(!isEmail, "邮箱无效，请重新输入");
        LocalDate birth = custom.getBirth();
        Assert.isNull(birth, "请选择您的生日");
        String resume = custom.getResume().trim();
        Assert.isBlank(resume, "请输入您的个性签名");
        boolean resumeCondition = (resume.length() > 255);
        Assert.isTrue(resumeCondition, "个性签名最多支持255个字符");
        custom.setDeleted(Boolean.FALSE);
        return customMapper.insert(custom);
    }

    /**
     * 根据人员ID获取用户
     * {@link org.soin.core.domain.cilentCustom.entity.Custom}
     *
     * @param userId 人员ID
     * @return 用户
     */
    @Override
    public Custom getOne(Long userId) {
        Assert.isNull(userId, "请提供人员ID");
        QueryWrapper<Custom> wrapper = new QueryWrapper<>();
        wrapper.eq("id", userId);
        wrapper.eq("deleted", Boolean.FALSE);
        Custom custom = customMapper.selectOne(wrapper);
        Assert.isNull(custom, "userId invalid");
        return custom;
    }

    /**
     * 注销账号
     *
     * @param userId 当前人员ID
     * @return 是否注销成功
     */
    @Override
    public boolean cancel(Long userId) {
        Custom custom = this.getOne(userId);
        custom.setDeleted(Boolean.TRUE);
        customMapper.updateById(custom);
        return Boolean.TRUE;
    }

    /**
     * 根据手机号码获取用户
     *
     * @param phone 手机号码
     * @return 用户信息
     */
    @Override
    public Custom getUserByPhone(String phone) {
        Assert.isBlank(phone, "请提供手机号码");
        QueryWrapper<Custom> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", phone);
        wrapper.eq("deleted", Boolean.FALSE);
        return customMapper.selectOne(wrapper);
    }

    /**
     * 统计用户总数
     *
     * @param params 统计数据源
     * @return 用户总数
     */
    @Override
    public int count(CustomParams params) {
        return customMapper.count(params);
    }

    /**
     * 查询用户列表
     *
     * @param params 查询数据源
     * @return 用户列表
     */
    @Override
    public List<org.soin.core.domain.cilentCustom.vo.backstage.CustomVo> list(CustomParams params) {
        List<org.soin.core.domain.cilentCustom.vo.backstage.CustomVo> list = customMapper.list(params);
        if (null == list) {
            return Lists.newArrayList();
        }
        return list;
    }

}


