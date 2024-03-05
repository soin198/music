package org.soin.manage.api;

import org.soin.core.domain.cilentCustom.vo.CustomVo;
import org.soin.core.domain.manageEmployee.params.CustomParams;
import org.soin.core.infrastructure.base.common.Page;
import org.soin.core.infrastructure.base.constant.BaseConstant;
import org.soin.core.infrastructure.base.response.GenericResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:37
 **/
@RequestMapping(BaseConstant.BASE_BACKSTAGE_SERVICE_URL + "/custom")
public interface ICustomApi {

    /**
     * 获取获取列表分页
     *
     * @param params 查询数据源
     * @return 客户分页列表
     */
    @PostMapping("/page")
    GenericResponse<Page<CustomVo>> page(@RequestBody CustomParams params);

}
