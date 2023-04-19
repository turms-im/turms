# Distribution

## The Directory Structure of Server Release Package

The directory structure of the turms-gateway and turms-service server release packages is as follows:

```
├─bin
│ └─run.sh
├─config
│ ├─application.yaml
│ └─jvm.options
├─hprof
├─jdk
│─jfr
│─lib
│ ├─turms-gateway.jar (or turms-service.jar)
│ └─... jar
│─log
└─plugins
```

| directory | must exist | role                                                         |
| --------- | ---------- | ------------------------------------------------------------ |
| bin       | no         | Holds executable scripts. run.sh is used to read the context configuration and start the Turms server |
| config    | yes        | Store configuration files. <br />application.yaml is used to override and add application layer configuration (such as Spring, Turms and other configurations); <br />jvm.options is used to set JVM configuration. Normally, you don't need to modify this file directly, but add JVM configuration through the environment variable `TURMS_GATEWAY_JVM_OPTS` (or `TURMS_SERVICE_JVM_OPTS`) |
| hprof     | no         | store heap dump snapshots                                    |
| jdk       | no         | Store the JDK. The bin script uses the JDK under `JAVA_HOME` first. If you do not set the `JAVA_HOME` environment variable, use the JDK under this directory |
| jfr       | No         | Store JFR real-time flight record information                |
| lib       | Yes        | Store runtime Jar package dependencies, excluding custom plug-in implementations |
| log       | No         | Store logs (including GC logs, API call logs, application logs, etc.) |
| plugins   | No         | Jar package dependencies for storing plugins. Note that the Turms server will only detect whether the JAR package ending with `jar` in the `plugins` directory is a plug-in implementation, so if you put the plug-in JAR package in the `lib` directory, these plug-ins will not be recognized. use |

Note: The environment variable `TURMS_GATEWAY_HOME` (corresponding to the turms-gateway server) or `TURMS_SERVICE_HOME` (corresponding to the turms-service server) is very important for the run.sh script and the turms server to read and store data correctly. If you run the Turms server through run.sh or a Docker image, and you do not set the above environment variables, the run.sh script will automatically deduce the HOME directory location. If you do not run it through the above method (such as starting directly through the IDE), it is recommended that you manually configure the `TURMS_GATEWAY_HOME` or `TURMS_SERVICE_HOME` environment variable, otherwise the Turms server will use `.` (current directory) as the HOME environment.

## Docker Images

It is strongly recommended that you use a Docker image to deploy the Turms server.

At present, the version number of the Docker image on the Turms server is `latest`, that is, the image with a specific version number is not provided for the time being. The specific command to pull the image is as follows:

```shell
docker pull ghcr.io/turms-im/turms-admin:latest
docker pull ghcr.io/turms-im/turms-gateway:latest
docker pull ghcr.io/turms-im/turms-service:latest
```

## Custom Publish Artifacts
If users don't want to use the official products provided by Turms (such as Docker image), they can also generate the products they need according to the source code and scripts of Turms projects. Although the packaging and release process of each sub-project of Turms is open source, considering that if users need to search the warehouse for research, the learning efficiency is relatively low, so this article summarizes the generation methods of each product:

