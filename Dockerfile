FROM openjdk:8-jdk-slim
COPY "./target/demo-5.2.8.RELEASE.jar" "app.jar"
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
