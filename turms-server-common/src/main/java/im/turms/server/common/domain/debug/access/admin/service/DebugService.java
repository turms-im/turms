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

package im.turms.server.common.domain.debug.access.admin.service;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jakarta.annotation.Nullable;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.common.service.BaseService;
import im.turms.server.common.domain.debug.access.admin.dto.request.CreateMethodCallDTO;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.cluster.node.NodeType;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.json.JsonCodecPool;
import im.turms.server.common.infra.lang.ClassUtil;
import im.turms.server.common.infra.lang.LongUtil;
import im.turms.server.common.infra.lang.MathUtil;
import im.turms.server.common.infra.lang.Pair;
import im.turms.server.common.infra.lang.PrimitiveUtil;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.common.adminapi.BaseAdminApiProperties;
import im.turms.server.common.infra.reflect.ReflectionUtil;
import im.turms.server.common.infra.validation.Validator;

/**
 * @author James Chen
 */
@Service
public class DebugService extends BaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DebugService.class);

    private static final Mono<Object> EXCEPTION_DEBUG_IS_DISABLED =
            Mono.error(ResponseException.get(ResponseStatusCode.DEBUG_IS_DISABLED));

    private final ApplicationContext applicationContext;

    private boolean enabled;

    protected DebugService(
            Node node,
            ApplicationContext applicationContext,
            TurmsPropertiesManager propertiesManager) {
        this.applicationContext = applicationContext;

        propertiesManager.notifyAndAddGlobalPropertiesChangeListener(
                properties -> updateGlobalProperties(properties, node.getNodeType()));
    }

    protected void updateGlobalProperties(TurmsProperties properties, NodeType nodeType) {
        BaseAdminApiProperties adminApiProperties = switch (nodeType) {
            case AI_SERVING -> properties.getAiServing()
                    .getAdminApi();
            case GATEWAY -> properties.getGateway()
                    .getAdminApi();
            case SERVICE -> properties.getService()
                    .getAdminApi();
            case MOCK -> properties.getMockNode()
                    .getAdminApi();
        };

        boolean localEnabled = enabled;
        enabled = adminApiProperties.getDebug()
                .isEnabled();
        if (enabled && !localEnabled) {
            LOGGER.warn("API debug is enabled");
        }
    }

    public Mono<Object> callMethod(
            @Nullable String beanName,
            @Nullable String className,
            String methodName,
            @Nullable List<CreateMethodCallDTO.ParamDTO> params) {
        // 1. Validate inputs
        try {
            Validator.notNull(methodName, "methodName");
        } catch (Exception e) {
            return Mono.error(e);
        }
        // 2. Check if debug is enabled
        if (!enabled) {
            return EXCEPTION_DEBUG_IS_DISABLED;
        }
        // 3. Find receiver
        Object receiver;
        Class<?> receiverClass;
        if (beanName != null) {
            if (className != null) {
                return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "\"beanName\" and \"className\" must not be specified"));
            }
            try {
                receiver = applicationContext.getBean(beanName);
            } catch (NoSuchBeanDefinitionException e) {
                return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "The specified bean does not exist: "
                                + beanName));
            }
            receiverClass = receiver.getClass();
        } else if (className != null) {
            try {
                receiverClass = Class.forName(className);
            } catch (ClassNotFoundException e) {
                return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "The specified class does not exist: "
                                + className));
            }
            ObjectProvider<?> beanProvider = applicationContext.getBeanProvider(receiverClass);
            // We allow the receiver to be null because the method may be static.
            receiver = beanProvider.getIfAvailable();
        } else {
            return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "Either \"beanName\" or \"className\" must be specified"));
        }
        // 4. Find method
        Method methodToCall;
        try {
            methodToCall = findTargetMethod(receiverClass, methodName);
        } catch (Exception e) {
            return Mono.error(e);
        }
        ReflectionUtil.setAccessible(methodToCall);
        // 5. Prepare arguments
        Object[] arguments;
        try {
            arguments = prepareArguments(params, methodToCall);
        } catch (Exception e) {
            return Mono.error(e);
        }
        // 6. Invoke method
        Object returnValue;
        try {
            returnValue = methodToCall.invoke(receiver, arguments);
        } catch (Exception e) {
            return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "Failed to invoke the method: "
                            + ClassUtil.getMethodSignature(methodToCall),
                    e));
        }
        // 7. Parse and return value
        return switch (returnValue) {
            case Mono mono -> mono;
            case Flux flux -> flux.collectList();
            case null -> Mono.empty();
            default -> Mono.just(returnValue);
        };
    }

    private Method findTargetMethod(Class<?> specifiedClass, String methodName) {
        int indexOfBrace = methodName.indexOf('(');
        boolean useMethodSignature = indexOfBrace >= 0;
        Class<?> currentClass = specifiedClass;
        if (useMethodSignature) {
            do {
                Method[] declaredMethods = currentClass.getDeclaredMethods();
                for (Method method : declaredMethods) {
                    if (ClassUtil.getMethodSignature(method)
                            .equals(methodName)) {
                        return method;
                    }
                }
                currentClass = currentClass.getSuperclass();
            } while (currentClass != Object.class && currentClass != null);
            List<String> similarMethods = new ArrayList<>(8);
            currentClass = specifiedClass;
            methodName = methodName.substring(0, indexOfBrace);
            do {
                Method[] declaredMethods = currentClass.getDeclaredMethods();
                for (Method method : declaredMethods) {
                    String methodSignature = ClassUtil.getMethodSignature(method);
                    if (StringUtil.findSimilarity(methodSignature, methodName) > 0.5) {
                        similarMethods.add(methodSignature);
                    }
                }
                currentClass = currentClass.getSuperclass();
            } while (currentClass != Object.class && currentClass != null);
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "The specified method does not exist. Similar methods: "
                            + similarMethods);
        } else {
            List<Method> candidateMethods = new ArrayList<>(4);
            do {
                Method[] declaredMethods = currentClass.getDeclaredMethods();
                for (Method method : declaredMethods) {
                    if (method.getName()
                            .equals(methodName)) {
                        candidateMethods.add(method);
                    }
                }
                currentClass = currentClass.getSuperclass();
            } while (currentClass != Object.class && currentClass != null);
            if (candidateMethods.isEmpty()) {
                List<String> similarMethods = new ArrayList<>(8);
                currentClass = specifiedClass;
                do {
                    Method[] declaredMethods = currentClass.getDeclaredMethods();
                    for (Method method : declaredMethods) {
                        String methodSignature = ClassUtil.getMethodSignature(method);
                        if (StringUtil.findSimilarity(methodSignature, methodName) > 0.5) {
                            similarMethods.add(methodSignature);
                        }
                    }
                    currentClass = currentClass.getSuperclass();
                } while (currentClass != Object.class && currentClass != null);
                throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "The specified method does not exist. Similar methods: "
                                + similarMethods);
            } else if (candidateMethods.size() > 1) {
                throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "The specified method is ambiguous, please specify the method signature. "
                                + "Candidates: "
                                + candidateMethods);
            } else {
                return candidateMethods.getFirst();
            }
        }
    }

    private Object[] prepareArguments(
            @Nullable List<CreateMethodCallDTO.ParamDTO> inputParams,
            Method methodToCall) {
        Parameter[] parameters = methodToCall.getParameters();
        int paramCount = parameters.length;
        int inputParamCount = CollectionUtil.getSize(inputParams);
        if (inputParamCount > paramCount) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "The count of arguments must not exceed "
                            + paramCount);
        }
        Object[] arguments = new Object[paramCount];
        if (inputParamCount == 0) {
            return arguments;
        }
        boolean hasName = inputParams.getFirst()
                .name() != null;
        if (hasName) {
            Set<String> specifiedNames = CollectionUtil.newSetWithExpectedSize(inputParamCount);
            Map<String, Pair<Integer, Parameter>> nameToParam =
                    CollectionUtil.newMapWithExpectedSize(paramCount);
            for (int i = 0; i < paramCount; i++) {
                Parameter parameter = parameters[i];
                nameToParam.put(parameter.getName(), Pair.of(i, parameter));
            }
            for (int i = 0; i < inputParamCount; i++) {
                CreateMethodCallDTO.ParamDTO param = inputParams.get(i);
                String name = param.name();
                if (name == null) {
                    throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                            "The parameter at index "
                                    + i
                                    + " has not specified a name");
                }
                Pair<Integer, Parameter> parameter = nameToParam.get(name);
                if (parameter == null) {
                    throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                            "The specified parameter is not found: \""
                                    + param.name()
                                    + "\"");
                }
                if (!specifiedNames.add(name)) {
                    throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                            "The specified parameter is duplicated: \""
                                    + name
                                    + "\"");
                }
                arguments[parameter.first()] = parseArg(parameter.second()
                        .getType(), param.value());
            }
        } else {
            for (int i = 0; i < inputParamCount; i++) {
                CreateMethodCallDTO.ParamDTO param = inputParams.get(i);
                Class<?> parameterType = parameters[i].getType();
                arguments[i] = parseArg(parameterType, param.value());
            }
        }
        return arguments;
    }

    @Nullable
    private Object parseArg(Class parameterType, Object arg) {
        Class parameterClassToDetect = parameterType;
        if (parameterType.isPrimitive()) {
            if (arg == null) {
                throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "The specified argument is null. Expected type: "
                                + parameterType.getName());
            }
            parameterClassToDetect = PrimitiveUtil.primitiveToWrapper(parameterType);
        }
        if (Byte.class == parameterClassToDetect) {
            try {
                return MathUtil.toBigIntegerExact(arg)
                        .byteValueExact();
            } catch (Exception e) {
                throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "The specified argument is not a byte: "
                                + arg);
            }
        }
        if (Short.class == parameterClassToDetect) {
            try {
                return MathUtil.toBigIntegerExact(arg)
                        .shortValueExact();
            } catch (Exception e) {
                throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "The specified argument is not a short: "
                                + arg);
            }
        }
        if (Integer.class == parameterClassToDetect) {
            try {
                return MathUtil.toBigIntegerExact(arg)
                        .intValueExact();
            } catch (Exception e) {
                throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "The specified argument is not an integer: "
                                + arg);
            }
        }
        if (Long.class == parameterClassToDetect) {
            try {
                return MathUtil.toBigIntegerExact(arg)
                        .longValueExact();
            } catch (Exception e) {
                throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "The specified argument is not a long: "
                                + arg);
            }
        }
        if (Float.class == parameterClassToDetect) {
            try {
                return MathUtil.toBigDecimalExact(arg)
                        .floatValue();
            } catch (Exception e) {
                throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "The specified argument is not a float: "
                                + arg);
            }
        }
        if (Double.class == parameterClassToDetect) {
            try {
                return MathUtil.toBigDecimalExact(arg)
                        .doubleValue();
            } catch (Exception e) {
                throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "The specified argument is not a double: "
                                + arg);
            }
        }
        if (Character.class == parameterClassToDetect) {
            return switch (arg) {
                case Character value -> value;
                case String value -> value.charAt(0);
                case null,
                        default ->
                    throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                            "The specified argument is not a char: "
                                    + arg);
            };
        }
        if (Boolean.class == parameterClassToDetect) {
            if (arg instanceof Boolean argBool) {
                return argBool;
            } else {
                throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "The specified argument is not a boolean: "
                                + arg);
            }
        }
        if (String.class == parameterClassToDetect) {
            if (arg == null) {
                return null;
            }
            return arg.toString();
        }
        if (parameterClassToDetect.isEnum()) {
            if (!(arg instanceof String argStr)) {
                throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "The parameter type is an enum, but the input argument type is not a string");
            }
            Enum<?>[] enumValues = ClassUtil.getSharedEnumConstants(parameterClassToDetect);
            for (Enum<?> enumValue : enumValues) {
                String a = argStr.toUpperCase();
                if (enumValue.name()
                        .toUpperCase()
                        .equals(a)) {
                    return enumValue;
                }
            }
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "Expected enum value: "
                            + Arrays.toString(enumValues)
                            + ", but got: "
                            + argStr);
        }
        if (Date.class == parameterClassToDetect) {
            if (arg instanceof Number argNumber) {
                return new Date(argNumber.longValue());
            }
            if (arg instanceof String argStr) {
                if ("now".equals(argStr)) {
                    return new Date();
                }
                Long l = LongUtil.tryParse(argStr);
                if (l == null) {
                    throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                            "The specified argument is not a number or \"now\": "
                                    + arg);
                }
                return new Date(l);
            } else {
                throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "The specified argument is not a string: "
                                + arg);
            }
        }
        if (arg instanceof String argStr) {
            try {
                return JsonCodecPool.MAPPER.readerFor(parameterClassToDetect)
                        .readValue(argStr);
            } catch (Exception e) {
                throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "Failed to convert the argument: \""
                                + arg
                                + "\" to the type: "
                                + parameterType.getName(),
                        e);
            }
        }
        try {
            return JsonCodecPool.MAPPER.readerFor(parameterClassToDetect)
                    .readValue(JsonCodecPool.MAPPER.writeValueAsBytes(arg));
        } catch (Exception e) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "Failed to convert the argument: "
                            + arg
                            + " to the type: "
                            + parameterType.getName(),
                    e);
        }
    }

}