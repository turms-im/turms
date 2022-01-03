# 发布

## 服务端发布目录结构

turms-gateway与turms-service服务端的发布目录结构如下：

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
│  ├─turms-gateway.jar (or turms-service.jar)
│  └─....jar
│─log
└─plugins
```

| 目录    | 必须存在 | 作用                                                         |
| ------- | -------- | ------------------------------------------------------------ |
| bin     | 否       | 存放可执行脚本。run.sh用于读取上下文配置，并启动Turms服务端  |
| config  | 是       | 存放配置文件。<br />application.yaml用于覆盖与添加应用层配置（如Spring、Turms等配置）；<br />jvm.options用于设置JVM配置。通常情况下，您不需要直接修改该文件，而是通过环境变量`TURMS_GATEWAY_JVM_OPTS`（或`TURMS_SERVICE_JVM_OPTS`）来增添JVM配置 |
| hprof   | 否       | 存放堆转储快照                                               |
| jdk     | 否       | 存放JDK。bin脚本优先使用`JAVA_HOME`下的JDK，如果您未设置`JAVA_HOME`环境变量，则使用该目录下的JDK |
| jfr     | 否       | 存放JFR实时飞行记录信息                                      |
| lib     | 是       | 存放运行时Jar包依赖，不包括自定义的插件实现                  |
| log     | 否       | 存储日志（包括GC日志、API调用日志、应用日志等）              |
| plugins | 否       | 存放插件的Jar包依赖。注意Turms服务端只会检测`plugins`目录下，以`jar`结尾的JAR包是否为插件实现，因此如果您将插件JAR包放到`lib`目录下，则这些插件将不会被识别与使用 |

注意：环境变量`TURMS_GATEWAY_HOME`（对应turms-gateway服务端）或`TURMS_SERVICE_HOME`（对应turms-service服务端）对于run.sh脚本与turms服务端的正确读取与存储数据都至关重要。如果您通过run.sh或Docker镜像运行Turms服务端，并且您没有设置上述的环境变量，则run.sh脚本会自动推导出HOME目录位置。如果您不通过上述方式运行（如通过IDE直接启动），则建议您手动配置`TURMS_GATEWAY_HOME`或`TURMS_SERVICE_HOME`环境变量，否则Turms服务端将以`.`（当前目录）作为HOME环境。

## Docker镜像

强烈建议您使用Docker镜像部署Turms服务端。

目前Turms服务端Docker镜像版本号均为`latest`，即暂不提供带具体版本号的镜像。具体拉取镜像的命令如下：

```shell
docker pull ghcr.io/turms-im/turms-admin:latest
docker pull ghcr.io/turms-im/turms-gateway:latest
docker pull ghcr.io/turms-im/turms-service:latest
```
