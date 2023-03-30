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

package im.turms.gateway.access.client.common.authorization.policy;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.infra.collection.CloseableCollection;
import im.turms.server.common.infra.collection.CollectionPool;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.collection.Pool;

/**
 * @author James Chen
 */
public class PolicyManager {

    private static final int MAX_ALLOWED_STATEMENTS = 100;
    private static final int REQUEST_TYPE_COUNT = PolicyStatementResource.ALL_REQUEST_TYPES.size();
    private static final int RESOURCE_COUNT = PolicyStatementResource.ALL.size();

    private final Pool<Set<TurmsRequest.KindCase>> requestTypesPool = new Pool<>(1024, 512);

    public Set<TurmsRequest.KindCase> findAllowedRequestTypes(Policy policy) {
        List<PolicyStatement> statements = policy.statements();
        if (statements.isEmpty()) {
            return Collections.emptySet();
        }
        int statementCount = statements.size();
        if (statementCount > MAX_ALLOWED_STATEMENTS) {
            throw new IllegalArgumentException(
                    "The policy must not have statements more than "
                            + MAX_ALLOWED_STATEMENTS);
        }
        if (statementCount == 1) {
            PolicyStatement statement = statements.get(0);
            if (statement.effect() != PolicyStatementEffect.ALLOW) {
                return Collections.emptySet();
            }
            Set<PolicyStatementResource> resources = statement.resources();
            if (resources.isEmpty()) {
                return Collections.emptySet();
            }
            int resourceCount = resources.size();
            Set<PolicyStatementAction> actions = statement.actions();
            if (resourceCount == 1) {
                if (actions.size() == 1) {
                    return findMatchedRequestTypes(actions.iterator()
                            .next(),
                            resources.iterator()
                                    .next());
                }
                try (CloseableCollection<Set<TurmsRequest.KindCase>> closeableCollection =
                        CollectionPool.getSet()) {
                    return pool(findMatchedRequestTypes(closeableCollection.value(),
                            actions,
                            resources.iterator()
                                    .next()));
                }
            }
            if (resourceCount == RESOURCE_COUNT) {
                for (PolicyStatementAction action : actions) {
                    if (action == PolicyStatementAction.ALL) {
                        return PolicyStatementResource.ALL_REQUEST_TYPES;
                    }
                }
            }
            try (CloseableCollection<Set<TurmsRequest.KindCase>> closeableCollection =
                    CollectionPool.getSet()) {
                Set<TurmsRequest.KindCase> matchedRequestTypesBuilder = closeableCollection.value();
                for (PolicyStatementResource resource : resources) {
                    findMatchedRequestTypes(matchedRequestTypesBuilder, actions, resource);
                }
                return pool(matchedRequestTypesBuilder);
            }
        }
        try (CloseableCollection<Set<TurmsRequest.KindCase>> closeableCollection =
                CollectionPool.getSet()) {
            Set<TurmsRequest.KindCase> allowedRequestTypesBuilder = closeableCollection.value();
            List<PolicyStatement> denyStatements = null;
            for (PolicyStatement statement : statements) {
                if (statement.effect() == PolicyStatementEffect.DENY) {
                    if (denyStatements == null) {
                        denyStatements = new LinkedList<>();
                    }
                    denyStatements.add(statement);
                    continue;
                }
                for (PolicyStatementResource resource : statement.resources()) {
                    for (PolicyStatementAction action : statement.actions()) {
                        allowedRequestTypesBuilder
                                .addAll(findMatchedRequestTypes(action, resource));
                    }
                }
            }
            if (denyStatements != null) {
                for (PolicyStatement statement : denyStatements) {
                    for (PolicyStatementResource resource : statement.resources()) {
                        for (PolicyStatementAction action : statement.actions()) {
                            allowedRequestTypesBuilder
                                    .removeAll(findMatchedRequestTypes(action, resource));
                        }
                    }
                }
            }
            if (allowedRequestTypesBuilder.isEmpty()) {
                return Collections.emptySet();
            } else if (allowedRequestTypesBuilder.size() == REQUEST_TYPE_COUNT) {
                return PolicyStatementResource.ALL_REQUEST_TYPES;
            }
            return pool(allowedRequestTypesBuilder);
        }
    }

    private Set<TurmsRequest.KindCase> pool(Set<TurmsRequest.KindCase> requestTypesBuilder) {
        return requestTypesPool.poolIfAbsent(requestTypesBuilder, CollectionUtil::toImmutableSet);
    }

    private Set<TurmsRequest.KindCase> findMatchedRequestTypes(
            Set<TurmsRequest.KindCase> matchedRequestTypesBuilder,
            Set<PolicyStatementAction> actions,
            PolicyStatementResource resource) {
        for (PolicyStatementAction action : actions) {
            matchedRequestTypesBuilder.addAll(findMatchedRequestTypes(action, resource));
        }
        return matchedRequestTypesBuilder;
    }

    private Set<TurmsRequest.KindCase> findMatchedRequestTypes(
            PolicyStatementAction action,
            PolicyStatementResource resource) {
        return switch (action) {
            case ALL -> resource.getAllRequestTypes();
            case CREATE -> resource.getRequestTypesForCreating();
            case DELETE -> resource.getRequestTypesForDeleting();
            case UPDATE -> resource.getRequestTypesForUpdating();
            case QUERY -> resource.getRequestTypesForQuerying();
        };
    }

}
