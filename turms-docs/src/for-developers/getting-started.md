# 搭建与启动

## 自动搭建与启动

### 单机环境

适用场景：搭建流程方便快捷，但无法满足容灾、弹性扩展、零宕机升级与负载均衡等需求，主要用于搭建Demo用于展示，与服务对SLA无要求的用户。

#### 基于docker-compose

通过以下命令，可以全自动地搭建一套完整的Turms最小集群（包含turms、turms-gateway与turms-admin）及其依赖服务端（MongoDB分片集群与Redis）

```bash
git clone --depth 1 https://github.com/turms-im/turms.git
cd turms
docker plugin install grafana/loki-docker-driver:latest --alias loki --grant-all-permissions
docker-compose -f docker-compose.standalone.yml up --force-recreate
```

等集群完成搭建后，可以通过 http://localhost:6510 访问turms-admin后台管理系统，并输入账号密码（默认均为`turms`）。如果登录成功，则说明turms服务端也已启动成功。

补充：配合`--profile monitoring`（`docker-compose -f docker-compose.standalone.yml --profile monitoring up --force-recreate`），还可以额外自动搭建Prometheus与Grafana服务端。

#### 基于Terraform与docker-compose

（由于Turms目前并没有提供封装好的镜像，因此仍需要使用docker-compose进行环境搭建）

该方案是在上述docker-compose方案的基础上，引入了Turms自定义的Terraform模块，以帮助用户自动开通并配置VPC、交换机、安全组与单机ECS抢占式实例。在这台ECS上，Terraform会通过user-data系统初始化脚本，来安装docker-compose与Turms等服务，并最终启动Turms单机集群。

具体操作命令如下：

```bash
git clone --depth 1 https://github.com/turms-im/turms.git
cd turms/terraform/alicloud/playground
export ALICLOUD_ACCESS_KEY=<your_access_key>
export ALICLOUD_SECRET_KEY=<your_secret_key>
terraform init
terraform apply
```

在`terraform apply`命令执行完毕后，等待约3~15分钟（阿里云ECS拉取ghcr镜像很慢），然后再访问`http://公网IP:6510`（公网IP可以通过查看控制台输出的`public_ip`或查看云服务的ECS控制台获得），如果可以成功访问turms-admin后台管理系统，则表明搭建成功。

注意：该方案需要购买与使用云服务，而其开销取决于ECS的运行时长。默认配置下，约一小时0.1元（抢占实例的价格随时有可能波动，因此具体价格请查阅云服务的价目表）

### 基于云服务的集群环境

适用场景：有容灾、弹性扩展、跨地域部署与负载均衡等需求。该方案提供的各种能力直接与搭建成本挂钩，因此您通常需要修改默认提供的Terraform module配置，以保证配置既能满足您的需求，同时搭建与运维成本最低。

具体操作命令如下：

```bash
git clone --depth 1 https://github.com/turms-im/turms.git
cd turms/terraform/alicloud/cluster
export ALICLOUD_ACCESS_KEY=<your_access_key>
export ALICLOUD_SECRET_KEY=<your_secret_key>
terraform init
terraform apply
```

该Terraform模块会进行按照互联网应用的常规同城容灾级的部署方案，进行云服务的部署。具体而言包括：

1. 在一个地域内搭建一个`VPC`，该VPC下开通2台`交换机`，代表两个`可用区`
2. 在上述两个可用区内默认部署一套`MongoDB分片集群服务`，实现同城容灾
3. 在一个可用区内默认部署一套`Redis服务`
4. 为接收发送给Turms ECS的外网流量，开通`SLB服务`
5. 为实现Turms ECS的外网访问，开通`NAT服务`
6. 为turms、turms-gateway与turms-admin服务端分别搭建各自的`安全组`
7. 为turms服务端，开通无公网带宽的`ECS实例`（默认数量为1）。通过user-data实现turms服务初始化与执行，并与上述SLB、NAT、安全组、MongoDB、Redis服务绑定
8. 为turms-gateway服务端，开通无公网带宽的`ECS实例`（默认数量为1）。通过user-data实现turms-gateway服务初始化与执行，并与上述SLB、NAT、安全组、MongoDB、Redis服务绑定
9. 为turms-admin服务端，开通无公网带宽的`ECS实例`（默认数量为1）。通过user-data实现turms-admin服务初始化与执行，并与上述安全组服务绑定

自此整套Turms基础集群搭建完成（未来还会提供诸如日志分析服务等）。若希望了解更多实现细节，请查阅`turms/terraform/alicloud/cluster`目录下的具体Terraform模块配置

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
   
   另外，您可以通过volume挂载的方式来使用自定义的`application.yaml`与`jvm.options`。如配置`-v /your-custom-config-dir:/opt/turms/turms/config`。
   
   方案二：~~下载并解压[Turms服务端](https://github.com/turms-im/turms/releases)压缩包~~（由于v.0.10.0尚未发布在release页面中，因此该方案暂不可用），根据下述步骤运行：
   
   - （如果您将MongoDB与Redis都安装默认配置安装在本地，可跳过此步骤）根据您的需求配置config/jvm.options、config/application.yaml（您可以在此处配置Turms自定义的配置参数，并且您也可以在此处配置多个MongoDB或mongos的服务端地址。具体可参考：https://docs.mongodb.com/manual/reference/connection-string）。
   
   - （推荐使用Ansible）在所有需要运行Turms服务端的系统上，运行bin/turms脚本（默认以Thin包形式执行，若需以Fat包形式执行，请在执行脚本时加上`-f`参数，如：`sh run.sh -f`。之后再运行turms-gateway服务端。turms与turms-gateway服务端会通过MongoDB（作为服务注册中心）来自动寻找其他服务端节点，由此Turms集群开始运作。
   
   方案三：克隆Turms仓库源码，直接通过IDE运行turms与turms-gateway服务端。（参考命令：`git clone --depth 1 https://github.com/turms-im/turms.git`）

**提醒**

* turms服务端在启动时，会自动检测数据库中是否已存在一个角色为`ROOT`，且账号为`turms`的超级管理员账号。如果不存在，则turms服务端会自动创建一个角色为`ROOT`、名称与密码均为`turms`的管理员账号。在生产环境中，请务必记得要修改默认密码。
* 上述操作主要用于您初次体验Turms集群使用，若您需将Turms部署在生产环境当中，请务必查阅Wiki手册，了解各种配置参数的意义，以最小的资源消耗，来定制属于您自己的业务需求与业务组合。