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

package im.turms.service.infra.address;

import org.springframework.stereotype.Component;

import im.turms.server.common.infra.address.BaseServiceAddressManager;
import im.turms.server.common.infra.address.IpDetector;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.common.AddressProperties;

/**
 * @author James Chen
 */
@Component
public class ServiceAddressManager extends BaseServiceAddressManager {

    public ServiceAddressManager(IpDetector ipDetector, TurmsPropertiesManager propertiesManager) {
        super(propertiesManager.getLocalProperties()
                .getService()
                .getAdminApi()
                .getHttp(), ipDetector, propertiesManager);
    }

    @Override
    protected AddressProperties getAdminAddressProperties(TurmsProperties properties) {
        return properties.getService()
                .getAdminApi()
                .getAddress();
    }

}