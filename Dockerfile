# FROM redis
# EXPOSE 6379
# # RUN mkdir /opt/redis && chown redis:redis /opt/redis
# # WORKDIR /opt/redis

FROM openjdk:8-jdk-alpine
COPY "./target/demo-5.2.8.RELEASE.jar" "app.jar"
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
