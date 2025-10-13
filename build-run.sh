
# sh脚本不需要加上开头那行 !#bin/bash
docker stop sentinel-dashboard
docker rm sentinel-dashboard
docker rmi sentinel-dashboard
# 构建镜像
docker build -t sentinel-dashboard .

# 运行容器
docker run \
  --name sentinel-dashboard \
  -p 8090:8090 \
  --restart=always \
  --privileged=true \
  -e username=weilai \
  -e password=wisark@2025 \
  -d \
  sentinel-dashboard
