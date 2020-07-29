package im.turms.gateway.redis.config;

import im.turms.common.constant.statuscode.SessionCloseStatus;
import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.gateway.pojo.bo.login.LoginFailureReasonKey;
import im.turms.gateway.pojo.bo.session.SessionDisconnectionReasonKey;
import im.turms.gateway.redis.RedisSerializationContextPool;
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
        RedisProperties properties = turmsPropertiesManager.getLocalProperties().getGateway().getRedis().getSession();
        LettuceConnectionFactory connectionFactory = RedisTemplateFactory.getRedisConnectionFactory(properties);
        return new ReactiveRedisTemplate<>(connectionFactory, im.turms.server.common.redis.RedisSerializationContextPool.USER_SESSIONS_STATUS_SERIALIZATION_CONTEXT);
    }

    @Bean
    public ReactiveRedisTemplate<String, UserSessionId> locationRedisTemplate() {
        RedisProperties properties = turmsPropertiesManager.getLocalProperties().getGateway().getRedis().getLocation();
        LettuceConnectionFactory connectionFactory = RedisTemplateFactory.getRedisConnectionFactory(properties);
        return new ReactiveRedisTemplate<>(connectionFactory, im.turms.server.common.redis.RedisSerializationContextPool.GEO_USER_SESSION_ID_SERIALIZATION_CONTEXT);
    }

    @Bean
    public ReactiveRedisTemplate<LoginFailureReasonKey, TurmsStatusCode> loginFailureRedisTemplate() {
        RedisProperties properties = turmsPropertiesManager.getLocalProperties().getGateway().getRedis().getLoginFailureReason();
        LettuceConnectionFactory connectionFactory = RedisTemplateFactory.getRedisConnectionFactory(properties);
        return new ReactiveRedisTemplate<>(connectionFactory, RedisSerializationContextPool.LOGIN_FAILURE_REASON_SERIALIZATION_CONTEXT);
    }

    @Bean
    public ReactiveRedisTemplate<SessionDisconnectionReasonKey, SessionCloseStatus> sessionDisconnectionRedisTemplate() {
        RedisProperties properties = turmsPropertiesManager.getLocalProperties().getGateway().getRedis().getSessionDisconnectionReason();
        LettuceConnectionFactory connectionFactory = RedisTemplateFactory.getRedisConnectionFactory(properties);
        return new ReactiveRedisTemplate<>(connectionFactory, RedisSerializationContextPool.SESSION_DISCONNECTION_REASON_SERIALIZATION_CONTEXT);
    }

}
