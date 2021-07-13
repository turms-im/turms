# 搭建与启动

## 云服务环境自动搭建与启动

适用场景：有容灾、弹性扩展、跨地域部署与负载均衡等需求。该方案提供的各种能力直接与搭建成本挂钩，因此您通常需要修改默认提供的Terraform module配置，以保证配置既能满足您的需求，同时搭建与运维成本最低。

TODO

## 单机环境自动搭建与启动

适用场景：搭建方式方便快捷，但运维人员无容灾、弹性扩展与负载均衡等需求，主要用于搭建Demo环境来展示。

通过以下命令，可以全自动地搭建一套完整的Turms最小集群（包含turms、turms-gateway与turms-admin）及其依赖服务端（MongoDB分片集群与Redis）

```bash
git clone --depth 1 https://github.com/turms-im/turms.git
cd turms
docker plugin install grafana/loki-docker-driver:latest --alias loki --grant-all-permissions
docker-compose -f docker-compose.standalone.yml up --force-recreate
```

等集群完成搭建后，可以通过“http://localhost:6510”访问turms-admin后台管理系统，并输入账号密码（默认均为“turms”）。如果登录成功，则说明turms服务端也已经成功启动。

补充：可使用`--profile monitoring`（`docker-compose -f docker-compose.standalone.yml --profile monitoring up --force-recreate`）来自动搭建Prometheus与Grafana服务端。

## 手动搭建与启动

适用场景：通用，无特别限制场景。但一般只适用于小规模手动部署。

若您网络畅通，第一次完成以下全部操作大概需要花费10~30分钟。当您熟练之后，可在1~3分钟完成各种集群的部署工作。

1. MongoDB集群搭建（用于业务数据存储、服务发现、配置管理）

   - 下载并安装[MongoDB](https://www.mongodb.com/download-center/community)（要求最低版本号为：4.0。推荐版本为最新稳定版）。以RHEL/CentOS为例（具体可参考：https://docs.mongodb.com/manual/tutorial/install-mongodb-on-red-hat）：

     ```bash
     cat <<EOF > /etc/yum.repos.d/mongodb-org-4.4.repo
     [mongodb-org-4.4]
     name=MongoDB Repository
     baseurl=https://repo.mongodb.org/yum/redhat/$releasever/mongodb-org/4.4/x86_64/
     gpgcheck=1
     enabled=1
     gpgkey=https://www.mongodb.org/static/pgp/server-4.4.asc
     EOF
     yum install -y mongodb-org
     ```
   
   - 搭建MongoDB服务端集群（以mlaunch为例。关于mlaunch提供的更多指令，请查阅：http://blog.rueckstiess.com/mtools/mlaunch.html）。
   
     ```bash
     pip3 install mtools[mlaunch]
     mlaunch init --replicaset --sharded 1 --nodes 1 --config 1 --hostname localhost --port 27017 --mongos 1
     ```
     
     请确保运行正常，否则Turms会抛出MongoSocketOpenException异常。
   
  2. 下载、安装并启动Redis服务端（用于实现用户状态管理以及“附近的用户”）。以RHEL/CentOS为例：

     ```bash
     yum install epel-release
     yum update
     yum install redis
     systemctl start redis
     systemctl enable redis
     ```

     对于Windows平台，可在 [tporadowski/redis](https://github.com/tporadowski/redis/releases) 下载Windows版本供本地开发测试用。

3. Turms集群搭建（以下为手动搭建方案，之后会提供turms-cli做自动化集群部署）

   方案一：拉取Turms服务端Docker镜像，并运行：

   ```bash
   # Pull images
   docker pull ghcr.io/turms-im/turms-admin
   docker pull ghcr.io/turms-im/turms
   docker pull ghcr.io/turms-im/turms-gateway
   
   # Run images
   docker run -p 6510:6510 ghcr.io/turms-im/turms-admin
   docker run -p 7510:7510 -p 8510:8510 ghcr.io/turms-im/turms
   docker run --ulimit nofile=102400:102400 -p 7510:7510 -p 9510:9510 -p 10510:10510 -p 11510:11510 -p 12510:12510 ghcr.io/turms-im/turms-gateway
   ```
   
   另外，您可以通过volume挂载的方式来使用自定义的application.yaml与jvm.options配置“-v /your-custom-config-dir:/opt/turms/turms/config”。
   
   方案二：~~下载并解压[Turms服务端](https://github.com/turms-im/turms/releases)压缩包~~（由于v.0.10.0尚未发布在release页面中，因此该方案暂不可用），根据下述步骤运行：
   
   - （如果您将MongoDB与Redis都安装默认配置安装在本地，可跳过此步骤）根据您的需求配置config/jvm.options、config/application.yaml（您可以在此处配置Turms自定义的配置参数，并且您也可以在此处配置多个MongoDB或mongos的服务端地址。具体可参考：https://docs.mongodb.com/manual/reference/connection-string）。
   
   - （推荐使用Ansible）在所有需要运行Turms服务端的系统上，运行bin/turms脚本（默认以Thin包形式执行，若需以Fat包形式执行，请在执行脚本时加上“-f”参数，如：“sh run.sh -f”。之后再运行turms-gateway服务端。turms与turms-gateway服务端会通过MongoDB（作为服务注册中心）来自动寻找其他服务端节点，由此Turms集群开始运作。
   
   方案三：克隆Turms仓库源码，直接通过IDE运行turms与turms-gateway服务端。（参考命令：git clone --depth 1 https://github.com/turms-im/turms.git）

**提醒**

* turms服务端在启动时，会自动检测数据库中是否已存在一个角色为“ROOT”，且账号为“turms”的超级管理员账号。如果不存在，则turms服务端会自动创建一个角色为“ROOT”、名称与密码均为“turms”的管理员账号。在生产环境中，请务必记得要修改默认密码。
* 上述操作主要用于您初次体验Turms集群使用，若您需将Turms部署在生产环境当中，请务必查阅Wiki手册，了解各种配置参数的意义，以最小的资源消耗，来定制属于您自己的业务需求与业务组合。