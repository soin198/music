package org.soin.core.infrastructure.repository.music;

import lombok.RequiredArgsConstructor;
import org.soin.core.domain.music.repository.IMusicLabelRepository;
import org.soin.core.infrastructure.mapper.music.MusicLabelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:06
 **/
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MusicLabelRepository implements IMusicLabelRepository {

    private final MusicLabelMapper musicLabelMapper;

}


