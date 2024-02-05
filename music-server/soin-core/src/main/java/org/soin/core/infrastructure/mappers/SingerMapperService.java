package org.soin.core.infrastructure.mappers;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.soin.core.domain.singer.entity.Singer;
import org.soin.core.infrastructure.mappers.mapper.SingerMapper;
import org.springframework.stereotype.Service;


/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:57
 **/
@Service(value = "SingerMapperService")
@SuppressWarnings("all")
public class SingerMapperService extends ServiceImpl<SingerMapper, Singer> implements IService<Singer> {

}
