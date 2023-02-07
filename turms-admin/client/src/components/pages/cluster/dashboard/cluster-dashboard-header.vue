<template>
    <div class="cluster-dashboard-header turms-card">
        <div class="cluster-dashboard-header__card-container">
            <div
                v-for="card in overviewCards"
                :key="card.title"
                class="cluster-dashboard-header__card"
            >
                <div class="cluster-dashboard-header__card-title">
                    {{ card.title }}
                    <a-tooltip
                        v-if="card.info"
                        placement="top"
                        :title="card.info"
                    >
                        <span class="cluster-config-item__info">
                            <icon type="question-circle" />
                        </span>
                    </a-tooltip>
                </div>
                <div class="cluster-dashboard-header__card-content">
                    {{ card.content }}
                </div>
            </div>
        </div>
        <div class="cluster-dashboard-header__last-updated-time">
            <div class="cluster-dashboard-header__card-title">
                {{ $t('lastUpdatedTime') }}
            </div>
            <div class="cluster-dashboard-header__card-content">
                {{ lastUpdatedTime }}
            </div>
        </div>
    </div>
</template>

<script>
import Icon from '../../../common/icon.vue';

export default {
    name: 'cluster-dashboard-header',
    components: {
        Icon
    },
    props: {
        members: {
            type: Array,
            required: true
        },
        onlineUsers: {
            type: Number,
            required: true
        },
        lastUpdatedTime: {
            type: String,
            required: true
        }
    },
    computed: {
        overviewCards() {
            let gatewayNumber = 0;
            let serviceNumber = 0;
            this.members.forEach(member => {
                if (member.nodeType.toUpperCase() === 'GATEWAY') {
                    gatewayNumber++;
                } else {
                    serviceNumber++;
                }
            });
            return [{
                title: this.$t('nodeNumber'),
                content: `${gatewayNumber} / ${serviceNumber}`,
                info: 'turms-gateway / turms-service'
            },
            {
                title: this.$t('onlineUserNumber'),
                content: this.onlineUsers
            }];
        }
    }
};
</script>

<style lang="scss">
.cluster-dashboard-header {
    display: flex;
    justify-content: space-between;
    height: 82px;
    padding: 12px 32px;

    // Disable the default padding of the container
    // TODO: use variables
    margin: -24px -24px 0;
    background: #fff;

    &__card-container {
        display: flex;
    }

    &__card {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: space-between;
        margin-right: 64px;

        &-title {
            font-size: 16px;
            color: #697b85;
        }

        &-content {
            font-size: 18px;
            font-weight: 600;
        }
    }

    &__last-updated-time {
        display: flex;
        flex-direction: column;
        align-items: flex-end;
        justify-content: space-between;

        .cluster-dashboard-header__card-content {
            font-size: 16px;
        }
    }
}
</style>