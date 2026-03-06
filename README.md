# MesAd - Manufacturing Execution System

[中文文档](./README.zh-CN.md)

A MES (Manufacturing Execution System) admin platform built on top of [RuoYi-Vue](https://gitee.com/y_project/RuoYi-Vue).

## Tech Stack

**Backend:** Spring Boot 2.5 + MyBatis + Spring Security + JWT + Redis + MySQL

**Frontend:** Vue 3 + Vite + Element Plus + Pinia + Vue Router 4

## Project Structure

```
MesAd
├── mesad-admin       # Application entry (port 8080)
├── mesad-framework   # Core framework (security, interceptors, config)
├── mesad-system      # System module (users, roles, menus, departments)
├── mesad-mes         # MES business module (orders, customers, products)
├── mesad-quartz      # Scheduled tasks
├── mesad-generator   # Code generator
├── mesad-common      # Common utilities
├── mesad-ui          # Frontend (Vue 3)
└── sql               # Database init scripts
```

## Features

- [X] Order management (CRUD, export, order details)
- [X] Customer management
- [X] Product management
- [X] Production execution
- [X] Auto-generated serial numbers
- [X] Production reports (CRUD, export)
- [ ] Quality management (WIP)

## Getting Started

### Prerequisites

- JDK 1.8+
- Maven 3.x
- MySQL 5.7+
- Redis
- Node.js 16+

### Backend

```bash
# 1. Import SQL scripts (sql/ry_20240629.sql and sql/mes.sql)
# 2. Update database config in mesad-admin/src/main/resources/application-druid.yml
# 3. Build and run
mvn clean package -DskipTests
java -jar mesad-admin/target/mesad-admin.jar
```

### Frontend

```bash
cd mesad-ui
npm install
npm run dev
```

### Production Build

```bash
cd mesad-ui
npm run build:prod
```

## TODO

- [ ] Quality management module

---

Graduation project, work in progress.
