package im.turms.server.common.redis;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.TimeoutOptions;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import io.lettuce.core.resource.ClientResources;
import io.lettuce.core.resource.DefaultClientResources;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * By the way, we don't need pool because we don't have any transactions and blocking commands currently
 *
 * @author James Chen
 * @see org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
 * @see org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration
 * @see org.springframework.boot.autoconfigure.data.redis.RedisConnectionConfiguration
 * @see org.springframework.boot.autoconfigure.data.redis.LettuceConnectionConfiguration
 */
public class RedisTemplateFactory {

    private static final List<ClientResources> POOL = new LinkedList<>();

    private RedisTemplateFactory() {
    }

    public static <K, V> List<ReactiveRedisTemplate<K, V>> getTemplates(List<RedisProperties> propertiesList, RedisSerializationContext<K, V> serializationContext) {
        List<ReactiveRedisTemplate<K, V>> templates = new ArrayList<>(propertiesList.size());
        for (RedisProperties properties : propertiesList) {
            LettuceConnectionFactory connectionFactory = RedisTemplateFactory.getRedisConnectionFactory(properties);
            templates.add(new ReactiveRedisTemplate<>(connectionFactory, serializationContext));
        }
        return templates;
    }

    private static LettuceConnectionFactory getRedisConnectionFactory(RedisProperties redisProperties) {
        ClientResources clientResources = DefaultClientResources.create();
        POOL.add(clientResources);
        LettuceClientConfiguration clientConfig = getLettuceClientConfiguration(clientResources, redisProperties);
        RedisSentinelConfiguration sentinelConfig = getSentinelConfig(redisProperties);
        RedisClusterConfiguration clusterConfiguration = getClusterConfiguration(redisProperties);
        LettuceConnectionFactory connectionFactory;
        if (sentinelConfig != null) {
            connectionFactory = new LettuceConnectionFactory(sentinelConfig, clientConfig);
        } else if (clusterConfiguration != null) {
            connectionFactory = new LettuceConnectionFactory(clusterConfiguration, clientConfig);
        } else {
            connectionFactory = new LettuceConnectionFactory(getStandaloneConfig(redisProperties), clientConfig);
        }
        connectionFactory.afterPropertiesSet();
        return connectionFactory;
    }

    public static void destroy() {
        for (ClientResources clientResources : POOL) {
            clientResources.shutdown();
        }
    }

    private static LettuceClientConfiguration getLettuceClientConfiguration(
            ClientResources clientResources, RedisProperties redisProperties) {
        LettuceClientConfiguration.LettuceClientConfigurationBuilder builder = LettuceClientConfiguration.builder();
        if (redisProperties.isSsl()) {
            builder.useSsl();
        }
        if (redisProperties.getTimeout() != null) {
            builder.commandTimeout(redisProperties.getTimeout());
        }
        if (redisProperties.getLettuce() != null) {
            RedisProperties.Lettuce lettuce = redisProperties.getLettuce();
            if (lettuce.getShutdownTimeout() != null && !lettuce.getShutdownTimeout().isZero()) {
                builder.shutdownTimeout(redisProperties.getLettuce().getShutdownTimeout());
            }
        }
        if (StringUtils.hasText(redisProperties.getClientName())) {
            builder.clientName(redisProperties.getClientName());
        }
        if (StringUtils.hasText(redisProperties.getUrl())) {
            ConnectionInfo connectionInfo = parseUrl(redisProperties.getUrl());
            if (connectionInfo.isUseSsl()) {
                builder.useSsl();
            }
        }

        // initializeClientOptionsBuilder
        ClientOptions.Builder clientOptionsBuilder;
        if (redisProperties.getCluster() != null) {
            ClusterClientOptions.Builder clusterClientOptionsBuilder = ClusterClientOptions.builder();
            RedisProperties.Lettuce.Cluster.Refresh refreshProperties = redisProperties.getLettuce().getCluster().getRefresh();
            ClusterTopologyRefreshOptions.Builder refreshBuilder = ClusterTopologyRefreshOptions.builder();
            if (refreshProperties.getPeriod() != null) {
                refreshBuilder.enablePeriodicRefresh(refreshProperties.getPeriod());
            }
            if (refreshProperties.isAdaptive()) {
                refreshBuilder.enableAllAdaptiveRefreshTriggers();
            }
            clientOptionsBuilder = clusterClientOptionsBuilder.topologyRefreshOptions(refreshBuilder.build());
        } else {
            clientOptionsBuilder = ClientOptions.builder();
        }

        // Build

        builder.clientOptions(clientOptionsBuilder.timeoutOptions(TimeoutOptions.enabled()).build());
        builder.clientResources(clientResources);
        return builder.build();
    }

