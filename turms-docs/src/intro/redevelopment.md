# 关于二次开发

## 申明

Turms基于Apache V2协议进行开发，并深入贯彻开源精神与宗旨。既欢迎其他开发者或团队的借鉴，也不担心抄袭。
因为Turms有且只有一个目标：致力于构建全球范围内最为卓越开源即时通讯引擎。

对于任何二次开发者或团队，Turms后期都会不加保留为其逐步开放各种相关架构设计、技术选型、代码视图设计等文档。
不管任何开发者或团队是借鉴还是抄袭，Turms对其提出的issues都将一视同仁，对有价值的问题都将予以热心答复。

因为Turms的目标很简单：致力于构建全球范围内最为卓越开源即时通讯引擎。

## 基于Turms做二次开发的原因

客观原因

   * Turms解决方案是全球即时通讯开源领域内唯一一个基于现代化架构与现代化工程技术，并且适合中大规模部署的解决方案
   * 由于Turms的架构设计是标准商用即时通讯架构的变种，因此如果您的专业团队是以常见的商用标准为要求，您的团队设计出来的架构也与Turms现在的架构相差不多的，没有必要另起炉灶从零自研
   * Turms整个架构其实还是比较简洁与轻量的，二次开发难度不高

主观原因

   * 您项目的核心业务与即时通讯相关，或者有深耕于即时通讯业务的计划
   * 您项目所需要的拓展功能Turms目前暂未提供，尤其是需要通过辅助索引表来实现的拓展功能（关于辅助索引表，可查看[Turms集合设计](https://turms-im.github.io/docs/for-developers/schema.html)）

## 培训

### 架构

#### 技术架构简述（TODO）

#### 业务请求流程（TODO）

### 开发

#### 代码拉取

1. git clone https://github.com/turms-im/turms.git

2. 由于Turms各子项目的proto模型文件放在一个独立的仓库之中，因此您还需要通过以下命令来拉取submodule中的代码。

```
git submodule update --init --recursive
git submodule foreach git pull origin master
```

#### 插件开发

参考 [插件开发](https://turms-im.github.io/docs/for-developers/custom-plugin.html)

#### 配置项开发（TODO）

### 测试（TODO）