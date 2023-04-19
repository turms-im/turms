# Basic Development Rules

## Conservative Design vs. Radical Design

Java itself is a very conservative language, and its ecology is also very conservative. Its design principle is to "provide a set of safe APIs, and how Java users use these APIs will not cause Java internal errors" (except for Unsafe classes), so various access control mechanisms, internal memory copies and repeated locking are provided. The code writing principle of the Turms server is generally "How to run the program fast, how to write. As long as the Caller dares to pass or use data indiscriminately, we will report an error or ignore it directly." For example, `StringUtil` of Turms uses `jdk.internal.misc.Unsafe#getReference` to obtain the `byte[]` object inside the `String` object to avoid memory copying, and the Caller needs to ensure that it does not "do anything wrong". The `String#getBytes()` provided by Java itself is to ensure that the user cannot modify the internal `byte[]`, so the `byte[]` object is copied and passed to the Caller.

Therefore, in the practice of strings, for a conventional Spring-based Web application, after an HTTP request is cut from the TCP byte stream, it may need to be repeated in `String`, `StringBuilder`, `byte[]`, ` HeapByteBuffer`, `DirectByteBuffer` and other data are switched and spliced. Finally, it is very common for a String type object on the business level to be copied 5 to 30 times by third-party libraries and Java internally.

Taking a specific application as an example, if we use Spring to create a Controller Bean, and define an API function with a return value type of `String` in it, so as to return the measurement data in Prometheus format through this API. If we do the "most elegant" writing method under this premise, we need to make at least 4 memory copies of this memory object (excluding the part where the system kernel brushes data to the network card; Turms is optimized and only needs to do one memory copy: that is, the heap Memory to off-heap memory; the actual size of this measurement data is about 8K):

1. Write the basic data of Java into `StringBuilder`, at this time heap memory -> heap memory copy
2. StringBuilder#toString(), another heap memory copy
3. String#getBytes(), at least another heap memory copy
4. Write byte[] to the off-heap memory DirectByteBuffer to hand over to the system kernel for writing data operations

The effective memory usage rate is extremely low, and note that the above is only the simplest API String response function, and the process involved in the actual application is more complicated, so it is very common for a string to be copied 5 to 30 times after a process things. Therefore, we often see that when an HTTP server is built based on the mainstream ecology of its language, the memory used by a conventional Java Web application may be tens or even hundreds of times that of its equivalent C++ HTTP server.

In addition to various network APIs, log implementations also need to deal frequently with `String`. In terms of memory, Turms is much more efficient than general-purpose implementations. Turms directly allocates cached off-heap memory through `PooledByteBufAllocator.DEFAULT`, and directly writes Java basic data into off-heap memory blocks. And throughout the process, we avoid using Java's own inefficient implementation, thereby avoiding meaningless heap-to-heap memory copies.

In summary, although Java itself is relatively conservative, Turms is relatively radical, and prioritizes performance rather than "elegant code", and makes good use of Unsafe classes when necessary. Of course, our "radical" is also limited, such as: 1. Never replace the Java internal class implementation; 2. Try not to write JNI and C language code

Replenish:

1. For the practice of Java syntax sugar level, our attitude is "relatively indifferent", such as `for (X x : Collection<X>)` (need to create an iterator object, consume at least dozens of B) and more efficient `for (int i = 0; i < length; i++)`, both are allowed
2. In addition to the conservative tendency, there is also a very paradoxical phenomenon in the Java circle, that is, "selective neglect during optimization". A `String` is copied dozens of times. On the other hand, study JVM memory optimization on a budget. Turms faces various optimization items, mainly based on "cost-effectiveness", prioritizing the parts with high cost-effectiveness, so as to avoid trying to find fish.

## Basic protocol for server-side development

### Prioritization of coding strategies

General rules: performance (low time and space complexity) > code readability > design patterns

