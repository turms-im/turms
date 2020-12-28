package im.turms.gateway.redis.config;

import im.turms.gateway.pojo.bo.login.LoginFailureReasonKey;
import im.turms.gateway.pojo.bo.session.SessionDisconnectionReasonKey;
import im.turms.server.common.bo.session.UserSessionId;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.redis.RedisTemplateFactory;
import im.turms.server.common.redis.sharding.ShardingAlgorithm;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisHash;

import javax.annotation.PreDestroy;
import java.util.List;

import static im.turms.gateway.redis.RedisSerializationContextPool.LOGIN_FAILURE_REASON_SERIALIZATION_CONTEXT;
import static im.turms.gateway.redis.RedisSerializationContextPool.SESSION_DISCONNECTION_REASON_SERIALIZATION_CONTEXT;
import static im.turms.server.common.redis.RedisSerializationContextPool.GEO_USER_SESSION_ID_SERIALIZATION_CONTEXT;
import static im.turms.server.common.redis.RedisSerializationContextPool.USER_SESSIONS_STATUS_SERIALIZATION_CONTEXT;
import static im.turms.server.common.redis.RedisTemplateFactory.getTemplates;

/**
 * @author James Chen
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

    // Sharding Algorithm

    @Bean
    public ShardingAlgorithm shardingAlgorithmForSession() {
        return turmsPropertiesManager.getLocalProperties().getGateway().getRedis().getShardingProperties().getAlgorithmForSession();
    }

    @Bean
    public ShardingAlgorithm shardingAlgorithmForLocation() {
        return turmsPropertiesManager.getLocalProperties().getGateway().getRedis().getShardingProperties().getAlgorithmForLocation();
    }

    @Bean
    public ShardingAlgorithm shardingAlgorithmForLoginFailure() {
        return turmsPropertiesManager.getLocalProperties().getGateway().getRedis().getShardingProperties().getAlgorithmForLoginFailureReason();
    }

    @Bean
    public ShardingAlgorithm shardingAlgorithmForSessionDisconnection() {
        return turmsPropertiesManager.getLocalProperties().getGateway().getRedis().getShardingProperties().getAlgorithmForSessionDisconnectionReason();
    }

    // Template

    @Bean
    public List<ReactiveRedisTemplate<Long, String>> sessionRedisTemplates() {
        List<RedisProperties> propertiesList = turmsPropertiesManager.getLocalProperties().getGateway().getRedis().getSession();
        return getTemplates(propertiesList, USER_SESSIONS_STATUS_SERIALIZATION_CONTEXT);
    }

    @Bean
    public List<ReactiveRedisTemplate<String, UserSessionId>> locationRedisTemplates() {
        List<RedisProperties> propertiesList = turmsPropertiesManager.getLocalProperties().getGateway().getRedis().getLocation();
        return getTemplates(propertiesList, GEO_USER_SESSION_ID_SERIALIZATION_CONTEXT);
    }

    @Bean
    public List<ReactiveRedisTemplate<LoginFailureReasonKey, TurmsStatusCode>> loginFailureRedisTemplates() {
        List<RedisProperties> propertiesList = turmsPropertiesManager.getLocalProperties().getGateway().getRedis().getLoginFailureReason();
        return getTemplates(propertiesList, LOGIN_FAILURE_REASON_SERIALIZATION_CONTEXT);
    }

    @Bean
    public List<ReactiveRedisTemplate<SessionDisconnectionReasonKey, Integer>> sessionDisconnectionRedisTemplates() {
        List<RedisProperties> propertiesList = turmsPropertiesManager.getLocalProperties().getGateway().getRedis().getSessionDisconnectionReason();
        return getTemplates(propertiesList, SESSION_DISCONNECTION_REASON_SERIALIZATION_CONTEXT);
    }

}