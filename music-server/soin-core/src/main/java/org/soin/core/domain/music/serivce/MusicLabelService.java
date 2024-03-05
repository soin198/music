package org.soin.core.domain.music.serivce;

import lombok.RequiredArgsConstructor;
import org.soin.core.domain.music.repository.IMusicLabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-22 15:20
 **/
@Service(value = "MusicLabelService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MusicLabelService {

    private final IMusicLabelRepository iMusicLabelRepository;

}
