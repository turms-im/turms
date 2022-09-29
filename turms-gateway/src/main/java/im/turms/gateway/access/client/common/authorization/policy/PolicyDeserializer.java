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

import im.turms.server.common.infra.collection.CloseableCollection;
import im.turms.server.common.infra.collection.CollectionPool;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.collection.Pool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author James Chen
 */
public class PolicyDeserializer {
    private static final Pool<Set<PolicyStatementAction>> ACTIONS_POOL = new Pool<>(Integer.MAX_VALUE, 16);
    private static final Pool<Set<PolicyStatementResource>> RESOURCES_POOL = new Pool<>(Integer.MAX_VALUE, 128);

    private static final String ILLEGAL_STATEMENT;
    private static final String ILLEGAL_RESOURCES;
    private static final String ILLEGAL_EFFECT = "Illegal policy format: " +
            PolicyStatement.Fields.effect +
            " must be one string of [\"ALLOW\", \"DENY\"]";
    private static final String ILLEGAL_ACTIONS = "Illegal policy format: " +
            PolicyStatement.Fields.actions +
            " must be one string of " +
            "[\"*\", \"CREATE\", \"DELETE\", \"UPDATE\", \"QUERY\"], or an array that contains these strings";

    static {
        ILLEGAL_STATEMENT = "Illegal policy format: statement must be an object consisting of the fields: "
                + List.of(PolicyStatement.Fields.effect, PolicyStatement.Fields.actions, PolicyStatement.Fields.resources);
        Set<PolicyStatementResource> all = PolicyStatementResource.ALL;
        int i = 0;
        int count = all.size();
        StringBuilder builder = new StringBuilder(count * 20);
        for (PolicyStatementResource resource : all) {
            builder.append('"')
                    .append(resource.name())
                    .append('"');
            if (i++ != count - 1) {
                builder.append(", ");
            }
        }
        ILLEGAL_RESOURCES = "Illegal policy format: " +
                PolicyStatement.Fields.resources +
                " must be one string of " +
                "[\"*\", " +
                builder +
                "], or an array that contains these strings";
    }

    private PolicyDeserializer() {
    }

    public static Policy parse(Map<String, Object> map) {
        Object statementsObj = map.get(Policy.Fields.statements);
        if (!(statementsObj instanceof List<?> statementObjs)) {
            throw new IllegalPolicyException("Illegal policy format: " + Policy.Fields.statements + " must be an array");
        }
        List<PolicyStatement> statements = null;
        for (Object statementObj : statementObjs) {
            if (!(statementObj instanceof Map<?, ?> statementMap)) {
                throw new IllegalPolicyException(ILLEGAL_STATEMENT);
            }
            PolicyStatementEffect effect = parseEffect(statementMap.get(PolicyStatement.Fields.effect));
            Set<PolicyStatementAction> actions = parseActions(statementMap.get(PolicyStatement.Fields.actions));
            Set<PolicyStatementResource> resources = parseResources(statementMap.get(PolicyStatement.Fields.resources));
            if (statements == null) {
                statements = new ArrayList<>(statementObjs.size());
            }
            statements.add(new PolicyStatement(effect, actions, resources));
        }
        return new Policy(statements);
    }

    private static PolicyStatementEffect parseEffect(Object effectObj) {
        if (!(effectObj instanceof String effectStr)) {
            throw new IllegalPolicyException(ILLEGAL_EFFECT);
        }
        try {
            return PolicyStatementEffect.valueOf(effectStr);
        } catch (IllegalArgumentException e) {
            throw new IllegalPolicyException(ILLEGAL_EFFECT);
        }
    }

    private static Set<PolicyStatementAction> parseActions(Object actionsObj) {
        if (actionsObj instanceof String actionStr) {
            PolicyStatementAction action = parseAction(actionStr);
            return ACTIONS_POOL.poolIfAbsent(1L << action.ordinal(), Set.of(action));
        }
        if (!(actionsObj instanceof List<?> actionObjs)) {
            throw new IllegalPolicyException(ILLEGAL_ACTIONS);
        }
        try (CloseableCollection<Set<PolicyStatementAction>> closeableCollection = CollectionPool.getSet()) {
            Set<PolicyStatementAction> actions = closeableCollection.value();
            long key = 0;
            for (Object actionObj : actionObjs) {
                if (!(actionObj instanceof String actionStr)) {
                    throw new IllegalPolicyException(ILLEGAL_ACTIONS);
                }
                PolicyStatementAction action = parseAction(actionStr);
                key |= 1L << action.ordinal();
                actions.add(action);
            }
            return ACTIONS_POOL.poolIfAbsent(key, actions, CollectionUtil::copyAsSet);
        }
    }

    private static PolicyStatementAction parseAction(String action) {
        return switch (action) {
            case "*" -> PolicyStatementAction.ALL;
            case "CREATE" -> PolicyStatementAction.CREATE;
            case "DELETE" -> PolicyStatementAction.DELETE;
            case "UPDATE" -> PolicyStatementAction.UPDATE;
            case "QUERY" -> PolicyStatementAction.QUERY;
            default -> throw new IllegalPolicyException(ILLEGAL_ACTIONS);
        };
    }

    private static Set<PolicyStatementResource> parseResources(Object resourcesObj) {
        if (resourcesObj instanceof String resourcesStr) {
            if (resourcesStr.equals("*")) {
                return PolicyStatementResource.ALL;
            }
            PolicyStatementResource resource;
            try {
                resource = PolicyStatementResource.valueOf(resourcesStr);
            } catch (Exception e) {
                throw new IllegalPolicyException(ILLEGAL_RESOURCES);
            }
            return RESOURCES_POOL.poolIfAbsent(1L << resource.ordinal(), Set.of(resource));
        }
        if (!(resourcesObj instanceof List<?> resourceObjs)) {
            throw new IllegalPolicyException(ILLEGAL_RESOURCES);
        }
        try (CloseableCollection<Set<PolicyStatementResource>> closeableCollection = CollectionPool.getSet()) {
            Set<PolicyStatementResource> resources = closeableCollection.value();
            long key = 0;
            for (Object resourceObj : resourceObjs) {
                if (!(resourceObj instanceof String resourcesStr)) {
                    throw new IllegalPolicyException(ILLEGAL_RESOURCES);
                }
                if (resourcesStr.equals("*")) {
                    return PolicyStatementResource.ALL;
                }
                try {
                    PolicyStatementResource resource = PolicyStatementResource.valueOf(resourcesStr);
                    key |= 1L << resource.ordinal();
                    resources.add(resource);
                } catch (Exception e) {
                    throw new IllegalPolicyException(ILLEGAL_RESOURCES);
                }
            }
            return RESOURCES_POOL.poolIfAbsent(key, resources, CollectionUtil::copyAsSet);
        }
    }

}
