# 发布

## 服务端发布目录结构

turms与turms-gateway服务端的发布目录结构如下：

```
├─bin
│  └─run.sh
├─config
│  ├─application.yaml
│  └─jvm.options
├─hprof
├─jdk
│─jfr
│─lib
│  ├─turms.jar (or turms-gateway.jar)
│  └─....jar
└─log
```

| 目录   | 必须存在 | 作用                                                         |
| ------ | -------- | ------------------------------------------------------------ |
| bin    | 否       | 存放可执行脚本。run.sh用于读取上下文配置，并启动Turms服务端  |
| config | 是       | 存放配置文件。<br />application.yaml用于覆盖与添加应用层配置（如Spring、Turms等配置）；<br />jvm.options用于设置JVM配置。通常情况下，您不需要直接修改该文件，而是通过环境变量`TURMS_JVM_OPTS`（或`TURMS_GATEWAY_JVM_OPTS`）来增添JVM配置 |
| hprof  | 否       | 存放内存堆导出数据                                           |
| jdk    | 否       | 存放JDK。bin脚本优先使用`JAVA_HOME`下的JDK，如果您未设置`JAVA_HOME`环境变量，则使用该目录下的JDK |
| jfr    | 否       | 存放JFR实时飞行记录信息                                      |
| lib    | 是       | 存放运行时Jar包依赖，包括自定义的插件实现                    |
| log    | 否       | 存储日志（包括GC日志、API调用日志、系统日志等）              |

注意：环境变量`TURMS_HOME`（对应turms服务端）或`TURMS_GATEWAY_HOME`（对应turms-gateway服务端）对于run.sh脚本与turms服务端的正确读取与存储数据都至关重要。如果您通过run.sh或Docker镜像运行Turms服务端，并且您没有设置该环境变量，则run.sh脚本会自动推导出HOME目录位置。如果您不通过上述方式运行（如通过IDE直接启动），则建议您手动配置`TURMS_HOME`或`TURMS_GATEWAY_HOME`环境变量。

## Docker镜像

强烈建议您使用Docker镜像部署Turms服务端。

目前Turms服务端Docker镜像版本号均为“latest”，即暂不提供带具体版本号的镜像。具体镜像拉取地址如下：

```shell
docker pull ghcr.io/turms-im/turms:latest
docker pull ghcr.io/turms-im/turms-gateway:latest
docker pull ghcr.io/turms-im/turms-admin:latest
```
