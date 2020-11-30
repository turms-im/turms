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

package im.turms.server.common.access.http.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.core.log.LogMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Use ErrorWebExceptionHandler instead of DefaultErrorAttributes
 * for better performance, code clarity and fine-grained control
 *
 * @author James Chen
 * @see org.springframework.boot.autoconfigure.web.reactive.error.ErrorWebFluxAutoConfiguration
 * @see org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler
 * @see org.springframework.web.server.handler.ResponseStatusExceptionHandler
 */
@Configuration
@Log4j2
public class TurmsErrorWebExceptionHandler implements ErrorWebExceptionHandler {

    private static final Set<String> DISCONNECTED_CLIENT_EXCEPTIONS = Set.of(
            "AbortedException",
            "ClientAbortException",
            "EOFException",
            "EofException");

    private final ServerResponse.Context context;
    private final List<HttpMessageReader<?>> messageReaders;

    public TurmsErrorWebExceptionHandler(ServerCodecConfigurer serverCodecConfigurer) {
        messageReaders = serverCodecConfigurer.getReaders();
        context = new ServerResponse.Context() {
            @Override
            public List<HttpMessageWriter<?>> messageWriters() {
                return serverCodecConfigurer.getWriters();
            }

            @Override
            public List<ViewResolver> viewResolvers() {
                return Collections.emptyList();
            }
        };
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable throwable) {
        if (exchange.getResponse().isCommitted() || isDisconnectedClientError(throwable)) {
            return Mono.error(throwable);
        }
        ErrorAttributes errorAttributes = new ErrorAttributes(throwable);
        int status = errorAttributes.getStatus();
        Mono<ServerResponse> responseMono = ServerResponse
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(errorAttributes));
        return responseMono.flatMap(response -> {
            if (status == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                ServerRequest request = ServerRequest.create(exchange, this.messageReaders);
                log.error(LogMessage.of(() -> String.format("%s 500 Server Error for %s",
                        request.exchange().getLogPrefix(), formatRequest(request))), throwable);
            }
            // force content-type since writeTo won't overwrite response header values
            exchange.getResponse().getHeaders().setContentType(response.headers().getContentType());
            return response.writeTo(exchange, context);
        });
    }

    private boolean isDisconnectedClientError(Throwable throwable) {
        if (DISCONNECTED_CLIENT_EXCEPTIONS.contains(throwable.getClass().getSimpleName())) {
            return true;
        }
        String message = NestedExceptionUtils.getMostSpecificCause(throwable).getMessage();
        if (message != null) {
            message = message.toLowerCase();
            return message.contains("broken pipe") || message.contains("connection reset by peer");
        } else {
            return false;
        }
    }

    private String formatRequest(ServerRequest request) {
        String rawQuery = request.uri().getRawQuery();
        String query = StringUtils.hasText(rawQuery) ? "?" + rawQuery : "";
        return "HTTP " + request.methodName() + " \"" + request.path() + query + "\"";
    }

}
