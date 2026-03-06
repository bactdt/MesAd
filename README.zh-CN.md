# MesAd - MES 生产管理系统

基于 [若依（RuoYi-Vue）](https://gitee.com/y_project/RuoYi-Vue) 平台二次开发的 MES（制造执行系统）管理后台。

## 技术栈

**后端：** Spring Boot 2.5 + MyBatis + Spring Security + JWT + Redis + MySQL

**前端：** Vue 3 + Vite + Element Plus + Pinia + Vue Router 4

## 项目结构

```
MesAd
├── mesad-admin       # 启动模块（主入口，端口 8080）
├── mesad-framework   # 框架核心（安全、拦截器、通用配置）
├── mesad-system      # 系统模块（用户、角色、菜单、部门等）
├── mesad-mes         # MES 业务模块（订单、客户、产品、报表）
├── mesad-quartz      # 定时任务模块
├── mesad-generator   # 代码生成模块
├── mesad-common      # 公共工具模块
├── mesad-ui          # 前端工程（Vue 3）
└── sql               # 数据库初始化脚本
```

## 业务功能

- [X] 订单管理（新增/编辑/删除/导出，含订单明细）
- [X] 客户管理
- [X] 产品管理
- [X] 生产执行
- [X] 自动生成编号
- [X] 生产报表（增删改查、导出）
- [ ] 质量管理（待开发）

## 快速开始

### 环境要求

- JDK 1.8+
- Maven 3.x
- MySQL 5.7+
- Redis
- Node.js 16+

### 后端启动

```bash
# 1. 导入数据库脚本（sql 目录下的 ry_20240629.sql、mes.sql 和 mes_report.sql）
# 2. 修改 mesad-admin/src/main/resources/application-druid.yml 中的数据库连接配置
# 3. 构建并启动
mvn clean package -DskipTests
java -jar mesad-admin/target/mesad-admin.jar
```

### 前端启动

```bash
cd mesad-ui
npm install
npm run dev
```

### 构建生产版本

```bash
cd mesad-ui
npm run build:prod
```

## TODO

- [ ] 质量管理模块

---

毕业设计项目，持续完善中。