* Performance > Code readability. For example, use `long` instead of `java.util.Date` or `java.time.Instant` to represent time to avoid creating new objects and calculations during time conversion; another example is `im.turms.server.common. The `nextIncreasingId` function and `nextLargeGapId` function under the infra.cluster.service.idgen.SnowflakeIdGenerator` class repeat about 10 lines of code, but we do not extract this common code to avoid opening up a new method stack (regardless of JVM lag Inline operation).
* Performance > Design Mode. Such as the scene:
  * Iterate over the `char[]` elements in `String`. If you use the chain of responsibility mode, you need to use different Handler classes to implement different types of processing logic. Although this can make the logic clear, each Handler needs to traverse `char[]`, so the time complexity of processing It is `O(n*m)` (n is the length of char[], and m is the number of Handlers). Codes of this complexity are prohibited in the Turms server code. At this time, it is necessary to write the code in an anti-design mode, write the processing logic in one traversal as much as possible, and try not to open a new function to distinguish logic (this is optional), but use comments to divide different processing logic to avoid function stack overhead.
  * The efficient design of the Protobuf model has always been praised, but the code implementation of the official Java version of Protobuf is conservative and inefficient. For example, the Protobuf model is Immutable, and only its Builder is Mutable. Therefore, if you want to modify the Protobuf model, you must first `toBuilder()` into a Builder, and then recreate a new Protobuf model instance. The effective memory usage rate is low (additional supplement : Its string decoding implementation is also very inefficient. For example, in order to be compatible with lower versions of Java, it uses `char[]` for encoding, but the String of the new version of Java only stores `byte[]` inside, so an additional type conversion). And our controllable code is to use the Builder without the Builder to avoid meaningless memory consumption.


Exception: As in rare cases, code readability takes precedence over performance. Take the "Prohibition of using reflection during the processing of client requests and admin API requests" mentioned in the article as an example. Despite this rule, if the request needs to create an Entity object for use by the database driver, then we will still create and populate this object through reflection. Because if you don't use reflection, you need to write hundreds of field serialization and deserialization logics by hand, which is a huge workload and error-prone. The profitability of using reflection is very high, so reflection is allowed.

There are many more examples of the above, see the Turms server code for details. When adding new code, just make sure that there is hardly any room for time or space optimization in the newly added code. If there is still room for optimization, but the benefits are low and the implementation is complex, allow for later optimization.

#### Threads and locks

* The use of elastic thread pools is prohibited, creating new threads requires a dedicated code review

* Try not to use synchronized and Lock operations (including reentrant locks) during the processing of client requests and admin API requests. If a critical section is really needed, priority should be given to refactoring the code flow or replacing it with CAS technology.

#### Memory and GC

* Prohibit copying of ByteBuf

* For network I/O operations, the use of non-pooled or heap memory is prohibited, only pooled direct memory is allowed

* Try not to create new objects, try to use the object pool. As is common in design: In order to logically separate the data models of different layers, the DTO and BO models are specially disassembled. For this scenario, Turms will try to use a data model and implement a response that conforms to the DTO model by customizing Jackson's serialization logic

  Also: this rule will change after Project Valhalla is released, in particular we will be removing most of the existing object pools

* Try not to create objects with multiple unused fields. For example, Turms reconstructed MongoDB's `FindOptions` model with a custom `QueryOptions` model. One of the reasons is that the `FindOptions` model is frequently used, but it has dozens of useless fields.

* During the processing of client requests and admin API requests, the use of Stream is prohibited

