<template>
    <a-card
        size="small"
        :bordered="true"
        :hoverable="true"
        class="statistics-card"
    >
        <p
            class="statistics-card__title ant-typography"
        >
            {{ title }}
            <a-tooltip
                v-if="tooltip"
                placement="right"
                :title="tooltip"
            >
                <question-circle-outlined class="statistics-card__title-icon" />
            </a-tooltip>
        </p>
        <h1 class="statistics-card__content">
            {{ content }}
        </h1>
        <div
            class="statistics-card__footer ant-typography ant-typography-secondary"
        >
            <caret-up-outlined
                v-if="direction > 0"
                class="statistics-card__footer-icon statistics-card__footer-icon__up"
            />
            <minus-outlined
                v-else-if="direction === 0"
                class="statistics-card__footer-icon statistics-card__footer-icon__middle"
            />
            <caret-down-outlined
                v-else-if="direction < 0"
                class="statistics-card__footer-icon statistics-card__footer-icon__down"
            />
            {{ footer }}
        </div>
    </a-card>
</template>

<script>
import {CaretDownOutlined, CaretUpOutlined, MinusOutlined, QuestionCircleOutlined} from '@ant-design/icons-vue';

export default {
    name: 'statistics-card',
    components: {
        QuestionCircleOutlined,
        CaretUpOutlined,
        MinusOutlined,
        CaretDownOutlined
    },
    props: {
        title: {
            type: String,
            default: ''
        },
        content: {
            type: [String, Number],
            required: true
        },
        tooltip: {
            type: String,
            default: ''
        },
        footer: {
            type: String,
            default: ''
        },
        direction: {
            type: Number,
            default: null
        }
    }
};
</script>

<style lang="scss" scoped>
.statistics-card {
    cursor: default;

    .statistics-card__title {
        text-align: center;
    }

    .statistics-card__title-icon {
        margin-left: 6px;
    }

    .statistics-card__content {
        text-align: center;
    }

    .statistics-card__footer {
        display: flex;
        align-items: center;
        justify-content: center;
        text-align: center;
    }

    .statistics-card__footer-icon {
        margin-right: 8px;
        font-size: 20px;
    }

    .statistics-card__footer-icon__up {
        color: #f00;
    }

    .statistics-card__footer-icon__middle {
        color: #00bfff;
    }

    .statistics-card__footer-icon__down {
        color: #3cb371;
    }
}
</style>
