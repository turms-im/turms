# turms-admin

turms-admin是一个为Turms项目定制的后台管理单页应用（SPA），具体包括：集群管理（集群监控、集群配置）、内容管理、客户端黑名单、权限控制、客户端终端，这五大版块。

注意：turms-admin的定位仅仅是Turms服务端Admin API的可视化Web应用，因此turms-admin自身不提供任何数据采集、数据分析与报警等功能。

## 部署概要

Turms采用了前后端分离设计，对于Turms的服务端而言，它们甚至不“知道”有`turms-admin`这个前端项目的存在。turms-admin的前端项目只是提供诸如JavaScript、CSS与图像等静态资源文件，因此用户甚至可以通过本地的静态HTML文件，直接在浏览器中打开turms-admin，并与Turms服务端进行交互。但为了方便开发者进行运维与部署，turms-admin项目也提供了以下两个部署方案。

### Docker镜像（推荐）

```shell
docker run -d -p 6510:6510 ghcr.io/turms-im/turms-admin
```

该镜像通过内置的Nginx服务端对外提供turms-admin的静态资源。您在运行完该指令后，就能访问`http://localhost:6510`页面了

### 简易Web服务端

turms-admin项目自身也提供了基于`Node.js`的简易Web服务端，这个Web服务端会通过HTTP接口，对外提供turms-admin的静态资源，并默认搭载PM2进行turms-admin的进程管理。

#### 安装与执行步骤

1. 安装[Node.js](https://nodejs.org/en)
2. 在`turms-admin`目录下，执行`npm run quickstart`指令，该指令由`npm install && npm run build && npm run start`组成，包括了依赖包安装、前端构建与服务端执行。等待PM2提示turms-admin的status为`online`，表明turms-admin服务端进程已启动
3. 打开浏览器，并访问`http://localhost:6510`页面

#### 常用运维指令

start：执行turms-admin服务端进程

stop：终止turms-admin服务端进程

delete：终止turms-admin服务端进程，并删除其在PM2的进程记录

restart：重启turms-admin服务端

reload：重新加载turms-admin服务端配置

更多指令与服务端配置，可参考[PM2文档](https://pm2.keymetrics.io/docs/usage/pm2-doc-single-page)

## 版块介绍

集群管理：

* 集群监控：查看集群的实时运行状态；查看某一个服务端的具体信息与度量数据
* 集群配置：该部分对应着Turms服务端的全局配置功能，可以零停机实时地修改Turms服务端配置
* 集群飞行记录器：管理集群各节点的飞行记录器
* 集群插件：管理集群各节点的插件

内容管理：增删改查各种业务数据

客户端黑名单：该部分对应着Turms服务端的全局黑名单机制，用于增删改查黑名单记录

权限控制：用于增删改管理员的信息与权限

客户端终端：搭载`turms-client-js`客户端实现，用于管理员快速测试真实客户端请求与服务端响应

TODO：贴GIF演示图