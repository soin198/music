package org.soin.core.infrastructure.mapper.clientCustom;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.soin.core.domain.cilentCustom.entity.Custom;
import org.soin.core.domain.cilentCustom.vo.CustomVo;
import org.soin.core.domain.manageEmployee.params.CustomParams;

import java.util.List;

/**
 * 用户接口
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-09 14:02
 **/
public interface CustomMapper extends BaseMapper<Custom> {

    /**
     * 统计用户总数
     *
     * @param params 统计数据源
     * @return 用户总数
     */
    int count(CustomParams params);

    /**
     * 查询用户列表
     *
     * @param params 查询数据源
     * @return 用户列表
     */
    List<CustomVo> list(CustomParams params);
}
