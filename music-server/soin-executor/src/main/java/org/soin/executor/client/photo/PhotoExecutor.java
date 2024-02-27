package org.soin.executor.client.photo;

import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import org.soin.core.domain.database.entity.ImageDataBase;
import org.soin.core.domain.database.service.ImageDataBaseService;
import org.soin.core.domain.singer.serivce.SingerService;
import org.soin.core.domain.singer.vo.SingerVo;
import org.soin.core.infrastructure.base.common.RunTimeTool;
import org.soin.core.infrastructure.utils.ChineseUtil;
import org.soin.core.infrastructure.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-27 11:41
 **/
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PhotoExecutor {

    /**
     * 歌手服务
     */
    private final SingerService singerService;

    /**
     * 图片库服务
     */
    private final ImageDataBaseService imageDataBaseService;

    /**
     * 图片根目录
     */
    @Value("${origin.local.imageNode:null}")
    private String imageNode;

    /**
     * 同步歌手文件夹
     *
     * @implNote 按需手动执行
     */
    @XxlJob(value = "packageBuild")
    public void packageBuild() {
        Date time = new Date();
        RunTimeTool.printTaskStartMsg("packageBuild", "同步歌手文件夹", time);
        List<SingerVo> list = singerService.list();
        if (null == list || list.isEmpty()) {
            RunTimeTool.printError("暂无歌手需要同步...");
            return;
        }
        RunTimeTool.printInfo(String.format("待同步歌手数量%s...", list.size()));
        for (SingerVo singerVo : list) {
            //歌手名称
            String name = singerVo.getName();
            //下一级包名
            String packName = ChineseUtil.chineseToPinyin(name);
            //生成文件夹
            FileUtil.ensureGet(imageNode, packName);
        }
        RunTimeTool.printTaskEndMsg("packageBuild", time);
    }

    /**
     * 同步文件大小
     *
     * @implNote 按需手动执行
     */
    @XxlJob(value = "sizeBuild")
    public void sizeBuild() {
        Date time = new Date();
        RunTimeTool.printTaskStartMsg("sizeBuild", "同步图片大小", time);
        List<ImageDataBase> list = imageDataBaseService.list();
        if (null == list || list.isEmpty()) {
            RunTimeTool.printWarn("暂无文件需要同步...");
            return;
        }
        for (ImageDataBase base : list) {
            long size = FileUtil.size(base.getPath());
            base.setSize(size);
            boolean isOpen = imageDataBaseService.update(base);
            RunTimeTool.printMethodResponseMsg("imageDataBaseService.update", isOpen);
        }
        RunTimeTool.printTaskEndMsg("sizeBuild", time);
    }
}