    private static RedisStandaloneConfiguration getStandaloneConfig(RedisProperties properties) {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        if (StringUtils.hasText(properties.getUrl())) {
            ConnectionInfo connectionInfo = parseUrl(properties.getUrl());
            config.setHostName(connectionInfo.getHostName());
            config.setPort(connectionInfo.getPort());
            config.setPassword(RedisPassword.of(connectionInfo.getPassword()));
        } else {
            config.setHostName(properties.getHost());
            config.setPort(properties.getPort());
            config.setPassword(RedisPassword.of(properties.getPassword()));
        }
        config.setDatabase(properties.getDatabase());
        return config;
    }

    private static RedisSentinelConfiguration getSentinelConfig(RedisProperties properties) {
        RedisProperties.Sentinel sentinelProperties = properties.getSentinel();
        if (sentinelProperties != null) {
            RedisSentinelConfiguration config = new RedisSentinelConfiguration();
            config.master(sentinelProperties.getMaster());
            config.setSentinels(createSentinels(sentinelProperties));
            if (properties.getPassword() != null) {
                config.setPassword(RedisPassword.of(properties.getPassword()));
            }
            if (sentinelProperties.getPassword() != null) {
                config.setSentinelPassword(RedisPassword.of(sentinelProperties.getPassword()));
            }
            config.setDatabase(properties.getDatabase());
            return config;
        }
        return null;
    }

    private static RedisClusterConfiguration getClusterConfiguration(RedisProperties properties) {
        if (properties.getCluster() == null) {
            return null;
        }
        RedisProperties.Cluster clusterProperties = properties.getCluster();
        RedisClusterConfiguration config = new RedisClusterConfiguration(clusterProperties.getNodes());
        if (clusterProperties.getMaxRedirects() != null) {
            config.setMaxRedirects(clusterProperties.getMaxRedirects());
        }
        if (properties.getPassword() != null) {
            config.setPassword(RedisPassword.of(properties.getPassword()));
        }
        return config;
    }

    private static List<RedisNode> createSentinels(RedisProperties.Sentinel sentinel) {
        List<RedisNode> nodes = new ArrayList<>(sentinel.getNodes().size());
        for (String node : sentinel.getNodes()) {
            try {
                String[] parts = StringUtils.split(node, ":");
                Assert.state(parts.length == 2, "Must be defined as 'host:port'");
                nodes.add(new RedisNode(parts[0], Integer.parseInt(parts[1])));
            } catch (RuntimeException ex) {
                throw new IllegalStateException("Invalid redis sentinel property '" + node + "'", ex);
            }
        }
        return nodes;
    }

    private static ConnectionInfo parseUrl(String url) {
        try {
            URI uri = new URI(url);
            boolean useSsl = (url.startsWith("rediss://"));
            String password = null;
            if (uri.getUserInfo() != null) {
                password = uri.getUserInfo();
                int index = password.indexOf(':');
                if (index >= 0) {
                    password = password.substring(index + 1);
                }
            }
            return new ConnectionInfo(uri, useSsl, password);
        } catch (URISyntaxException ex) {
            throw new IllegalArgumentException("Malformed url '" + url + "'", ex);
        }
    }

    private static class ConnectionInfo {

        private final URI uri;

        private final boolean useSsl;

        private final String password;

        ConnectionInfo(URI uri, boolean useSsl, String password) {
            this.uri = uri;
            this.useSsl = useSsl;
            this.password = password;
        }

        boolean isUseSsl() {
            return this.useSsl;
        }

        String getHostName() {
            return this.uri.getHost();
        }

        int getPort() {
            return this.uri.getPort();
        }

        String getPassword() {
            return this.password;
        }

    }

}
