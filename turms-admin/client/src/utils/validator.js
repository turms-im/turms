export default class Validator {

    static getMessage;

    static required(message) {
        return {
            required: true,
            message: message
        };
    }

    static onlyNumber(message) {
        return {
            validator: (rule, value) => (!value || /^\d*$/.test(value) ? Promise.resolve() : Promise.reject(message))
        };
    }

    static onlyNumberAndComma(message) {
        return {
            validator: (rule, value) => (!value || /^(\d|,)*$/.test(value) ? Promise.resolve() : Promise.reject(message))
        };
    }

    static noBlank(message) {
        return {
            validator: (rule, value) => (/^\S*$/.test(value) ? Promise.resolve() : Promise.reject(message))
        };
    }

    static maxNumber(max, message) {
        return {
            max,
            message
        };
    }

    static isUrl(message) {
        return {
            type: 'url',
            message
        };
    }

    static create(options, baseRules) {
        const rules = [];
        if (options) {
            for (const [type, value] of Object.entries(options)) {
                switch (type) {
                case 'required':
                    if (value) {
                        rules.push(Validator.required(Validator.getMessage('fieldIsRequired')));
                    }
                    break;
                case 'onlyNumber':
                    if (value) {
                        rules.push(Validator.onlyNumber(Validator.getMessage('fieldOnlyNumber')));
                    }
                    break;
                case 'onlyNumberAndComma':
                    if (value) {
                        rules.push(Validator.onlyNumberAndComma(Validator.getMessage('fieldOnlyNumberAndComma')));
                    }
                    break;
                case 'noBlank':
                    if (value) {
                        rules.push(Validator.noBlank(Validator.getMessage('fieldHasBlank')));
                    }
                    break;
                case 'maxNumber':
                    rules.push(Validator.maxNumber(Validator.getMessage('fieldMaxLength', {number: value})));
                    break;
                case 'isUrl':
                    rules.push(Validator.isUrl(Validator.getMessage('fieldMustBeUrl')));
                    break;
                }
            }
        }
        return rules.concat(baseRules || []);
    }

}