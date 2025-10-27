@echo off
REM 服务启动脚本
REM 该脚本将启动所有微服务，并提供日志输出和错误处理

echo 开始构建和启动所有微服务...

REM 检查Docker是否运行
docker info >nul 2>&1
if %errorlevel% neq 0 (
    echo [ERROR] Docker未运行，请先启动Docker
    pause
    exit /b 1
)

echo [INFO] Docker环境检查通过

REM 构建所有服务
echo [INFO] 开始构建所有服务的Docker镜像...
docker-compose build
if %errorlevel% equ 0 (
    echo [SUCCESS] 所有服务的Docker镜像构建完成
) else (
    echo [ERROR] Docker镜像构建失败
    pause
    exit /b 1
)

REM 启动所有服务
echo [INFO] 开始启动所有服务...
docker-compose up -d
if %errorlevel% equ 0 (
    echo [SUCCESS] 所有服务已启动在后台运行
) else (
    echo [ERROR] 服务启动失败
    pause
    exit /b 1
)

REM 等待服务启动
echo [INFO] 等待服务启动完成...
timeout /t 10 /nobreak >nul

REM 检查各个服务状态
echo [INFO] 检查各服务运行状态...

REM 检查网关服务
docker-compose ps | findstr "gateway" | findstr "Up" >nul
if %errorlevel% equ 0 (
    echo [SUCCESS] 服务 gateway 启动成功
) else (
    echo [ERROR] 服务 gateway 启动失败
)

REM 检查用户服务
docker-compose ps | findstr "user" | findstr "Up" >nul
if %errorlevel% equ 0 (
    echo [SUCCESS] 服务 user 启动成功
) else (
    echo [ERROR] 服务 user 启动失败
)

REM 检查AI服务
docker-compose ps | findstr "ai" | findstr "Up" >nul
if %errorlevel% equ 0 (
    echo [SUCCESS] 服务 ai 启动成功
) else (
    echo [ERROR] 服务 ai 启动失败
)

REM 检查文件服务
docker-compose ps | findstr "file" | findstr "Up" >nul
if %errorlevel% equ 0 (
    echo [SUCCESS] 服务 file 启动成功
) else (
    echo [ERROR] 服务 file 启动失败
)

REM 检查话题服务
docker-compose ps | findstr "topic" | findstr "Up" >nul
if %errorlevel% equ 0 (
    echo [SUCCESS] 服务 topic 启动成功
) else (
    echo [ERROR] 服务 topic 启动失败
)

REM 检查直播服务
docker-compose ps | findstr "stream" | findstr "Up" >nul
if %errorlevel% equ 0 (
    echo [SUCCESS] 服务 stream 启动成功
) else (
    echo [ERROR] 服务 stream 启动失败
)

echo.
echo [INFO] 服务启动完成！
echo [INFO] 可以通过以下命令查看各服务日志：
echo    docker-compose logs -f [service_name]
echo.
echo [INFO] 服务列表：
echo    - gateway
echo    - user
echo    - ai
echo    - file
echo    - topic
echo    - stream
echo.
echo [INFO] 要停止所有服务，请运行：docker-compose down
pause