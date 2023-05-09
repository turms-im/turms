const updateScrollMaxHeight = function () {
    // @ts-ignore
    const table = this.$refs.table?.$el;
    if (!table) {
        return;
    }
    const {top, height} = table.getBoundingClientRect();
    let tableBody = table.tableBody;
    if (!tableBody) {
        tableBody = table.querySelector('.ant-table-body');
        table.tableBody = tableBody;
    }
    const {height: bodyHeight} = tableBody.getBoundingClientRect();

    // @ts-ignore
    this.scrollMaxHeight = window.scrollY + window.innerHeight - top - height + bodyHeight - 30;
};

export default {
    methods: {
        refreshTableUi(): void {
            // We update the maxHeight of table by JS instead of CSS because:
            // To make a child div not overflow its parent while parent should not use "overflow"
            // (we don't want the parent displays the scrollbar),
            // we need to add "display: flex" to the parent and "flex-shrink: 1" to the child.
            // It works fine until the children are <table> and <tbody> because table elements will
            // become messy if we apply "flex" to them. So we use JS.

            // "setTimeout(() => updateScrollMaxHeight.call(this))" is more accurate,
            // but it will cause a jerk in the UI, so we don't use it
            updateScrollMaxHeight.call(this);
        }
    }
};