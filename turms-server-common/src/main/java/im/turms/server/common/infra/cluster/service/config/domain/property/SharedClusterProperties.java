/*
 * Copyright (C) 2019 The Turms Project
 * https://github.com/turms-im/turms
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.turms.server.common.infra.cluster.service.config.domain.property;

import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.env.gateway.GatewayProperties;
import im.turms.server.common.infra.property.env.service.ServiceProperties;
import im.turms.server.common.storage.mongo.entity.annotation.Document;
import im.turms.server.common.storage.mongo.entity.annotation.Id;
import im.turms.server.common.storage.mongo.entity.annotation.PropertySetter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Transient;

import java.util.Date;

/**
 * @author James Chen
 */
@Document
@FieldNameConstants
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class SharedClusterProperties {

    @Id
    private String clusterId;

    private CommonProperties commonProperties;

    private ServiceProperties serviceProperties;

    private GatewayProperties gatewayProperties;

    private Date lastUpdatedTime;

    @Transient
    private TurmsProperties turmsProperties;

    public SharedClusterProperties(String clusterId, TurmsProperties localProperties, Date lastUpdatedTime) {
        this.clusterId = clusterId;
        this.turmsProperties = localProperties;
        commonProperties = getCommonProperties(localProperties);
        serviceProperties = localProperties.getService();
        gatewayProperties = localProperties.getGateway();
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public static CommonProperties getCommonProperties(TurmsProperties turmsProperties) {
        return new CommonProperties(
                turmsProperties.getCluster(),
                turmsProperties.getHealthCheck(),
                turmsProperties.getIp(),
                turmsProperties.getLocation(),
                turmsProperties.getLogging(),
                turmsProperties.getPlugin(),
                turmsProperties.getSecurity(),
                turmsProperties.getUserStatus());
    }

    public void tryInitTurmsProperties() {
        if (commonProperties != null) {
            turmsProperties = new TurmsProperties(
                    commonProperties.getCluster(),
                    commonProperties.getHealthCheck(),
                    commonProperties.getIp(),
                    commonProperties.getLocation(),
                    commonProperties.getLogging(),
                    commonProperties.getPlugin(),
                    commonProperties.getSecurity(),
                    commonProperties.getUserStatus(),
                    gatewayProperties,
                    serviceProperties);
        }
    }

    @PropertySetter
    public void setCommonProperties(CommonProperties commonProperties) {
        this.commonProperties = commonProperties;
        tryInitTurmsProperties();
    }

    @PropertySetter
    public void setServiceProperties(ServiceProperties serviceProperties) {
        this.serviceProperties = serviceProperties;
        tryInitTurmsProperties();
    }

    @PropertySetter
    public void setGatewayProperties(GatewayProperties gatewayProperties) {
        this.gatewayProperties = gatewayProperties;
        tryInitTurmsProperties();
    }

}