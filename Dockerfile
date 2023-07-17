FROM maven:3.6.3-openjdk-17 AS builder

WORKDIR /app

COPY pom.xml ./pom.xml

COPY src ./src

RUN --mount=type=cache,target=/root/.m2 mvn -f ./pom.xml clean package

FROM openjdk:17-jdk-slim AS runner

EXPOSE 8080

WORKDIR /app


ENV TZ=Asia/Tehran
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone


COPY --from=builder /app/target/partner.jar /app/partner.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar","/app/partner.jar"]
