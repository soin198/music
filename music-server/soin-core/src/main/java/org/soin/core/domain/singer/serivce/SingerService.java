package org.soin.core.domain.singer.serivce;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.soin.core.domain.singer.repository.ISingerRepository;
import org.soin.core.domain.singer.vo.SingerVo;
import org.soin.core.infrastructure.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
     * 随机获取歌手
     *
     * @param limit 查询条数
     * @return 歌手列表
     */
    public List<SingerVo> singerQuery(Integer limit) {
        List<SingerVo> list = iSingerRepository.singerQuery(limit);
        if (null == list || list.isEmpty()) {
            return Lists.newArrayList();
        }
        return list.stream().peek(p -> p.setBase64(ImageUtil.generate(p.getPhoto()))).collect(Collectors.toList());
    }
}
