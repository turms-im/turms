<template>
    <div
        v-show="isVisible"
        class="client-terminal-autocomplete"
    >
        <div
            v-for="(item, i) in items"
            :key="item.name"
            class="client-terminal-autocomplete__item"
            :class="{
                'client-terminal-autocomplete__item--selected': i === selectedItemIndex
            }"
            @click="selectItem(item)"
        >
            {{ item.name }}
        </div>
    </div>
</template>

<script>

const MAX_VISIBLE_ITEM_COUNT = 10;
const ITEM_HEIGHT = 24;

export default {
    name: 'autocomplete',
    props: {
        items: {
            type: Array,
            required: true
        }
    },
    emits: ['selectItem'],
    data() {
        return {
            selectedItemIndex: 0
        };
    },
    computed: {
        isVisible() {
            return this.items.length;
        }
    },
    methods: {
        updatePosition(x, y) {
            this.$el.style.left = `${x}px`;
            this.$el.style.top = `${y}px`;
        },
        select() {
            this.selectItem(this.items[this.selectedItemIndex]);
        },
        selectItem(item) {
            this.selectedItemIndex = 0;
            this.$emit('selectItem', item);
        },
        scrollUp() {
            const selectedItemIndex = this.selectedItemIndex - 1;
            this.selectedItemIndex = selectedItemIndex < 0 ? this.items.length - 1 : selectedItemIndex;
            this.scroll(true);
        },
        scrollDown() {
            const selectedItemIndex = this.selectedItemIndex + 1;
            this.selectedItemIndex = selectedItemIndex < this.items.length ? selectedItemIndex : 0;
            this.scroll(false);
        },
        scroll(up) {
            if (!this.isItemVisible(this.selectedItemIndex)) {
                const y = up
                    ? ITEM_HEIGHT * this.selectedItemIndex
                    : ITEM_HEIGHT * (this.selectedItemIndex - MAX_VISIBLE_ITEM_COUNT + 1);
                this.$el.scrollTo(0, y);
            }
        },
        isItemVisible(index) {
            const item = this.$el.querySelectorAll('.client-terminal-autocomplete__item')[index];
            const elementRect = item.getBoundingClientRect();
            const parentRect = this.$el.getBoundingClientRect();
            return (
                elementRect.top >= parentRect.top &&
                elementRect.right >= parentRect.left &&
                elementRect.top + elementRect.height <= parentRect.bottom &&
                elementRect.left + elementRect.width <= parentRect.right
            );
        }
    }
};
</script>

<style lang="scss">
.client-terminal-autocomplete {
    position: absolute;
    z-index: 100;
    width: 400px;
    max-height: 240px;
    overflow-y: auto;
    background-color: #454545;
    border-radius: 2px;

    &__item {
        display: flex;
        align-items: center;
        height: 24px;
        padding: 0 8px;
        font-family: Consolas, Monaco, serif;
        color: #fff;
        cursor: pointer;

        &:hover {
            background-color: #113a5c;
        }

        &--selected {
            background-color: #515457;
        }
    }

    &::-webkit-scrollbar-thumb {
        background: #bfbfbf;
        border-radius: 0;
    }
}
</style>