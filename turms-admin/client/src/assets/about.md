# turms-admin

turms-admin是一个专门为turms量身打造的兼有内容管理（CMS）与集群配置管理的单页面应用（SPA）。其中，CMS部分包括了数据统计、业务内容管理与权限控制三大版块。

## 架构概要
为了便于开发者进行运维与部署，turms-admin默认采用单体架构部署，其自身前后端一体。
turms-admin的前端部分采用ant-design-vue为UI框架。
而其服务端部分则以Node.js为底，采用express作为其Web服务端框架，并默认搭载PM2进行turms-admin的进程管理。

特别值得一提的是，由于turms-admin的服务端部分被设计为仅用于对外提供turms-admin的前端静态资源，因此您完全可以不采用turms-admin自带的服务端，并以Nginx、CDN等技术取而代之。

## Quick Start

1. 安装[Node.js](https://nodejs.org/en)
2. 执行npm run quickstart指令（该指令由npm install && npm run build && npm run start组成，包括了依赖包安装、前端构建与服务端执行）。等待PM2提示turms-admin的status为online（表明turms-admin服务端进程已启动）
3. 打开浏览器，并输入http://localhost:9512/

## 常用运维指令

start：执行turms-admin服务端进程
stop：终止turms-admin服务端进程
delete：终止turms-admin服务端进程，并删除其在PM2的进程记录
restart：重启turms-admin服务端
reload：重新加载turms-admin服务端配置

更多指令与服务端配置，可参考[PM2文档](https://pm2.keymetrics.io/docs/usage/pm2-doc-single-page)