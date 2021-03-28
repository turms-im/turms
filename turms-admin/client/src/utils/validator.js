export default class Validator {

    static getMessage;

    static parseRuleList(rules) {
        const newRules = [];
        for (const rule of rules) {
            const newRule = JSON.parse(JSON.stringify(rule));
            newRule.validator = rule.validator;
            if (newRule.messageId) {
                newRule.message = Validator.getMessage(rule.messageId, rule.value);
            }
            newRules.push(newRule);
        }
        return newRules;
    }

    static parseRules(rules) {
        const newRules = {};
        if (rules instanceof Array) {
            return Validator.parseRuleList(rules);
        }
        if (rules) {
            Object.entries(rules).forEach(([name, ruleList]) => {
                newRules[name] = Validator.parseRuleList(ruleList);
            });
        }
        return newRules;
    }

    static required(messageId) {
        return {
            required: true,
            messageId
        };
    }

    static onlyNumber(messageId) {
        return {
            validator: (rule, value) => (!value || /^\d*$/.test(value) ? Promise.resolve() : Promise.reject(Validator.getMessage(messageId)))
        };
    }

    static onlyNumberAndComma(messageId) {
        return {
            validator: (rule, value) => (!value || /^(\d|,)*$/.test(value) ? Promise.resolve() : Promise.reject(Validator.getMessage(messageId)))
        };
    }

    static noBlank(messageId) {
        return {
            validator: (rule, value) => (/^\S*$/.test(value) ? Promise.resolve() : Promise.reject(Validator.getMessage(messageId)))
        };
    }

    static maxNumber(messageId, value, max) {
        return {
            messageId,
            value,
            max
        };
    }

    static isUrl(messageId) {
        return {
            type: 'url',
            messageId
        };
    }

    static create(options, baseRules) {
        const rules = [];
        if (options) {
            for (const [type, value] of Object.entries(options)) {
                switch (type) {
                case 'required':
                    if (value) {
                        rules.push(Validator.required('fieldIsRequired'));
                    }
                    break;
                case 'onlyNumber':
                    if (value) {
                        rules.push(Validator.onlyNumber('fieldOnlyNumber'));
                    }
                    break;
                case 'onlyNumberAndComma':
                    if (value) {
                        rules.push(Validator.onlyNumberAndComma('fieldOnlyNumberAndComma'));
                    }
                    break;
                case 'noBlank':
                    if (value) {
                        rules.push(Validator.noBlank('fieldHasBlank'));
                    }
                    break;
                case 'maxNumber':
                    rules.push(Validator.maxNumber('fieldMaxLength', {number: value}, value));
                    break;
                case 'isUrl':
                    rules.push(Validator.isUrl('fieldMustBeUrl'));
                    break;
                }
            }
        }
        return rules.concat(baseRules || []);
    }

}