# 开发基本规约

## 保守设计与激进设计

Java自身是一个很保守的语言，其大生态也非常保守。其设计原则是“提供一套安全的API，Java使用者怎么使用这些API，都不会导致Java内部出错”（除了Unsafe类），因此提供各种访问控制机制、内部内存拷贝与反复加锁。而Turms服务端代码的编写原则一般是“程序怎么跑的快，怎么写。只要Caller敢乱传或乱用数据，我们就直接报错或直接无视”。举例而言，Turms的`StringUtil`通过`jdk.internal.misc.Unsafe#getReference`获取`String`对象内部的`byte[]`对象，以避免内存拷贝，Caller需要自行保证不“胡作非为”。而Java自身提供的`String#getBytes()`为了保证使用者无法修改到内部的`byte[]`，因此是将该`byte[]`对象拷贝一份，再传给Caller。

因此在字符串实践中，对于一个常规基于Spring搭建的Web应用，一个HTTP请求从TCP字节流切割出来之后，可能需要反反复复在`String`、`StringBuilder`、`byte[]`、`HeapByteBuffer`、`DirectByteBuffer`等数据之间进行切换与拼接，最终一个业务层面上的String类型对象，被第三方库与Java内部拷贝5~30次是很常见的。

再以具体应用为例，如果我们使用Spring创建了一个Controller Bean，并在其中定义了一个返回值类型为`String`的API函数，以通过这个API返回Prometheus格式的度量数据。如果我们在这前提下做“最优雅”的写法，我们至少需要对这个内存对象做4次内存拷贝（不含系统内核刷数据到网卡部分；Turms通过优化，只需要做一次内存拷贝：即堆内存到堆外内存；这个度量数据实际大小约8K）：

1. 将Java的基本数据写入`StringBuilder`，此时堆内存->堆内存拷贝
2. StringBuilder#toString()，又一次堆内存拷贝
3. String#getBytes()，至少又一次堆内存拷贝
4. 将byte[]写到堆外内存DirectByteBuffer，以交给系统内核做写入数据操作

内存有效使用率极低，且注意上面只是一个最简单的API String响应返回的功能，实际应用中涉及到的流程更为复杂，因此一个流程下来，一个字符串被拷贝5~30次是非常常见的事情。因此我们经常能见到当一个HTTP服务端基于其语言主流生态构建时，一个常规Java Web应用所使用到的内存，可能是其等量C++ HTTP服务端的数十倍甚至百倍。

除了各种网络API，日志实现也需要频繁跟`String`打交道。而Turms在内存实践上就比通用实现高效太多了，Turms直接通过`PooledByteBufAllocator.DEFAULT`分配缓存了的堆外内存，并直接将Java的基本数据写入堆外内存块中。并且在整个过程中，我们避免使用Java自身的低效实现，从而避免无意义的堆到堆内存拷贝。

综上，尽管Java自身比较保守，Turms则相对强调激进，并以性能优先，而非“代码优雅”，必要时善用Unsafe类。当然，我们“激进”也是有限度的，诸如：1. 绝不替换Java内部类实现；2. 尽量不编写JNI与C语言代码

补充：

1. 对于Java语法糖级别的实践，我们的态度是“比较无所谓”，如`for (X x : Collection<X>)` （需要创建迭代器对象，多消耗至少几十B）与更高效的`for (int i = 0; i < length; i++)`，两者写法都允许
2. 除了保守的倾向，Java圈子还有一个很吊诡的现象，即“优化时选择性忽视”，比如一方面放任`String`与`StringBuilder`的内存拷贝，一个API处理流程下来，需要把数个`String`拷贝数十次。另一方面，就精打细算地研究JVM内存优化。Turms面对各种优化项，主要就是根据“性价比”，优先优化性价比高的部分，以避免缘木求鱼。

## 服务端开发的基本规约

### 代码编写策略的优先级

一般规则：性能（低时间复杂度与空间复杂度） > 代码可读性 > 设计模式

