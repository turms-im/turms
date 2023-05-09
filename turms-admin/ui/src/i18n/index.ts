import {createI18n} from 'vue-i18n';
import en_US from './langs/en_US';
import ja_JP from './langs/ja_JP';
import zh_CN from './langs/zh_CN';

const i18n = createI18n({
    locale: 'en',
    legacy: false,
    messages: {
        en: en_US,
        ja: ja_JP,
        'zh-cn': zh_CN
    }
});

export default i18n;