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

package im.turms.service.domain.message.access.admin.controller;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.admin.dto.response.DeleteResultDTO;
import im.turms.server.common.access.admin.dto.response.HttpHandlerResult;
import im.turms.server.common.access.admin.dto.response.PaginationDTO;
import im.turms.server.common.access.admin.dto.response.ResponseDTO;
import im.turms.server.common.access.admin.dto.response.UpdateResultDTO;
import im.turms.server.common.access.admin.permission.RequiredPermission;
import im.turms.server.common.access.admin.web.annotation.DeleteMapping;
import im.turms.server.common.access.admin.web.annotation.GetMapping;
import im.turms.server.common.access.admin.web.annotation.PostMapping;
import im.turms.server.common.access.admin.web.annotation.PutMapping;
import im.turms.server.common.access.admin.web.annotation.QueryParam;
import im.turms.server.common.access.admin.web.annotation.RequestBody;
import im.turms.server.common.access.admin.web.annotation.RestController;
import im.turms.server.common.infra.net.InetAddressUtil;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.time.DateRange;
import im.turms.server.common.infra.time.DivideBy;
import im.turms.service.domain.common.access.admin.controller.BaseController;
import im.turms.service.domain.message.access.admin.dto.request.CreateMessageDTO;
import im.turms.service.domain.message.access.admin.dto.request.UpdateMessageDTO;
import im.turms.service.domain.message.access.admin.dto.response.MessageStatisticsDTO;
import im.turms.service.domain.message.po.Message;
import im.turms.service.domain.message.service.MessageService;

import static im.turms.server.common.access.admin.permission.AdminPermission.MESSAGE_CREATE;
import static im.turms.server.common.access.admin.permission.AdminPermission.MESSAGE_DELETE;
import static im.turms.server.common.access.admin.permission.AdminPermission.MESSAGE_QUERY;
import static im.turms.server.common.access.admin.permission.AdminPermission.MESSAGE_UPDATE;

/**
 * @author James Chen
 */
@RestController("messages")
public class MessageController extends BaseController {

    private final MessageService messageService;

    public MessageController(
            TurmsPropertiesManager propertiesManager,
            MessageService messageService) {
        super(propertiesManager);
        this.messageService = messageService;
    }

    @PostMapping
    @RequiredPermission(MESSAGE_CREATE)
    public Mono<HttpHandlerResult<ResponseDTO<Void>>> createMessages(
            @QueryParam(defaultValue = "true") boolean send,
            @RequestBody CreateMessageDTO createMessageDTO) {
        String ip = createMessageDTO.senderIp();
        Mono<Void> sendMono = messageService.authAndSaveAndSendMessage(send,
                null,
                createMessageDTO.senderId(),
                createMessageDTO.senderDeviceType(),
                ip == null
                        ? null
                        : InetAddressUtil.ipStringToBytes(ip),
                createMessageDTO.id(),
                createMessageDTO.isGroupMessage(),
                createMessageDTO.isSystemMessage(),
                createMessageDTO.text(),
                createMessageDTO.records(),
                createMessageDTO.targetId(),
                createMessageDTO.burnAfter(),
                createMessageDTO.referenceId(),
                createMessageDTO.preMessageId());
        return sendMono.thenReturn(HttpHandlerResult.RESPONSE_OK);
    }

    @GetMapping
    @RequiredPermission(MESSAGE_QUERY)
    public Mono<HttpHandlerResult<ResponseDTO<Collection<Message>>>> queryMessages(
            @QueryParam(required = false) Set<Long> ids,
            @QueryParam(required = false) Boolean areGroupMessages,
            @QueryParam(required = false) Boolean areSystemMessages,
            @QueryParam(required = false) Set<Long> senderIds,
            @QueryParam(required = false) Set<Long> targetIds,
            @QueryParam(required = false) Date deliveryDateStart,
            @QueryParam(required = false) Date deliveryDateEnd,
            @QueryParam(required = false) Date deletionDateStart,
            @QueryParam(required = false) Date deletionDateEnd,
            @QueryParam(required = false) Date recallDateStart,
            @QueryParam(required = false) Date recallDateEnd,
            @QueryParam(required = false) Integer size,
            @QueryParam(required = false) Boolean ascending) {
        Flux<Message> completeMessagesFlux = messageService.queryMessages(ids,
                areGroupMessages,
                areSystemMessages,
                senderIds,
                targetIds,
                DateRange.of(deliveryDateStart, deliveryDateEnd),
                DateRange.of(deletionDateStart, deletionDateEnd),
                DateRange.of(recallDateStart, recallDateEnd),
                0,
                getPageSize(size),
                ascending);
        return HttpHandlerResult.okIfTruthy(completeMessagesFlux);
    }

