package org.soin.core.domain.singer.serivce;

import lombok.RequiredArgsConstructor;
import org.soin.core.domain.singer.repository.ISingerRepository;
import org.soin.core.domain.singer.vo.SingerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-04 13:05
 **/
@Service(value = "SingerService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SingerService {

    private final ISingerRepository iSingerRepository;

    /**
     * 随机获取20个歌手
     *
     * @return 歌手列表
     */
    public List<SingerVo> singerQuery() {
        return iSingerRepository.singerQuery();
    }
}
