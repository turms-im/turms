<template>
    <div>
        <daily-statistics-collapse
            :file-name="fileName"
            :worksheet-name="worksheetName"
            :title="title"
            :cards="parsedCards"
            :refresh-func="updateCount"
            :date="$date().add(-1, 'days').startOf('day').format()"
        />
        <statistics-number-collapse
            ref="numberPanel"
            class="statistics-number-collapse"
            :url="url"
            :name="name"
            :params="params"
            :worksheet-name="worksheetName"
            :title="dailyStatisticsPanelTitle"
            :options="cardOptions"
            :resources="resources.all"
        />
    </div>
</template>

<script>
import DailyStatisticsCollapse from './daily-statistics-collapse.vue';
import StatisticsNumberCollapse from './statistics-number-collapse.vue';

export default {
    name: 'statistics-template',
    components: {
        DailyStatisticsCollapse,
        StatisticsNumberCollapse
    },
    props: {
        name: {
            type: String,
            required: true
        },
        nameLabel: {
            type: String,
            required: true
        },
        path: {
            type: String,
            required: true
        },
        url: {
            type: String,
            required: true
        },
        params: {
            type: Object,
            default: () => ({})
        },
        resources: {
            type: Object,
            default: () => ({})
        },
        keyMap: {
            type: Object,
            default: () => ({})
        },
        cards: {
            type: Array,
            default: () => []
        }
    },
    data() {
        const yesterday = this.$date()
            .subtract(1, 'days')
            .format('YYYY-MM-DD');
        const title = `昨日${this.nameLabel}数统计 ${yesterday}`;
        const parsedCards = this.cards.map(card => {
            card.title = card.title || this.$t(`${card.id}Number`);
            card.content = '- -';
            card.footer = '较前日- -';
            return card;
        });
        const cardOptions = this.cards
            .filter(value => !value.excluded)
            .map(value => {
                return {
                    value: value.id,
                    label: value.title
                };
            });
        return {
            title,
            parsedCards,
            cardOptions,
            fileName: `turms-statistics-${this.name}(${yesterday})`,
            worksheetName: `${this.name}`,
            dailyStatisticsPanelTitle: `${this.nameLabel}数统计`,
            initialized: false
        };
    },
    computed: {
        tab() {
            return this.$store.getters.tab;
        },
        admin() {
            return this.$store.getters.admin;
        }
    },
    watch: {
        tab() {
            this.refresh();
        },
        admin() {
            if (this.admin) {
                this.updateCount();
            }
        }
    },
    mounted() {
        if (this.admin) {
            this.updateCount();
        }
    },
    methods: {
        refresh() {
            this.$refs.numberPanel.refresh();
        },
        renameKey(object, oldKey, newKey) {
            delete Object.assign(object, {[newKey]: object[oldKey]})[oldKey];
        },
        updateCount() {
            if (!this.$store.getters.admin) {
                return Promise.reject();
            }
            const todayStart = this.$date().startOf('day').format();
            const yesterdayStart = this.$date().add(-1, 'days').startOf('day').format();
            const dayBeforeYesterdayStart = this.$date().add(-2, 'days').startOf('day').format();
            const promises = [
                this.fetchAllCounts(yesterdayStart, todayStart),
                this.fetchAllCounts(dayBeforeYesterdayStart, yesterdayStart)
            ];
            const shouldCountBeforeDay = this.resources.beforeDay?.length > 0;
            if (shouldCountBeforeDay) {
                promises.push(this.fetchNumberBeforeDay(todayStart),
                    this.fetchNumberBeforeDay(yesterdayStart));
            }
            return Promise.all(promises)
                .then(responses => {
                    const results = responses.flatMap(response => response.data.data);
                    if (shouldCountBeforeDay && this.keyMap?.beforeDay) {
                        Object.entries(this.keyMap.beforeDay).forEach(entry => {
                            this.renameKey(results[2], entry[0], entry[1]);
                            this.renameKey(results[3], entry[0], entry[1]);
                        });
                    }
                    const allCountsResult = shouldCountBeforeDay ? Object.assign(results[0], results[2]) : results[0];
                    const beforeYesterdayCountsResult = shouldCountBeforeDay ? Object.assign(results[1], results[3]) : results[1];
                    this.parsedCards = this.parsedCards.map(card => {
                        const totalCountValue = allCountsResult[card.id];
                        if (totalCountValue != null) {
                            card.content = totalCountValue;
                        }
                        const beforeYesterdayCountValue = beforeYesterdayCountsResult[card.id];
                        if (beforeYesterdayCountValue != null) {
                            const yesterdayCount = parseInt(card.content);
                            const percent = this.$cal.percentageChange(beforeYesterdayCountValue, yesterdayCount);
                            let excludedItems = [];
                            if (shouldCountBeforeDay && this.keyMap && this.keyMap.beforeDay) {
                                excludedItems = Object.values(this.keyMap.beforeDay);
                            }
                            if (!excludedItems.includes(card.id)) {
                                card.footer = `较前日 ${percent} (${beforeYesterdayCountValue})`;
                                card.direction = yesterdayCount === beforeYesterdayCountValue ? 0 : (yesterdayCount > beforeYesterdayCountValue ? 1 : -1);
                            }
                        }
                        return card;
                    });
                    this.initialized = true;
                    return responses;
                });
        },
        fetchAllCounts(startDate, endDate) {
            const params = this.$rq.generateDateRangeParams(this.resources.all, startDate, endDate);
            Object.assign(params, this.params || {});
            return this.$http.get(this.url, {params});
        },
        fetchNumberBeforeDay(endDate) {
            const params = this.$rq.generateDateRangeParams(this.resources.beforeDay, null, endDate);
            Object.assign(params, this.params || {});
            return this.$http.get(this.url, {params});
        }
    }
};
</script>
<style scoped>
.statistics-number-collapse {
    margin-top: 16px;
}
</style>