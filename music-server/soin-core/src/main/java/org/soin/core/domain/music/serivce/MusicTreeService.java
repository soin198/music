package org.soin.core.domain.music.serivce;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.soin.core.domain.music.entity.MusicTree;
import org.soin.core.domain.music.repository.IMusicTreeRepository;
import org.soin.core.domain.music.vo.MusicTreeVo;
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
@Service(value = "MusicTreeService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MusicTreeService {

    private final IMusicTreeRepository iMusicTreeRepository;

    /**
     * 获取所有启用歌单
     *
     * @param musicType 歌单类型
     * @return 歌单列表
     */
    public List<MusicTreeVo> musicTreeQueryByType(MusicTree.Type musicType) {
        return iMusicTreeRepository.musicTreeQueryByType(musicType);
    }

    /**
     * 随机获取歌单
     *
     * @param limit 查询条数
     * @return 歌单列表
     */
    public List<MusicTreeVo> musicTreeQuery(Integer limit) {
        List<MusicTreeVo> list = iMusicTreeRepository.musicTreeQuery(limit);
        if (null == list || list.isEmpty()) {
            return Lists.newArrayList();
        }
        return list.stream().peek(p -> p.setBase64(ImageUtil.generate(p.getPhoto()))).collect(Collectors.toList());
    }
}