* Regarding the question of "why some functions that seem to be able to use primitive parameters still use wrapper classes". Wrapper classes are still used because: although some parameters in a function may seem to be able to use primitives, in fact, these primitives will eventually be passed to Java collection class implementations with a high probability (such as `Map<Long, Object>`). Functions that accept objects (such as `Object` types, `Long` types, generics, etc.) or Object fields as classes, etc. Therefore, if a function just uses primitives on its own, after the entire logic is processed, this primitive is likely to be repeatedly converted between the wrapper class and the primitive many times. To sum up, in most cases, the Turms server uniformly uses wrapper classes to avoid such multiple conversions. Only when we can guarantee that the primitive will not be converted into a wrapper class, we will use the primitive uniformly.

  In addition, this is why we are in [About the Valhalla project](https://turms-im.github.io/docs/server/module/system-resource-management#%E5%85%B3%E4%BA%8Evalhalla%E9%A1%B9%E7%9B%AE) said that the design concept of "everything is an object" "lingers like a curse". It is difficult for a primitive not to be converted into a package in complex logic. Classes, meaningless objects waste a lot of memory, which is why we've been waiting for the Valhalla project to finalize wrapper classes and support features like the `List<int>` type.

#### Proxy and Reflection

* Do not use dynamic proxy technology (such as Java dynamic proxy, CGLib, Spring AOP, etc.), try not to use proxy or use static compilation technology instead (such as Lombok).

  The only exception: In the plug-in mechanism of the Turms server, Java's dynamic proxy is used to proxy plug-ins written in JavaScript.

* During the processing of client requests and admin API requests, unless you need to write a lot of complicated code without using reflection, the use of reflection technology is prohibited in other scenarios. For example: Turms uses reflection when serializing and deserializing hundreds of fields of MongoDB's Entity model.

In addition, if there is a third-party dependency that violates the above principles, the third-party dependency will be refactored according to the cost-effectiveness schedule.
### text format

#### toString() text format

The text format implemented by the Java project `toString()` is varied, and even the internal code of Java itself has many text formats with inconsistent styles. As far as the bracket style is concerned, there are not only the default `[key=value]` format of Java record, but also the `(key=value)` format generated by Lombok, and the `{key=value}` format generated by Google AutoValue.

In order to achieve a unified text format, the Turms server project uniformly adopts the following format:

* For the prefix and suffix of the text, use `{` and `}` respectively, instead of `[]` or `()`. Because in the text format design of Turms, `[]` refers to an array, and `()` refers to a special mark to make important information more eye-catching. For specific rules, see `Server operation log and exception text format` below.

* Use the mainstream `=` instead of `:` between keys and values.

* For string values, you need to use `""` to wrap the value; for other non-array values, use the `toString()` form of the value; for array values, use `[]` to include the value in the array .

  For example: `ClassName{key1=value, key2=[value1, value2]}`

**Note: The Turms server has not yet unified the text format of `toString()`, but the content described above is the direction of improvement in the future. **

#### Server running log and exception text format

Because there are many details in the text format design of logs and exceptions, and the principles of many common practices are in conflict with each other, and there is no unified best practice in the Java field, almost all large and medium-sized open source projects (including Java itself) source code) cannot achieve a unified text format, but a mix of various text formats, and the specific format depends mainly on the current "feeling" of the engineer.

Therefore, this section specifically explains which text formats the Turms server uses, and why some other common text formats are not used, so as to reduce readers' confusion in practice.

##### The Importance of a Uniform Format

For some text formatting rules, readers may not feel the difference between the rules when reading a single log. But when readers need to read dozens, even hundreds, or thousands of different logs, they can understand how much energy saving in reading using a standardized and unified text format.

##### specific rules

simply put:

* Important information in the text should be put in the end of the sentence as much as possible. Vital information is usually a variable.
* When the important information is at the end of the sentence, you need to use `:` to separate the important information from other texts. For example: use `Could not find the class: my.company.Main` instead of `The class (my.company.Main) could not be found`.
* Sentences do not need to omit the articles `a`, `an` and `the`. This point is especially emphasized because most well-known large and medium-sized open source projects tend to omit articles.
* For noun phrases, restrictive appositions are usually used instead of attributive nouns. For example, restrictive appositions: `The collection "messasge"` or `The setting "turms.whatever.min"`; attributive nouns: `The "messasge" collection` and `The "turms.whatever.min" setting`.
* Function and use of special symbols:

