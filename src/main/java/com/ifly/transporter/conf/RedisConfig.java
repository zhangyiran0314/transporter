package com.ifly.transporter.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * //redis key和value序列化配�?
 * 1.redis默认序列化为private RedisSerializer<?> defaultSerializer = new JdkSerializationRedisSerializer();
 * 此序列化方式会导致redis key value存储会存在\xac\xed\x00\x05t\x0 类似�?不方便redis客户端调�?
 * 
 * 2.修改默认key value序列化方式采用StringRedisSerializer stringSerializer = new StringRedisSerializer();
 * 3.后期扩展可定义json序列化方�?
 * @author zhangguan
 */
@Configuration
public class RedisConfig {
	@Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<Object,Object> redisTemplate = new RedisTemplate<Object, Object>();
        redisTemplate.setConnectionFactory(factory);
        
        // 使用Jackson2JsonRedisSerialize 替换默认序列�?
        /* Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);*/
        
        //用stringSerializer定义key序列化
        StringRedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
