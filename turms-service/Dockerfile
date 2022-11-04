# Run "docker build -t turms-service -f turms-service/Dockerfile ." in the root dir of all turms projects

####################################################################
# Stage 0 : BUILD JAR
####################################################################
FROM maven:3.8.6-eclipse-temurin-17-focal AS maven-builder
COPY . ./
#RUN sed -i '/<mirrors>/a                                       \
#<mirror>                                                       \
#    <id>aliyunmaven</id>                                       \
#    <mirrorOf>*</mirrorOf>                                     \
#    <name>Aliyun Repository</name>                             \
#    <url>https://maven.aliyun.com/repository/public</url>      \
#</mirror>' /usr/share/maven/conf/settings.xml
RUN mvn clean package -am -B -DskipUTs -DskipITs -P artifact-fat-jar -pl turms-service --no-transfer-progress

####################################################################
# Stage 1: BUILD LAYERS
####################################################################
FROM eclipse-temurin:17.0.4.1_1-jre-focal AS layers-builder
WORKDIR /build/
COPY --from=maven-builder /turms-service/target/*-exec.jar ./app.jar
RUN ["mkdir", "dependencies", "snapshot-dependencies", "spring-boot-loader", "application"]
RUN java -Djarmode=layertools -jar app.jar extract

####################################################################
# Stage 2: BUILD IMAGE
####################################################################
# Declare the exact version to ensure there is no unexpected behavior in Java
# because we have some UNSAFE operations on Java internal classes
FROM eclipse-temurin:17.0.4.1_1-jre-focal

ENV TURMS_SERVICE_HOME=/opt/turms/turms-service
WORKDIR ${TURMS_SERVICE_HOME}

COPY /turms-service/dist ./
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