package org.soin.core.domain.cilentCustom.service;

import lombok.RequiredArgsConstructor;
import org.soin.core.domain.cilentCustom.repository.ICustomAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户省市区服务
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-09 11:50
 **/
@Service("iCustomAreaService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomAreaService {

    private final ICustomAreaRepository customAreaRepository;

    /**
     * 添加用户人员地址
     *
     * @param userId   人员ID
     * @param province 省份
     * @param city     市区
     * @param region   区县
     * @return 是否添加成功
     */
    protected boolean insert(Long userId, Integer province, Integer city, Integer region) {
        return customAreaRepository.insert(userId, province, city, region);
    }


}