    @GetMapping("page")
    @RequiredPermission(MESSAGE_QUERY)
    public Mono<HttpHandlerResult<ResponseDTO<PaginationDTO<Message>>>> queryMessages(
            @QueryParam(required = false) Set<Long> ids,
            @QueryParam(required = false) Boolean areGroupMessages,
            @QueryParam(required = false) Boolean areSystemMessages,
            @QueryParam(required = false) Set<Long> senderIds,
            @QueryParam(required = false) Set<Long> targetIds,
            @QueryParam(required = false) Date deliveryDateStart,
            @QueryParam(required = false) Date deliveryDateEnd,
            @QueryParam(required = false) Date deletionDateStart,
            @QueryParam(required = false) Date deletionDateEnd,
            @QueryParam(required = false) Date recallDateStart,
            @QueryParam(required = false) Date recallDateEnd,
            int page,
            @QueryParam(required = false) Integer size,
            @QueryParam(required = false) Boolean ascending) {
        DateRange deliveryDateRange = DateRange.of(deliveryDateStart, deliveryDateEnd);
        DateRange deletionDateRange = DateRange.of(deletionDateStart, deletionDateEnd);
        DateRange recallDateRange = DateRange.of(recallDateStart, recallDateEnd);
        Mono<Long> count = messageService.countMessages(ids,
                areGroupMessages,
                areSystemMessages,
                senderIds,
                targetIds,
                deliveryDateRange,
                deletionDateRange);
        Flux<Message> completeMessagesFlux = messageService.queryMessages(ids,
                areGroupMessages,
                areSystemMessages,
                senderIds,
                targetIds,
                deliveryDateRange,
                deletionDateRange,
                recallDateRange,
                page,
                getPageSize(size),
                ascending);
        return HttpHandlerResult.page(count, completeMessagesFlux);
    }

