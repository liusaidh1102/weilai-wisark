# 智慧云舟（防掘金）- 后端微服务项目

## 📖 项目简介
**智慧云舟（防掘金）** 是一个基于 **Spring Cloud Alibaba** 生态搭建的高可用、高并发后端微服务项目，完整复刻了掘金社区的核心业务能力，涵盖用户、文章、评论、直播、消息推送等全链路功能，同时集成了分布式事务、流量控制、服务网关等企业级中间件，是一套可直接用于学习、面试、二次开发的完整微服务解决方案。

---

## 🎯 项目背景
本项目为个人/团队学习型项目，旨在通过完整的微服务架构实践，深入理解Spring Cloud Alibaba生态、分布式系统设计、高并发场景优化等核心技术，同时为后端开发同学提供一套可直接参考的企业级项目模板。

> ⚠️ 本项目仅用于技术学习与交流，无任何商业用途，所有业务逻辑均为模拟实现，不涉及真实数据与商业场景。

---

## 🛠️ 技术栈
### 核心框架
| 技术 | 版本 | 说明 |
|------|------|------|
| Spring Boot | 2.7.x | 基础开发框架 |
| Spring Cloud | 2021.x | 微服务核心框架 |
| Spring Cloud Alibaba | 2021.x | 微服务生态组件 |
| MyBatis-Plus | 3.5.x | ORM持久层框架 |

### 中间件与工具
| 技术 | 版本 | 说明 |
|------|------|------|
| Nacos | 2.2.x | 服务注册与配置中心 |
| Seata | 1.5.x | 分布式事务解决方案 |
| Sentinel | 1.8.x | 流量控制与熔断降级 |
| Gateway | 3.1.x | 微服务API网关 |
| Redis | 6.x | 缓存、分布式锁、消息队列 |
| MySQL | 8.0 | 主数据库 |
| Docker & Docker Compose | - | 容器化部署 |
| Swagger/ Knife4j | - | 接口文档生成 |
| MinIO | - | 对象存储（文件/图片上传） |
| RocketMQ | - | 消息中间件（异步处理） |

### 其他技术
- JWT：用户身份认证与授权
- Lombok：简化Java代码
- Hutool：Java工具类库
- FastJSON：JSON序列化
- AOP：统一日志、权限拦截

---

## 📁 项目结构
```
weilai-wisark
├── sql/                          # 数据库初始化SQL脚本
├── weilai-wisark-common/         # 公共模块（工具类、常量、通用配置）
├── weilai-wisark-feign-api/      # Feign接口定义模块（服务间调用契约）
├── weilai-wisark-gateway/        # API网关模块（路由、鉴权、限流）
├── weilai-wisark-model/          # 数据模型模块（DTO、VO、实体类）
├── weilai-wisark-service/        # 核心业务服务模块（用户、文章、评论、直播等）
├── weilai-wisark-starter/        # 自定义Spring Boot Starter（通用组件封装）
├── weilai-wisark-test/           # 单元测试模块
├── weilai-wisark-utils/          # 工具类模块（通用工具、第三方封装）
├── Dockerfile                    # 项目容器化构建文件
├── docker-compose.yml            # 本地一键启动配置
├── build-run.sh                  # Linux环境构建启动脚本
├── start-services.bat            # Windows环境启动脚本
└── pom.xml                       # 父工程Maven配置
```

---

## ✨ 核心功能
### 1. 用户模块
- 用户注册/登录（JWT认证）
- 个人信息管理、头像/资料修改
- 关注/粉丝系统、用户权限管理
- 第三方登录（模拟）

### 2. 文章模块
- 文章发布/编辑/删除
- 文章分类、标签管理
- 点赞/收藏/分享功能
- 文章热度排行（时间衰减算法）
- 文章搜索（ElasticSearch集成）

### 3. 互动模块
- 评论/回复功能（多级嵌套）
- 评论点赞/举报
- 消息通知（点赞、评论、关注）
- 私信功能

