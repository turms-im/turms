package im.turms.turms.redis.config;

import im.turms.server.common.bo.session.UserSessionId;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.redis.RedisTemplateFactory;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisHash;

import javax.annotation.PreDestroy;

/**
 * @author James Chen
 * @see org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
 * @see org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration
 * @see org.springframework.boot.autoconfigure.data.redis.RedisConnectionConfiguration
 * @see org.springframework.boot.autoconfigure.data.redis.LettuceConnectionConfiguration
 */
@Configuration
@RedisHash
public class RedisConfig {

    private final TurmsPropertiesManager turmsPropertiesManager;

    protected RedisConfig(TurmsPropertiesManager turmsPropertiesManager) {
        this.turmsPropertiesManager = turmsPropertiesManager;
    }

    @PreDestroy
    public void destroy() {
        RedisTemplateFactory.destroy();
    }

    @Bean
    public ReactiveRedisTemplate<Long, String> sessionRedisTemplate() {
        RedisProperties properties = turmsPropertiesManager.getLocalProperties().getService().getRedis().getSession();
        LettuceConnectionFactory connectionFactory = RedisTemplateFactory.getRedisConnectionFactory(properties);
        return new ReactiveRedisTemplate<>(connectionFactory, im.turms.server.common.redis.RedisSerializationContextPool.USER_SESSIONS_STATUS_SERIALIZATION_CONTEXT);
    }

    @Bean
    public ReactiveRedisTemplate<String, UserSessionId> locationRedisTemplate() {
        RedisProperties properties = turmsPropertiesManager.getLocalProperties().getService().getRedis().getLocation();
        LettuceConnectionFactory connectionFactory = RedisTemplateFactory.getRedisConnectionFactory(properties);
        return new ReactiveRedisTemplate<>(connectionFactory, im.turms.server.common.redis.RedisSerializationContextPool.GEO_USER_SESSION_ID_SERIALIZATION_CONTEXT);
    }

}
