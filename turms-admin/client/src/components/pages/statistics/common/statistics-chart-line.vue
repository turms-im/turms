<template>
    <div class="statistics-chart-line" />
</template>
<script>
const G2 = require('@antv/g2/lib/core');
require('@antv/g2/lib/geom/line');
require('@antv/g2/lib/geom/point');
G2.Global.renderer = 'svg';
export default {
    name: 'statistics-chart-line',
    props: {
        statisticsData: {
            type: Array,
            default: function () {
                return [];
            }
        },
        isMonthMode: {
            type: Boolean,
            required: true
        }
    },
    watch: {
        statisticsData: {
            handler() {
                this.update();
            },
            deep: true,
            immediate: true
        }
    },
    mounted() {
        window.addEventListener('resize', this.onResize);
    },
    beforeDestroy() {
        window.removeEventListener('resize', this.onResize);
    },
    methods: {
        onResize() {
            this.forceFit();
        },
        forceFit() {
            if (this.chart && this.$el.getBoundingClientRect().width) {
                this.chart.forceFit();
            }
        },
        update() {
            if (this.statisticsData.length > 0) {
                const records = JSON.parse(JSON.stringify(this.statisticsData))
                    .flatMap(record => {
                        const results = [];
                        Object.entries(record).forEach(entry => {
                            const key = entry[0];
                            if (key !== 'date') {
                                const result = {};
                                const value = entry[1];
                                result.date = record['date'];
                                result.type = this.$t(`${key}Number`);
                                result.number = value;
                                results.push(result);
                            }
                        });
                        return results;
                    });
                let format;
                if (this.isMonthMode) {
                    format = 'Y-MM';
                } else {
                    const isSameYear = this.$moment(records[0].date).year() === this.$moment(records[records.length - 1].date).year();
                    format = isSameYear ? 'MM-DD' : 'Y-MM-DD';
                }
                for (const record of records) {
                    record.date = this.$moment(record.date).format(format);
                }
                if (this.chart) {
                    this.chart.changeData(records);
                } else {
                    const scale = {
                        date: {
                            type: 'cat'
                        },
                        number: {
                            type: 'linear',
                            minTickInterval: 1
                        }
                    };
                    this.chart = new G2.Chart({
                        container: this.$el,
                        forceFit: false
                    });
                    this.chart.source(records, scale);
                    this.chart.tooltip({
                        crosshairs: {
                            type: 'line'
                        }
                    });
                    this.chart.legend({
                        textStyle: {
                            fontSize: '18'
                        }
                    });
                    this.chart.line()
                        .shape('hv')
                        .position('date*number')
                        .color('type');
                    this.chart.forceFit();
                }
            }
        },
        refresh() {
            if (this.chart) {
                setTimeout(() => {
                    this.forceFit();
                });
            }
        }
    }
};
</script>