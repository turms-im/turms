# 安全

## 客户端安全

出于安全原因，本文不对Turms暂未提供专门抵御机制的CC攻击进行说明。

### 客户端黑名单机制

#### 服务端对封禁客户端的处理

当turms-gateway检测到有新的IP或用户ID被封禁时，会首先向已建立且被封禁的会话发送Turms业务层的关闭通知，该通知带有`USER_IS_BLOCKED`状态码，告知客户端它被封禁了。当数据Flush之后，Turms服务端再自动断开底层TCP连接。

当turms-gateway检查到新建立的TCP连接的对端IP已被封禁，或检测到发送登录请求的用户ID已被封禁，则在默认情况下turms-gateway会直接关闭与其的TCP连接，并且不会发送连接关闭原因的通知，如“您的IP/User ID已被封禁XX时间”。

其中有两点需要注意：

* turms-gateway自身无法在TCP连接建立之前，拒绝与被封禁的IP进行连接。如果您希望在TCP握手之前，服务端就能拒绝对被封禁的IP进行连接，您可通过Turms之后提供的：封禁用户时的回调插件，来通知云服务安全系统封禁IP，从而彻底实现IP封禁。

  另外，我们之所以不调用系统服务来彻底封禁IP，这是因为：服务端被强制关闭时，被封禁的IP将不会被自动移除；自行修改底层网络配置可能会和云服务自身的网络管理服务发生冲突，造成服务器异常。

* 在客户端连接或登陆时，turms-gateway会主动断开与封禁的IP或用户的连接，但是并不会发送连接关闭原因的通知。这么做的好处是：1. 云服务的带宽是按出网带宽收费的，入网带宽不收费，因此turms-gateway不发送业务层上的响应，可以减缓被DDoS攻击时带来的带宽费用开销；2. 减少信息暴露，尽量不要给黑客提供有效信息

#### 自动封禁机制

目前支持自动检测并封禁客户端的时机有：

* 当用户发送请求频繁，并达到一定次数时
* 当用户发送的WebSocket帧不符合规范或过大，并达到一定次数时。请求的大小依据WebSocket Frame Header中的Payload Length值
* 当用户发送的Turms客户端请求无法解析或过大，并达到一定次数时。请求的大小依据TCP字节流中客户端请求Header的Payload Length值

  补充：
  
  * 服务端检测到数据帧或客户端请求“过大”时，不会继续解析其后续的Payload部分。如果客户端的Payload Length与实际Payload长度不符，则判定为非法请求
  * 具体请求大小限制可通过`turms.gateway.client-api.max-request-size-bytes`配置

换言之，在TCP连接建立后，用户的任何行为都可能触发封禁。

Turms的自动封禁机制采用分级制度，默认提供3个等级，这3个等级的封禁时长分别是：1分钟、30分钟、60分钟。默认配置下，当客户端触发5次非法行为，则服务端会以等级1的配置封禁客户端的IP与用户ID，如果在封禁时间内，又触发了一定次数的非法行为，则进入下一个封禁等级，以此类推。

如果您想要修改默认配置，您可以通过`turms.security.blocklist.ip.auto-block`与``turms.security.blocklist.user-id.auto-block``前缀，并配合IDEA的智能提示对默认配置进行修改。其具体的配置项声明在`im.turms.server.common.infra.property.env.common.security.AutoBlockItemProperties`类中。

#### 封禁相关API

