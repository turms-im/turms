export default class SystemUtil {
    static isBrowser(): boolean {
        return typeof window !== 'undefined' && typeof window.document !== 'undefined';
    }
}