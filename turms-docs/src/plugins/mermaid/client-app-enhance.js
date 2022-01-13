import { defineClientAppEnhance } from '@vuepress/client';
import Mermaid from './mermaid.vue';

export default defineClientAppEnhance(({ app }) => {
    app.component('mermaid', Mermaid);
});