### 4. 直播模块
- 直播间创建/管理
- 弹幕实时发送/接收
- 礼物打赏、直播间热度计算
- 直播状态同步

### 5. 系统模块
- 分布式事务（Seata AT模式）
- 流量控制与熔断降级（Sentinel）
- 服务网关路由与鉴权（Gateway）
- 接口文档自动生成（Knife4j）
- 容器化一键部署（Docker Compose）

---

## 🚀 快速启动
### 环境要求
- JDK 1.8+
- Maven 3.6+
- MySQL 8.0+
- Redis 6.0+
- Nacos 2.2.x
- Docker & Docker Compose（可选，用于一键部署）

### 本地启动步骤
1. **克隆项目**
```bash
git clone https://github.com/liusaidh1102/weilai-wisark.git
```

2. **初始化数据库**
- 执行 `sql/` 目录下的SQL脚本，创建数据库并初始化表结构

3. **配置中间件**
- 启动Nacos，创建对应配置文件
- 启动Redis、MySQL，确保服务正常运行

4. **修改配置**
- 在 `weilai-wisark-common` 或对应服务的 `application.yml` 中，修改数据库、Redis、Nacos等连接信息

5. **启动服务**
- Windows：直接运行 `start-services.bat` 一键启动
- Linux：执行 `sh build-run.sh` 构建并启动
- 手动启动：按顺序启动 `gateway`、`service` 等核心服务

6. **访问接口文档**
- 网关地址：`http://localhost:8080/doc.html`
- 各服务独立文档：`http://localhost:服务端口/doc.html`

### Docker一键部署
```bash
# 进入项目根目录
cd weilai-wisark
# 一键构建并启动所有服务
docker-compose up -d
```

---

## 🧠 核心技术亮点
### 1. 完整微服务架构
- 基于Spring Cloud Alibaba实现服务注册、配置中心、网关、熔断、分布式事务全链路解决方案
- 服务拆分遵循高内聚、低耦合原则，各模块职责清晰

### 2. 分布式事务解决方案
- 集成Seata AT模式，解决跨服务数据一致性问题
- 针对订单、支付等核心场景实现事务保障

### 3. 高并发优化
- Redis缓存热点数据（文章、用户信息）
- Sentinel实现流量控制、熔断降级，保障系统高可用
- 异步处理（RocketMQ）：文章发布、消息通知等耗时操作异步化

### 4. 工程化实践
- 统一异常处理、统一日志、统一返回体
- 自定义Spring Boot Starter封装通用组件
- Docker容器化部署，一键启动
- 规范的Git提交记录、代码结构

### 5. 业务场景完整
- 覆盖社区类产品全链路业务，从用户到内容、互动、直播，完整可运行
- 文章热度排行采用时间衰减算法，模拟真实社区热榜逻辑

---

## 📝 项目说明
- 本项目为学习型项目，所有业务逻辑均为模拟实现，无真实数据
- 代码已做脱敏处理，不涉及任何公司商业机密与敏感信息
- 欢迎Star、Fork，如有问题可提交Issue交流

---

## 🤝 贡献者
- [liusaidh1102](sslocal://flow/file_open?url=https%3A%2F%2Fgithub.com%2Fliusaidh1102&flow_extra=eyJsaW5rX3R5cGUiOiJjb2RlX2ludGVycHJldGVyIn0=) - 项目发起与核心开发
- [panderBother](sslocal://flow/file_open?url=https%3A%2F%2Fgithub.com%2FpanderBother&flow_extra=eyJsaW5rX3R5cGUiOiJjb2RlX2ludGVycHJldGVyIn0=) - 核心开发

---

## 📄 许可证
本项目基于 **MIT** 许可证开源，仅供学习与交流使用。

---

## 📞 联系方式
如有技术交流、问题反馈，可通过以下方式联系：
- GitHub Issue：直接在本仓库提交Issue
- 邮箱：可通过GitHub个人主页获取

---

⭐ 如果本项目对你有帮助，欢迎给个Star支持一下！⭐
