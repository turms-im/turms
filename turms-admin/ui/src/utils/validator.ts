import IpRegex from 'ip-regex';

const WEBSOCKET_URL_REGEX = new RegExp('^(?:(?:ws|wss)://|//)(?:\\S+(?::\\S*)?@)?(?:(?:(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[0-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z\\u00a1-\\uffff0-9]+-*)*[a-z\\u00a1-\\uffff0-9]+)(?:\\.(?:[a-z\\u00a1-\\uffff0-9]+-*)*[a-z\\u00a1-\\uffff0-9]+)*(?:\\.(?:[a-z\\u00a1-\\uffff]{2,})))|localhost)(?::\\d{2,5})?(?:(/|\\?|#)[^\\s]*)?$', 'i');

interface Rule {
    messageId?: string,
    message?: string,
    value: any
}

export default class Validator {

    static getMessage: (id: string, args?: Record<string, string>) => string;

    static parseRuleList(rules: Array<Rule>) {
        return rules.map(rule => ({
            ...rule,
            message: rule.messageId ? Validator.getMessage(rule.messageId, rule.value) : rule.message
        }));
    }

    static parseRules(rules: Array<Rule> | Record<string, Array<Rule>>): Array<Rule> {
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

    static required(messageId: string) {
        return {
            validator: (rule, value) => {
                return value == null ? Promise.reject(Validator.getMessage(messageId)) : Promise.resolve();
            }
        };
    }

    static onlyNumber(messageId: string) {
        return {
            validator: (rule, value) => (!value || /^\d*$/.test(value) ? Promise.resolve() : Promise.reject(Validator.getMessage(messageId)))
        };
    }

    static onlyNumberAndComma(messageId: string) {
        return {
            validator: (rule, value) => (!value || /^(\d|,)*$/.test(value) ? Promise.resolve() : Promise.reject(Validator.getMessage(messageId)))
        };
    }

    static noBlank(messageId: string) {
        return {
            validator: (rule, value) => (/^\S*$/.test(value) ? Promise.resolve() : Promise.reject(Validator.getMessage(messageId)))
        };
    }

    static maxNumber(messageId: string, value, max) {
        return {
            messageId,
            value,
            max
        };
    }

    static isIp(messageId: string) {
        return {
            validator: (rule, value) => IpRegex({exact: true}).test(value)
                ? Promise.resolve()
                : Promise.reject(Validator.getMessage(messageId))
        };
    }

    static isUrl(messageId: string) {
        return {
            type: 'url',
            messageId
        };
    }

    static isWsUrl(messageId: string) {
        return {
            validator: (rule, value) => WEBSOCKET_URL_REGEX.test(value)
                ? Promise.resolve()
                : Promise.reject(Validator.getMessage(messageId))
        };
    }

    static create(options: Record<string, any>, baseRules) {
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
                case 'isWsUrl':
                    rules.push(Validator.isWsUrl('fieldMustBeWsUrl'));
                    break;
                default:
                    throw new Error(`Cannot create a rule for the unknown type ${type}`);
            }
        }
        return rules.concat(baseRules || []);
    }

}