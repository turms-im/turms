# Run "docker build -t turms -f turms/Dockerfile ." in the root dir of all turms projects

####################################################################
# Stage 0 : BUILD JAR
####################################################################
FROM maven:3-adoptopenjdk-11 AS maven-builder
COPY . ./
RUN mvn clean package -am -B -DskipTests -P artifact-fat-jar -pl turms --no-transfer-progress

####################################################################
# Stage 1: BUILD LAYERS
####################################################################
FROM adoptopenjdk/openjdk11:jre AS layers-builder
WORKDIR /build/
COPY --from=maven-builder /turms/target/*.jar ./app.jar
RUN ["mkdir", "dependencies", "snapshot-dependencies", "spring-boot-loader", "application"]
RUN java -Djarmode=layertools -jar app.jar extract

####################################################################
# Stage 2: BUILD IMAGE
####################################################################
FROM adoptopenjdk/openjdk11:jre

ENV TURMS_HOME=/opt/turms/turms
WORKDIR ${TURMS_HOME}

COPY /turms/dist ./
COPY --from=layers-builder /build/dependencies/ ./
COPY --from=layers-builder /build/snapshot-dependencies/ ./
COPY --from=layers-builder /build/spring-boot-loader/ ./
COPY --from=layers-builder /build/application/ ./

# RPC
EXPOSE 7510
# Admin APIs
EXPOSE 8510

RUN ["chmod", "+x", "./bin/turms.sh"]
ENTRYPOINT ["./bin/turms.sh", "-f"]