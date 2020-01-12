<template>
    <a-collapse
        accordion
        default-active-key="1"
    >
        <a-collapse-panel key="1">
            <template slot="header">
                <div class="daily-statistics-collapse__header">
                    <span>
                        {{ title }}
                    </span>
                    <div>
                        <refresh-button
                            class="daily-statistics-collapse__header__item"
                            :refresh-func="refreshFunc"
                        />
                        <export-button
                            class="daily-statistics-collapse__header__item"
                            :items="cards"
                            :file-name="fileName"
                            :worksheet-name="worksheetName"
                            :headers="headers"
                            :rows="rows"
                        />
                    </div>
                </div>
            </template>
            <a-row :gutter="16">
                <a-col
                    v-for="(card, index) in cards"
                    :key="card.title"
                    :span="6"
                >
                    <statistics-card
                        :title="card.title"
                        :content="card.content"
                        :footer="card.footer"
                        :direction="card.direction"
                        :tooltip="card.tooltip"
                        :style="index > 3? 'margin-top: 12px':''"
                    />
                </a-col>
            </a-row>
        </a-collapse-panel>
    </a-collapse>
</template>

<script>
import StatisticsCard from './statistics-card';
import RefreshButton from '../../../common/refresh-button';
import ExportButton from '../../../common/export-button';

export default {
    name: 'daily-statistics-collapse',
    components: {
        StatisticsCard,
        RefreshButton,
        ExportButton
    },
    props: {
        title: {
            type: String,
            required: true
        },
        date: {
            type: String,
            required: true
        },
        fileName: {
            type: String,
            required: true
        },
        worksheetName: {
            type: String,
            required: true
        },
        cards: {
            type: Array,
            required: true
        },
        refreshFunc: {
            type: Function,
            required: true
        }
    },
    computed: {
        headers() {
            const headers = [];
            const width = 25;
            headers.push({
                header: this.$t('date'),
                key: 1,
                width: width
            });
            for (let i = 0; i < this.cards.length; i++) {
                const item = this.cards[i];
                headers.push({
                    header: item.title,
                    key: i + 2,
                    width: width
                });
            }
            return headers;
        },
        rows() {
            const rows = [];
            const row = {};
            row[1] = this.$moment(this.date).format('Y-MM-DD');
            for (let i = 0; i < this.cards.length; i++) {
                const item = this.cards[i];
                row[i + 2] = item.content;
            }
            rows.push(row);
            return rows;
        }
    }
};
</script>
<style lang="scss" scoped>
    .daily-statistics-collapse__header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding-right: 18px;

        &__item {
            margin-left: 16px;
        }
    }
</style>