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
            :open="popconfirmOpen"
            :title="$t('confirmDeletion')"
            @openChange="onPopconfirmOpenChanged"
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
            <a-button
                class="content-action-group__export"
                :disabled="!hasRecords"
                :loading="exporting"
                type="primary"
                @click="fetchAndExport"
            >
                {{ $t('exportData') }}
            </a-button>
        </div>
        <modal-form
            v-if="actions.length"
            :members="members"
            :open="activeAction.open"
            :title="$t(activeAction.title)"
            :form-items="activeAction.fields"
            :form-state="activeAction.formState"
            :type="activeAction.type"
            :query-key="queryKey"
            :target-records="selectedRecords"
            :target-record-keys="selectedRecordKeys"
            :submit-url="activeAction.url ? activeAction.url : submitUrl"
            :size="activeAction.size"
            @hide="hideModalForm"
            @onRecordCreated="onRecordCreated"
            @onRecordsUpdated="onRecordsUpdated"
        />
    </div>
</template>

<script>
import ExportMixin from './export-mixin';
import ModalForm from './modal-form.vue';

export default {
    name: 'action-group',
    components: {
        ModalForm
    },
    mixins: [ExportMixin],
    props: {
        members: {
            type: Array,
            default: () => []
        },
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
        resourceUrl: {
            type: String,
            required: true
        },
        submitUrl: {
            type: String,
            required: true
        },
        selectedRecords: {
            type: Array,
            required: true
        },
        selectedRecordKeys: {
            type: Array,
            required: true
        },
        hasRecords: {
            type: Boolean,
            required: true
        }
    },
    emits: ['onRecordCreated', 'onRecordsUpdated', 'requestDelete'],
    data() {
        const activeAction = this.actions[0];
        return {
            activeAction: activeAction,
            popconfirmOpen: false
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
                action.open = true;
                this.activeAction = action;
            } else {
                this.$message.error(this.$t('noRecordsSelected'));
            }
        },
        hideModalForm(formState) {
            const action = this.activeAction;
            action.open = false;
            action.formState = formState;
        },
        onPopconfirmOpenChanged(open) {
            this.popconfirmOpen = open && this.hasSelectedRows;
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