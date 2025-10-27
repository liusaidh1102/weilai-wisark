# 使用官方 OpenJDK 17 镜像作为基础镜像
FROM openjdk:17-jre-slim

MAINTAINER wlgzs

# 设置容器内的工作目录
WORKDIR /sentinel

COPY ./sentinel-dashboard.jar ./sentinel-dashboard.jar

# 暴露容器的 8089 端口
EXPOSE 8090

# 设置容器启动时运行的命令
# 加上对应的-Dcsp.sentinel.dashboard.server指定控制台
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Dserver.port=8090 -Dauth.username=weilai -Dauth.password=wisark@2025 -jar -Dcsp.sentinel.dashboard.server=http://82.156.115.174:8090 sentinel-dashboard.jar"]