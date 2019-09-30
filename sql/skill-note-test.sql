/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : skill-note-test

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 01/10/2019 00:15:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_knowledge
-- ----------------------------
DROP TABLE IF EXISTS `tb_knowledge`;
CREATE TABLE `tb_knowledge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` longtext,
  `status` int(11) DEFAULT '1',
  `deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_knowledge
-- ----------------------------
BEGIN;
INSERT INTO `tb_knowledge` VALUES (1, '2019-09-30 23:59:32', '2019-09-30 23:59:37', '介绍', '::: hljs-center \n # java 核心基础资源库\n:::\n\n::: hljs-center\n\n![](http://img5.imgtn.bdimg.com/it/u=2887709787,478021268&fm=26&gp=0.jpg)\n\n**学\n习\n是\n个\n不\n断\n总\n结\n归\n纳\n的\n过\n程\n&emsp;！**\n\n\n:::\n\n\n', 1, 0);
INSERT INTO `tb_knowledge` VALUES (12, '2019-10-01 00:11:49', NULL, '反射的使用', '**java 开发中 任何的一个框架 都离不开发射机制，比如我们最熟悉的 框架`Mybatis`、`Spring`...** 几乎所有封装的  框架中都离不开它，想要做架构，做工具、写一个 starter 都离不开反射机制。 所以说在开发中，反射机制运用得当可以省下很多代码。\n\n**接下来个人学习的见解**\n**获取当前类的相关信息**\n\n# 反射机制\n &emsp; 反射应该是 JVM读取相应类的 字节码文件；在类的运行状态中，通过字节码文件也就是.class文件，获取当前类的所有信息，包括 成员变量，私有、共有的方法、构造函数、以及相关的注解等。\n\n# 反射的三种方式\n1.  对于任意一个类，都能够知道这个类的所有属性和方法 `User.class`\n2. 对于任意一个类的全路径名，都能够知道这个类的所有属性和方法 `class.forName(\"com.baidu.User\")`\n3. 对于任意一个实例对象，都能够调用它的任意一个方法和属性 `(new User()).getClass()`\n\n\n@[TOC]', 1, 0);
INSERT INTO `tb_knowledge` VALUES (13, '2019-10-01 00:12:07', NULL, '反射之·真实战', '\n\n> **==脱离实战的讲解 都是 流氓，我们即将进行反射的实战==**\n>> **我们将实现一个 根据 实体 bean 加上我们的自定义注解 正向生成向库里 生成对应的表的一个 starter (SpringBoot )**\n\n**我们的项目得先引入依赖**\nmysql 驱动\n```\n<dependency>\n   <groupId>mysql</groupId>\n   <artifactId>mysql-connector-java</artifactId>\n   <scope>runtime</scope>\n</dependency>\n```\n\n\n# 1、建立好我们的项目（SpringBoot）\n![image.png](https://www.memoryoverflow.cn/file/articleContentImg/2019-09-20/1568957376173.png)\n![image.png](https://www.memoryoverflow.cn/file/articleContentImg/2019-09-20/1568955195605.png)\n\n**demo-test  是测试项目**\n**forward-generation-starter 就是一个自己造的一个starter,打了一个jar 可以自行引入** \n\n# 2、自定义注解说明\n![image.png](https://www.memoryoverflow.cn/file/articleContentImg/2019-09-20/1568957448890.png)\n1. **`@EnableAutoForwardGeneration`**\n是否开启主动生成表操作 放在App启动类里面\nOnOff()  true:开启，false:不开启\n```\n/**\n * <br>\n * 开启正向生成操作\n * @author 永健\n * @since 2019/5/7 14:21\n */\n@Retention(RetentionPolicy.RUNTIME)\n@Target(ElementType.TYPE)\n@Documented\n@Import(RegisterScaner.class)\npublic @interface EnableAutoForwardGeneration\n{\n    // 指定实体类所在的包\n    String entityPackages();\n    // 是否开启表自动生成\n    boolean  OnOff() default true;\n}\n```\n2.**`@LColumn` 列属性注解**\n```\n/**\n * <br>\n * 列注解\n * @author 永健\n * @since 2019/5/7 14:37\n */\n@Retention(RetentionPolicy.RUNTIME)\n@Target(ElementType.FIELD)\n@Documented\npublic @interface LColumn\n{\n    /**\n     * <br>\n     * 列长度 String 类型的默认为\n     */\n    int width() default 0;\n\n    /**\n     * <br>\n     * 指定数据库类型\n     */\n    ColumnType type() default ColumnType.FIELDTYPE;\n\n    /**\n     * <br>\n     * 列是否默认为空\n     */\n    boolean isNull() default true;\n\n    /**\n     * <br>\n     * 是否有默认值\n     */\n    String defaultValue() default \"\";\n\n    /**\n     * <br>\n     * 注释\n     */\n    String comment() default \"\";\n\n}\n```\n\n3.**`@LIncrement`**\n\n```\n/**\n * <br>\n * 是否自增\n * @author 永健\n * @since 2019/5/7 15:01\n */\n@Retention(RetentionPolicy.RUNTIME)\n@Target(ElementType.FIELD)\n@Documented\npublic @interface LIncrement\n{\n    IdType type() default IdType.NONE;\n}\n```\n\n4.**`@LNotTableField` 排除非表中字段**\n```\n/**\n * <br>\n * 排除非表中字段\n * @author 永健\n * @since 2019/5/7 15:09\n */\n@Retention(RetentionPolicy.RUNTIME)\n@Target(ElementType.FIELD)\n@Documented\npublic @interface LNotTableField\n{\n}\n```\n\n5.**`LTable` 实体注解 指定表名**\n```\n/**\n * <br>\n * 实体注解 指定表名否则转下划线\n * @author 永健\n * @since 2019/5/7 15:23\n */\n@Retention(RetentionPolicy.RUNTIME)\n@Target(ElementType.TYPE)\n@Documented\npublic @interface LTable\n{\n  String name() default \"\";\n}\n```\n\n6.**`@LTableId 主键注解`**\n```\n/**\n * <br>\n * 主键注解\n * @author 永健\n * @since 2019/5/7 14:36\n */\n@Retention(RetentionPolicy.RUNTIME)\n@Target(ElementType.FIELD)\n@Documented\npublic @interface LTableId\n{\n    IdType type() default IdType.NONE;\n}\n```\n\n# 3、注解使用例子\n\n\n**实体上的注解**\n```\n/**\n * <br>\n *\n * @author 永健\n * @since 2019/5/7 17:31\n */\n@LTable(name = \"tb_user\")\npublic class User extends BaseEntity\n{\n    @LTableId(type = IdType.AUTO)\n    @LColumn(width = 11,type = ColumnType.INT,isNull = false,comment = \"主键\")\n    private Integer id;\n\n    @LColumn(width = 64,isNull = true,comment = \"姓名\",type = ColumnType.VARCHAR)\n    private String name;\n\n    @LColumn(width = 0,isNull = true,comment = \"出生日期\")\n    private Date birthDay;\n\n    @LNotTableField\n    private Company company;\n    \n     // get set 省略\n}\n```\n\n**App 启动类加上注解**\n```\n@SpringBootApplication\n@EnableAutoForwardGeneration(entityPackages=\"com.example.demo.entity\",OnOff = true)\npublic class DemoApplication\n{\n    public static void main(String[] args)\n    {\n        SpringApplication.run(DemoApplication.class, args);\n    }\n}\n```\n\n**application.yml 配置**\n```\nspring:\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://127.0.0.1:3307/test?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8\n    username: root\n    password: tiger\n\n    #update 在原有的基础上更新,create：删除原来的表 根据实体信息重新建\n    ddl-auto: create\n```\n\n# 4、思路\n其实我是学 mybatis的初始化过程的，之前有看过mybatis 源码，mybatis 也是利用Spring的扫描接口 去扫描的 mappeer.java  文件；[mybatis mappe接口扫描](https://www.memoryoverflow.cn/blog/#/index/article/home/detail/4e5fbdf81f224085889eab51b06c6cc2)\n\n使用SpringBoot 自定义一个starter,利用Spring的ClassPathBeanDefinitionScanner.java 的 doScan扫描接口，自定义扫描规则，也就是加了@LTable 注解的实体；\n在starter 中的LyjAutoConfigure 实现于 `InitializingBean.java` 中的`afterPropertiesSet` 方法，在初始化bean的时候，此方法会被执行，所以这个时候我们就可以通过`ConfigurableListableBeanFactory.java` 中的方法 `getBeansWithAnnotation(Class<? extends Annotation> var1)` 拿到加了我们自定义注解的bean了。\n```\n@Override\n    public void afterPropertiesSet()\n    {\n        // 获取自定义类注解的类\n        List<Class<?>> classes = new ArrayList<>();\n        Map<String, Object> map = beanFactory.getBeansWithAnnotation(LTable.class);\n        for (Map.Entry<String, Object> entry : map.entrySet()) {\n            classes.add(entry.getValue().getClass());\n        }\n\n        if (classes.size()<=0){\n            return;\n        }\n\n        ConfigureEntityTable configureEntity = new ConfigureEntityTable();\n        configureEntity.setProperties(this.mySqlProperties);\n\n        // 解析实体\n        EntityParserHandler parserHandler = new EntityParserHandler(configureEntity);\n        parserHandler.parser(classes);\n\n        // 读取数据库中的表信息\n        TableParserHandler tableParserHandler = new TableParserHandler(configureEntity,ddlExecutor);\n        tableParserHandler.parser();\n\n        DataBaseServiceHandler serviceHandler = new DataBaseServiceHandler(this.ddlExecutor,configureEntity);\n        serviceHandler.excute();\n\n        logger.info(\"表已更新-------->\");\n\n    }\n```\n拿到了我们的bean的信息，这会就可以通过反射，去读取类的相关信息了，比如 类上的注解啊，属性注解啊，等。这会就可以通过这个信息，属性名，转驼峰，和加上注解的一些配置值，就可以生成 我们的DDl语句了，接下来就是 连上数据库了 执行我们的sql语句去见表了。\n\n\n**⚠️ 只是一个demo ，对反射的实践操作**\n\n项目源码   [github 源码](https://github.com/memoryoverflow/forward-generation-starter)\n\n\n@[toc]', 1, 0);
INSERT INTO `tb_knowledge` VALUES (14, '2019-10-01 00:13:09', NULL, 'Http协议～问答', '&emsp; http协议是网页开发的必备知识，是基于TCP/IP通信协议来传输数据的。\n\n# 1、HTTP 协议的定义是什么？\n\nHTTP协议是基于 客户端/服务端 的架构模型；是一种超文本传输协议。\n\n# 2、HTTP请求有那三部分组成？\n### 1.请求行\n![image.png](https://www.memoryoverflow.cn/file/articleContentImg/2019-09-28/1569643462174.png)\n\n请求行包括了： 请求方法 url http协议版本 \n例如：\n```\nGET /write/edit/68 HTTP/1.1\nUser-Agent: curl/7.16.3 libcurl/7.16.3 OpenSSL/0.9.7l zlib/1.2.3\nHost: www.memoryoverflow.cn\n.......\n```\n### 2.请求头部\n请求头包含的东西就很多可以自定义：自带一般会有 date  server content-type 等字段\n```\naccess-control-allow-credentials: true\naccess-control-allow-headers: Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Accept\naccess-control-allow-methods: GET, POST, PATCH, DELETE, PUT, OPTIONS\ncontent-type: application/json;charset=UTF-8\ndate: Sat, 28 Sep 2019 03:59:55 GMT\nserver: nginx/1.12.2\nstatus: 200\n```\n### 3.空行\n**空行，请求头部后面的空行是必须的；即使第四部分的请求数据为空，也必须有空行。**\n\n### 4.请求数据\n请求数据就是：客户端于服务端交流时候，所携带的参数内容\n```\nid=68&type=intenet\n```\n\n**一个HTTP的请求例子**\n```\nPOST /write/edit/68  HTTPS1.1\nHost:www.memoryoverflow.cn\nUser-Agent:Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1;\nContent-Type:application/x-www-form-urlencoded\nConnection: Keep-Alive\n\nid=68\n\n```\n\n## HTTP响应：\n响应报文包括四部分：状态行，消息报头 空行 响应内容\n\n如图所示：\n![image.png](https://www.memoryoverflow.cn/file/articleContentImg/2019-09-28/1569644539894.png)\n\n\n\n# 3、HTTP 客户端于服务端的交流过程？\n\n是基于TCP/IP通信协议来传输数据的；\n\n**客户端（浏览器，默认端口是 80）-----  建立TCP连接（三次握手）  ----> 服务端----断开连接（四次挥手）-->响应客户端，**\n\n![image.png](https://www.memoryoverflow.cn/file/articleContentImg/2019-09-28/1569642894760.png)\n\n\n**Http(应用层)--->TCP/UDP(传输层)--->网络层（IP）---> 链路层(网络硬件)**\n\n![image.png](https://www.memoryoverflow.cn/file/articleContentImg/2019-09-28/1569646104250.png)\n\n### 1.TCP的三次握手\n**客户端于服务端建立连接之前需要进行通信测试连接**\n\n![image.png](https://www.memoryoverflow.cn/file/articleContentImg/2019-09-28/1569647802279.png)\n\n### 2.TCP的四次挥手\n**当客户端的请求到服务端，服务端数据处理完成后，客户端主动断开当前连接请求**\n\n![image.png](https://www.memoryoverflow.cn/file/articleContentImg/2019-09-28/1569648944856.png)\n\n第一次：客户端发送连接释放请求，FIN=1,seq=u;客户端进入FIN-WAIT-1（终止等待1）状态\n\n第二次：服务器收到连接释放信息，响应确认，ACK=1，ack=u+1，并且带上自己的序列号seq=v，此时，服务端就进入了CLOSE-WAIT（关闭等待）状态。\n\n第三次：客户端收到服务器的确认请求后，此时，客户端就进入FIN-WAIT-2（终止等待2）状态，等待服务器发送连接释放报文（在这之前还需要接受服务器发送的最后的数据）。\n服务器将最后的数据发送完毕后，就向客户端发送连接释放信息，FIN=1，ack=u+1，；\n\n\n第四此：客户端收到服务器的连接释确认消息后，必须发出确认消息，ACK=1，ack=w+1，而自己的序列号是seq=u+1，此时，客户端就进入了TIME-WAIT（时间等待）状态。\n**注意此时TCP连接还没有释放，必须经过2∗ *∗MSL（最长报文段寿命）的时间后，当客户端撤销相应的TCB后，才进入CLOSED状态。\n\n# 4、常用的HTTP方法有哪些？\n1. POST：常用于 传输数据到服务端\n2. GET：一般用于 向服务去获取消息\n3. DELETE： 用于删除数据/文件请求\n4. PUT:常用于数据修改\n5. HEAD:类似于 GET 请求，只不过返回的响应中没有具体的内容，用于获取报头\n6. OPTIONS:查询相应URI支持的HTTP方法\n\n# 5、HTTP 常见状态码，有哪些？\n1. 200 请求成功\n2. 301 资源网页被永久转移到其它URL\n3. 404 请求的路径错误，不存在\n4. 500 服务器抛出异常，服务器错误\n5. 503 服务器不存在，连接不上服务器\n6. 400 客户端请求数据格式错误\n7. 401 没有权限，未进行身份认证\n8. 405 请求方法错误 比如 后台指定的HTTP方法为 POST ,客户端发送GET请求\n9.504 网关错误 \n\n# 6、HTTP 与 HTTPS 有什么区别？\nhttp:\n1.通信不加密，内容可能被窃听\n2.不验证通信方身份，可能遭到伪装\n3.无法验证报文完整性，可能被篡改\nHttps:\n是一个加密的安全协议\n\n# 7、Http协议有哪些特征?\n### 1.无状态：\n&emsp; HTTP协议是无状态协议。无状态是指协议对于事务处理没有记忆能力。缺少状态意味着如果后续处理需要前面的信息，则它必须重传，这样可能导致每次连接传送的数据量增大。另一方面，在服务器不需要先前信息时它的应答就较快\n\n### 2.无连接\n&emsp;无连接的含义是限制每次连接只处理一个请求。服务器处理完客户的请求，并收到客户的应答后，即断开连接。采用这种方式可以节省传输时间\n\n### 3.媒体独立\n&emsp;这意味着，只要客户端和服务器知道如何处理的数据内容，任何类型的数据都可以通过HTTP发送。客户端以及服务器指定使用适合的MIME-type内容类型。\n\n@[toc]', 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT '0',
  `article_id` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT '1',
  `sort` int(11) DEFAULT NULL,
  `deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
BEGIN;
INSERT INTO `tb_menu` VALUES (1, '2019-09-30 23:57:43', '2019-09-30 23:57:49', '基础说明', 0, NULL, 1, 1, 0);
INSERT INTO `tb_menu` VALUES (2, '2019-09-30 23:58:05', '2019-09-30 23:58:09', '介绍', 1, '1', 1, NULL, 0);
INSERT INTO `tb_menu` VALUES (3, '2019-10-01 00:10:57', NULL, '反射', 0, NULL, 1, 99, 0);
INSERT INTO `tb_menu` VALUES (4, NULL, NULL, '反射的使用', 3, '12', 1, 99, 0);
INSERT INTO `tb_menu` VALUES (5, NULL, NULL, '反射之·真实战', 3, '13', 1, 99, 0);
INSERT INTO `tb_menu` VALUES (6, '2019-10-01 00:12:43', NULL, '网络', 0, NULL, 1, 99, 0);
INSERT INTO `tb_menu` VALUES (7, NULL, NULL, 'Http协议～问答', 6, '14', 1, 99, 0);
COMMIT;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(255) DEFAULT NULL,
  `login_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `token_expire_time` bigint(20) DEFAULT '864000000',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
BEGIN;
INSERT INTO `tb_user` VALUES (1, '2019-09-30 17:59:32', '2019-10-01 00:13:41', NULL, 'admin', '123456', 'ff317406-c5dd-4fa2-b488-c286632785ad', 7200000);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
