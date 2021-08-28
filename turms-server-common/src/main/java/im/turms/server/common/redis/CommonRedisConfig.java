package im.turms.server.common.redis;

import im.turms.server.common.property.env.common.CommonRedisProperties;
import im.turms.server.common.redis.codec.context.RedisCodecContext;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;

import javax.annotation.PreDestroy;
import java.util.List;

import static im.turms.server.common.redis.codec.context.RedisCodecContextPool.GEO_USER_ID_CODEC_CONTEXT;
import static im.turms.server.common.redis.codec.context.RedisCodecContextPool.GEO_USER_SESSION_ID_CODEC_CONTEXT;
import static im.turms.server.common.redis.codec.context.RedisCodecContextPool.USER_SESSIONS_STATUS_CODEC_CONTEXT;

/**
 * @author James Chen
 * @see org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
 * @see org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration
 * @see org.springframework.boot.autoconfigure.data.redis.RedisConnectionConfiguration
 * @see org.springframework.boot.autoconfigure.data.redis.LettuceConnectionConfiguration
 */
@Log4j2
public abstract class CommonRedisConfig {

    private final TurmsRedisClientManager sessionRedisClientManager;
    private final TurmsRedisClientManager locationRedisClientManager;

    private final TurmsRedisClient ipBlocklistRedisClient;
    private final TurmsRedisClient userIdBlocklistRedisClient;

    protected CommonRedisConfig(CommonRedisProperties redisProperties, boolean treatUserIdAndDeviceTypeAsUniqueUser) {
        sessionRedisClientManager = newSessionRedisClientManager(redisProperties.getSession());
        locationRedisClientManager = newLocationRedisClientManager(redisProperties.getLocation(), treatUserIdAndDeviceTypeAsUniqueUser);
        ipBlocklistRedisClient = newIpBlocklistRedisClient(redisProperties.getIpBlocklist().getUri());
        userIdBlocklistRedisClient = newUserIdBlocklistRedisClient(redisProperties.getUserIdBlocklist().getUri());
    }

    public static TurmsRedisClientManager newSessionRedisClientManager(RedisProperties properties) {
        return new TurmsRedisClientManager(properties, USER_SESSIONS_STATUS_CODEC_CONTEXT);
    }

    public static TurmsRedisClientManager newLocationRedisClientManager(RedisProperties properties,
                                                                        boolean treatUserIdAndDeviceTypeAsUniqueUser) {
        RedisCodecContext codecContext = treatUserIdAndDeviceTypeAsUniqueUser
                ? GEO_USER_SESSION_ID_CODEC_CONTEXT
                : GEO_USER_ID_CODEC_CONTEXT;
        return new TurmsRedisClientManager(properties, codecContext);
    }

    public static TurmsRedisClient newIpBlocklistRedisClient(String uri) {
        return new TurmsRedisClient(uri, RedisCodecContext.builder().build());
    }

    public static TurmsRedisClient newUserIdBlocklistRedisClient(String uri) {
        return new TurmsRedisClient(uri, RedisCodecContext.builder().build());
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
        for (TurmsRedisClient client : List.of(ipBlocklistRedisClient, userIdBlocklistRedisClient)) {
            try {
                client.destroy();
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

    @Bean
    public TurmsRedisClient ipBlocklistRedisClient() {
        return ipBlocklistRedisClient;
    }

    @Bean
    public TurmsRedisClient userIdBlocklistRedisClient() {
        return userIdBlocklistRedisClient;
    }

}