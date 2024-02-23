package org.soin.core.domain.singer.serivce;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.soin.core.domain.singer.entity.Singer;
import org.soin.core.domain.singer.params.SingerParams;
import org.soin.core.domain.singer.repository.ISingerRepository;
import org.soin.core.domain.singer.vo.SingerVo;
import org.soin.core.infrastructure.base.common.Page;
import org.soin.core.infrastructure.base.common.Assert;
import org.soin.core.infrastructure.utils.ConvertUtil;
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
     * 获取歌手分页列表
     *
     * @param singerParams 歌手列表查询数据源
     * @return 歌手分页列表
     */
    public Page<SingerVo> singerQuery(SingerParams singerParams) {
        int totalRows = iSingerRepository.count(singerParams);
        List<SingerVo> list = (totalRows > 0) ? iSingerRepository.singerQuery(singerParams) : Lists.newArrayList();
        return new Page<>(totalRows, list.stream().peek(p -> p.setBase64(ImageUtil.generate(p.getPhoto()))).collect(Collectors.toList()));
    }

    /**
     * 根据歌手ID获取歌手详情
     *
     * @param singerId 歌手ID
     * @return 歌手数据
     */
    public SingerVo singerQueryById(Long singerId) {
        Assert.isNull(singerId, "请提供歌手ID");
        Singer singer = iSingerRepository.getOneByKeyId(singerId);
        Assert.isNull(singer, "singerId is invalid");
        SingerVo convert = ConvertUtil.convert(singer, SingerVo.class);
        convert.setBase64(ImageUtil.generate(convert.getPhoto()));
        return convert;
    }

    /**
     * 获取所有的歌手
     *
     * @return 歌手列表
     */
    public List<SingerVo> list() {
        List<Singer> list = iSingerRepository.list();
        if (null == list || list.isEmpty()) {
            return Lists.newArrayList();
        }
        return ConvertUtil.converts(list, SingerVo.class);
    }

}
