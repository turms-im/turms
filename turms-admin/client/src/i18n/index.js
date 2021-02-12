import {createI18n} from 'vue-i18n';
import messages from './langs';

const i18n = createI18n({
    locale: 'zh',
    messages
});

export default i18n;