* 性能 > 代码可读性。如使用`long`，而不是`java.util.Date`或`java.time.Instant`来表示时间，以避免创建新对象以及时间转换时的计算；又比如`im.turms.server.common.infra.cluster.service.idgen.SnowflakeIdGenerator`类下的`nextIncreasingId`函数与`nextLargeGapId`函数重复了约10行代码，但我们不提取这公共代码出来，以避免开辟新方法栈（不考虑JVM的滞后Inline操作）。
* 性能 > 设计模式。如场景：
  * 遍历处理`String`中的`char[]`元素。如果使用责任链模式，则需要用不同的Handler类实现不同类别的处理逻辑，虽然这样可以把逻辑理得很清晰，但是每个Handler都需要遍历一遍`char[]`，因此处理的时间复杂度为`O(n*m)`（n为char[]长度，m为Handler个数），这种复杂度的代码在Turms服务端代码中是禁止的。此时，就需要反设计模式来编写代码，尽可能把处理逻辑都写在一次遍历中，且尽量不要新开函数区分逻辑（这条可选），而是用注释分块来区分不同的处理逻辑，以避免函数栈开销。
  * Protobuf模型的高效设计一直受人称道，但官方Java版本的Protobuf的代码实现是偏保守且低效的。比如Protobuf模型是Immutable的，只有其Builder是Mutable的，因此想要修改Protobuf模型，还得先`toBuilder()`成一个Builder，再重新创建一个新Protobuf模型实例，内存有效使用率低下（额外补充：其字符串解码实现也是非常地低效，比如其为了兼容低版本Java，采用了`char[]`进行编码，但新版本Java的String内部只存储`byte[]`，因此需要一次额外的类型转换）。而我们可控的代码是能不用Builder就不用Builder，避免无意义的内存消耗。


例外：如在极少数情况下，代码可读性优先于性能。以下文中提到的`禁止在客户端请求与管理员API请求的处理过程中使用反射`为例。尽管有这个规则，但如果请求中需要创建供数据库驱动使用的Entity对象时，那我们还是会通过反射创建并填充这个对象。因为如果不使用反射，就需要手写上百个字段序列化与反序列化逻辑，工作量巨大，且容易出错。而使用反射的收益性就很高，所以允许使用反射。

上述的示例还有很多，具体可以看Turms服务端代码。添加新代码时，只需要保证：新加的代码几乎没有任何时间或空间上的优化余地。如果还有优化空间，但收益很低且实现复杂，则允许后期再进行优化。

#### 线程与锁

* 禁止使用弹性线程池，如需创建新线程，则需要进行专门的代码审查

* 在客户端请求与管理员API请求的处理过程中，尽量不使用synchronized与Lock操作（包括可重入锁）。如果确实需要临界区，则优先考虑重构代码流程或用CAS技术替代。

#### 内存与GC

* 禁止对ByteBuf进行拷贝操作

* 对于网络I/O操作，禁止使用非池化或堆内存，只允许使用池化的直接内存

* 尽量不要创建新对象，尽量使用对象池。如设计中常见的：为了将不同层的数据模型进行逻辑分离，专门拆成了DTO与BO模型。Turms对于这种场景，会尽量使用一个数据模型，并通过自定义Jackson的序列化逻辑来实现符合DTO模型的响应

  另外：该规则会在Valhalla项目发布之后，发生改变，尤其是我们将移除大部分现有的对象池

* 尽量不要创建带多个unused字段的对象。如Turms用自定义的`QueryOptions`模型重构了MongoDB的`FindOptions`模型，其中一个原因就是`FindOptions`模型会被频繁使用，但其带有数十个无用字段

* 在客户端请求与管理员API请求的处理过程中，禁止使用Stream

