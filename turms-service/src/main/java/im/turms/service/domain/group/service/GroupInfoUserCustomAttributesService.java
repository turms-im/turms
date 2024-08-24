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

package im.turms.service.domain.group.service;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.service.domain.common.service.UserDefinedAttributesService;
import im.turms.service.domain.group.repository.GroupRepository;

/**
 * @author James Chen
 */
@Service
public class GroupInfoUserCustomAttributesService extends UserDefinedAttributesService {

    private final GroupRepository groupRepository;

    public GroupInfoUserCustomAttributesService(
            TurmsPropertiesManager propertiesManager,
            GroupRepository groupRepository) {
        this.groupRepository = groupRepository;

        propertiesManager.notifyAndAddGlobalPropertiesChangeListener(this::updateGlobalProperties);
    }

    private void updateGlobalProperties(TurmsProperties properties) {
        super.updateGlobalProperties(properties.getService()
                .getGroup()
                .getInfo()
                .getUserDefinedAttributes());
    }

    @Override
    protected Mono<List<String>> findUserDefinedAttributes(
            Collection<String> immutableAttributesForUpsert) {
        return groupRepository.findUserDefinedAttributes(immutableAttributesForUpsert);
    }
}