    @GetMapping("count")
    @RequiredPermission(MESSAGE_QUERY)
    public Mono<HttpHandlerResult<ResponseDTO<MessageStatisticsDTO>>> countMessages(
            @QueryParam(required = false) Boolean areGroupMessages,
            @QueryParam(required = false) Boolean areSystemMessages,
            @QueryParam(required = false) Date sentStartDate,
            @QueryParam(required = false) Date sentEndDate,
            @QueryParam(required = false) Date sentOnAverageStartDate,
            @QueryParam(required = false) Date sentOnAverageEndDate,
            @QueryParam(required = false) Date acknowledgedStartDate,
            @QueryParam(required = false) Date acknowledgedEndDate,
            @QueryParam(required = false) Date acknowledgedOnAverageStartDate,
            @QueryParam(required = false) Date acknowledgedOnAverageEndDate,
            @QueryParam(defaultValue = "NOOP") DivideBy divideBy) {
        List<Mono<?>> counts = new LinkedList<>();
        MessageStatisticsDTO.MessageStatisticsDTOBuilder builder = MessageStatisticsDTO.builder();
        if (divideBy == null || divideBy == DivideBy.NOOP) {
            if (sentOnAverageStartDate != null || sentOnAverageEndDate != null) {
                counts.add(messageService
                        .countSentMessagesOnAverage(DateRange.of(sentOnAverageStartDate,
                                sentOnAverageEndDate), areGroupMessages, areSystemMessages)
                        .doOnNext(builder::sentMessagesOnAverage));
            }
//            if (acknowledgedStartDate != null || acknowledgedEndDate != null) {
//                counts.add(messageService.countAcknowledgedMessages(
//                        DateRange.of(acknowledgedStartDate, acknowledgedEndDate),
//                        areGroupMessages,
//                        areSystemMessages)
//                        .doOnNext(builder::acknowledgedMessages));
//            }
//            if (acknowledgedOnAverageStartDate != null || acknowledgedOnAverageEndDate != null) {
//                counts.add(messageService.countAcknowledgedMessagesOnAverage(
//                        DateRange.of(acknowledgedOnAverageStartDate, acknowledgedOnAverageEndDate),
//                        areGroupMessages,
//                        areSystemMessages)
//                        .doOnNext(builder::acknowledgedMessagesOnAverage));
//            }
            if (counts.isEmpty() || sentStartDate != null || sentEndDate != null) {
                counts.add(messageService
                        .countSentMessages(DateRange.of(sentStartDate, sentEndDate),
                                areGroupMessages,
                                areSystemMessages)
                        .doOnNext(builder::sentMessages));
            }
        } else {
            if (sentOnAverageStartDate != null && sentOnAverageEndDate != null) {
                counts.add(checkAndQueryBetweenDate(
                        DateRange.of(sentOnAverageStartDate, sentOnAverageEndDate),
                        divideBy,
                        messageService::countSentMessagesOnAverage,
                        areGroupMessages,
                        areSystemMessages).doOnNext(builder::sentMessagesOnAverageRecords));
            }
//            if (acknowledgedStartDate != null && acknowledgedEndDate != null) {
//                counts.add(checkAndQueryBetweenDate(
//                        DateRange.of(acknowledgedStartDate, acknowledgedEndDate),
//                        divideBy,
//                        messageService::countAcknowledgedMessages,
//                        areGroupMessages,
//                        areSystemMessages)
//                        .doOnNext(builder::acknowledgedMessagesRecords));
//            }
//            if (acknowledgedOnAverageStartDate != null && acknowledgedOnAverageEndDate != null) {
//                counts.add(checkAndQueryBetweenDate(
//                        DateRange.of(acknowledgedOnAverageStartDate, acknowledgedOnAverageEndDate),
//                        divideBy,
//                        messageService::countAcknowledgedMessagesOnAverage,
//                        areGroupMessages,
//                        areSystemMessages)
//                        .doOnNext(builder::acknowledgedMessagesOnAverageRecords));
//            }
            if (sentStartDate != null && sentEndDate != null) {
                counts.add(checkAndQueryBetweenDate(DateRange.of(sentStartDate, sentEndDate),
                        divideBy,
                        messageService::countSentMessages,
                        areGroupMessages,
                        areSystemMessages).doOnNext(builder::sentMessagesRecords));
            }
            if (counts.isEmpty()) {
                return Mono.empty();
            }
        }
        return HttpHandlerResult.okIfTruthy(Mono.when(counts)
                .then(Mono.fromCallable(builder::build)));
    }

    @PutMapping
    @RequiredPermission(MESSAGE_UPDATE)
    public Mono<HttpHandlerResult<ResponseDTO<UpdateResultDTO>>> updateMessages(
            Set<Long> ids,
            @RequestBody UpdateMessageDTO updateMessageDTO) {
        String ip = updateMessageDTO.senderIp();
        Mono<UpdateResultDTO> updateMono = messageService
                .updateMessages(updateMessageDTO.senderId(),
                        updateMessageDTO.senderDeviceType(),
                        ids,
                        updateMessageDTO.isSystemMessage(),
                        updateMessageDTO.text(),
                        updateMessageDTO.records(),
                        updateMessageDTO.burnAfter(),
                        updateMessageDTO.recallDate(),
                        ip == null
                                ? null
                                : InetAddressUtil.ipStringToBytes(ip),
                        null)
                .map(UpdateResultDTO::get);
        return HttpHandlerResult.okIfTruthy(updateMono);
    }

    @DeleteMapping
    @RequiredPermission(MESSAGE_DELETE)
    public Mono<HttpHandlerResult<ResponseDTO<DeleteResultDTO>>> deleteMessages(
            Set<Long> ids,
            @QueryParam(required = false) Boolean deleteLogically) {
        Mono<DeleteResultDTO> deleteMono = messageService.deleteMessages(ids, deleteLogically)
                .map(DeleteResultDTO::get);
        return HttpHandlerResult.okIfTruthy(deleteMono);
    }

}