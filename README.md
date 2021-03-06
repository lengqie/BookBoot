## 基于Springboot前后端分离的后端图书管理系统

### 介绍

#### 流程图

![](./file/流程.png)

#### 技术栈
后端：

SpringBoot, Mybatis, Mysql, Swagger ,Shiro, ...

前端：

...

#### 地址

首页：[http:127.0.0.1:8081](http://127.0.0.1:8081)

Api：[http://127.0.0.1:8081/swagger-ui](http://127.0.0.1:8081/swagger-ui)

SQL：[BootBook.sql](https://github.com/lengqie/BookBoot/blob/master/file/BookBoot.sql)

假数据生成：[createFakeDbInfo.py](https://github.com/lengqie/BookBoot/blob/master/file/createFakeDbInfo.py)

### 进度
- 2021/5/13 Shiro Restful 中文支持
- 2021/5/11 添加了 数据库Book.name 全文检索
- 2021/5/10 添加了Mybatis 索引，开启Mysql 缓存
- 2021/5/8  完善了Swagger3 (3.0 似乎有一些Bug，且缺少文档)
- 2021/5/6  整合PageInfo
- 2021/4/29 添加了索引 假数据生成
- 2021/4/27 优化了代码规范 完善了Shiro
- 2021/4/26 优化了代码规范 添加Shiro注解, 密码md5, 删除了数据冗余
- 2021/4/23 优化了代码规范 完善了业务方法、逻辑
- 2021/4/22 优化了代码规范 restful
- 2021/4/21 优化了代码规范 修改了数据库
- 2021/4/20 优化了代码规范 修改了数据库
- 2021/4/19 优化了代码规范
- 2021/4/14 修复了授权认证
- 2021/4/13 整合了Shiro
- 2021/4/2 修改了数据库和其他规范 
- 2021/4/1 优化逻辑..
- 2021/4/1 更改前端方案  ~~尝试FastApi + Jinjia2~~
- 2021/4/1 添加了利用Cookie的认证 完成基本逻辑
- 2021/3/31 修复异常 完善业务逻辑
- 2021/3/30 创建Vue
- 2021/3/30 整合Swagger 完善业务逻辑
- 2021/3/29 项目创建