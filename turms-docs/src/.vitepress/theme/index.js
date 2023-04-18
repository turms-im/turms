import DefaultTheme from 'vitepress/theme';
import registerMermaid from "../plugins/mermaid";
import '../styles/index.scss';

export default {
    extends: DefaultTheme,
    enhanceApp(ctx) {
        const app = ctx.app;
        registerMermaid(app);
    }
}