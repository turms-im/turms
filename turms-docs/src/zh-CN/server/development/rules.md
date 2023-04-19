# 基本开发规约

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

  另外，这既是为什么我们在[关于Valhalla项目](https://turms-im.github.io/docs/zh-CN/server/module/system-resource-management#%E5%85%B3%E4%BA%8Evalhalla%E9%A1%B9%E7%9B%AE)中说“万物皆对象”的设计理念“像诅咒一样挥之不去”，一个primitive在复杂的逻辑中，很难不会被转换成包装类，无意义的对象浪费了大量的内存，也是为什么我们一直在等待Valhalla项目终结包装类、并支持诸如`List<int>`类型等特性。

#### 代理与反射

* 禁止使用动态代理技术（如Java动态代理、CGLib、Spring AOP等），尽量不使用代理或使用静态编译技术代替（如Lombok）。

  唯一的例外情况：Turms服务端的插件机制中，使用Java的动态代理去代理JavaScript编写的插件。

* 在客户端请求与管理员API请求的处理过程中，除非不使用反射就需要写大量繁杂代码，其他场景下禁止使用反射技术。如：Turms在对MongoDB的Entity模型的数百个字段进行序列化与反序列化时，使用了反射。

另外，如果有第三方依赖违背了以上原则，则根据性价比，排期对第三方依赖进行重构。

### 文本格式

#### toString()文本格式

Java项目`toString()`实现的文本格式五花八门，甚至Java自身的内部代码都有很多风格不一致的文本格式。就括号的风格来说，既有Java record默认的`[key=value]`格式，也有Lombok生成的`(key=value)`格式，还有Google AutoValue生成的`{key=value}`格式。

为了实现文本格式统一，Turms服务端项目统一采用如下格式：

* 对于文本的前缀与后缀，分别使用`{`与`}`，而不是`[]`或`()`。因为在Turms的文本格式设计中，`[]`指代数组，`()`指代需要特别标记，以让重要信息更为醒目。具体规则见下文的`服务端运行日志与异常文本格式`。

* 在键与值之间使用主流的`=`，而不是`: `。

* 对于字符串值，需要使用`""`对值进行包裹；对于其它非数组值，均采用值的`toString()`形式；对于数组值，则使用`[]`来包括数组中的值。

  比如：`ClassName{key1=value, key2=[value1, value2]}`

**注意：Turms服务端目前暂未统一`toString()`的文本格式，但上文所述内容是之后的改进方向。**

#### 服务端运行日志与异常文本格式

因为日志与异常的文本格式设计存在非常多的细节，而很多常见实践的原则又是互相冲突的，并且Java领域也没有一个统一的最佳实践，所以几乎所有的大中开源项目（包括Java自身的源码）都做不到文本格式统一，而是各种文本格式混合使用，具体用啥格式主要就靠工程师当下的“感觉”。

因此本节专门讲解Turms服务端采用哪些文本格式，与为什么不采用另外一些常见的文本格式，以减少读者在实践中困惑。

##### 统一格式的重要性

对于一些文本格式规则，可能读者在阅读单条日志，感觉不出规则之间有什么差别。但当读者需要翻阅数十条，甚至数百条、数千条各种不同的日志时，就能明白使用规范统一的文本格式有多么地节省阅读精力了。

##### 具体规则

简单来说：

* 文本中的重要信息尽量放句未。重要信息通常是变量。
* 当重要信息在句末时，需要使用`: `来分割重要信息与其他文本。如：使用`Could not find the class: my.company.Main`，而不使用`The class (my.company.Main) could not be found`。
* 句子不需要省略冠词`a`、`an`与`the`。特别强调这点是因为大部分知名大中开源项目偏向于省略冠词。
* 对于名词短语，通常使用限制性同位语，而非定语名词。比如，限制性同位语：`The collection "messasge"`或`The setting "turms.whatever.min"`；定语名词：`The "messasge" collection`与`The "turms.whatever.min" setting`。
* 特殊符号的作用与使用：

| 作用                                 | 使用的符号                 | 在句中时                                                     | 与`: `搭配时                                                 | 与数组搭配时                                                 | 常见例子                           |
| ------------------------------------ | -------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ---------------------------------- |
| 表示数组值                           | `[,]`                      | 使用`[value]`格式。<br />如`Detected illegal operations [CREATE, DELETE] on the collection "message"` | 使用`: [value]`格式。<br />如`Detected illegal operations: [CREATE, DELETE]` |                                                              |                                    |
| 表示区间                             | `[..]`闭区间，`(..)`开区间 | 如：`[1..2]`、``                                             |                                                              |                                                              |                                    |
| 包裹需要特别分离以达到醒目效果的信息 | `()`                       | 使用`(value)`格式。<br />如`The path (/turms/1.txt/) is illegal` | 无需使用`()`，使用`: value`格式即可。<br />如`Could not find any resource from the path: /turms/1.txt` | 无需使用`()`，使用`[value]`格式即可。<br />如`The paths [/1.txt, /2.txt] are illegal` | 对象、枚举值、路径、域名、字段引用 |
| 包裹键值对                           | `{}`                       | 使用`{key=value}`格式。<br />如                              | 使用`: {key=value}`格式                                      | 使用`[{key=value}, {key=value}]`格式                         |                                    |
| 包裹名称或字符串值                   | `""`                       | 使用`"value"`格式。<br />如`The property "turms.whatever.min" must be greater than 0`；`The setting name "abc123" should not contain any digit` | 使用`: "value"`格式。<br />如`Unknown property: "turms.whatever.min"` | 使用`["value", "value"]`格式。<br />如`The properties ["turms.whatever.min",  "turms.whatever.max"] are unknown` | 字段名、参数名、数据库集合名       |

* 名称与引用的区别

  先举一个相对容易理解的例子，以字段的名称与引用为例，假设有一个类`com.abc.Song`（歌曲）中有一个字段`name`，则该字段的名称是`name`，而名称在句中被使用时需要加上双引号`""`，如`The field "name" contains illegal characteres`。而字段的引用是`com.abc.Song#name`，而引用在句中被使用时需要加上括号`()`，如`The field (com.abc.Song#name) should be accessible`。
  
  但在实际开发过程中，我们会发现很多字符串本身是可以有多种解释的。比如有一个类的名称是`com.my.Main`，那这个名称既可以被解释为`类的名称`，也可以解释为`类的引用`。而考虑到类名称不会出现像上述`名称`可能带来的严重歧义，且大多数中大知名开源项目的实践也不用`""`包裹类名，因此对于类名，Turms在设计时，统一将其作为`类的引用`，而非`类的名称`来解释，故此类的引用需要遵循`()`的使用规则，而非`""`的使用规则。

下一小节将讲解为什么Turms要这么设计，以及为什么不使用一些其他常见的设计。

**TODO：稍后更新**

## 关于依赖库的使用

很多依赖库热衷于对底层实现进行抽象与封装，以实现“内部逻辑透明，使用者不用关心背后的逻辑”。这样的设计对于一些逻辑简单、要求快速上线、且不追求性能的应用来说比较实用。但随着一个项目越往后发展，越深入优化，这个不可控的抽象层，会成为问题排查、性能优化、功能定制的绊脚石。抽象层带来的问题，诸如：

* 需求迭代与版本更新严重滞后。如果我们的项目使用了一个抽象层的A依赖，A依赖封装了B依赖。如果我们需要往B依赖添加一个新特性或改Bug，通常的流程是：我们向B依赖的社区提Issue，运气好的话，平均2~4天得到回复。如果运气还很好，对方愿意改。假设改动不大，1周后相关PR被merged。可能等2周、1个月、甚至几个月，B依赖终于发布新版本。然后我们还要等A依赖更新B依赖版本，可能又过了2周、1个月、甚至几个月。等真到我们能使用到新特性，可能几个月已经过了。但更多的情况是，B依赖的维护者压根不愿意修改相关代码。
* 绝大部分知名依赖库，只关心功能实现，并不关心性能，基本是“功能够用，性能凑合就行”的态度。（Turms通过重构依赖代码，解决了大部分下述问题）诸如：

  * `mongo-java-driver`在进行API调用时，反反复复创建大量的中间对象。对于默认配置对象，也不做Cache。
  * Lettuce在序列化传递给Redis的指令参数时需要反复扩充内存，并且该Cache的内存数据也没Cache。
  * Log4j2竟然使用`getBytes`读取字符串的数据，并使用`StringBuilder`做日志的拼接（对比Turms的日志实现直接使用`String`内部的`byte[] value`数据，并使用Netty提供的`io.netty.buffer.AbstractByteBufAllocator#directBuffer`来拼接日志并做日志输出）。（补充：如果读者对日志实现感兴趣，可以阅读[日志实现](https://turms-im.github.io/docs/zh-CN/server/module/observability#%E8%87%AA%E7%A0%94%E5%AE%9E%E7%8E%B0-%E6%8B%93%E5%B1%95%E7%9F%A5%E8%AF%86)，了解Turms是如何实现日志的）
  * 在Protobuf的官方Java实现中，其字符串解码实现也是非常地低效，比如它为了兼容低版本Java，采用了`char[]`进行编码，但新版本Java的String内部只存储`byte[]`，因此需要一次无意义的内存拷贝（注意：字符串本身就是客户端请求中最大的数据）。
  * Spring是低效代码的典型代表，如：
    * `org.springframework.core.codec.CharSequenceEncoder`在处理`UTF-8`编码的字符串时，会以1个字符对应3字节来开辟DirectByteBuffer用于输出。换言之，上述的8K Prometheus数据，光这块Spring就需要用2.4MB，多用1.6MB。当然，Spring还要更低效，因为它`String#getBytes(...)`的时候还要进行字符串拷贝。
    * 导出巨大的堆转储文件时，`spring-boot-actuator:v2.6.6`竟然不支持零拷贝（见`org.springframework.boot.actuate.management.HeapDumpWebEndpoint.TemporaryFileSystemResource#isFile`）
    * Spring的AOP常用于代理Controller层方法调用，可用于捕获解析后参数，进行日志打印（WebFilter无法获得解析后的参数）。但AOP会给一个方法徒增19个stacks并大量使用反射，从AOP代理开始到Controller方法层的调用所需时间甚至比Turms内部业务处理时间还长（额外补充：AOP是个非常糟糕的设计，Spring应该为Controller层采用的责任链设计）。


  综上，很多知名Java依赖库的代码质量并不高，甚至代码性能与质量堪忧，源码读得让人触目惊心。相反，读者可以参考Turms服务端是怎么编码，以把各种细节实现优化到极致的。

* 关注于抽象实现的依赖库在与响应式编程结合时，在问题排查问题上，会给开发者带来地狱级的体验，尤其是Bug与需要手动释放的内存相关。在常规问题排查上，我们通常可以通过栈信息来很快的排查出问题。但在响应式编程中，这样的方法通常行不通，我们更多的靠逻辑推理来排查问题。即熟读上下游代码（包括依赖包内的代码），推演代码可能经过的所有流程。

  如果代码的抽象层少、且调用关系扁平，这个排查过程其实很简单，可能我们只用在一个类内的几十行代码上扫几眼，就能大概知道出现问题的原因了。但如果流程中，使用到了大量“封装、抽象，用户无需关注底层实现逻辑”依赖库，地狱级体验就来了。原本我们可能只需要一个小数十行的函数就能实现所有相关逻辑。但如果基于抽象库去实现相关功能，我们在问题排查时，可能要查看的代码可能是A抽象类(A1,A2,A3...)类->B抽象类(B1,B2,B3...)->C抽象类(C1,C2,C3...)->...，在数十个类、数十个方法间跳转，并进行推理。

  其中最典型的对照例子就是：Turms的`im.turms.gateway.access.client.websocket.WebSocketServerFactory#getHttpRequestHandler`在一个小数十行的函数内实现了一组WebSocket握手逻辑。但如果这套逻辑让Spring来实现，它会将各个不同包下的类，各种逻辑东拼西凑地混在一起，在问题排查时，如果还伴随着一些需要手动释放的内存，地狱级的问题排查体验就来了。原本几十行代码能解决的事情，Spring这样的库需要花上千行代码。比如WebFlux内部就有多套Web底层实现，美其名曰“封装、抽象，用户无需关注底层实现逻辑”。

* 部分依赖库在一些地方会自行Suppress异常，上层应用代码无法感知。由于出问题的时候，底层库代码与上层应用代码在大部分情况下，是跑在不同的栈上的。除非底层依赖库支持全局的异常回调，否则上层应用甚至无法感知异常的发生。对于一些Trivial级别的错误，上层应用感知不到也没关系。但如果是一些上层应用非常关注的异常（如RPC的TCP连接的异常断开），这将是引发整个系统异常与失序的导火索了。

* 部分知名依赖库的开发人员甚至缺乏最基本的安全常识。比如`Log4j`的开发人员竟然添加代码来自动检测预备打印的字符串中是否存在`${jndi}`模式，如果存在则调用对应的JNDI服务，并默认开启该功能。作为专门编写日志依赖库的开发人员竟然如此缺乏安全常识，且还通过了PR review。

另一方面，自研能规避掉上述所有问题，在提高代码可控性的同时，也极大地降低了研发难度与问题排查难度，并提升代码性能与资源利用率。

综上，Turms项目在引用一个类库时，通常不引入抽象封装库（如Spring），而仅引入实现库。对依赖库中需要性能优化或逻辑优化的点，会直接在Turms项目内部进行重构。结合考虑到自研的难易程度与代码可控性，我们在大部分情况下会尽可能选择自研。

补充：Java的生态虽然繁荣，但高质量的库其实很少，所以大部分对性能有追求的中大型Java开源项目通常也是尽量自研各种功能模块，而不使用第三方依赖库，比如：[Elasticsearch](https://github.com/elastic/elasticsearch)、[Cassandra](https://github.com/apache/cassandra)、[Ignite](https://github.com/apache/ignite)。另外，在整个Java生态中，我们目前唯一信任其开发人员技术水平的库是：Netty

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

### 有且仅自定义继承自`RuntimeException`的异常类

在Turms服务端项目中，有且仅自定义继承自`RuntimeException`的异常类，禁止自定义继承自`Exception`（`Checked Exception`）的异常类。

关于使用`Checked Exception`，还是`Unchecked Exception`的讨论至今都是众说纷纭，但如今不少文章直接批评`Checked Exception`是Java的设计败笔，像是Kotlin/Scala/C#这些后来的语言甚至压根没有`Checked Exception`这一概念，而如今大部分大中知名开源项目一般也只自定义`RuntimeException`的子类，而不自定义`Checked Exception`的子类。

常见的认为`Checked Exception`是糟糕设计的原因比如有：

* 作为第三方库/下游代码，`Checked Exception`存在接口签名版本化兼容问题。

* 作为大中项目，当子模块都使用`Checked Exception`，则上游代码的接口可以最终会声明数十个异常，当接口的异常声明做增删改后，牵一发动全身。

* Java代码内部，自己都存在异常设计冲突。比如Java Streams设计中的Lamba自己都不支持抛`Checked Exception`，对于在Stream里的Lambda，其实现必须当成处理（通常是错误实践）或将其转换成`Unchecked Exception`（丢失了使用`Checked Exception`的意义），Java内部甚至因此还引入了`UncheckedIOException`。

* 在实践中，人们经常会回避`Checked Exception`被设计出来的目的，导致不如不用`Checked Exception`，比如：

  * 直接捕获所有`Exception`
  * 将`Checked Exception`翻译成`RuntimeException`。如`try { ... } catch (Exception e) { throw new RuntimeException(e); }`
  * 由于栈太深，为了避免污染上游代码，直接在下游进行无意义的捕获，甚至有可能错误地直接`catch (Exception e) { do nothing }`

* 不少开发者会错误地理解异常设计，然后错误地去自定义异常。比如说不少开发者认为`如果是上游代码可以避免的异常，则用RuntimeException的子类。如果是上游代码不可避免的异常，则用Checked Exception`，类似的观点就非常盲目乐观与缺乏实际项目经验与编码经验了，因为下游抛出的异常到底可不可以处理取决于上游代码逻辑，而不是下游代码的臆想。

  举例来说Turms服务端的插件模块在加载插件时，可能插件的类加载器会抛出`NoClassDefFoundError`，如果按Java早期团队的说法`An Error is a subclass of Throwable that indicates serious problems that a reasonable application should not try to catch`，那插件模块的上游代码就不应该捕获`Error`，但Turms作为一个服务端不可能因为加载了一个有问题的类插件，就让服务端异常，因此上游代码真正合理的做法是捕获这些`Error`，而不是让服务端直接奔溃，陷入异常状态。

而对于Turms服务端项目来说，考虑到`Checked Exception`唯一能真正发挥作用的场景是：在个别场景中，在设计下游功能模块时，已知上游调用方代码需要根据下游抛出的各种异常做异常区分，为了保证上游没有遗漏处理一些下游抛出的异常，因此可以考虑使用`Checked Exception`。但由于这种场景非常地少，而且根据上游调用方代码逻辑来设计下游代码也是非常糟糕的实践。

因此为了规避`Checked Exception`带来了各种问题、统一异常设计风格，与避免把时间浪费在“为什么同样都是某类的模块，A模块用了某类异常，B模块用了某类异常”这类无关紧要的争论上，在Turms服务端项目中，有且仅自定义继承自`RuntimeException`的异常类，禁止自定义继承自`Exception`（`Checked Exception`）的异常类。