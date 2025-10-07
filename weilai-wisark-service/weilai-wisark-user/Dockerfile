# 使用官方 OpenJDK 17 镜像作为基础镜像
FROM openjdk:17-jdk-slim

MAINTAINER wlgzs

# 设置容器内的工作目录
WORKDIR /user

# 将当前目录下的 jar 文件拷贝到容器内的 /app 目录下
# 假设你的 jar 文件名为 weilai-wisark-gateway-1.0-SNAPAHOT.jar
COPY ./weilai-wisark-user-1.0-SNAPSHOT.jar ./app.jar

# 暴露容器的 8080 端口
EXPOSE 8118

# 设置容器启动时运行的命令
ENTRYPOINT ["java", "-jar", "app.jar"]



#docker run -d --name user -p 8118:8118 -v /home/lzw/wisark/user/weilai-wisark-user-1.0-SNAPSHOT.jar:/user/app.jar user