# 使用官方 OpenJDK 17 镜像作为基础镜像
FROM openjdk:17-jdk-slim

MAINTAINER wlgzs

# 设置容器内的工作目录
WORKDIR /file

# 将当前目录下的 jar 文件拷贝到容器内的 /app 目录下
# 假设你的 jar 文件名为 weilai-wisark-gateway-1.0-SNAPAHOT.jar
COPY ./weilai-wisark-file-starter-1.0-SNAPSHOT.jar ./app.jar

# 暴露容器的 8080 端口
EXPOSE 8111

# 设置容器启动时运行的命令
ENTRYPOINT ["java", "-jar", "app.jar"]



#docker run -d --name file -p 8111:8111 -v /home/lzw/wisark/file/weilai-wisark-file-starter-1.0-SNAPSHOT.jar:/file/app.jar file