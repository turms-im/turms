package im.turms.gateway.redis.config;

import im.turms.server.common.property.TurmsPropertiesManager;
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
 */
@Configuration
@Log4j2
public class RedisConfig {

    private final TurmsRedisClientManager sessionRedisClientManager;
    private final TurmsRedisClientManager locationRedisClientManager;

    protected RedisConfig(TurmsPropertiesManager turmsPropertiesManager) {
        sessionRedisClientManager =
                new TurmsRedisClientManager(turmsPropertiesManager.getLocalProperties().getGateway().getRedis().getSession(),
                        USER_SESSIONS_STATUS_CODEC_CONTEXT);
        locationRedisClientManager =
                new TurmsRedisClientManager(turmsPropertiesManager.getLocalProperties().getGateway().getRedis().getLocation(),
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