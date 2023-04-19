# Quick Start

1. Clone the Turms repository (currently none of the client code is released to any public dependency repository). Reference command: `git clone --depth 1 https://github.com/turms-im/turms.git`

2. In your project, import the corresponding client as follows:

    * For projects using turms-client-js:

      First go to the directory of the turms-client-js subproject, and execute the command `npm run quickbuild`, which will install the dependencies and compile the release package of the turms-client-js. Then:

        * For projects using modules:
            * Installation: Add under `dependencies` of `package.json`: `"turms-client-js": "file:<YOUR_OWN_PATH>/turms-client-js"`
            * Use: import Turms client through `import TurmsClient from 'turms-client-js'`
        * For projects that do not use modules: add on HTML: `<script type="text/javascript" src="<YOUR_OWN_PATH>/turms-client-js/dist/turms-client.iife.js"></script >`, and use the global object `TurmsClient` directly.

    * For projects using turms-client-kotlin:

        * Installation: In the directory of the turms-client-kotlin subproject, execute the command `mvn clean install`, which will compile turms-client-kotlin and install its JAR file to the local Maven repository.

        * Usage:

            * For Maven projects, add:

              ```xml
              <dependency>
                  <groupId>im.turms</groupId>
                  <artifactId>turms-client-kotlin</artifactId>
                  <version>0.10.0-SNAPSHOT</version>
              </dependency>
              ```

            * For Gradle projects, add:

              ```groovy
              repositories {
                  mavenLocal()
              }
              dependencies {
                  implementation 'im.turms:turms-client-kotlin:0.10.0-SNAPSHOT'
              }
              ```

    * For projects using turms-client-swift: In Xcode, specify the local turms-client-swift folder path through the `Frameworks, Libraries, and Embedded Content` under the `General` tab and add it.

    * For projects using turms-client-dart: add the following dependencies to your project's `pubspec.yaml`:

      ```yaml
      dependencies:
        turms_client_dart:
          path: <YOUR_OWN_DIR>/turms_client_dart
      ```

3. Write business logic code