# Run "docker build -t turms-gateway -f turms-gateway/Dockerfile ." in the root dir of all turms projects

####################################################################
# Stage 0 : BUILD JAR
####################################################################
FROM maven:3-adoptopenjdk-11 AS maven-builder
COPY . ./
RUN mvn clean package -am -B -DskipTests -P artifact-fat-jar -pl turms-gateway --no-transfer-progress

####################################################################
# Stage 1:  BUILD LAYERS
####################################################################
FROM adoptopenjdk/openjdk11:jre AS layers-builder
WORKDIR /build/
COPY --from=maven-builder /turms-gateway/target/*.jar ./app.jar
RUN ["mkdir", "dependencies", "snapshot-dependencies", "spring-boot-loader", "application"]
RUN java -Djarmode=layertools -jar app.jar extract

####################################################################
# Stage 2: BUILD IMAGE
####################################################################
FROM adoptopenjdk/openjdk11:jre

ENV TURMS_GATEWAY_HOME=/opt/turms/turms-gateway
WORKDIR ${TURMS_GATEWAY_HOME}

COPY /turms-gateway/dist ./
COPY --from=layers-builder /build/dependencies/ ./
COPY --from=layers-builder /build/snapshot-dependencies/ ./
COPY --from=layers-builder /build/spring-boot-loader/ ./
COPY --from=layers-builder /build/application/ ./

# RPC
EXPOSE 7510
# Metrics APIs
EXPOSE 9510
# WebSocket
EXPOSE 10510
# TCP
EXPOSE 11510
# UDP
EXPOSE 12510

RUN ["chmod", "+x", "./bin/turms-gateway.sh"]
ENTRYPOINT ["./bin/turms-gateway.sh", "-f"]