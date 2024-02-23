package org.soin.client.controller;

import lombok.RequiredArgsConstructor;
import org.soin.client.api.ISingerApi;
import org.soin.core.domain.singer.params.SingerParams;
import org.soin.core.domain.singer.entity.Singer;
import org.soin.core.domain.singer.serivce.SingerService;
import org.soin.core.domain.singer.vo.SingerVo;
import org.soin.core.infrastructure.base.common.Page;
import org.soin.core.infrastructure.base.response.GenericResponse;
import org.soin.core.infrastructure.base.common.Assert;
import org.soin.core.infrastructure.utils.DateUtil;
import org.soin.core.infrastructure.base.common.RunTimeTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-08 18:13
 **/
@ResponseBody
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SingerController implements ISingerApi {

    private final SingerService singerService;

    /**
     * 获取歌手分页列表
     *
     * @param singerParams 歌手列表查询数据源
     * @return 歌手分页列表
     */
    @Override
    public GenericResponse<Page<SingerVo>> singerQuery(SingerParams singerParams) {
        RunTimeTool.printMethodMsg("singerQuery", "获取歌手分页列表", singerParams);
        Assert.isTrue(Singer.Gender.contain(singerParams.getSingerType()), "歌手类型异常");
        Page<SingerVo> page = singerService.singerQuery(singerParams);
        RunTimeTool.printMethodResponseMsg("singerQuery", page.getTotalRows());
        return GenericResponse.builder().success(page);
    }

    /**
     * 根据歌手ID获取歌手详情
     *
     * @param singerId 歌手ID
     * @return 歌手数据
     */
    @Override
    public GenericResponse<SingerVo> singerQueryById(Long singerId) {
        Assert.isNull(singerId, "请正确选择查看的歌手");
        RunTimeTool.printMethodMsg("singerQueryById", "根据歌手ID获取歌手详情", singerId);
        SingerVo singerVo = singerService.singerQueryById(singerId);
        String build = DateUtil.format(singerVo.getBirth(), DateUtil.DATE_YEAR_MONTH_DAY);
        singerVo.setBirth(build);
        RunTimeTool.printMethodResponseMsg("singerQueryById", singerVo);
        return GenericResponse.builder().success(singerVo);
    }
}
