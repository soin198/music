package org.soin.client.api;

import org.soin.core.domain.singer.params.SingerParams;
import org.soin.core.domain.singer.vo.SingerVo;
import org.soin.core.infrastructure.base.common.Page;
import org.soin.core.infrastructure.base.constant.BaseConstant;
import org.soin.core.infrastructure.base.response.GenericResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-21 15:37
 **/
@RequestMapping(BaseConstant.BASE_SERVICE_URL + "/music/entry")
public interface IMusicEntryApi {

}
