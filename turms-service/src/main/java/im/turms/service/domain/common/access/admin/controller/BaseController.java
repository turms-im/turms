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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import jakarta.annotation.Nullable;

import org.apache.commons.lang3.tuple.Pair;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.function.Function3;

import im.turms.server.common.access.admin.web.HttpResponseException;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.service.env.adminapi.AdminApiProperties;
import im.turms.server.common.infra.time.DateRange;
import im.turms.server.common.infra.time.DateUtil;
import im.turms.server.common.infra.time.DivideBy;
import im.turms.service.domain.common.access.admin.dto.response.StatisticsRecordDTO;

/**
 * @author James Chen
 */
public abstract class BaseController {

    protected final TurmsPropertiesManager propertiesManager;

    private int defaultAvailableRecordsPerRequest;
    private int maxAvailableRecordsPerRequest;
    private int maxHourDifferencePerCountRequest;
    private int maxDayDifferencePerCountRequest;
    private int maxMonthDifferencePerCountRequest;

    protected BaseController(TurmsPropertiesManager propertiesManager) {
        this.propertiesManager = propertiesManager;
        propertiesManager.notifyAndAddGlobalPropertiesChangeListener(this::updateProperties);
    }

    private void updateProperties(TurmsProperties properties) {
        AdminApiProperties apiProperties = properties.getService()
                .getAdminApi();
        defaultAvailableRecordsPerRequest = apiProperties.getDefaultAvailableRecordsPerRequest();
        maxAvailableRecordsPerRequest = apiProperties.getMaxAvailableRecordsPerRequest();
        maxHourDifferencePerCountRequest = apiProperties.getMaxHourDifferencePerCountRequest();
        maxDayDifferencePerCountRequest = apiProperties.getMaxDayDifferencePerCountRequest();
        maxMonthDifferencePerCountRequest = apiProperties.getMaxMonthDifferencePerCountRequest();
    }

    public int getPageSize(@Nullable Integer size) {
        if (size == null || size <= 0) {
            return defaultAvailableRecordsPerRequest;
        }
        return Math.min(size, maxAvailableRecordsPerRequest);
    }

    public Mono<List<StatisticsRecordDTO>> queryBetweenDate(
            DateRange dateRange,
            DivideBy divideBy,
            Function3<DateRange, Boolean, Boolean, Mono<Long>> function,
            @Nullable Boolean areGroupMessages,
            @Nullable Boolean areSystemMessages) {
        List<Pair<Date, Date>> dates =
                DateUtil.divideDuration(dateRange.start(), dateRange.end(), divideBy);
        List<Mono<StatisticsRecordDTO>> monos = new ArrayList<>(dates.size());
        for (Pair<Date, Date> datePair : dates) {
            Mono<Long> result =
                    function.apply(DateRange.of(datePair.getLeft(), datePair.getRight()),
                            areGroupMessages,
                            areSystemMessages);
            monos.add(result.map(total -> new StatisticsRecordDTO(datePair.getLeft(), total)));
        }
        return mergeStaticsRecords(monos);
    }

    public Mono<List<StatisticsRecordDTO>> queryBetweenDate(
            DateRange dateRange,
            DivideBy divideBy,
            Function<DateRange, Mono<Long>> function) {
        List<Pair<Date, Date>> dates =
                DateUtil.divideDuration(dateRange.start(), dateRange.end(), divideBy);
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
        if (isDurationNotGreaterThanMax(dateRange,
                divideBy,
                maxHourDifferencePerCountRequest,
                maxDayDifferencePerCountRequest,
                maxMonthDifferencePerCountRequest)) {
            return queryBetweenDate(dateRange,
                    divideBy,
                    function,
                    areGroupMessages,
                    areSystemMessages);
        }
        return Mono
                .error(new HttpResponseException(ResponseStatusCode.ADMIN_REQUESTS_TOO_FREQUENT));
    }

    public Mono<List<StatisticsRecordDTO>> checkAndQueryBetweenDate(
            DateRange dateRange,
            DivideBy divideBy,
            Function<DateRange, Mono<Long>> function) {
        if (isDurationNotGreaterThanMax(dateRange,
                divideBy,
                maxHourDifferencePerCountRequest,
                maxDayDifferencePerCountRequest,
                maxMonthDifferencePerCountRequest)) {
            return queryBetweenDate(dateRange, divideBy, function);
        }
        return Mono
                .error(new HttpResponseException(ResponseStatusCode.ADMIN_REQUESTS_TOO_FREQUENT));
    }

    private Mono<List<StatisticsRecordDTO>> mergeStaticsRecords(
            List<Mono<StatisticsRecordDTO>> recordMonos) {
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
            case HOUR -> maxHourRanges == null
                    || calculateDuration(startDate, endDate, divideBy) <= maxHourRanges;
            case DAY -> maxDayRanges == null
                    || calculateDuration(startDate, endDate, divideBy) <= maxDayRanges;
            case MONTH -> maxMonthRanges == null
                    || calculateDuration(startDate, endDate, divideBy) <= maxMonthRanges;
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

}
