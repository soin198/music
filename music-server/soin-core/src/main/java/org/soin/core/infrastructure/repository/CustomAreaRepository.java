package org.soin.core.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.soin.core.domain.cilentCustom.entity.CustomArea;
import org.soin.core.domain.cilentCustom.repository.ICustomAreaRepository;
import org.soin.core.infrastructure.mappers.mapper.ICustomAreaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:06
 **/
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomAreaRepository implements ICustomAreaRepository {

    private final ICustomAreaMapper customAreaMapper;

    /**
     * 添加用户人员地址
     *
     * @param userId   人员ID
     * @param province 省份
     * @param city     市区
     * @param region   区县
     * @return 是否添加成功
     */
    @Override
    public boolean insert(Long userId, Integer province, Integer city, Integer region) {
        CustomArea customArea = new CustomArea();
        customArea.setUserId(userId);
        customArea.setProvince(province);
        customArea.setCity(city);
        customArea.setRegion(region);
        customAreaMapper.insert(customArea);
        return Boolean.TRUE;
    }
}