| Role                                                         | Symbols used                                 | In a sentence                                                | When paired with `: `                                        | When paired with an array                                    | Common examples                                              |
| ------------------------------------------------------------ | -------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Represents an array value                                    | `[,]`                                        | Use `[value]` format. <br />Such as `Detected illegal operations [CREATE, DELETE] on the collection "message"` | use `: [value]` format. <br />Such as `Detected illegal operations: [CREATE, DELETE]` |                                                              |                                                              |
| Indicates interval                                           | `[..]` closed interval, `(..)` open interval | such as: `[1..2]`, ``                                        |                                                              |                                                              |                                                              |
| Wrap information that needs to be specially separated for eye-catching | `()`                                         | Use the `(value)` format. <br />Such as `The path (/turms/1.txt/) is illegal` | No need to use `()`, just use `: value` format. <br />Such as `Could not find any resource from the path: /turms/1.txt` | No need to use `()`, just use `[value]` format. <br />Such as `The paths [/1.txt, /2.txt] are illegal` | object, enumeration value, path, domain name, field reference |
| Wrap key-value pairs                                         | `{}`                                         | Use `{key=value}` format. <br />Example                      | use `: {key=value}` format                                   | use `[{key=value}, {key=value}]` format                      |                                                              |
| Package name or string value                                 | `""`                                         | Use `"value"` format. <br />Such as `The property "turms.whatever.min" must be greater than 0`; `The setting name "abc123" should not contain any digit` | use `: "value"` format. <br />Such as `Unknown property: "turms.whatever.min"` | use `["value", "value"]` format. <br />Such as `The properties ["turms.whatever.min", "turms.whatever.max"] are unknown` | field name, parameter name, database collection name         |

* Difference between name and reference

  Let’s give a relatively easy-to-understand example first. Take the field name and reference as an example. Suppose there is a field `name` in a class `com.abc.Song` (song), then the name of the field is `name`, When the name is used in a sentence, double quotes `""` are required, such as `The field "name" contains illegal characters`. The reference of the field is `com.abc.Song#name`, and when the reference is used in a sentence, parentheses `()` are required, such as `The field (com.abc.Song#name) should be accessible`.

  But in the actual development process, we will find that many strings themselves can have multiple interpretations. For example, if there is a class whose name is `com.my.Main`, then this name can be interpreted as either a `class name` or a `class reference`. And considering that the class name will not have the serious ambiguity that may be brought about by the above-mentioned `name`, and the practice of most well-known open source projects of CUHK does not use `""` to wrap the class name, so for the class name, when designing Turms, It is uniformly interpreted as a `class reference` rather than a `class name`, so this type of reference needs to follow the usage rules of `()`, not the usage rules of `""`.

The next section will explain why Turms is designed this way, and why some other common designs are not used.

**TODO: Update later**

## About the use of dependent libraries

Many dependency libraries are keen to abstract and encapsulate the underlying implementation to achieve "internal logic transparency, and users do not need to care about the logic behind it". Such a design is more practical for some applications that are simple in logic, require fast online, and do not pursue performance. But as a project develops further and further optimized, this uncontrollable abstraction layer will become a stumbling block for troubleshooting, performance optimization, and function customization. Problems caused by the abstraction layer, such as:

