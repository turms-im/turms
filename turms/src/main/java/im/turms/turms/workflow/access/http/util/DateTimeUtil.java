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

package im.turms.turms.workflow.access.http.util;

import im.turms.common.constant.DivideBy;
import im.turms.server.common.cluster.node.Node;
import im.turms.turms.bo.DateRange;
import im.turms.turms.workflow.access.http.dto.response.StatisticsRecordDTO;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.function.Function3;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.function.Function;

/**
 * @author James Chen
 */
@Component
public class DateTimeUtil {

    private final Node node;

    public DateTimeUtil(Node node) {
        this.node = node;
    }

    public boolean checkRangesNumber(
            @NotNull DateRange dateRange,
            @NotNull DivideBy divideBy,
            @Nullable Integer maxHourRanges,
            @Nullable Integer maxDayRanges,
            @Nullable Integer maxMonthRanges) {
        Date startDate = dateRange.getStart();
        Date endDate = dateRange.getStart();
        switch (divideBy) {
            case HOUR:
                return maxHourRanges == null || getRangesNumber(startDate, endDate, divideBy) <= maxHourRanges;
            case DAY:
                return maxDayRanges == null || getRangesNumber(startDate, endDate, divideBy) <= maxDayRanges;
            case MONTH:
                return maxMonthRanges == null || getRangesNumber(startDate, endDate, divideBy) <= maxMonthRanges;
            case NOOP:
            default:
                return true;
        }
    }

    public Integer getRangesNumber(
            @NotNull Date startDate,
            @NotNull Date endDate,
            @NotNull DivideBy divideBy) {
        long differenceMillis = endDate.getTime() - startDate.getTime();
        switch (divideBy) {
            case HOUR:
                return (int) Math.ceil((double) differenceMillis / 3600000);
            case DAY:
                return (int) Math.ceil((double) differenceMillis / 86400000);
            case MONTH:
                return (int) Math.ceil((double) differenceMillis / 2629746000L);
            case NOOP:
            default:
                return 1;
        }
    }

    public List<Pair<Date, Date>> divide(
            @NotNull Date startDate,
            @NotNull Date endDate,
            @NotNull DivideBy divideBy) {
        if (!endDate.after(startDate)) {
            return Collections.emptyList();
        } else {
            if (startDate.getTime() == endDate.getTime()) {
                return Collections.emptyList();
            } else {
                int unit;
                switch (divideBy) {
                    case HOUR:
                        unit = Calendar.HOUR_OF_DAY;
                        break;
                    case DAY:
                        unit = Calendar.DAY_OF_YEAR;
                        break;
                    case MONTH:
                        unit = Calendar.MONTH;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + divideBy);
                }
                List<Pair<Date, Date>> lists = new LinkedList<>();
                while (true) {
                    // Note: Do not use Instant because it doesn't support plussing months
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(startDate);
                    if (unit == Calendar.MONTH) {
                        Calendar nextMonth = Calendar.getInstance();
                        nextMonth.setTime(startDate);
                        nextMonth.add(Calendar.MONTH, 1);
                        int days = nextMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
                        calendar.add(Calendar.DAY_OF_YEAR, days);
                    } else {
                        calendar.add(unit, 1);
                    }
                    Date currentEndDate = calendar.getTime();
                    if (endDate.before(currentEndDate)) {
                        break;
                    } else {
                        Pair<Date, Date> datePair = Pair.of(startDate, currentEndDate);
                        lists.add(datePair);
                        startDate = currentEndDate;
                    }
                }
                return lists;
            }
        }
    }

    //TODO: moves to somewhere more suitable
    public Mono<List<StatisticsRecordDTO>> queryBetweenDate(
            @NotNull DateRange dateRange,
            @NotNull DivideBy divideBy,
            @NotNull Function3<DateRange, Boolean, Boolean, Mono<Long>> function,
            @Nullable Boolean areGroupMessages,
            @Nullable Boolean areSystemMessages) {
        List<Pair<Date, Date>> dates = divide(dateRange.getStart(), dateRange.getEnd(), divideBy);
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
        return merge(monos);
    }

    public Mono<List<StatisticsRecordDTO>> queryBetweenDate(
            @NotNull DateRange dateRange,
            @NotNull DivideBy divideBy,
            @NotNull Function<DateRange, Mono<Long>> function) {
        List<Pair<Date, Date>> dates = divide(dateRange.getStart(), dateRange.getEnd(), divideBy);
        List<Mono<StatisticsRecordDTO>> monos = new ArrayList<>(dates.size());
        for (Pair<Date, Date> datePair : dates) {
            DateRange range = DateRange.of(datePair.getLeft(), datePair.getRight());
            Mono<StatisticsRecordDTO> result = function.apply(range)
                    .map(total -> new StatisticsRecordDTO(datePair.getLeft(), total));
            monos.add(result);
        }
        return merge(monos);
    }

    public Mono<List<StatisticsRecordDTO>> checkAndQueryBetweenDate(
            @NotNull DateRange dateRange,
            @NotNull DivideBy divideBy,
            @NotNull Function3<DateRange, Boolean, Boolean, Mono<Long>> function,
            @Nullable Boolean areGroupMessages,
            @Nullable Boolean areSystemMessages) {
        int maxHourRanges = node.getSharedProperties()
                .getService().getAdminApi().getMaxHourDifferencePerCountRequest();
        int maxDayRanges = node.getSharedProperties()
                .getService().getAdminApi().getMaxDayDifferencePerCountRequest();
        int maxMonthRanges = node.getSharedProperties()
                .getService().getAdminApi().getMaxMonthDifferencePerCountRequest();
        boolean checked = checkRangesNumber(dateRange, divideBy,
                maxHourRanges, maxDayRanges, maxMonthRanges);
        if (checked) {
            return queryBetweenDate(dateRange, divideBy, function, areGroupMessages, areSystemMessages);
        } else {
            throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS);
        }
    }

    public Mono<List<StatisticsRecordDTO>> checkAndQueryBetweenDate(
            @NotNull DateRange dateRange,
            @NotNull DivideBy divideBy,
            @NotNull Function<DateRange, Mono<Long>> function) {
        int maxHourRanges = node.getSharedProperties()
                .getService().getAdminApi().getMaxHourDifferencePerCountRequest();
        int maxDayRanges = node.getSharedProperties()
                .getService().getAdminApi().getMaxDayDifferencePerCountRequest();
        int maxMonthRanges = node.getSharedProperties()
                .getService().getAdminApi().getMaxMonthDifferencePerCountRequest();
        boolean checked = checkRangesNumber(dateRange, divideBy,
                maxHourRanges, maxDayRanges, maxMonthRanges);
        if (checked) {
            return queryBetweenDate(dateRange, divideBy, function);
        } else {
            throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS);
        }
    }

    private Mono<List<StatisticsRecordDTO>> merge(List<Mono<StatisticsRecordDTO>> monos) {
        return Flux.merge(monos)
                .collectSortedList((o1, o2) -> {
                    Date date1 = o1.getDate();
                    Date date2 = o2.getDate();
                    if (date1.before(date2)) {
                        return -1;
                    } else if (date1.after(date2)) {
                        return 1;
                    }
                    return 0;
                });
    }

}
