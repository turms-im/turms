export default class Calculate {
    static percentageChange(oldValue: number, newValue: number): string {
        if (oldValue === 0 && newValue !== 0) {
            return '∞';
        } else if (oldValue !== 0 && newValue === 0) {
            return '-∞';
        } else if (oldValue === newValue) {
            return '0%';
        } else if (oldValue < newValue) {
            return `${((newValue - oldValue) / oldValue * 100).toFixed(2)}%`;
        } else {
            return `-${((oldValue - newValue) / oldValue * 100).toFixed(2)}%`;
        }
    }
}