# Quick Start

1. 克隆Turms仓库（目前客户端代码均未发布到公开的依赖仓库中）。参考命令：`git clone --depth 1 https://github.com/turms-im/turms.git`

2. 在您的项目中，引入对应的客户端实现。具体操作如下：

   * 对于使用turms-client-js的项目：

     首先进入到turms-client-js子项目的目录，执行命令`npm run quickbuild`，该命令会安装相关依赖并编译turms-client-js的发布包。然后：

     * 对于使用模块的项目：
       * 安装：在`package.json`的`dependencies`下添加：`"turms-client-js": "file:<YOUR_OWN_PATH>/turms-client-js"`即可
       * 使用：通过`import TurmsClient from 'turms-client-js'`引入Turms客户端实现
     * 对于不使用模块的项目：在HTML上添加：`<script type="text/javascript" src="<YOUR_OWN_PATH>/turms-client-js/dist/turms-client.iife.js"></script>`，并直接使用全局对象`TurmsClient`。

   * 对于使用turms-client-kotlin的项目：

     * 安装：在turms-client-kotlin子项目的目录下，执行命令`mvn clean install`，该命令会将turms-client-kotlin编译并安装其JAR包到本地Maven仓库。

     * 使用：

       * 对于Maven项目，添加：

         ```xml
         <dependency>
             <groupId>im.turms</groupId>
             <artifactId>turms-client-kotlin</artifactId>
             <version>0.10.0-SNAPSHOT</version>
         </dependency>
         ```

       * 对于Gradle项目，添加：

         ```groovy
         repositories {
             mavenLocal()
         }
         dependencies {
             implementation 'im.turms:turms-client-kotlin:0.10.0-SNAPSHOT'
         }
         ```

   * 对于使用turms-client-swift的项目：在Xcode中，通过`General`标签页下的`Frameworks, Libraries, and Embedded Content`指定本地turms-client-swift文件夹路径并添加即可。

   * 对于使用turms-client-dart的项目：在您项目的`pubspec.yaml`里添加下述依赖即可：

     ```yaml
     dependencies:
       turms_client_dart:
         path: <YOUR_OWN_DIR>/turms_client_dart
     ```

3. 编写业务逻辑代码