package im.turms.turms.redis.config;

import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.service.env.redis.TurmsRedisProperties;
import im.turms.server.common.redis.TurmsRedisClientManager;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.util.List;

import static im.turms.server.common.redis.codec.context.RedisCodecContextPool.GEO_USER_SESSION_ID_CODEC_CONTEXT;
import static im.turms.server.common.redis.codec.context.RedisCodecContextPool.USER_SESSIONS_STATUS_CODEC_CONTEXT;

/**
 * @author James Chen
 * @see org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
 * @see org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration
 * @see org.springframework.boot.autoconfigure.data.redis.RedisConnectionConfiguration
 * @see org.springframework.boot.autoconfigure.data.redis.LettuceConnectionConfiguration
 */
@Configuration
@Log4j2
public class RedisConfig {

    private final TurmsRedisClientManager sessionRedisClientManager;
    private final TurmsRedisClientManager locationRedisClientManager;

    protected RedisConfig(TurmsPropertiesManager turmsPropertiesManager) {
        TurmsRedisProperties redisProperties = turmsPropertiesManager.getLocalProperties().getService().getRedis();
        sessionRedisClientManager = new TurmsRedisClientManager(redisProperties.getSession(),
                USER_SESSIONS_STATUS_CODEC_CONTEXT);
        locationRedisClientManager = new TurmsRedisClientManager(redisProperties.getLocation(),
                GEO_USER_SESSION_ID_CODEC_CONTEXT);
    }

    @PreDestroy
    public void destroy() {
        for (TurmsRedisClientManager manager : List.of(sessionRedisClientManager, locationRedisClientManager)) {
            try {
                manager.destroy();
            } catch (Exception e) {
                log.error("Failed to destroy a redis client", e);
            }
        }
    }

    @Bean
    public TurmsRedisClientManager sessionRedisClientManager() {
        return sessionRedisClientManager;
    }

    @Bean
    public TurmsRedisClientManager locationRedisClientManager() {
        return locationRedisClientManager;
    }

}