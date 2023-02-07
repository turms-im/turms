import {createRouter, createWebHistory, type RouteRecordRaw} from 'vue-router';
import About from '../components/pages/about/index.vue';
import AccessControl from '../components/pages/access/index.vue';
import Blocklist from '../components/pages/blocklist/index.vue';
import ClientTerminal from '../components/pages/terminal/index.vue';
import ClusterConfig from '../components/pages/cluster/config/index.vue';
import ClusterDashboard from '../components/pages/cluster/dashboard/index.vue';
import ClusterFlightRecorder from '../components/pages/cluster/flight-recorder/index.vue';
import ClusterPlugin from '../components/pages/cluster/plugin/index.vue';
import ContentConversation from '../components/pages/content/conversation/index.vue';
import ContentGroup from '../components/pages/content/group/index.vue';
import ContentMessage from '../components/pages/content/message/index.vue';
import ContentUser from '../components/pages/content/user/index.vue';

const routes: RouteRecordRaw[] = [
    {
        path: '/',
        redirect: '/cluster/dashboard'
    },
    {
        path: '/cluster/config',
        component: ClusterConfig
    },
    {
        path: '/cluster/dashboard',
        component: ClusterDashboard
    },
    {
        path: '/cluster/flight-recorder',
        component: ClusterFlightRecorder
    },
    {
        path: '/cluster/plugin',
        component: ClusterPlugin
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
        path: '/blocklist',
        component: Blocklist
    },
    {
        path: '/access',
        component: AccessControl
    },
    {
        path: '/terminal',
        component: ClientTerminal
    },
    {
        path: '/about',
        component: About
    },
    {
        path: '/:pathMatch(.*)*',
        redirect: '/'
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;