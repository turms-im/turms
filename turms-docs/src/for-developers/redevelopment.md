# 关于二次开发

## 基于Turms做二次开发的原因

### 客观原因

   * 唯一性。Turms解决方案是全球即时通讯开源领域内，唯一一个基于现代化架构与现代化工程技术，并且适合中大规模部署的解决方案。而其他数十款IM开源项目仍处于刀耕火种时代，多是强调企业通讯、或端到端安全的IM项目，通常只能获得企业用户的青睐。除Turms之外，全球开源界尚未有一款面向常规互联网应用设计的中大型IM开源项目。

   * 规范性。由于Turms的架构设计是标准商用即时通讯架构的变种，因此如果您的专业团队是以常见的商用标准为要求，您的团队设计出来的架构也与Turms现在的架构相差不多的，没有必要另起炉灶从零自研。

   * 简易性。Turms整个架构与各个模块的实现其实都比较简洁与轻量的，二次开发难度不高。

   * 可控性。Turms基于Apache V2协议进行开发，100%开源，并对很多基础中间件进行了自研，保证了底层技术的可控，避免了项目后期发展动力不足。

   * 文档齐全。其中包括了诸如消息感知、可观察性体系、敏感词过滤、防刷限流、全局黑名单等等模块的设计文档。而其他部分IM项目的人员为了赚取咨询费与担心被抄袭，因此不“敢”写好文档

   * IM系统自身细节繁多，而开发人员水平又参差不齐，很难保证做出来的项目质量如何。实现用户A能给用户B/群B发消息最多也只是实现IM系统功能的1%功能，并且这些功能模块不像是一些通用的依赖库可以随意插拔而是要定制实现，且各实现环环相扣，因此各模块都要自研，要求设计人员与开发人员有很强的功底（若想了解一个完整的IM系统具体有多少细节功能，可以参考Turms的文档）。

     而Turms基本已经实现了一个完整的IM服务端系统，基本用户能想到的，与没想到的，我们都已经实现了，或者已经打好底子了，就算是不实现的功能我们一般也已经写明了为什么故意不实现，保证透明。

   * 代码质量高。Turms服务端在代码实现上能始终保持着高级工程师应有的水平，能在代码性能与可读性中取得平衡。具体请看Turms的服务端源码。特别一提，部分开源项目说得性能很好，但是一看代码就露馅了。Turms服务端之所以敢说能达到Java生态的极限，除了Turms服务端自身实现本来就高效外，我们对很多低效但关键的依赖库（如`mongo-java-driver`与`lettuce`）进行了重构，甚至自研实现（如日志实现），以保证极致的性能

   * 自研IM服务的市场需求大。即便现在到各招聘网站查询IM工程师相关岗位，也能发现国内外还有大量企业招聘IM相关人才，各公司投入上百或千万从零或基于古老的IM开源项目自研，重复造IM服务，社会资源利用率低。

另外，如果您还在犹豫是否要采用其他开源IM项目，那我们非常推荐您将Turms与它们做对比，在您大概读过Turms的文档与源码，相信您心中会有明确的答案。

### 主观原因

   * 您项目的核心业务与即时通讯相关，或者有深耕于即时通讯业务的计划。
   * 您项目所需要的拓展功能Turms目前暂未提供，尤其是需要通过辅助索引表来实现的拓展功能（关于辅助索引表，可查看[Turms集合设计](https://turms-im.github.io/docs/for-developers/schema.html)）。
   * 您项目存在大量项目独有的IM实现细节。Turms虽然提供了上百个配置项，但这些也只是普适的配置。根据具体业务需求的不同，IM相关功能的具体实现极其丰富，但Turms不可能直接提供这些相对小众业务功能的实现，否则代码量将会指数级增加，因此需要您自行做二次开发。

## 项目引入

1. 拉取Turms仓库：`git clone https://github.com/turms-im/turms.git`

2. 由于Turms各子项目的proto模型文件放在一个独立的仓库之中，因此您还需要在Turms项目的根目录下，通过以下命令来拉取submodule中的代码。

```
git submodule update --init --recursive
git submodule foreach git pull origin master
```

3. （可选）如果您使用的是Intellij IDEA，则可以通过`File` -> `New` -> `Project from Existing Source`引入整个Turms项目。IDEA将自动识别整个Turms项目的目录结构，并引入对应的Maven依赖库。

## 搭建开发环境

除了Turms服务端外，Turms其他子项目的搭建都非常常规与简单，故不累述。

Turms服务端开发环境的搭建其实也非常简单，具体步骤包括：

1. 安装[JDK 17](https://adoptium.net/)以开发Turms服务端

2. 下载、安装并启动Redis服务端。以RHEL/CentOS为例：

   ```bash
   yum install epel-release
   yum update
   yum install redis
   systemctl start redis
   systemctl enable redis
   ```

   对于Windows平台，可在 [tporadowski/redis](https://github.com/tporadowski/redis/releases) 下载Windows版本供本地开发测试用。

3. 下载、安装并启动MongoDB分片集群

   * 下载[MongoDB 4.4](https://www.mongodb.com/try/download/community)
   * 启动MongoDB分片集群：推荐安装`mtools`以全自动搭建MongoDB分片集群，其安装指令为：`pip3 install mtools[mlaunch]`。在安装完`mtools`后，只需运行`mlaunch init --replicaset --sharded 1 --nodes 1 --config 1 --hostname localhost --port 27017 --mongos 1`这一条指令，并等待数秒，即可完成MongoDB分片集群的搭建

4. 确认Redis服务端与MongoDB分片集群都正常运行后，即可启动Turms服务端

补充：

* 对于Redis、MongoDB的启动，可以设置成开机自启服务，这样就不用每次重启电脑后再手动搭建了。另外，就算是手动搭建，其实开发者多操作几次，基本也可以在10~30秒完成Redis与MongoDB分片集群的搭建，搭建与启动流程非常简单。
* 在进行服务端开发时，推荐将`turms-gateway`与`turms-service`两个项目下的`application.yaml`中的`spring.profiles.active=prod`改为`dev`。这是因为：
  * 在默认生产环境配置下，Turms服务端是不会在控制台打印日志的，因此不方便开发者进行调试
  * `dev`环境下，turms-service会自动向MongoDB数据库生成Fake数据，并且turms-gateway也会自动创建基于TCP的Fake客户端，这些客户端会随机地（请求类型随机、请求参数随机）向turms-gateway发送真实客户端请求，以方便开发者测试。
* 如果您想替换MongoDB服务端的端口，您只需在Turms项目下全局替换`27017`为您的目标端口即可。