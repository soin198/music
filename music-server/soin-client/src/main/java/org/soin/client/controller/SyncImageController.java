package org.soin.client.controller;

import lombok.RequiredArgsConstructor;
import org.soin.client.api.privateApi.ISyncImageApi;
import org.soin.core.domain.singer.serivce.SingerService;
import org.soin.core.domain.singer.vo.SingerVo;
import org.soin.core.infrastructure.base.common.RunTimeTool;
import org.soin.core.infrastructure.base.response.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class SyncImageController implements ISyncImageApi {

    private final SingerService singerService;

    /**
     * 图片跟目录
     */
    @Value("${image.local.cache}")
    private String path;

    /**
     * 同步歌手头像图片名称
     *
     * @return 是否同步成功
     */
    @Override
    public GenericResponse<Boolean> syncImageName() {
        RunTimeTool.printMethodMsg("syncImageName", "同步歌手图片");
        List<SingerVo> list = singerService.list();
        if (null == list || list.isEmpty()) {
            RunTimeTool.printError("暂无歌手需要同步...");
            return GenericResponse.builder().success(Boolean.FALSE);
        }
        RunTimeTool.printInfo(String.format("待同步歌手数量%s...", list.size()));
        for (SingerVo singerVo : list) {

        }
        RunTimeTool.printMethodResponseMsg("syncImageName", Boolean.TRUE);
        return GenericResponse.builder().success(Boolean.TRUE);
    }
}
