import Vue from 'vue';
import VueRouter from 'vue-router';
import ContentUser from '../components/pages/content/user/index';
import ContentGroup from '../components/pages/content/group/index';
import ContentConversation from '../components/pages/content/conversation/index';
import ContentMessage from '../components/pages/content/message/index';
import AccessControl from '../components/pages/access/index';
import ClusterManagement from '../components/pages/cluster/index';
import About from '../components/pages/about/index';

Vue.use(VueRouter);

const router = new VueRouter({
    mode: 'history',
    routes: [
        {
            path: '/',
            redirect: '/content/user'
        },
        // TODO: We hide the statistics pages because the server side isn't ready for it.
        //  And we will integrate Flink to do the job later.
        // {
        //     path: '/',
        //     redirect: '/statistics/user'
        // },
        // {
        //     path: '/statistics/user',
        //     component: StatisticsUserPane
        // },
        // {
        //     path: '/statistics/group',
        //     component: StatisticsGroupPane
        // },
        // {
        //     path: '/statistics/message',
        //     component: StatisticsMessage
        // },
        {
            path: '/content/user',
            component: ContentUser
        },
        {
            path: '/content/group',
            component: ContentGroup
        },
        {
            path: '/content/conversation',
            component: ContentConversation
        },
        {
            path: '/content/message',
            component: ContentMessage
        },
        {
            path: '/access',
            component: AccessControl
        },
        {
            path: '/cluster',
            component: ClusterManagement
        },
        {
            path: '/about',
            component: About
        },
        {
            path: '*',
            redirect: '/'
        }
    ]
});
export default router;