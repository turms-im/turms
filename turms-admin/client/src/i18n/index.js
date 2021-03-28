import {createI18n} from 'vue-i18n';
import en_US from './langs/en_US';
import zh_CN from './langs/zh_CN';

const i18n = createI18n({
    locale: 'en',
    messages: {
        en: en_US,
        'zh-cn': zh_CN
    }
});

export default i18n;