* Requirement iteration and version update are seriously lagging behind. If our project uses an abstraction layer A dependency, A dependency encapsulates B dependency. If we need to add a new feature or fix a bug to the B dependency, the usual process is: we raise an issue to the B dependency community. If we are lucky, we will get a reply within 2 to 4 days on average. If luck is still good, the other party is willing to change. Assuming that the changes are not significant, the relevant PRs will be merged after 1 week. It may wait 2 weeks, 1 month, or even a few months, and the B dependency finally releases a new version. Then we have to wait for the A dependency to update the B dependency version, which may take another 2 weeks, 1 month, or even a few months. By the time we actually get to use the new features, it may have been a few months. But more often than not, the maintainer of B's dependency is not willing to modify the relevant code at all.
* The vast majority of well-known dependent libraries only care about function realization, not performance, and basically have the attitude of "the function can be used, and the performance can make do". (Turms solves most of the following problems by refactoring dependent code) such as:

  * `mongo-java-driver` repeatedly creates a large number of intermediate objects when making API calls. For the default configuration object, no Cache is used.
  * Lettuce needs to repeatedly expand the memory when serializing the instruction parameters passed to Redis, and the memory data of the Cache is not cached.
  * Log4j2 actually uses `getBytes` to read the data of the string, and uses `StringBuilder` to do the splicing of the log (compared to the log implementation of Turms, which directly uses the `byte[] value` data inside `String`, and uses the` provided by Netty` io.netty.buffer.AbstractByteBufAllocator#directBuffer` to splice logs and do log output). (Supplement: If readers are interested in log implementation, you can read [log implementation](https://turms-im.github.io/docs/server/module/observability#%E8%87%AA%E7%A0%94%E5%AE%9E%E7%8E%B0-%E6%8B%93%E5%B1%95%E7%9F%A5%E8%AF%86), understand how Turms implements logs)
  * In the official Java implementation of Protobuf, its string decoding implementation is also very inefficient. For example, in order to be compatible with lower versions of Java, it uses `char[]` for encoding, but the String of the new version of Java only stores `byte[ ]`, so a meaningless memory copy is required (note: the string itself is the largest data in the client request).
  * Spring is a typical representative of inefficient code, such as:
    * When `org.springframework.core.codec.CharSequenceEncoder` processes `UTF-8` encoded strings, 1 character corresponds to 3 bytes to open up DirectByteBuffer for output. In other words, for the above-mentioned 8K Prometheus data, only this piece of Spring needs to use 2.4MB, and an extra 1.6MB is used. Of course, Spring is even more efficient, because it also performs string copying when `String#getBytes(...)`.
    * `spring-boot-actuator:v2.6.6` does not support zero copy when exporting huge heap dump files (see `org.springframework.boot.actuate.management.HeapDumpWebEndpoint.TemporaryFileSystemResource#isFile`)
    * Spring's AOP is often used to proxy Controller layer method calls, which can be used to capture parsed parameters and print logs (WebFilter cannot obtain parsed parameters). But AOP will add 19 stacks to a method and use a lot of reflection. The time required to call from the AOP proxy to the Controller method layer is even longer than the internal business processing time of Turms (additional supplement: AOP is a very bad design, Spring Should be designed for the chain of responsibility adopted by the Controller layer).


To sum up, the code quality of many well-known Java dependent libraries is not high, and even the code performance and quality are worrying, and the source code is shocking to read. Instead, readers can refer to how the Turms server is coded to optimize various details to the extreme.

* When the dependency library that focuses on abstract implementation is combined with responsive programming, it will bring developers a hell-level experience in troubleshooting problems, especially when bugs are related to memory that needs to be released manually. In the troubleshooting of conventional problems, we can usually quickly troubleshoot the problem through the stack information. But in reactive programming, such a method usually does not work, and we rely more on logical reasoning to troubleshoot problems. That is, familiarize yourself with the upstream and downstream code (including the code in the dependent package), and deduce all the processes that the code may go through.

  If the code has few abstraction layers and the call relationship is flat, the troubleshooting process is actually very simple. Maybe we only need to glance at dozens of lines of code in a class to roughly know the cause of the problem. However, if a large number of "encapsulation, abstraction, users do not need to pay attention to the underlying implementation logic" dependency libraries are used in the process, the hell-level experience will come. Originally, we might only need a function with dozens of lines to implement all the relevant logic. But if we implement related functions based on the abstract library, when we troubleshoot, the code we may want to check may be A abstract class (A1, A2, A3...) class -> B abstract class (B1, B2, B3.. .)->C abstract class (C1,C2,C3...)->..., jump between dozens of classes and dozens of methods, and reason.

  The most typical comparison example is: Turms' `im.turms.gateway.access.client.websocket.WebSocketServerFactory#getHttpRequestHandler` implements a set of WebSocket handshake logic in a function of dozens of lines. But if this set of logic is implemented by Spring, it will mix the classes under different packages and various logics together. When troubleshooting, if it is accompanied by some memory that needs to be released manually, hell level Here comes the troubleshooting experience. What can be solved with dozens of lines of code, a library like Spring requires thousands of lines of code. For example, there are multiple sets of underlying Web implementations inside WebFlux, which is euphemistically called "encapsulation and abstraction, and users do not need to pay attention to the underlying implementation logic."

* Some dependent libraries will automatically suppress exceptions in some places, and the upper-layer application code cannot perceive them. When something goes wrong, the underlying library code and the upper-level application code run on different stacks in most cases. Unless the underlying dependency library supports global exception callbacks, the upper-layer application cannot even perceive the occurrence of exceptions. For some Trivial-level errors, it doesn't matter if the upper-layer application cannot perceive them. But if it is an abnormality that some upper-layer applications are very concerned about (such as the abnormal disconnection of the RPC TCP connection), this will be the fuse that causes the abnormality and disorder of the entire system.

* Developers of some well-known dependent libraries even lack the most basic security knowledge. For example, the developers of `Log4j` actually added code to automatically detect whether there is a `${jndi}` pattern in the string to be printed, and if it exists, call the corresponding JNDI service, and enable this function by default. As a developer who specializes in writing log-dependent libraries, he lacks security common sense and has passed PR review.

On the other hand, self-development can avoid all the above-mentioned problems. While improving the controllability of the code, it also greatly reduces the difficulty of research and development and troubleshooting, and improves code performance and resource utilization.

In summary, when a Turms project references a class library, it usually does not introduce an abstract encapsulation library (such as Spring), but only an implementation library. Points that require performance optimization or logic optimization in the dependent library will be directly refactored inside the Turms project. Considering the difficulty of self-development and code controllability, we will choose self-development as much as possible in most cases.

Supplement: Although the Java ecosystem is prosperous, there are actually few high-quality libraries. Therefore, most medium and large-scale Java open source projects that pursue performance usually try to develop various functional modules by themselves instead of using third-party dependent libraries, such as: [Elasticsearch](https://github.com/elastic/elasticsearch), [Cassandra](https://github.com/apache/cassandra), [Ignite](https://github.com/apache/ignite) . In addition, in the entire Java ecosystem, the only library we currently trust in the technical level of its developers is: Netty

## Exception capture and printing

Role: Understanding the exception capture and printing principles of the Turms server can help developers quickly locate the exception and find the root cause of the exception.

In reactive programming, the most criticized exception is that exceptions under this programming paradigm are usually very difficult to locate, and their stack information is basically useless. If the developer randomly prints the exception log in the reactive programming mode, it is very likely that the debugger will not even be able to judge where the exception is thrown from the log, let alone reverse the execution code.

But in fact, the principle and practice of good exception log printing are relatively simple, and if you follow this principle, it usually takes a few seconds or minutes to locate the exception. The basic principle is that the most downstream code throws an exception without printing. If the midstream code needs to translate the exception, it will continue to be thrown upwards after the translation, without printing; the most upstream exception will be received and ** will be printed. As for what code is considered "the most upstream", the code that calls `subscribe()` is considered the "most upstream". This principle is actually very simple in practice, but the exception capture in reactive programming "looks" more complicated. For example, under the `im.turms.service.access.servicerequest.dispatcher.ServiceRequestDispatcher#dispatch0` function in the turms-service server, there is an operation of "send notifications to relevant users according to the processing results of the Service layer" , whose code is as follows:

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

As mentioned above, this piece of code performs the notification delivery operation through the `notifyRelatedUsersOfAction` function. We don't care about its internal implementation. We only need to pass `subscribe(...)` at the most upstream to ensure that it can catch the exceptions that may be thrown. and print it.

### There are and only custom exception classes inherited from `RuntimeException`

In the Turms server project, there are and only exception classes inherited from `RuntimeException` can be customized, and exception classes inherited from `Exception` (`Checked Exception`) are prohibited from being customized.

The discussion on whether to use `Checked Exception` or `Unchecked Exception` has been divided so far, but now many articles directly criticize `Checked Exception` as a design failure of Java, and later languages such as Kotlin/Scala/C# don’t even have it at all. The concept of `Checked Exception`, and now most of the well-known large and medium-sized open source projects generally only customize the subclasses of `RuntimeException`, but not the subclasses of `Checked Exception`.

Common reasons why `Checked Exception` is bad design include:

* As a third-party library/downstream code, `Checked Exception` has interface signature version compatibility issues.

* As a large and medium-sized project, when all submodules use `Checked Exception`, the interface of the upstream code may eventually declare dozens of exceptions. When the exception declaration of the interface is added, deleted, or modified, it will affect the whole body.

* Inside the Java code, there are exception design conflicts. For example, Lambda in the design of Java Streams does not support throwing `Checked Exception`. For Lambda in Stream, its implementation must be treated as processing (usually a wrong practice) or converted into `Unchecked Exception` (losing the use of `Checked Exception`). Exception` meaning), Java even introduced `UncheckedIOException`.

* In practice, people often avoid the purpose that `Checked Exception` was designed, so it is better not to use `Checked Exception`, for example:

  * Catch all `Exception` directly
  * Translate `Checked Exception` to `RuntimeException`. Such as `try { ... } catch (Exception e) { throw new RuntimeException(e); }`
  * Because the stack is too deep, in order to avoid polluting the upstream code, it is possible to directly perform meaningless capture downstream, and it is even possible to directly `catch (Exception e) { do nothing }` by mistake

* Many developers will misunderstand exception design, and then mistakenly customize exceptions. For example, many developers think that if it is an exception that can be avoided by the upstream code, use the subclass of RuntimeException. If it is an unavoidable exception in the upstream code, use Checked Exception`. A similar view is very blindly optimistic and lacks actual project experience and coding experience, because whether the exception thrown downstream can be handled depends on the logic of the upstream code, not The assumptions of the downstream code.

  For example, when the plug-in module of the Turms server loads the plug-in, the class loader of the plug-in may throw `NoClassDefFoundError`, if according to the early Java team, `An Error is a subclass of Throwable that indicates serious problems that a reasonable application should not try to catch`, then the upstream code of the plug-in module should not catch `Error`, but as a server, Turms cannot make the server abnormal because it loads a problematic class plug-in, so the upstream code is really reasonable The approach is to catch these `Error`, instead of causing the server to crash directly and fall into an abnormal state.

For the Turms server project, considering that the only scenario where `Checked Exception` can really play a role is: in individual scenarios, when designing downstream functional modules, it is known that the upstream caller code needs to be based on various events thrown downstream. Exceptions are distinguished by exceptions. In order to ensure that the upstream does not miss processing some exceptions thrown by the downstream, you can consider using `Checked Exception`. But since this kind of scenario is very rare, it is also very bad practice to design downstream code according to the logic of upstream caller code.

Therefore, in order to avoid various problems caused by `Checked Exception`, unify the exception design style, and avoid wasting time on "why are they all modules of a certain type, module A uses a certain type of exception, and module B uses a certain type of exception "For such insignificant disputes, in the Turms server project, there are and only exception classes that inherit from `RuntimeException`, and it is forbidden to customize exception classes that inherit from `Exception` (`Checked Exception`).