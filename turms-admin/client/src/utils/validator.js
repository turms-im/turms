import IpRegex from 'ip-regex';

export default class Validator {

    static getMessage;

    static parseRuleList(rules) {
        return rules.map(rule => ({
            ...rule,
            message: rule.messageId ? Validator.getMessage(rule.messageId, rule.value) : rule.message
        }));
    }

    static parseRules(rules) {
        if (rules instanceof Array) {
            return Validator.parseRuleList(rules);
        }
        if (typeof rules === 'object') {
            const newRules = {};
            Object.entries(rules).forEach(([name, ruleList]) => {
                newRules[name] = Validator.parseRuleList(ruleList);
            });
        }
    }

    static required(messageId) {
        return {
            validator: (rule, value) => value != null ? Promise.resolve() : Promise.reject(Validator.getMessage(messageId))
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

    static isIp(messageId) {
        return {
            validator: (rule, value) => {
                return IpRegex({exact: true}).test(value)
                    ? Promise.resolve()
                    : Promise.reject(Validator.getMessage(messageId))
            }
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
                case 'isIp':
                    rules.push(Validator.isIp('fieldMustBeIp'));
                    break;
                case 'isUrl':
                    rules.push(Validator.isUrl('fieldMustBeUrl'));
                    break;
                default:
                    throw new Error(`Cannot create a rule for the unknown type ${type}`);
            }
        }
        return rules.concat(baseRules || []);
    }

}