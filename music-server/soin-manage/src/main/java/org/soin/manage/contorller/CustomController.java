package org.soin.manage.contorller;

import lombok.RequiredArgsConstructor;
import org.soin.core.domain.cilentCustom.service.CustomService;
import org.soin.core.domain.cilentCustom.vo.backstage.CustomVo;
import org.soin.core.domain.manageEmployee.params.CustomParams;
import org.soin.core.infrastructure.base.common.Page;
import org.soin.core.infrastructure.base.common.RunTimeTool;
import org.soin.core.infrastructure.base.response.GenericResponse;
import org.soin.manage.api.ICustomApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-08 15:07
 **/
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomController implements ICustomApi {

    private final CustomService customService;

    /**
     * 获取获取列表分页
     *
     * @param params 查询数据源
     * @return 客户分页列表
     */
    @Override
    public GenericResponse<Page<CustomVo>> page(CustomParams params) {
        RunTimeTool.printMethodMsg("page", "获取用户列表", params);
        Page<CustomVo> page = customService.page(params);
        RunTimeTool.printMethodResponseMsg("page", page.getTotalRows());
        return GenericResponse.builder().success(page);
    }
}
