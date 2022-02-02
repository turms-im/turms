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

package im.turms.service.workflow.access.http.controller.message;

import im.turms.server.common.access.http.dto.response.DeleteResultDTO;
import im.turms.server.common.access.http.dto.response.PaginationDTO;
import im.turms.server.common.access.http.dto.response.ResponseDTO;
import im.turms.server.common.access.http.dto.response.ResponseFactory;
import im.turms.server.common.access.http.dto.response.UpdateResultDTO;
import im.turms.server.common.access.http.permission.RequiredPermission;
import im.turms.server.common.bo.common.DateRange;
import im.turms.service.constant.DivideBy;
import im.turms.service.workflow.access.http.dto.request.message.CreateMessageDTO;
import im.turms.service.workflow.access.http.dto.request.message.MessageStatisticsDTO;
import im.turms.service.workflow.access.http.dto.request.message.UpdateMessageDTO;
import im.turms.service.workflow.access.http.util.DateTimeUtil;
import im.turms.service.workflow.access.http.util.PageUtil;
import im.turms.service.workflow.dao.domain.message.Message;
import im.turms.service.workflow.service.impl.message.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static im.turms.server.common.access.http.permission.AdminPermission.MESSAGE_CREATE;
import static im.turms.server.common.access.http.permission.AdminPermission.MESSAGE_DELETE;
import static im.turms.server.common.access.http.permission.AdminPermission.MESSAGE_QUERY;
import static im.turms.server.common.access.http.permission.AdminPermission.MESSAGE_UPDATE;

/**
 * @author James Chen
 */