| Projects           | Products                                                     | Reference Methods                                            |
| ------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| turms-gateway      | Fat/Uber JAR package (including dependencies)                | 1. Install [Temurin JDK 17](https://adoptium.net/temurin/releases)<br />2. Install [Maven 3](https://maven.apache.org/download.cgi)<br />3. Execute `mvn clean package -am -B -DskipUTs -DskipITs -P artifact-fat-jar -pl turms in the root directory of the Turms project -gateway --no-transfer-progress` |
|                    | Thin JAR package (the package does not include dependent packages) | 1. Install [Temurin JDK 17](https://adoptium.net/temurin/releases)<br />2. Install [Maven 3](https:/ /maven.apache.org/download.cgi)<br />3. In the root directory of the Turms project, execute the command `mvn clean package -am -B -DskipUTs -DskipITs -P artifact-thin-jar -pl turms-gateway - -no-transfer-progress` |
|                    | Docker image (linux/amd64)                                   | 1. Install [Docker](https://docs.docker.com/engine/install)<br />2. Execute the command `docker build -- in the root directory of the Turms project rm=false -t "www.mydomain.com/turms-im/turms-gateway:latest" -f turms-gateway/Dockerfile .` |
|                    | Docker image (linux/arm64)                                   | (cross-compilation)<br />If users use GitHub Action, they can directly reuse the `.github/workflows/publish-turms-gateway.yml` script under the Turms project for image packaging . <br />If the user does not use GitHub Action, and the system is not `linux/arm64`, cross-compilation is required. The specific method is as follows:<br />1. Install [Docker](https://docs.docker.com/engine/install)<br />2. Install [buildx and QEMU](https://docs.docker. com/build/building/multi-platform)<br />3. In the root directory of the Turms project, execute the command `DOCKER_BUILDKIT=1 docker buildx build --platform linux/arm64 --rm=false -t "www.mydomain.com /turms-im/turms-gateway:latest" -f turms-gateway/Dockerfile .` |
| turms-service      | Fat/Uber JAR package (including dependencies)                | 1. Install [Temurin JDK 17](https://adoptium.net/temurin/releases)<br />2. Install [Maven 3](https://maven.apache.org/download.cgi)<br />3. Execute `mvn clean package -am -B -DskipUTs -DskipITs -P artifact-fat-jar -pl turms in the root directory of the Turms project -service --no-transfer-progress` |
|                    | Thin JAR package (the package does not include dependent packages) | 1. Install [Temurin JDK 17](https://adoptium.net/temurin/releases)<br />2. Install [Maven 3](https:/ /maven.apache.org/download.cgi)<br />3. In the root directory of the Turms project, execute the command `mvn clean package -am -B -DskipUTs -DskipITs -P artifact-thin-jar -pl turms-service - -no-transfer-progress` |
|                    | Docker image (linux/amd64)                                   | 1. Install [Docker](https://docs.docker.com/engine/install)<br />2. Execute the command `docker build -- in the root directory of the Turms project rm=false -t "www.mydomain.com/turms-im/turms-service:latest" -f turms-service/Dockerfile .` |
|                    | Docker image (linux/arm64)                                   | (cross-compilation)<br />If users use GitHub Action, they can directly reuse the `.github/workflows/publish-turms-service.yml` script under the Turms project for image packaging . <br />If the user does not use GitHub Action, and the system is not `linux/arm64`, cross-compilation is required. The specific method is as follows:<br />1. Install [Docker](https://docs.docker.com/engine/install)<br />2. Install [buildx and QEMU](https://docs.docker.com/build/building/multi-platform)<br />3. In the root directory of the Turms project, execute the command `DOCKER_BUILDKIT=1 docker buildx build --platform linux/arm64 --rm=false -t "www.mydomain.com /turms-im/turms-service:latest" -f turms-service/Dockerfile .` |
| turms-admin        | Static resources (such as HTML, JavaScript, CSS, etc.)       | 1. Install [Node.js 18](https://nodejs.org/en/download)<br />2. In turms-admin Execute the command `npm i --no-optional && npm run build` under the project |
|                    | Docker image with Nginx service (linux/amd64)                | 1. Install [Docker](https://docs.docker.com/engine/install)<br />2. Execute the command in the root directory of the Turms project` docker build --rm=false -t "www.mydomain.com/turms-im/turms-admin:latest" -f turms-admin/Dockerfile .` |
|                    | Docker image with Nginx service (linux/arm64)                | (cross-compilation)<br />If users use GitHub Action, they can directly reuse `.github/workflows/publish-turms-admin.yml` under the Turms project The script performs image packaging. <br />If the user does not use GitHub Action, and the system is not `linux/arm64`, cross-compilation is required. The specific method is as follows:<br />1. Install [Docker](https://docs.docker.com/engine/install)<br />2. Install [buildx and QEMU](https://docs.docker. com/build/building/multi-platform)<br />3. In the root directory of the Turms project, execute the command `DOCKER_BUILDKIT=1 docker buildx build --platform linux/arm64 --rm=false -t "www.mydomain.com /turms-im/turms-admin:latest" -f turms-admin/Dockerfile .` |
| turms-client-dart  | No packaging required                                        | None                                                         |
| turms-client-java  | JAR package                                                  | 1. Install [Temurin JDK 17](https://adoptium.net/temurin/releases)<br />2. Install [Maven 3](https://maven.apache .org/download.cgi)<br />3. Execute the command `mvn clean install` in the root directory of the turms-client-java project |
| turms-client-js    | Static resources                                             | 1. Install [Node.js 18](https://nodejs.org/en/download)<br />2. Execute the command `npm under the turms-client-js project i --no-optional && npm run build` |
| turms-client-swift | No packaging required                                        | None                                                         |

## Reference Configuration of Linux system

### /etc/security/limits.conf

```
*        soft    nofile          1048576
*        hard    nofile          1048576
```
* nofile is configured as the default maximum value of 1048576 (1024*1024), which is limited by `fs.nr_open`.

  If users need a larger value, they can use the command `sudo sysctl -w fs.nr_open=2147483584` to modify the value of `fs.nr_open`, thereby increasing the upper limit of the maximum value of `nofile`.

* The Turms server only needs a few threads to run normally, so the operation and maintenance personnel generally do not need to modify the `noproc` configuration specifically for the Turms server.

  Regarding why the Turms server only needs a few threads to run normally, readers can read [Turms server thread model](https://turms-im.github.io/docs/server/module/system-resource-management#%E7%BA%BF%E7%A8%8B%E6%A8%A1%E5%9E%8B)

Reference documents:

* https://www.kernel.org/doc/Documentation/sysctl/fs.txt

### /etc/sysctl.conf

The default values mentioned in the configuration below are from Ubuntu 20.04 LTS.

```
# Default value: 1629424. The total number of file descriptors that can be opened by all processes in the system. A socket needs to occupy a file descriptor
fs.file-max = 1629424

# Default value: 60. Use swap when the memory usage is less than 10%. Try to avoid using swap to reduce the wake-up soft interrupt process
vm.swappiness = 10

# Default: 1024. Defines the maximum length of the SYN half-open connection queue. If this parameter is too large, it may intensify the effect of SYN flood attack
net.ipv4.tcp_max_syn_backlog = 65536
# Default: 4096. Adjust the length of the accept queue. You can use the command: netstat -s | grep "listen queue" to see how many connections were dropped due to queue overflow. If there are continuous connections being discarded due to accept queue overflow, the backlog and somaxconn parameters should be increased
net.core.somaxconn=65536
# Default: 1. The SYN Cookie function is enabled only when the SYN semi-connection queue cannot hold a half-open connection. Syncookies can make half-open connections skip the SYN queue and establish connections directly, and can also mitigate SYN flood attacks
net.ipv4.tcp_syncookies = 1

# Default: 0. Do not cache metrics for closed TCP connections
net.ipv4.tcp_no_metrics_save = 1

# Default value: 15. Control the number of timeout retransmissions of TCP. RFC1122 recommends that the corresponding timeout period should not be less than 100s, that is, at least 8
net.ipv4.tcp_retries2 = 10
# Default value: 6. When acting as a TCP client, the number of times to retry sending SYN to initiate a handshake. It will wait 1, 2, 4 seconds before each retry (7 seconds in total). When communicating in the intranet, the number of retries can be appropriately reduced to expose errors to the application as soon as possible
net.ipv4.tcp_syn_retries = 3
# Default value: 5. When used as a TCP server, the number of retries for SYN+ACK packets. It will wait for 1, 2, 4, 8, 16, and 32 seconds before each retry (a total of 63 seconds is required), and if the last retry still does not receive ACK, the connection will be closed
net.ipv4.tcp_synack_retries = 5

# Default: 1. Enable selection confirmation, so that TCP only resends lost TCP segments, without sending all unacknowledged TCP segments
net.ipv4.tcp_sack = 1

# Default: 0. Set to 0 to drop the connection when the accept queue overflows. The ACK is retransmitted by the TCP client, and the server tries to put the connection into the accept queue again. Discarding the connection can improve the success rate of connection establishment. Only when it is very sure that the accept queue will overflow for a long time can it be set to 1. By sending an RST reset message to the client, the client will be notified as soon as possible that the connection has failed
net.ipv4.tcp_abort_on_overflow = 0

# Default value: 65536. The maximum number of TCP connections in the system are not associated with any process (when the process calls the close function to close the connection, no matter whether the connection is in the FIN_WAIT1 state or is actually closed), if the number of orphan connections is greater than it, the newly added Orphan connections will no longer wave four times, but will directly send RST reset messages to force close
net.ipv4.tcp_max_orphans = 65536

# Default: 2. Enable TIME_WAIT reuse, so that new connections can reuse ports in TIME_WAIT state
net.ipv4.tcp_tw_reuse = 1
# Default: 1. In order to make tcp_tw_reuse take effect, the timestamps parameter needs to be set to 1 (tcp_timestamps should also be enabled on the peer). This option provides a more accurate method of calculating the round trip time (Round Trip Time) RTT between the two communicating parties
net.ipv4.tcp_timestamps = 1

# Default value: 65536. The system maintains the maximum number of TIME_WAIT sockets simultaneously. When the number of TIME_WAIT connections exceeds this parameter, the newly closed connection will no longer experience TIME_WAIT and will be closed directly
net.ipv4.tcp_max_tw_buckets = 65536

# Default value: 60. Specify how long the TCP connection with the state of FIN_WAIT_2 is kept
net.ipv4.tcp_fin_timeout = 30

# Keep the default configuration related to TCP keepalive, because the application layer of Turms has its own set of heartbeat mechanism
# net.ipv4.tcp_keepalive_probes = ...
# net.ipv4.tcp_keepalive_intvl = ...
# net.ipv4.tcp_keepalive_time = ...

# Default value 1. Enable TCP Fast Open, the client can carry the request in the first SYN message to save 1 RTT time
net.ipv4.tcp_fastopen = 3

# Default value: 1000. When the network card receives data packets faster than the kernel can process them, there will be a backlog to cache these data packets. This parameter represents the maximum value of the queue. When the backlog overflows, the kernel will drop packets
net.core.netdev_max_backlog=65536

# Define the maximum value that the receiving window can use, which can be adjusted according to the BDP value
net.core.rmem_max = 16777216
net.core.wmem_max = 16777216

# [low, pressure, high], the unit is page (4096 bytes)
# low: When the total used memory of all TCP connections is lower than this value, TCP memory will not be automatically adjusted
# pressure: When the total used of all TCP connections is greater than pressure, the kernel starts to adjust the buffer size
# high: When the total used memory of all TCP connections is greater than this value, the kernel will no longer allocate new memory for TCP and will not establish new connections. It should be ensured that the dynamic adjustment upper limit of the buffer reaches the bandwidth-delay product
# No custom configuration, use the default value automatically calculated by the system
# net.ipv4.tcp_mem = ...

# [min, default, max], the unit is byte
# min: Specifies the minimum memory reserved for receiving buffers for each TCP connection. Even in pressure mode, TCP connections will reserve at least this part of memory for receiving buffers
# default: Specifies the initial memory size of each TCP connection for receiving buffers
# max: Specifies the maximum memory used for receiving buffers per TCP connection
# If the buffer is too small, the TCP throughput will be reduced, and the network bandwidth cannot be used efficiently, resulting in increased communication delay; if the buffer is too large, the memory usage of the TCP connection will be high and the bottleneck limited by the bandwidth and delay product will cause memory loss. waste
net.ipv4.tcp_rmem = 4096 87380 16777216
net.ipv4.tcp_wmem = 4096 87380 16777216

# Default: 1. Enable the adjustment function of the receive buffer
net.ipv4.tcp_moderate_rcvbuf = 1
# Default: 1. TCP uses 16 bits to record the window size, and the maximum value can be 65535B. If this value is exceeded, the tcp_window_scaling mechanism needs to be enabled
net.ipv4.tcp_window_scaling = 1
```

Once configured, execute `sudo sysctl -p` to load the latest configuration of sysctl.

Special mention is: we are in [system resource management](https://turms-im.github.io/docs/server/module/system-resource-management#%E5%8F%AF%E6%8E%A7%E5%86%85%E5%AD%98-managed-memory-%E7%9A%84%E4%BD%BF%E7%94%A8) mentioned that the Turms server will reserve part of the memory for the system Kernel, this part of memory mainly refers to the buffer of the above-mentioned TCP connection.

### Initial congestion window (initcwnd) configuration

Keep the default value: 10MSS.

Reference documents:

* https://www.kernel.org/doc/Documentation/sysctl/net.txt
* https://www.kernel.org/doc/Documentation/networking/ip-sysctl.txt