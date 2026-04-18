FROM eclipse-temurin:21-jre AS builder
WORKDIR /applicatio
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM eclipse-temurin:21-jre
WORKDIR /applicatio
COPY --from=builder /applicatio/dependencies/ ./
COPY --from=builder /applicatio/spring-boot-loader/ ./
COPY --from=builder /applicatio/snapshot-dependencies/ ./
COPY --from=builder /applicatio/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]