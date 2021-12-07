package im.turms.service.redis.config;

import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.service.ServiceProperties;
import im.turms.server.common.property.env.service.business.message.SequenceIdProperties;
import im.turms.server.common.redis.CommonRedisConfig;
import im.turms.server.common.redis.RedisProperties;
import im.turms.server.common.redis.TurmsRedisClientManager;
import im.turms.server.common.redis.codec.context.RedisCodecContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author James Chen
 */
@Configuration
public class RedisConfig extends CommonRedisConfig {

    private final TurmsRedisClientManager sequenceIdRedisClientManager;

    protected RedisConfig(TurmsPropertiesManager turmsPropertiesManager) {
        super(turmsPropertiesManager.getLocalProperties().getService().getRedis(),
                turmsPropertiesManager.getLocalProperties().getLocation().isTreatUserIdAndDeviceTypeAsUniqueUser());
        ServiceProperties serviceProperties = turmsPropertiesManager.getLocalProperties().getService();
        SequenceIdProperties sequenceIdProperties = serviceProperties.getMessage().getSequenceId();
        sequenceIdRedisClientManager = sequenceIdProperties.isUseSequenceIdForGroupConversation()
                || sequenceIdProperties.isUseSequenceIdForPrivateConversation()
                ? newSequenceIdRedisClientManager(serviceProperties.getRedis().getSequenceId())
                : null;
        if (sequenceIdRedisClientManager != null) {
            registerClientManagers(List.of(sequenceIdRedisClientManager));
        }
    }

    public static TurmsRedisClientManager newSequenceIdRedisClientManager(RedisProperties properties) {
        return new TurmsRedisClientManager(properties, RedisCodecContext.builder().build());
    }

    @Bean
    public TurmsRedisClientManager sequenceIdRedisClientManager() {
        return sequenceIdRedisClientManager;
    }

}