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

package im.turms.service.domain.common.access.admin.controller;

import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.property.env.service.env.adminapi.AdminApiProperties;
import im.turms.server.common.infra.time.DateRange;
import im.turms.server.common.infra.time.TimeZoneConst;
import im.turms.service.domain.common.access.admin.dto.request.DivideBy;
import im.turms.service.domain.common.access.admin.dto.response.StatisticsRecordDTO;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.function.Function3;

import javax.annotation.Nullable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

/**
 * @author James Chen
 */
public abstract class BaseController {

    private final Node node;

    protected BaseController(Node node) {
        this.node = node;
    }

    public int getPageSize(@Nullable Integer size) {
        AdminApiProperties properties = node.getSharedProperties().getService().getAdminApi();
        if (size == null || size <= 0) {
            return properties.getDefaultAvailableRecordsPerRequest();
        }
        int maxLimit = properties.getMaxAvailableRecordsPerRequest();
        return size > maxLimit ? maxLimit : size;
    }

    public Mono<List<StatisticsRecordDTO>> queryBetweenDate(
            DateRange dateRange,
            DivideBy divideBy,
            Function3<DateRange, Boolean, Boolean, Mono<Long>> function,
            @Nullable Boolean areGroupMessages,
            @Nullable Boolean areSystemMessages) {
        List<Pair<Date, Date>> dates = divideDate(dateRange.start(), dateRange.end(), divideBy);
        List<Mono<StatisticsRecordDTO>> monos = new ArrayList<>(dates.size());
        for (Pair<Date, Date> datePair : dates) {
            Mono<Long> result = function.apply(
                    DateRange.of(datePair.getLeft(), datePair.getRight()),
                    areGroupMessages,
                    areSystemMessages);
            monos.add(result.map(total -> new StatisticsRecordDTO(
                    datePair.getLeft(),
                    total)));
        }
        return mergeStaticsRecords(monos);
    }

    public Mono<List<StatisticsRecordDTO>> queryBetweenDate(
            DateRange dateRange,
            DivideBy divideBy,
            Function<DateRange, Mono<Long>> function) {
        List<Pair<Date, Date>> dates = divideDate(dateRange.start(), dateRange.end(), divideBy);
        List<Mono<StatisticsRecordDTO>> monos = new ArrayList<>(dates.size());
        for (Pair<Date, Date> datePair : dates) {
            DateRange range = DateRange.of(datePair.getLeft(), datePair.getRight());
            Mono<StatisticsRecordDTO> result = function.apply(range)
                    .map(total -> new StatisticsRecordDTO(datePair.getLeft(), total));
            monos.add(result);
        }
        return mergeStaticsRecords(monos);
    }

    public Mono<List<StatisticsRecordDTO>> checkAndQueryBetweenDate(
            DateRange dateRange,
            DivideBy divideBy,
            Function3<DateRange, Boolean, Boolean, Mono<Long>> function,
            @Nullable Boolean areGroupMessages,
            @Nullable Boolean areSystemMessages) {
        int maxHourRanges = node.getSharedProperties()
                .getService().getAdminApi().getMaxHourDifferencePerCountRequest();
        int maxDayRanges = node.getSharedProperties()
                .getService().getAdminApi().getMaxDayDifferencePerCountRequest();
        int maxMonthRanges = node.getSharedProperties()
                .getService().getAdminApi().getMaxMonthDifferencePerCountRequest();
        boolean checked = isDurationNotGreaterThanMax(dateRange, divideBy,
                maxHourRanges, maxDayRanges, maxMonthRanges);
        if (checked) {
            return queryBetweenDate(dateRange, divideBy, function, areGroupMessages, areSystemMessages);
        }
        throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS);
    }

    public Mono<List<StatisticsRecordDTO>> checkAndQueryBetweenDate(
            DateRange dateRange,
            DivideBy divideBy,
            Function<DateRange, Mono<Long>> function) {
        AdminApiProperties properties = node.getSharedProperties().getService().getAdminApi();
        int maxHourRanges = properties.getMaxHourDifferencePerCountRequest();
        int maxDayRanges = properties.getMaxDayDifferencePerCountRequest();
        int maxMonthRanges = properties.getMaxMonthDifferencePerCountRequest();
        boolean checked = isDurationNotGreaterThanMax(dateRange, divideBy, maxHourRanges, maxDayRanges, maxMonthRanges);
        if (checked) {
            return queryBetweenDate(dateRange, divideBy, function);
        }
        throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS);
    }

    private Mono<List<StatisticsRecordDTO>> mergeStaticsRecords(List<Mono<StatisticsRecordDTO>> recordMonos) {
        return Flux.merge(recordMonos)
                .collectSortedList((o1, o2) -> {
                    Date date1 = o1.date();
                    Date date2 = o2.date();
                    return date1.compareTo(date2);
                });
    }

    private boolean isDurationNotGreaterThanMax(
            DateRange dateRange,
            DivideBy divideBy,
            @Nullable Integer maxHourRanges,
            @Nullable Integer maxDayRanges,
            @Nullable Integer maxMonthRanges) {
        Date startDate = dateRange.start();
        Date endDate = dateRange.end();
        return switch (divideBy) {
            case HOUR -> maxHourRanges == null || calculateDuration(startDate, endDate, divideBy) <= maxHourRanges;
            case DAY -> maxDayRanges == null || calculateDuration(startDate, endDate, divideBy) <= maxDayRanges;
            case MONTH -> maxMonthRanges == null || calculateDuration(startDate, endDate, divideBy) <= maxMonthRanges;
            case NOOP -> true;
        };
    }

    private int calculateDuration(Date startDate, Date endDate, DivideBy divideBy) {
        long diffInMillis = endDate.getTime() - startDate.getTime();
        return switch (divideBy) {
            case HOUR -> (int) Math.ceil((double) diffInMillis / 3600000);
            case DAY -> (int) Math.ceil((double) diffInMillis / 86400000);
            case MONTH -> (int) Math.ceil((double) diffInMillis / 2629746000L);
            case NOOP -> 1;
        };
    }

    private List<Pair<Date, Date>> divideDate(Date startDate, Date endDate, DivideBy divideBy) {
        if (!endDate.after(startDate)) {
            return Collections.emptyList();
        }
        if (startDate.equals(endDate)) {
            return Collections.emptyList();
        }
        List<Pair<Date, Date>> lists = new LinkedList<>();
        while (true) {
            ZonedDateTime time = ZonedDateTime.now(TimeZoneConst.ZONE_ID);
            Date currentEndDate = switch (divideBy) {
                case HOUR -> new Date(time.plusHours(1).toEpochSecond());
                case DAY -> new Date(time.plusDays(1).toEpochSecond());
                case MONTH -> new Date(time.plusMonths(1).toEpochSecond());
                default -> throw new IllegalStateException("Unexpected value: " + divideBy);
            };
            if (endDate.before(currentEndDate)) {
                break;
            }
            Pair<Date, Date> datePair = Pair.of(startDate, currentEndDate);
            lists.add(datePair);
            startDate = currentEndDate;
        }
        return lists;
    }

}
