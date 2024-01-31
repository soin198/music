package org.soin.core.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.soin.core.domain.custom.entity.Custom;
import org.soin.core.domain.custom.repository.ICustomRepository;
import org.soin.core.infrastructure.mappers.mapper.ICustomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
     * 根据用户名密码获取用户
     * {@link Custom}
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户
     */
    @Override
    public Custom getUserByNameAndPassword(String username, String password) {
        QueryWrapper<Custom> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        wrapper.eq("password", password);
        return customMapper.selectOne(wrapper);
    }
}
