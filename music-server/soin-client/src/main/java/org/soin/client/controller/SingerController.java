package org.soin.client.controller;

import lombok.RequiredArgsConstructor;
import org.soin.client.api.ISingerApi;
import org.soin.core.domain.singer.serivce.SingerService;
import org.soin.core.domain.singer.vo.SingerVo;
import org.soin.core.infrastructure.base.response.GenericResponse;
import org.soin.core.infrastructure.utils.RunTimeTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
     * 随机获取20个歌手
     *
     * @return 歌手列表
     */
    @Override
    public GenericResponse<List<SingerVo>> singerQuery() {
        RunTimeTool.printMethodMsg("singerQuery", "随机获取20个歌手");
        List<SingerVo> list = singerService.singerQuery();
        RunTimeTool.printMethodResponseMsg("singerQuery", list);
        return GenericResponse.builder().success(list);
    }
}
