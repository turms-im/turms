# 发布

## 服务端发布包的目录结构

turms-gateway与turms-service服务端发布包的目录结构如下：

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

## 自定义发布制品

用户如果不想使用Turms官方提供的制品（如Docker镜像），也可以自行根据Turms各项目的源码与脚本生成自己所需要的制品。尽管Turms各子项目的打包与发布流程都是开源的，但考虑到如果需要用户自己翻仓库研究的话，学习效率就相对比较低，因此本文总结了各制品的生成方法：

| 项目               | 制品                                      | 参考方法                                                     |
| ------------------ | ----------------------------------------- | ------------------------------------------------------------ |
| turms-gateway      | Fat/Uber JAR包（包中包括依赖包）          | 1. 安装[Temurin JDK 17](https://adoptium.net/temurin/releases)<br />2. 安装[Maven 3](https://maven.apache.org/download.cgi)<br />3. 在Turms项目的根目录执行`mvn clean package -am -B -DskipUTs -DskipITs -P artifact-fat-jar -pl turms-gateway --no-transfer-progress` |
|                    | Thin JAR包（包中不包括依赖包）            | 1. 安装[Temurin JDK 17](https://adoptium.net/temurin/releases)<br />2. 安装[Maven 3](https://maven.apache.org/download.cgi)<br />3. 在Turms项目的根目录执行命令`mvn clean package -am -B -DskipUTs -DskipITs -P artifact-thin-jar -pl turms-gateway --no-transfer-progress` |
|                    | Docker镜像（linux/amd64）                 | 1. 安装[Docker](https://docs.docker.com/engine/install)<br />2. 在Turms项目的根目录执行命令`docker build --rm=false -t "www.mydomain.com/turms-im/turms-gateway:latest" -f turms-gateway/Dockerfile .` |
|                    | Docker镜像（linux/arm64）                 | （交叉编译）<br />如果用户使用GitHub Action，可以直接复用Turms项目下的`.github/workflows/publish-turms-gateway.yml`脚本进行镜像打包。<br />如果用户不使用GitHub Action，且系统也不是`linux/arm64`，则需要进行交叉编译。具体方法如下：<br />1. 安装[Docker](https://docs.docker.com/engine/install)<br />2. 安装[buildx与QEMU](https://docs.docker.com/build/building/multi-platform)<br />3. 在Turms项目的根目录执行命令`DOCKER_BUILDKIT=1 docker buildx build --platform linux/arm64 --rm=false -t "www.mydomain.com/turms-im/turms-gateway:latest" -f turms-gateway/Dockerfile .` |
| turms-service      | Fat/Uber JAR包（包中包括依赖包）          | 1. 安装[Temurin JDK 17](https://adoptium.net/temurin/releases)<br />2. 安装[Maven 3](https://maven.apache.org/download.cgi)<br />3. 在Turms项目的根目录执行`mvn clean package -am -B -DskipUTs -DskipITs -P artifact-fat-jar -pl turms-service --no-transfer-progress` |
|                    | Thin JAR包（包中不包括依赖包）            | 1. 安装[Temurin JDK 17](https://adoptium.net/temurin/releases)<br />2. 安装[Maven 3](https://maven.apache.org/download.cgi)<br />3. 在Turms项目的根目录执行命令`mvn clean package -am -B -DskipUTs -DskipITs -P artifact-thin-jar -pl turms-service --no-transfer-progress` |
|                    | Docker镜像（linux/amd64）                 | 1. 安装[Docker](https://docs.docker.com/engine/install)<br />2. 在Turms项目的根目录执行命令`docker build --rm=false -t "www.mydomain.com/turms-im/turms-service:latest" -f turms-service/Dockerfile .` |
|                    | Docker镜像（linux/arm64）                 | （交叉编译）<br />如果用户使用GitHub Action，可以直接复用Turms项目下的`.github/workflows/publish-turms-service.yml`脚本进行镜像打包。<br />如果用户不使用GitHub Action，且系统也不是`linux/arm64`，则需要进行交叉编译。具体方法如下：<br />1. 安装[Docker](https://docs.docker.com/engine/install)<br />2. 安装[buildx与QEMU](https://docs.docker.com/build/building/multi-platform)<br />3. 在Turms项目的根目录执行命令`DOCKER_BUILDKIT=1 docker buildx build --platform linux/arm64 --rm=false -t "www.mydomain.com/turms-im/turms-service:latest" -f turms-service/Dockerfile .` |
| turms-admin        | 静态资源（如HTML、JavaScript、CSS等文件） | 1. 安装[Node.js 18](https://nodejs.org/en/download)<br />2. 在turms-admin项目下执行命令`npm i --no-optional && npm run build` |
|                    | 带Nginx服务的Docker镜像（linux/amd64）    | 1. 安装[Docker](https://docs.docker.com/engine/install)<br />2. 在Turms项目的根目录执行命令`docker build --rm=false -t "www.mydomain.com/turms-im/turms-admin:latest" -f turms-admin/Dockerfile .` |
|                    | 带Nginx服务的Docker镜像（linux/arm64）    | （交叉编译）<br />如果用户使用GitHub Action，可以直接复用Turms项目下的`.github/workflows/publish-turms-admin.yml`脚本进行镜像打包。<br />如果用户不使用GitHub Action，且系统也不是`linux/arm64`，则需要进行交叉编译。具体方法如下：<br />1. 安装[Docker](https://docs.docker.com/engine/install)<br />2. 安装[buildx与QEMU](https://docs.docker.com/build/building/multi-platform)<br />3. 在Turms项目的根目录执行命令`DOCKER_BUILDKIT=1 docker buildx build --platform linux/arm64 --rm=false -t "www.mydomain.com/turms-im/turms-admin:latest" -f turms-admin/Dockerfile .` |
| turms-client-dart  | 无需打包                                  | 无                                                           |
| turms-client-java  | JAR包                                     | 1. 安装[Temurin JDK 17](https://adoptium.net/temurin/releases)<br />2. 安装[Maven 3](https://maven.apache.org/download.cgi)<br />3. 在turms-client-java项目的根目录执行命令`mvn clean install` |
| turms-client-js    | 静态资源                                  | 1. 安装[Node.js 18](https://nodejs.org/en/download)<br />2. 在turms-client-js项目下执行命令`npm i --no-optional && npm run build` |
| turms-client-swift | 无需打包                                  | 无                                                           |

## Linux系统的参考配置

### /etc/security/limits.conf

```
*        soft    nofile          1048576
*        hard    nofile          1048576
```

* nofile配置为默认最大值1048576（1024*1024），该值受限于`fs.nr_open`。

  用户如果需要更大的值，可以通过`sudo sysctl -w fs.nr_open=2147483584`命令，来修改`fs.nr_open`的值，从而提高`nofile`的最大值上限。

* Turms服务端只需要很少的线程就能正常运行，因此运维人员一般无需再专门为Turms服务端修改`noproc`配置。

  关于为什么Turms服务端只需要很少的线程就能正常运行，读者可以阅读[Turms服务端线程模型](https://turms-im.github.io/docs/zh-CN/server/module/system-resource-management#%E7%BA%BF%E7%A8%8B%E6%A8%A1%E5%9E%8B)

参考文档：

* https://www.kernel.org/doc/Documentation/sysctl/fs.txt

### /etc/sysctl.conf

下文配置中提到的默认值来自Ubuntu 20.04 LTS。

```
# 默认值：1629424。系统所有进程一共可以打开的文件描述符数量。一个套接字需要占用一个文件描述符
fs.file-max = 1629424

# 默认值：60。当内存使用率不足10%时使用swap。尽量避免使用swap，以减少唤醒软中断进程
vm.swappiness = 10

# 默认值：1024。定义SYN半开连接队列的最大长度。此参数过大可能加剧SYN flood攻击效果
net.ipv4.tcp_max_syn_backlog = 65536
# 默认值：4096。调整accept队列的长度。可以通过命令：netstat -s | grep "listen queue"，来查看有多少个连接因为队列溢出而被丢弃。如果持续不断地有连接因为accept队列溢出被丢弃，就应该调大backlog以及somaxconn参数
net.core.somaxconn = 65536
# 默认值：1。仅当SYN半连接队列放不下半开连接时，启动SYN Cookie功能。syncookies可以让半开连接跳过SYN队列，并直接建立连接，同时也可以缓解SYN flood攻击
net.ipv4.tcp_syncookies = 1

# 默认值：0。不缓存已关闭的TCP连接的指标
net.ipv4.tcp_no_metrics_save = 1 

# 默认值：15。控制TCP的超时重传次数，RFC1122建议对应的超时时间不低于100s，即至少为8
net.ipv4.tcp_retries2 = 10
# 默认值：6。作为TCP客户端时，重试发送SYN发起握手次数。每次重试前会等待1、2、4秒（共需等待7秒）。内网中通讯时，就可以适当调低重试次数，尽快把错误暴露给应用程序
net.ipv4.tcp_syn_retries = 3
# 默认值：5。作为TCP服务端时，SYN+ACK报文的重试次数。在每次重试前会等待1、2、4、8、16、32秒（共需等待63秒），如果最后一次重试仍然没有收到ACK，才会关闭连接
net.ipv4.tcp_synack_retries = 5

# 默认值：1。开启选择确认，让TCP只重新发送丢失的TCP报文段，不用发送所有未被确认的TCP报文段
net.ipv4.tcp_sack = 1

# 默认值：0。设为0在accept队列溢出时，丢弃连接。通过TCP客户端重传ACK，服务端再次尝试将连接放入accept队列。丢弃连接可以提高连接建立的成功率，只有非常肯定accept队列会长期溢出时，才能设置为1，通过向客户端发送RST复位报文，尽快通知客户端连接已经建立失败
net.ipv4.tcp_abort_on_overflow = 0

# 默认值：65536。系统中最多有多少个TCP连接不被关联到任何一个进程上（当进程调用close函数关闭连接后，无论该连接是在FIN_WAIT1状态，还是确实关闭了），如果孤儿连接数量大于它，新增的孤儿连接将不再走四次挥手，而是直接发送RST复位报文强制关闭
net.ipv4.tcp_max_orphans = 65536

# 默认值：2。启用TIME_WAIT复用，让新连接能够复用TIME_WAIT状态的端口
net.ipv4.tcp_tw_reuse = 1
# 默认值：1。为了使tcp_tw_reuse生效，需要把timestamps参数设置为1（对端也要打开tcp_timestamps）。该选项提供了较为准确的计算通信双方之间的回路时间（Round Trip Time）RTT的方法
net.ipv4.tcp_timestamps = 1

# 默认值：65536。系统同时保持TIME_WAIT套接字的最大数目。当TIME_WAIT的连接数量超过该参数时，新关闭的连接就不再经历TIME_WAIT而直接关闭
net.ipv4.tcp_max_tw_buckets = 65536

# 默认值：60。指定状态为FIN_WAIT_2的TCP连接保存多长时间
net.ipv4.tcp_fin_timeout = 30

# 保持TCP keepalive相关默认配置，因为Turms的应用层有自己的一套心跳机制
# net.ipv4.tcp_keepalive_probes = ...
# net.ipv4.tcp_keepalive_intvl = ...
# net.ipv4.tcp_keepalive_time = ...

# 默认值1。开启TCP Fast Open，客户端可以在首个SYN报文中就携带请求，以节省1个RTT的时间
net.ipv4.tcp_fastopen = 3

# 默认值：1000。当网卡接收数据包的速度大于内核处理的速度时，会有一个backlog缓存这些数据包。这个参数表示该队列的最大值。当backlog溢出时，内核会进行丢包
net.core.netdev_max_backlog = 65536

# 定义接收窗口可以使用的最大值，可以根据BDP值进行调节
net.core.rmem_max = 16777216
net.core.wmem_max = 16777216

# [low, pressure, high]，单位是页（4096字节）
# low: 当所有TCP连接的总已用内存低于该值时，TCP内存不进行自动调节
# pressure: 当所有TCP连接的总已用大于pressure时，内核开始调节缓冲区大小
# high：当所有TCP连接的总已用内存大于该值时，内核不再为TCP分配新内存，并不再建立新连接。应当保证缓冲区的动态调整上限达到带宽时延积
# 不进行自定义配置，使用系统自动计算的默认值
# net.ipv4.tcp_mem = ...

# [min, default, max]，单位是字节
# min：指定为每个TCP连接预留用于接收缓冲区的最小内存，即使在pressure模式下TCP连接都至少会预留这部分内存用于接收缓冲
# default：指定每个TCP连接用于接收缓冲的初始内存大小
# max：指定每个TCP连接用于接收缓冲的最大内存
# 缓冲区太小，会降低TCP吞吐量，无法高效利用网络带宽，导致通信延迟升高；缓冲区太大，会导致TCP连接内存占用高以及受限于带宽时延积的瓶颈，从而造成内存浪费
net.ipv4.tcp_rmem = 4096 87380 16777216
net.ipv4.tcp_wmem = 4096 87380 16777216

# 默认值：1。开启接收缓冲区的调节功能
net.ipv4.tcp_moderate_rcvbuf = 1
# 默认值：1。TCP使用16位来记录窗口大小，最大值可以是65535B。如果超过该值，就需要开启tcp_window_scaling机制
net.ipv4.tcp_window_scaling = 1
```

配置完后，执行`sudo sysctl -p`以加载sysctl的最新配置。

特别一提的是：我们在[系统资源管理](https://turms-im.github.io/docs/zh-CN/server/module/system-resource-management#%E5%8F%AF%E6%8E%A7%E5%86%85%E5%AD%98-managed-memory-%E7%9A%84%E4%BD%BF%E7%94%A8)提到了Turms服务端会预留部分内存给系统内核，该部分内存主要就是指上述的TCP连接的缓冲区。

### 初始拥塞窗口（initcwnd）配置

保持默认值：10MSS。

参考文档：

* https://www.kernel.org/doc/Documentation/sysctl/net.txt
* https://www.kernel.org/doc/Documentation/networking/ip-sysctl.txt