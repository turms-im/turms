<template>
    <div class="content-action-group">
        <a-button
            v-for="action in actions"
            :key="action.id || action.title"
            type="primary"
            :disabled="action.type === 'UPDATE' && !hasSelectedRows"
            class="content-action-group__action"
            @click="showModalForm(action)"
        >
            {{ $t(action.buttonLabel || action.title) }}
        </a-button>
        <a-popconfirm
            v-if="!deletion.disabled"
            class="content-action-group__action"
            :visible="popconfirmVisible"
            :title="$t('confirmDeletion')"
            @visibleChange="onPopconfirmVisibleChanged"
            @confirm="deleteSelectedRows"
        >
            <a-button
                type="primary"
                danger
                :disabled="!hasSelectedRows"
            >
                {{ $t('deleteSelectedRecords') }}
            </a-button>
        </a-popconfirm>
        <div class="content-action-group__action">
            <export
                :resource-url="resourceUrl"
                :query-params="queryParams"
                :disabled="!hasRecords"
                :file-name="exportFileName"
                :transform="transform"
            />
        </div>
        <modal-form
            v-if="actions.length"
            :visible="activeAction.visible"
            :title="$t(activeAction.title)"
            :form-items="activeAction.fields"
            :type="activeAction.type"
            :query-key="queryKey"
            :target-record-keys="selectedRecordKeys"
            :submit-url="submitUrl"
            :size="activeAction.size"
            @hide="hideModalForm"
            @onRecordCreated="onRecordCreated"
            @onRecordsUpdated="onRecordsUpdated"
        />
    </div>
</template>

<script>
import Export from './export';
import ModalForm from './modal-form';

export default {
    name: 'action-group',
    components: {
        Export,
        ModalForm
    },
    props: {
        actions: {
            type: Array,
            required: true
        },
        deletion: {
            type: Object,
            required: true
        },
        queryKey: {
            type: String,
            required: true
        },
        queryParams: {
            type: Object,
            required: true
        },
        exportFileName: {
            type: String,
            required: true
        },
        resourceUrl: {
            type: String,
            required: true
        },
        submitUrl: {
            type: String,
            required: true
        },
        selectedRecordKeys: {
            type: Array,
            required: true
        },
        hasRecords: {
            type: Boolean,
            required: true
        },
        transform: {
            type: Function,
            default: null
        }
    },
    emits: ['onRecordCreated', 'onRecordsUpdated', 'requestDelete'],
    data() {
        const activeAction = this.actions[0];
        return {
            activeAction: activeAction,
            popconfirmVisible: false
        };
    },
    computed: {
        hasSelectedRows() {
            return this.selectedRecordKeys.length;
        }
    },
    methods: {
        deleteSelectedRows() {
            this.$emit('requestDelete', this.selectedRecordKeys);
        },
        showModalForm(action) {
            if (action.type === 'CREATE' || (action.type === 'UPDATE' && this.selectedRecordKeys.length)) {
                action.visible = true;
                this.activeAction = action;
            } else {
                this.$message.error(this.$t('noRecordsSelected'));
            }
        },
        hideModalForm() {
            this.activeAction.visible = false;
        },
        onPopconfirmVisibleChanged(visible) {
            this.popconfirmVisible = visible && this.hasSelectedRows;
        },
        onRecordsUpdated(recordKeys, updatedFields) {
            this.$emit('onRecordsUpdated', recordKeys, updatedFields);
        },
        onRecordCreated(record) {
            this.$emit('onRecordCreated', record);
        }
    }
};
</script>

<style lang="scss">
.content-action-group {
    display: flex;
    flex-wrap: wrap;

    &__action {
        display: flex;
        flex-wrap: wrap;
        margin-right: 18px;
        margin-bottom: 18px;
    }
}

</style>