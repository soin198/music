package org.soin.core.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis缓存配置
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2023-06-26 10:49
 **/
@Slf4j
@Configuration
public class RedisConfig {

    /**
     * 采用lettuce连接工厂
     *
     * @return factory
     */
    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory(RedisProperties redisProperties) {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(redisProperties.getHost());
        config.setPort(redisProperties.getPort());
        config.setUsername(redisProperties.getUsername());
        config.setPassword(redisProperties.getPassword());
        config.setDatabase(redisProperties.getDatabase());
        return new LettuceConnectionFactory(config);
    }

    /**
     * 配置自定义redisTemplate
     *
     * @return 序列化完成的redis
     * @apiNote <p>使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值</p>
     * <p>使用StringRedisSerializer来序列化和反序列化redis的key值</p>
     */
    @Bean
    RedisTemplate<String, String> redisTemplate(LettuceConnectionFactory lettuce) {
        RedisTemplate<String, String> redisTemp = new RedisTemplate<>();
        redisTemp.setConnectionFactory(lettuce);
        Jackson2JsonRedisSerializer<Object> serializer =
                new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        PolymorphicTypeValidator ptv =
                BasicPolymorphicTypeValidator
                        .builder()
                        .allowIfBaseType(Object.class)
                        .build();
        mapper.activateDefaultTyping(ptv);
        //序列化类型
        serializer.setObjectMapper(mapper);
        //value序列化
        redisTemp.setValueSerializer(serializer);
        //key序列化
        redisTemp.setKeySerializer(new StringRedisSerializer());
        //初始化
        redisTemp.afterPropertiesSet();
        return redisTemp;
    }

}
