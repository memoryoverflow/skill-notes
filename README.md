# skill-notes

#### 介绍

用于记录、整理、自己的学习笔记内容的一个技术站点。

类似 其它官方说明说明文档的的布局格式

#### 软件架构

前后端分离

后台采用:SpringBoot2.0.1 + Mybatis-plus3.1.0 + Mysql 5.7
前端：vue

#### 安装教程
> 后端

note:文件夹 为后端项目

导入IDEA即可，数据库文件不需要导入，只需要建好一个数据库即可，项目跑起来自动建表；


如果生成表失败，请手动导入sql；

主动生成表的starter ⚠️源码：https://github.com/memoryoverflow/forward-generation-starter

> **数据库的表自定生成配置：**

>yml:配置

```aidl
# 生成表的类型 update:在原有的基础上修改 create:删除重建
spring:
 ddl-auto: update/create
 
```
> 启动累加注解

**@EnableAutoForwardGeneration(entityPackages = "cn.yj.notes.core.entity",OnOff = true)**
- entityPackages: 实体类所在包路径，默认生成表——tb_开头，属性字段 默认都是驼峰
- OnOff：true/false; 是否开启表的自动生成；



> **前端**

vue->memoryoverflow:前端项目

vue 建议使用vscode 跑；
命令：
```java
cnpm/npm install
cnpm/npm run dev

```





#### 使用说明

将文件图片地址换成自己的即可

#### 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request


#### 码云特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. 码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5. 码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6. 码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)