* 关于“为什么一些看似可以用primitive参数的函数，依旧使用包装类”的问题。依旧使用包装类是因为：一个函数中的部分参数虽然可能看似可以使用primitives，但实际上这些primitives最终大概率会传给Java的集合类实现（如`Map<Long, Object>`）、只接受对象的函数（如`Object`类型、`Long`类型、泛型等）或作为类的Object字段等。因此，如果一个函数只是自顾自地使用primitive，那整条逻辑处理下来，这个primitive很可能在包装类与primitive之间反复转换多次。综上，Turms服务端在大部分情况下，统一使用包装类，以避免这样多次的转换。只有能保证primitive不会转成包装类，我们才统一使用primitive。

  另外，这既是为什么我们在[关于Valhalla项目](https://turms-im.github.io/docs/for-developers/system-resource-management.html#%E5%85%B3%E4%BA%8Evalhalla%E9%A1%B9%E7%9B%AE)中说“万物皆对象”的设计理念“像诅咒一样挥之不去”，一个primitive在复杂的逻辑中，很难不会被转换成包装类，无意义的对象浪费了大量的内存，也是为什么我们一直在等待Valhalla项目终结包装类、并支持诸如`List<int>`类型等特性。

#### 代理与反射

* 禁止使用动态代理技术（如Java动态代理、CGLib、Spring AOP等），尽量不使用代理或使用静态编译技术代替（如Lombok）

* 在客户端请求与管理员API请求的处理过程中，除非不使用反射就需要写大量繁杂代码，其他场景下禁止使用反射技术。如：Turms在对MongoDB的Entity模型的数百个字段进行序列化与反序列化时，使用了反射。

另外，如果有第三方依赖违背了以上原则，则根据性价比，排期对第三方依赖进行重构。

## 关于依赖库的使用

很多依赖库热衷于对底层实现进行抽象与封装，以实现“内部逻辑透明，使用者不用关心背后的逻辑”。这样的设计对于一些逻辑简单、要求快速上线、且不追求性能的应用来说比较实用。但随着一个项目越往后发展，越深入优化，这个不可控的抽象层，会成为问题排查、性能优化、功能定制的绊脚石。抽象层带来的问题，诸如：

* 需求迭代与版本更新严重滞后。如果我们的项目使用了一个抽象层的A依赖，A依赖封装了B依赖。如果我们需要往B依赖添加一个新特性或改Bug，通常的流程是：我们向B依赖的社区提Issue，运气好的话，平均2~4天得到回复。如果运气还很好，对方愿意改。假设改动不大，1周后相关PR被merged。可能等2周、1个月、甚至几个月，B依赖终于发布新版本。然后我们还要等A依赖更新B依赖版本，可能又过了2周、1个月、甚至几个月。等真到我们能使用到新特性，可能几个月已经过了。但更多的情况是，B依赖的维护者压根不愿意修改相关代码。

* 绝大部分知名依赖库，只关心功能实现，并不关心性能，基本是“功能够用，性能凑合就行”的态度。（Turms通过重构依赖代码，解决了大部分下述问题）诸如`mongo-java-driver`在进行API调用时，反反复复创建大量的中间对象；Lettuce在序列化传递给Redis的指令参数时需要反复扩充内存，并且该Cache的内存数据也没Cache；Spring的AOP常用于代理Controller层方法调用，可用于捕获解析后参数，进行日志打印（WebFilter无法获得解析后的参数）。但AOP会给一个方法徒增19个stacks并大量使用反射，从AOP代理开始到Controller方法层的调用所需时间甚至比Turms内部业务处理时间还长（额外补充：AOP是个非常糟糕的设计，Spring应该为Controller层采用的责任链设计）。综上，很多的知名Java依赖库的代码质量并不高，甚至代码性能与质量堪忧。

* 关注于抽象实现的依赖库在与响应式编程结合时，在问题排查问题上，会给开发者带来地狱级的体验，尤其是Bug与需要手动释放的内存相关。在常规问题排查上，我们通常可以通过栈信息来很快的排查出问题。但在响应式编程中，这样的方法通常行不通，我们更多的靠逻辑推理来排查问题。即熟读上下游代码（包括依赖包内的代码），推演代码可能经过的所有流程。

  如果代码的抽象层少、且调用关系扁平，这个排查过程其实很简单，可能我们只用在一个类内的几十行代码上扫几眼，就能大概知道出现问题的原因了。但如果流程中，使用到了大量“封装、抽象，用户无需关注底层实现逻辑”依赖库，地狱级体验就来了。原本我们可能只需要一个小数十行的函数就能实现所有相关逻辑。但如果基于抽象库去实现相关功能，我们在问题排查时，可能要查看的代码可能是A抽象类(A1,A2,A3...)类->B抽象类(B1,B2,B3...)->C抽象类(C1,C2,C3...)->...，在数十个类、数十个方法间跳转，并进行推理。其中最典型的对照例子就是：Turms的`im.turms.gateway.access.client.websocket.WebSocketServerFactory#getHttpRequestHandler`在一个小数十行的函数内实现了一组WebSocket握手逻辑。但如果这套逻辑让Spring来实现，它会将各个不同包下的类，各种逻辑东拼西凑地混在一起，在问题排查时，如果还伴随着一些需要手动释放的内存，地狱级的问题排查体验就来了。

* 部分依赖库在一些地方会自行Suppress异常，上层应用代码无法感知。由于出问题的时候，底层库代码与上层应用代码在大部分情况下，是跑在不同的栈上的。除非底层依赖库支持全局的异常回调，否则上层应用甚至无法感知异常的发生。对于一些Trivial级别的错误，上层应用感知不到也没关系。但如果是一些上层应用非常关注的异常（如RPC的TCP连接的异常断开），这将是引发整个系统异常与失序的导火索了。

* 部分知名依赖库的开发人员甚至缺乏最基本的安全常识。比如`Log4j`的开发人员竟然添加代码来自动检测预备打印的字符串中是否存在`${jndi}`模式，如果存在则调用对应的JNDI服务，并默认开启该功能。作为专门编写日志依赖库的开发人员竟然如此缺乏安全常识，且还通过了PR review。

另一方面，自研能规避掉上述所有问题，在提高代码可控性的同时，也极大地降低了研发难度与问题排查难度，并提升代码性能与资源利用率。

综上，Turms项目在引用一个类库时，通常不引入抽象封装库（如Spring），而仅引入实现库。对依赖库中需要性能优化或逻辑优化的点，会直接在Turms项目内部进行重构。结合考虑到自研的难易程度与代码可控性，我们在大部分情况下会尽可能选择自研。

补充：

* 如果移除Spring框架，就能让Controller层的实现变得非常清晰、且实现高效（我们可以直接将Response数据写入DirectByteBuf，避免无意义的堆内存拷贝），也能让代码变得非常可控。举例来说：

  1. Turms的日志实现需要读取用户配置，而读取用户配置要先等Spring加载完用户配置，而Spring在加载用户配置之前又会打日志，因此这里有个循环依赖的问题，而我们不得不通过一些迂回手段去避免Spring加载完配置前打印日志。
  2. 我们预期Valhalla项目能在2023年的Java 20/21版本中，发布[Value Objects](https://openjdk.java.net/jeps/8277163)、[Primitive Classes](https://openjdk.java.net/jeps/401)、[Classes for the Basic Primitives](https://openjdk.java.net/jeps/402)这三个将Java发展到新纪元的特性，但Valhalla项目的Side Effect巨大，而Spring作为Java生态的基石之一，几乎不可能敢如此激进地支持Valhalla项目，因此如果我们还需要等待Spring支持Valhalla项目，那基本是遥遥无期了。相反的，如果我们移除了Spring，由于Turms所有重要模块基本都自研，且我们追踪Valhalla的发展数年，很熟悉其设计，集成Valhalla项目大致只需1~2周时间。

  但我们之所以目前还没移除Spring框架，是因为从零写一套Controller/JSON序列化/自动生成Swagger API文档/Actuator/IoC/配置读取实现并适配，估计需要两周至三周时间，而这么长的时间足够我们做IM系统中很多更为重要的优化与特性，且Admin API相比客户端API并不是那么在意性能与可维护性，因此目前只是对Spring相关的低效实现进行了重构，等重要的IM系统优化都完成后再来移除整个Spring。

  而在移除Spring之后，我们就能保证Turms服务端能够主动地统筹所有代码，即所有代码都为Turms服务，而不是Turms要去适配其他框架的代码。

* Turms在整个Java生态中，唯一信任的依赖是：Netty

## 异常捕获与打印

作用：理解Turms服务端的异常捕获与打印原则能够帮助开发者快速定位异常并发现异常的Root Cause。

在响应式编程中，最为人所诟病的就是该编程范式下的异常通常非常难定位，其堆栈信息基本没用。如果开发者在响应式编程模式下胡乱打印异常日志，很有可能调式者甚至无法根据日志判断这个异常是从哪里抛出来的，更别说反推其执行代码了。

但其实好的异常日志打印原则与实践都比较简单，并且如果遵循该原则，定位异常通常也就几秒或几分钟的事情。其基本原则就是**最下游代码抛异常，无需打印。中游代码如果要做异常Translate，那就Translate后继续往上抛，无需打印；最上游接异常并打印**。至于什么代码算是“最上游”，调用`subscribe()`的代码就算“最上游”。该原则实践起来其实也很简单，只是响应式编程里的异常捕获“看起来”比较复杂而已。举例而言，在turms-service服务端中的`im.turms.service.access.servicerequest.dispatcher.ServiceRequestDispatcher#dispatch0`函数下，有段“根据Service层的处理结果，向相关用户发送通知”的操作，其代码如下：

```java
return result
        .name(CLIENT_REQUEST_NAME)
        .tag(CLIENT_REQUEST_TAG_TYPE, requestType.name())
        .metrics()
        .defaultIfEmpty(RequestHandlerResultFactory.NO_CONTENT)
        .doOnEach(signal -> {
            if (!signal.isOnNext()) {
                return;
            }
            RequestHandlerResult requestResult = signal.get();
            if (requestResult == null || requestResult.code() != ResponseStatusCode.OK) {
                return;
            }
            notifyRelatedUsersOfAction(requestResult, userId, deviceType)
                    .contextWrite(signal.getContextView())
                    .subscribe(null, t -> {
                        try (TracingCloseableContext ignored = context.asCloseable()) {
                            LOGGER.error("Failed to notify related users of the action", t);
                        }
                    });
        })
        ...
```

如上文所述，该段代码通过`notifyRelatedUsersOfAction`函数进行通知下发操作，其内部实现我们并不关心，我们只要在最上游通过`subscribe(...)`保证能捕获其可能抛出的异常并打印即可。