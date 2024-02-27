package org.soin.executor.client.music;

import com.xxl.job.core.handler.annotation.XxlJob;
import org.soin.core.infrastructure.base.common.RunTimeTool;
import org.springframework.stereotype.Component;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-27 11:41
 **/
@Component
public class MusicExecutor {

    @XxlJob("sync")
    public void sync(){
        RunTimeTool.printInfo("bean sync start....");
    }
}


