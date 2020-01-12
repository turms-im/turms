export default class Validator {

    static required(message) {
        return { required: true, message: message };
    }

    static onlyNumber(message) {
        return { validator: (rule, value, cb) => (!value || /^\d*$/.test(value) ? cb() : cb(false)), message: message };
    }

    static onlyNumberAndComma(message) {
        return { validator: (rule, value, cb) => (!value || /^(\d|,)*$/.test(value) ? cb() : cb(false)), message: message };
    }

    static noBlank(message) {
        return { validator: (rule, value, cb) => (/^\S*$/.test(value) ? cb() : cb(false)), message: message};
    }

    static maxNumber(max, message) {
        return { max: max, message: message };
    }

    static isUrl(message) {
        return { type: 'url', message: message};
    }

    static create(key, options, base) {
        const rules = [];
        if (options) {
            for (const entry of Object.entries(options)) {
                switch (entry[0]) {
                case 'required':
                    if (entry[1]) {
                        rules.push(Validator.required(Validator.vm.$t('fieldIsRequired')));
                    }
                    break;
                case 'onlyNumber':
                    if (entry[1]) {
                        rules.push(Validator.onlyNumber(Validator.vm.$t('fieldOnlyNumber')));
                    }
                    break;
                case 'onlyNumberAndComma':
                    if (entry[1]) {
                        rules.push(Validator.onlyNumberAndComma(Validator.vm.$t('fieldOnlyNumberAndComma')));
                    }
                    break;
                case 'noBlank':
                    if (entry[1]) {
                        rules.push(Validator.noBlank(Validator.vm.$t('fieldHasBlank')));
                    }
                    break;
                case 'maxNumber':
                    rules.push(Validator.maxNumber(Validator.vm.$t('fieldMaxLength', {number: entry[1]})));
                    break;
                case 'isUrl':
                    rules.push(Validator.isUrl(Validator.vm.$t('fieldMustBeUrl')));
                    break;
                }
            }
            return [key, Object.assign(base || {}, {rules})];
        } else {
            return [key, base || {}];
        }
    }
}