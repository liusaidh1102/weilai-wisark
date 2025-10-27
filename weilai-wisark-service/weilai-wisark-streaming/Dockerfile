# 使用官方 OpenJDK 17 镜像作为基础镜像
FROM openjdk:17-ea-slim

MAINTAINER wlgzs

# 设置容器内的工作目录
WORKDIR /streaming

# 将当前目录下的 jar 文件拷贝到容器内的工作目录下
COPY ./target/weilai-wisark-streaming-1.0-SNAPSHOT.jar ./app.jar

# 暴露容器的端口
EXPOSE 8002

# 设置容器启动时运行的命令
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]