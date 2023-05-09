<template>
    <div
        ref="container"
        class="chart-area"
    />
</template>
<script>

import {
    Chart,
    registerAction,
    registerComponentController,
    registerEngine,
    registerGeometry,
    registerInteraction
} from '@antv/g2/lib/core';
import Area from '@antv/g2/lib/geometry/area';
import Line from '@antv/g2/lib/geometry/line';
import Axis from '@antv/g2/lib/chart/controller/axis';
import Tooltip from '@antv/g2/lib/chart/controller/tooltip';
import TooltipAction from '@antv/g2/lib/interaction/action/component/tooltip/geometry';
import * as SvgEngine from '@antv/g-svg';

registerEngine('svg', SvgEngine);
registerGeometry('area', Area);
registerGeometry('line', Line);
registerComponentController('axis', Axis);
registerComponentController('tooltip', Tooltip);

registerAction('tooltip', TooltipAction);
registerInteraction('tooltip', {
    start: [
        { trigger: 'plot:mousemove', action: 'tooltip:show', throttle: { wait: 50, leading: true, trailing: false } },
        { trigger: 'plot:touchmove', action: 'tooltip:show', throttle: { wait: 50, leading: true, trailing: false } }
    ],
    end: [
        { trigger: 'plot:mouseleave', action: 'tooltip:hide' },
        { trigger: 'plot:leave', action: 'tooltip:hide' },
        { trigger: 'plot:touchend', action: 'tooltip:hide' }
    ]
});

export default {
    name: 'chart-area',
    props: {
        data: {
            type: Array,
            default: () => []
        },
        scale: {
            type: Object,
            required: true
        },
        position: {
            type: String,
            required: true
        },
        height: {
            type: Number,
            default: 150
        }
    },
    watch: {
        data: {
            handler() {
                this.updateUi();
            },
            deep: true
        }
    },
    mounted() {
        const chart = new Chart({
            container: this.$refs.container,
            autoFit: true,
            renderer: 'svg',
            height: this.height
        });
        chart.scale(this.scale)
            .tooltip({
                showCrosshairs: true,
                shared: true
            });
        chart.area()
            .adjust('stack')
            .position(this.position)
            .color('type');
        chart.line()
            .adjust('stack')
            .position(this.position)
            .color('type');
        this.chart = chart;
        const svg = this.$refs.container.querySelector('svg');
        // We need to add the extra height here otherwise
        // the axis labels will be cut
        const height = parseInt(svg.style.height);
        svg.style.height = `${height + 4}px`;
        this.updateUi();
    },
    unmounted() {
        this.chart.destroy();
    },
    methods: {
        updateUi() {
            if (this.chart) {
                this.chart.data(this.data);
                this.chart.render();
            }
        }
    }
};
</script>