package com.vng.ewallet.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

import java.time.Duration;


@Configuration
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String redisHostName;

    @Value("${spring.redis.port}")
    private Integer redisPort;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisClientConfiguration jedisConfig = JedisClientConfiguration.builder().usePooling().build();
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration(redisHostName, redisPort);
        return new JedisConnectionFactory(redisConfig, jedisConfig);
    }
    @Bean
    public RedisTemplate<Object, Object> redisTemplate() {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());

//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashKeySerializer(new JdkSerializationRedisSerializer());
//        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());

        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


//    @Bean
//    public CacheManager cacheManager() {
//        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration
//                .defaultCacheConfig()
//                .disableCachingNullValues()
//                .entryTtl(Duration.ofHours(1))
//                .serializeValuesWith(RedisSerializationContext
//                        .SerializationPair
//                        .fromSerializer(new JdkSerializationRedisSerializer()));
//        redisCacheConfiguration.usePrefix();
//
//        return RedisCacheManager.RedisCacheManagerBuilder
//                .fromConnectionFactory(jedisConnectionFactory())
//                .cacheDefaults(redisCacheConfiguration).build();
//    }
}