@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;
    private final PageUtil pageUtil;
    private final DateTimeUtil dateTimeUtil;

    public MessageController(MessageService messageService, PageUtil pageUtil, DateTimeUtil dateTimeUtil) {
        this.messageService = messageService;
        this.pageUtil = pageUtil;
        this.dateTimeUtil = dateTimeUtil;
    }

    @PostMapping
    @RequiredPermission(MESSAGE_CREATE)
    public Mono<ResponseEntity<ResponseDTO<Void>>> createMessages(
            @RequestParam(defaultValue = "true") Boolean send,
            @RequestBody CreateMessageDTO createMessageDTO) {
        Mono<Void> sendMono = messageService.authAndSaveAndSendMessage(
                send,
                createMessageDTO.id(),
                createMessageDTO.isGroupMessage(),
                createMessageDTO.isSystemMessage(),
                createMessageDTO.text(),
                createMessageDTO.records(),
                createMessageDTO.senderId(),
                createMessageDTO.targetId(),
                createMessageDTO.burnAfter(),
                createMessageDTO.referenceId(),
                createMessageDTO.preMessageId());
        return sendMono.thenReturn(ResponseFactory.OK);
    }

    @GetMapping
    @RequiredPermission(MESSAGE_QUERY)
    public Mono<ResponseEntity<ResponseDTO<Collection<Message>>>> queryMessages(
            @RequestParam(required = false) Set<Long> ids,
            @RequestParam(required = false) Boolean areGroupMessages,
            @RequestParam(required = false) Boolean areSystemMessages,
            @RequestParam(required = false) Set<Long> senderIds,
            @RequestParam(required = false) Set<Long> targetIds,
            @RequestParam(required = false) Date deliveryDateStart,
            @RequestParam(required = false) Date deliveryDateEnd,
            @RequestParam(required = false) Date deletionDateStart,
            @RequestParam(required = false) Date deletionDateEnd,
            @RequestParam(required = false) Integer size) {
        Flux<Message> completeMessagesFlux = messageService.queryMessages(
                false,
                ids,
                areGroupMessages,
                areSystemMessages,
                senderIds,
                targetIds,
                DateRange.of(deliveryDateStart, deliveryDateEnd),
                DateRange.of(deletionDateStart, deletionDateEnd),
                0,
                pageUtil.getSize(size));
        return ResponseFactory.okIfTruthy(completeMessagesFlux);
    }

    @GetMapping("/page")
    @RequiredPermission(MESSAGE_QUERY)
    public Mono<ResponseEntity<ResponseDTO<PaginationDTO<Message>>>> queryMessages(
            @RequestParam(required = false) Set<Long> ids,
            @RequestParam(required = false) Boolean areGroupMessages,
            @RequestParam(required = false) Boolean areSystemMessages,
            @RequestParam(required = false) Set<Long> senderIds,
            @RequestParam(required = false) Set<Long> targetIds,
            @RequestParam(required = false) Date deliveryDateStart,
            @RequestParam(required = false) Date deliveryDateEnd,
            @RequestParam(required = false) Date deletionDateStart,
            @RequestParam(required = false) Date deletionDateEnd,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(required = false) Integer size) {
        Mono<Long> count = messageService.countMessages(
                ids,
                areGroupMessages,
                areSystemMessages,
                senderIds,
                targetIds,
                DateRange.of(deliveryDateStart, deliveryDateEnd),
                DateRange.of(deletionDateStart, deletionDateEnd));
        Flux<Message> completeMessagesFlux = messageService.queryMessages(
                false,
                ids,
                areGroupMessages,
                areSystemMessages,
                senderIds,
                targetIds,
                DateRange.of(deliveryDateStart, deliveryDateEnd),
                DateRange.of(deletionDateStart, deletionDateEnd),
                page,
                pageUtil.getSize(size));
        return ResponseFactory.page(count, completeMessagesFlux);
    }

    @GetMapping("/count")
    @RequiredPermission(MESSAGE_QUERY)
    public Mono<ResponseEntity<ResponseDTO<MessageStatisticsDTO>>> countMessages(
            @RequestParam(required = false) Boolean areGroupMessages,
            @RequestParam(required = false) Boolean areSystemMessages,
            @RequestParam(required = false) Date sentStartDate,
            @RequestParam(required = false) Date sentEndDate,
            @RequestParam(required = false) Date sentOnAverageStartDate,
            @RequestParam(required = false) Date sentOnAverageEndDate,
            @RequestParam(required = false) Date acknowledgedStartDate,
            @RequestParam(required = false) Date acknowledgedEndDate,
            @RequestParam(required = false) Date acknowledgedOnAverageStartDate,
            @RequestParam(required = false) Date acknowledgedOnAverageEndDate,
            @RequestParam(defaultValue = "NOOP") DivideBy divideBy) {
        List<Mono<?>> counts = new LinkedList<>();
        MessageStatisticsDTO statistics = new MessageStatisticsDTO();
        if (divideBy == null || divideBy == DivideBy.NOOP) {
            if (sentOnAverageStartDate != null || sentOnAverageEndDate != null) {
                counts.add(messageService.countSentMessagesOnAverage(
                                DateRange.of(sentOnAverageStartDate, sentOnAverageEndDate),
                                areGroupMessages,
                                areSystemMessages)
                        .doOnNext(statistics::setSentMessagesOnAverage));
            }
//            if (acknowledgedStartDate != null || acknowledgedEndDate != null) {
//                counts.add(messageService.countAcknowledgedMessages(
//                        DateRange.of(acknowledgedStartDate, acknowledgedEndDate),
//                        areGroupMessages,
//                        areSystemMessages)
//                        .doOnNext(statistics::setAcknowledgedMessages));
//            }
//            if (acknowledgedOnAverageStartDate != null || acknowledgedOnAverageEndDate != null) {
//                counts.add(messageService.countAcknowledgedMessagesOnAverage(
//                        DateRange.of(acknowledgedOnAverageStartDate, acknowledgedOnAverageEndDate),
//                        areGroupMessages,
//                        areSystemMessages)
//                        .doOnNext(statistics::setAcknowledgedMessagesOnAverage));
//            }
            if (counts.isEmpty() || sentStartDate != null || sentEndDate != null) {
                counts.add(messageService.countSentMessages(
                                DateRange.of(sentStartDate, sentEndDate),
                                areGroupMessages,
                                areSystemMessages)
                        .doOnNext(statistics::setSentMessages));
            }
        } else {
            if (sentOnAverageStartDate != null && sentOnAverageEndDate != null) {
                counts.add(dateTimeUtil.checkAndQueryBetweenDate(
                                DateRange.of(sentOnAverageStartDate, sentOnAverageEndDate),
                                divideBy,
                                messageService::countSentMessagesOnAverage,
                                areGroupMessages,
                                areSystemMessages)
                        .doOnNext(statistics::setSentMessagesOnAverageRecords));
            }
//            if (acknowledgedStartDate != null && acknowledgedEndDate != null) {
//                counts.add(dateTimeUtil.checkAndQueryBetweenDate(
//                        DateRange.of(acknowledgedStartDate, acknowledgedEndDate),
//                        divideBy,
//                        messageService::countAcknowledgedMessages,
//                        areGroupMessages,
//                        areSystemMessages)
//                        .doOnNext(statistics::setAcknowledgedMessagesRecords));
//            }
//            if (acknowledgedOnAverageStartDate != null && acknowledgedOnAverageEndDate != null) {
//                counts.add(dateTimeUtil.checkAndQueryBetweenDate(
//                        DateRange.of(acknowledgedOnAverageStartDate, acknowledgedOnAverageEndDate),
//                        divideBy,
//                        messageService::countAcknowledgedMessagesOnAverage,
//                        areGroupMessages,
//                        areSystemMessages)
//                        .doOnNext(statistics::setAcknowledgedMessagesOnAverageRecords));
//            }
            if (sentStartDate != null && sentEndDate != null) {
                counts.add(dateTimeUtil.checkAndQueryBetweenDate(
                                DateRange.of(sentStartDate, sentEndDate),
                                divideBy,
                                messageService::countSentMessages,
                                areGroupMessages,
                                areSystemMessages)
                        .doOnNext(statistics::setSentMessagesRecords));
            }
            if (counts.isEmpty()) {
                return Mono.empty();
            }
        }
        return ResponseFactory.okIfTruthy(Flux.merge(counts).then(Mono.just(statistics)));
    }

    @PutMapping
    @RequiredPermission(MESSAGE_UPDATE)
    public Mono<ResponseEntity<ResponseDTO<UpdateResultDTO>>> updateMessages(
            @RequestParam Set<Long> ids,
            @RequestBody UpdateMessageDTO updateMessageDTO) {
        Mono<UpdateResultDTO> updateMono = messageService.updateMessages(
                        ids,
                        updateMessageDTO.isSystemMessage(),
                        updateMessageDTO.text(),
                        updateMessageDTO.records(),
                        updateMessageDTO.burnAfter(),
                        updateMessageDTO.recallDate(),
                        null)
                .map(UpdateResultDTO::get);
        return ResponseFactory.okIfTruthy(updateMono);
    }

    @DeleteMapping
    @RequiredPermission(MESSAGE_DELETE)
    public Mono<ResponseEntity<ResponseDTO<DeleteResultDTO>>> deleteMessages(
            @RequestParam Set<Long> ids,
            @RequestParam(required = false) Boolean deleteLogically) {
        Mono<DeleteResultDTO> deleteMono = messageService
                .deleteMessages(ids, deleteLogically)
                .map(DeleteResultDTO::get);
        return ResponseFactory.okIfTruthy(deleteMono);
    }

}