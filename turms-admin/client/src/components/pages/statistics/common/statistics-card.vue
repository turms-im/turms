<template>
    <a-card
        size="small"
        bordered
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
                <a-icon
                    type="question-circle"
                    class="statistics-card__title-icon"
                />
            </a-tooltip>
        </p>
        <h1 class="statistics-card__content">
            {{ content }}
        </h1>
        <div
            class="statistics-card__footer ant-typography ant-typography-secondary"
        >
            <a-icon
                v-if="direction > 0"
                type="caret-up"
                class="statistics-card__footer-icon statistics-card__footer-icon__up"
            />
            <a-icon
                v-else-if="direction === 0"
                type="minus"
                class="statistics-card__footer-icon statistics-card__footer-icon__middle"
            />
            <a-icon
                v-else-if="direction < 0"
                type="caret-down"
                class="statistics-card__footer-icon statistics-card__footer-icon__down"
            />
            {{ footer }}
        </div>
    </a-card>
</template>

<script>
export default {
    name: 'statistics-card',
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
            text-align: center;
            justify-content: center;
            align-items: center;
        }

        .statistics-card__footer-icon {
            font-size: 20px;
            margin-right: 8px;
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