管理员可以通过API：`/blocked-clients/ips`与`/blocked-clients/users`，分别对封禁IP与封禁用户ID做增删改查操作，具体操作遵循[Turms HTTP接口设计的一般规则](https://turms-im.github.io/docs/zh-CN/reference/admin-api#%E6%8E%A5%E5%8F%A3%E8%AE%BE%E8%AE%A1%E5%87%86%E5%88%99)，故不赘述。

#### 封禁实现原理（拓展知识）

封禁客户端数据的同步实现原理与常见的分布式Replicated Map实现类似。即每个服务端都持有该Map的弱一致的副本，又有一个或多个Redis服务端存有一个基准副本，并且还记录了每个封禁与解封行为的logs，用于各服务端做增量同步。当新服务端上线或某服务端本地logs数据滞后100,000个记录时，这些服务端会向Redis请求全量同步，否则服务端只需以默认的10秒时间间隔向Redis请求增量logs以同步本地副本。

另外Turms目前采用的因果一致性实现是：封禁与解封动作的先后顺序以在Redis的封禁logs队列的插入顺序为基准，各服务端基于该队列的logs顺序，进行因果同步，保证封禁客户端数据的最终一致性。

##### 为什么不使用Bloom Filter

基于Bloom Filter实现黑名单功能的理论方案广为人知，但其实Bloom Filter在这场景下有非常多的陷阱，具体而言：

* Bloom Filter支持的功能特性与工程实践都很受限。诸如：

  * 在分布式环境下，如何判断“封禁操作”与“解封操作”的先后顺序，并且如何保证最终一致性
  * 如何给不同的封禁用户设定不同的封禁时长（如五分钟/半个钟）
  * 如何给附加的被封禁用户附加信息，比如附加被拉黑原因
  * 节点间黑名单列表如何同步，如何做增量同步
  * 如何实现“取消拉黑操作”，代价是什么

  综上，Bloom Filter在分布式环境下，连黑名单系统最为基础的功能都无法实现，就算Bloom Filter配合其他工程实践勉强实现，那Bloom Filter自身的优势也就不存在了。

* 被拉黑用户数据量本身很小，Bloom Filter无法发挥其优势。而且如果只是判断用户是否被拉黑，我们按100万的被封禁的用户ID来看，一共也才需要12MiB或61.4MiB内存（额外补充：这个例子也印证了我们在[关于Valhalla项目](https://turms-im.github.io/docs/zh-CN/server/module/system-resource-management#%E5%85%B3%E4%BA%8Evalhalla%E9%A1%B9%E7%9B%AE)篇章中提及到的：`Java对内存的浪费就让人感觉有些“自暴自弃”了`）。因为在实际编程中通常都使用线程安全的集合，且大部分线程安全的Set内部一般都是基于Map实现的，因此下文统一使用的是线程安全的Map：

  ```java
  public static void main(String[] args) {
      int number = 1_000_000;
      var map1 = ConcurrentHashMap.newKeySet((int)(number / 0.75F + 1.0F));
      var map2 = new NonBlockingHashMapLong<>(number);
      for (long i = 0; i < number; i++) {
          map1.add(i);
          map2.put(i, Boolean.TRUE);
      }
      System.out.println(GraphLayout.parseInstance(map1).toFootprint());
      System.out.println(GraphLayout.parseInstance(map2).toFootprint());
  }
  ```
  其内存占用的输出如下（基于`org.openjdk.jol.jol-core`库实现计算）：
  ```text
  java.util.concurrent.ConcurrentHashMap$KeySetView@593634add footprint:
       COUNT       AVG       SUM   DESCRIPTION
           1   8388624   8388624   [Ljava.util.concurrent.ConcurrentHashMap$Node;
           1        16        16   java.lang.Boolean
     1000000        24  24000000   java.lang.Long
           1        64        64   java.util.concurrent.ConcurrentHashMap
           1        24        24   java.util.concurrent.ConcurrentHashMap$KeySetView
     1000000        32  32000000   java.util.concurrent.ConcurrentHashMap$Node
     2000004            64388728   (total)
  
  org.jctools.maps.NonBlockingHashMapLong@51ca57d6d footprint:
       COUNT       AVG       SUM   DESCRIPTION
           3   2796304   8388912   [J
           1   4194320   4194320   [Ljava.lang.Object;
           1        16        16   java.lang.Boolean
           2        16        32   org.jctools.maps.ConcurrentAutoTable
           2        40        80   org.jctools.maps.ConcurrentAutoTable$CAT
           1        40        40   org.jctools.maps.NonBlockingHashMapLong
           1        64        64   org.jctools.maps.NonBlockingHashMapLong$CHM
          11            12583464   (total)
  ```

* 存在误差

### 客户端接口防刷限流

turms-gateway的限流实现采用的是主流算法`令牌桶算法`（如AWS的API Gateway提供流量整型实现就用的是令牌桶算法）。

#### 基础知识

无论什么算法，其根本都需要计算“被允许的请求数”，下文为统一说明，均用“令牌”（Token）一词指代“被允许的请求数”。另外，下表为该类算法的一般实现，其变种并不会影响其算法的本质，故不进行讨论。

|                    | 固定时间窗口算法                                             | 滑动时间窗口算法                                             | 令牌桶算法                                                   | 漏桶算法                                                     |
| ------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 令牌上限           | 固定或动态令牌上限（通常固定上限）                           | 固定或动态令牌上限（通常固定上限）                           | 固定或动态令牌上限（通常固定上限）                           | 固定或动态令牌上限（通常固定上限）                           |
| 当前可用令牌数     | 通过单个时间区间来计算                                       | 通过多个时间区间来计算                                       | 通过当前存量令牌数来计算                                     | 通过当前存量令牌数计算                                       |
| 令牌发放间隔       | 强调粗颗粒度间隔发放（如间隔1分钟）                          | 强调细颗粒度间隔发放（如间隔15秒）                           | 强调细颗粒度间隔发放（如间隔1秒）                            | 强调细颗粒度间隔放行（如间隔1秒）                            |
| 令牌发放时清空计数 | 是                                                           | 是。但一般只对最早的几个窗口进行清空                         | 否                                                           | 否                                                           |
| 资源开销           | 无需定时器，开销极小                                         | 无需定时器，开销极小                                         | 无需定时器，开销极小                                         | 每个会话都需要维护一个MPSC同步队列，与一个定时器来定时Poll队列，开销很大 |
| 实现难度           | 非常简单                                                     | 非常简单                                                     | 非常简单                                                     | 相对麻烦                                                     |
| 总评               | 由于需要清空计数，且颗粒太大，客户端可以在每次令牌发放前突发大量请求，造成“双倍突发流量”的问题 | 避免了“双倍突发流量”的问题，但因为有“清空计数”的操作，所以其控制精度不如令牌桶算法与漏桶算法 | 既可以通过存量令牌来处理突发请求，<br />又可以通过细颗粒度间隔的令牌发放来平滑地对请求进行限流。<br />其实云服务的CPU积分机制就与此类似 | 篇幅略长，见下文                                             |

漏桶算法与令牌桶算法都具有处理突发请求与平滑地对请求进行限流的能力。但漏桶算法的一个特别作用就是能对下游服务（最主要的就是数据库）进行限流。但对下游进行限流也是有代价的，它要求运维人员能够精准地估算下游服务吞吐量，否则可能造成下游服务一边处于空闲状态，上游服务却在限流的情况。

另外利用MPSC队列缓存请求，既降低了吞吐量，增加了内存开销与GC次数，导致常规用户体验更差，并加剧了DDoS攻击效果，这与我们引入防刷限流的目的背道而驰。（补充：通过阅读Turms服务端源码，您会发现Turms在处理客户端请求的流程中，代码都尽可能极致地“轻”，因此对每个用户会话都使用MPSC队列算是很重的操作了）

综上，Turms服务端最终使用`令牌桶算法`。

特别一提的是：相比于传统HTTP服务端，其接收并处理一次常规HTTP请求与响应的CPU与内存所需系统资源可能百倍于Turms服务端与其客户端交互所需系统资源（如：除开网络层协议头，Turms客户端一个请求的平均大小约32B）。因此并不需要把少部分用户的突发Turms客户端请求太当回事，可能处理上百个Turms客户端请求所用系统资源就跟处理一个HTTP请求差不多（当然，还有其他形态的CC攻击会造成大量资源消耗）。

其他：

* turms-gateway不支持并且目前也没计划支持全局的限流实现，原因是：全局限流通常是过度设计，全局限流为了时刻缓解DDoS攻击，增加Redis故障点，拉低整个系统的请求处理吞吐量，很多时候顾此失彼，得不偿失
* Turms暂不支持给不同类型的请求赋予不同的权重，如登录请求需要3个令牌，发送消息请求需要1个令牌
* turms-gateway支持运行时零停机更新令牌桶算法的配置

## 用户信息安全

对于大部分国内稍微有些网龄的群体，除非其具有很强的安全意识，他们的明文密码极有可能已经泄漏了（具体内容可以通过`社工库`进行了解）。结合大部分用户使用的密码都比较固定，因此不管服务端再怎么加密，其实“密码”的安全性还是偏低。

TODO

## 管理员安全

### 管理员认证与授权

#### 认证（Authentication）

认证：服务端基于常见的HTTP Basic authentication实现，确认HTTP请求的发送者是哪位管理员。

配置项：`turms.security.password.admin-password-encoding-algorithm`，其可选值为：`bcrypt`（默认）、`salted_sha256`与`noop`。

##### 支持的密钥加密算法

* BCrypt。其`cost`为硬编码的`10`（2^10 rounds），用于避免被脱库时，黑客通过彩虹表轻松破解出明文密码。
  
  其具体算法实现可查看`turms-server-common`子项目下Fork的`Bouncy Castle`源码实现：`org.bouncycastle.crypto.generators.BCrypt#generate`
  
* 加盐SHA-256

* NOOP（明文存储）

特别一提：`admin`集合里的`password`字段，其存储形式并不是`string`（如常见的Base64编码的字符串），而是原始的`byte[]`字节数据。

#### 授权（Authorization）

授权：服务端确认HTTP请求的发送者有什么权限做什么事

由于Turms自身权限管理的需求很简单，因此其设计与实现也比较简单，比如没有用户组、组角色、角色继承等概念，没有用户与角色的多对多关系。具体而言，Turms采用RBAC（基于角色的访问控制）设计方案。

##### Turms的RBAC模型

Turms的RBAC模型由`管理员（Admin）`、`角色（Role）`以及`权限（Permission）`这三个主体构成。一个用户只可以有一个角色，一个角色可以有多个权限。其中：

* 每个角色还具有一个字段`rank`，只有相对高rank的管理员可以增、删与修改相对低rank的管理员账号信息，如密码。
* 权限用于描述角色可以对什么资源进行什么操作，如对用户资源进行增删改查操作

##### 特殊角色——Root

`Root`是Turms内置的管理员角色，拥有所有管理员权限，并且不能被修改与删除。

##### 特殊根账号——turms

根账号`turms`用户拥有`Root`根角色权限，其账号名暂不支持修改（但可以通过修改硬编码的`im.turms.server.common.domain.admin.constant.AdminConst#ROOT_ADMIN_ACCOUNT`值来修改根账号名），其初始密码默认为`turms`，但用户可以通过配置项`turms.security.password.initial-root-password`在`admin`集合尚未创建、turms-service启动时，应用自定义的初始密码。

## 日志脱敏

TODO