package org.soin.core.infrastructure.repository.database;

import lombok.RequiredArgsConstructor;
import org.soin.core.domain.database.repository.IAudioDataRepository;
import org.soin.core.domain.database.repository.IVideoDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-23 16:39
 **/
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VideoDataRepository implements IVideoDataRepository {

}


