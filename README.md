# Matrix-Web

##  前言

Matrxi-Web是一个前后端分离的管理系统，前端采用vue开发框架，后端使用springboot开发框架，具体快速开发、简单可复用的特点。只要把整体框架搭建起来了，后面就只用写CRUD了。

Matrxi-Web具备基本的管理系统的基本功能，后端CRUD代码只需要配置好数据库，便可一键生成。

## 使用教程

- [跟我学Springboot开发后端管理系统1：概述](https://www.fangzhipeng.com/springboot/2020/05/01/mw-summary.html)
- [跟我学Springboot开发后端管理系统2：Mybatis-Plus实战](https://www.fangzhipeng.com/springboot/2020/05/02/mw-mybatis-plus.html)
- [跟我学Springboot开发后端管理系统2：Mybatis-Plus实战2](https://www.fangzhipeng.com/springboot/2020/05/03/mw-mybatis-plus2.html)
- [跟我学Springboot开发后端管理系统4：数据库连接池Druid和HikariCP](https://www.fangzhipeng.com/springboot/2020/05/04/mw-durid.html)
- [跟我学Springboot开发后端管理系统5：数据库读写分离](https://www.fangzhipeng.com/springboot/2020/05/05/mysql-rwp.html)
- [跟我学Springboot开发后端管理系统6：缓存框架Caffeine](https://www.fangzhipeng.com/springboot/2020/05/06/mw-caffine.html)
- [跟我学Springboot开发后端管理系统7：Matrxi-Web权限设计](https://www.fangzhipeng.com/springboot/2020/05/07/mw-permission.html)
- [跟我学Springboot开发后端管理系统8：Matrxi-Web权限设计实现](https://www.fangzhipeng.com/springboot/2020/05/08/permission-done.html)

## 目录

本项目一共有三个模块：

- matrix-web-backend 后端代码模块
- matrix-web-admin 前端代码模块
- matrix-gen-code 后端代码生成模块

##  使用的技术栈

### 后端模块

- Web框架：Spring Boot 2.0.3
- 数据库ORM：Mybatis-Plus
- 数据库读写分离：Sharding-JDBC
- 数据库连接池：支持Druid、也支持Hikari
- 缓存：Caffeine
- 权限控制：AOP+注解
- 日志输出：AOP+logback+MDC
- 任务调度：Quartz
- 工作流：activiti5.22.0，设计页面放在前端模块。、
- API管理：Swagger

### 前端模块

- 前端框架：Vue
- 网络框架：axios
- 组件：element-ui
- 路由：vue-router
- cookie技术：js-cookie

###  代码生产模块

- 生产代码：Mybatis-Plus自带的代码生成

## 包含的功能

Matrix-Web融合了开源界的优秀技术，旨在为开发者迅速搭建一个管理后端，默认包含了以下的功能：

- 用户管理
- 角色管理
- 菜单管理
- 权限管理
- 字段管理
- 业务日志管理
- 定时任务管理
- 登录日志统计
- 密码策略
- 工作流管理

##  开发工具和环境

- JDK 1.8
- IDEA
- Webstorm
- node v10.16.0 ，npm v6.9.0

## 如何运行Matrix-Web

- git clone git@github.com:forezp/matrix-web.git
- 在MySQL数据库中创建数据库aries，初始化matyrix-web-backend/src/resource/sql的2个sql文件，依次执行task.sql、matrix-web.sql，此时不导入ac522.sql。
- 在application配置文件配置MySQL，修改logback.xml的LOG_HOME的目录。
- 修改quartz.properties的mysql的链接配置。
- 启动后端matrix-web-backend的Spring Boot工程
- Spring Boot工程执行成功之后，再执行sql文件夹中的ac522.sql，导入样例工作流数据
- 前端模块需要安装ndoe.js、vue。然后进入matrix-web-admin目录下，执行以下步骤：
  - 运行 npm install --registry=https://registry.npm.taobao.org
  - 运行npm install --unsafe-perm node-sass
  - 运行npm run dev
  - 启动成功后访问 [http://localhost:9528](http://localhost:9528/) ,登录，用户名密码:fangzhipeng/123456

## 项目截图

系统管理页面：

![](https://static.javajike.com/img/2020/05/matrix/matrix-web01.png)

流程管理界面：

![](https://static.javajike.com/img/2020/05/matrix/matrix-web002.png)

任务管理界面： 
![](https://static.javajike.com/img/2020/05/matrix/matrix-web003.png)

运维管理界面：
![](https://static.javajike.com/img/2020/05/matrix/matrix-web004.png)

##  鸣谢

本项目的前端页面参考了[web-flash](https://github.com/enilu/web-flash)和[vue-element-admin](https://panjiachen.github.io/vue-element-admin-site/zh/)，感谢二位前端大佬。

## 源码下载

https://github.com/forezp/matrix-web
