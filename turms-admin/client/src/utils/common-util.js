export default class CommonUtil {

    static splitByCapitals(text) {
        return text.split(/(?=[A-Z])/).join(' ');
    }

    static upperFirst(text) {
        return text.charAt(0).toUpperCase() + text.slice(1);
    }

}