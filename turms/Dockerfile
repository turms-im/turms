# Run "docker build -t turms -f turms/Dockerfile ." in the root dir of all turms projects

####################################################################
# Stage 0 : BUILD JAR
####################################################################
FROM maven:3-adoptopenjdk-16 AS maven-builder
COPY . ./
#RUN sed -i '/<mirrors>/a                                       \
#<mirror>                                                       \
#    <id>aliyunmaven</id>                                       \
#    <mirrorOf>*</mirrorOf>                                     \
#    <name>Aliyun Repository</name>                             \
#    <url>https://maven.aliyun.com/repository/public</url>      \
#</mirror>' /usr/share/maven/conf/settings.xml
RUN mvn clean package -am -B -DskipUTs -DskipITs -P artifact-fat-jar -pl turms --no-transfer-progress

####################################################################
# Stage 1: BUILD LAYERS
####################################################################
FROM adoptopenjdk/openjdk16:jre AS layers-builder
WORKDIR /build/
COPY --from=maven-builder /turms/target/*.jar ./app.jar
RUN ["mkdir", "dependencies", "snapshot-dependencies", "spring-boot-loader", "application"]
RUN java -Djarmode=layertools -jar app.jar extract

####################################################################
# Stage 2: BUILD IMAGE
####################################################################
FROM adoptopenjdk/openjdk16:jre

ENV TURMS_HOME=/opt/turms/turms
WORKDIR ${TURMS_HOME}

COPY /turms/dist ./
COPY --from=layers-builder /build/dependencies/ ./
COPY --from=layers-builder /build/snapshot-dependencies/ ./
COPY --from=layers-builder /build/spring-boot-loader/ ./
COPY --from=layers-builder /build/application/ ./

# RPC
EXPOSE 7510
# Metrics APIs + Admin APIs
EXPOSE 8510

RUN ["chmod", "+x", "./bin/run.sh"]
ENTRYPOINT ["./bin/run.sh", "-f"]