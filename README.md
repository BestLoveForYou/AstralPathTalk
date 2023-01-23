# AstralPathTalk
## 介绍
基于SpringBoot的开源论坛系统,拥有高效的后台管理界面,快捷的文章发布,方便的留言系统等.
目前项目正处于起步阶段,是属于**AstralPath**的旗下项目.
## 版本

### 1.20230122.0010
- 更新了发布文章再次编辑的功能
- 修复了粉丝系统的Bug
### 1.20230120.0009
- 修复了查看粉丝功能的Bug
### 1.20230119.0008
- 更新了粉丝系统
- 关注和被关注
- 更新了文章系统的评论
### 1.20221231.0007
- 更新了用户界面的UI
### 1.20221220.0006
- 修复了已知的显示Bug
### 1.20221217.0005
- 修复了文章系统的编辑Bug
### 1.20221216.0004
- 更新文章编辑功能
### 1.20221216.0003
- 更新了留言板系统
### 1.20221216.0002
- 更新了用户系统的功能
### 1.20221215.0001
- 第一个版本发行

## 使用
### 下载
[Github下载链接](https://github.com/BestLoveForYou/AstralPathTalk "Github")
### 使用前的准备
- 我们使用**8111**端口作为开放端口,您需要开放此端口,您当然可以在源码中更改以适配您的需求,不过我们建议使用Nginx来映射或是docker运行以提升速度.
- 您需要在**/src/main/resources/application.properties**配置以适配您的需求,下方将告诉您需要配置的内容
- 您需要准备**mysql数据库,一个支持SMTP的邮箱**,当然数据库我们将会把创建代码告诉您

### 配置文件
```
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
spring.datasource.username=名
spring.datasource.password=数据库密码
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/webtest

spring.mail.host=smtp.163.com
spring.mail.port=465
spring.mail.username=您的邮箱
spring.mail.password=授权码
spring.mail.default-encoding=UTF-8
spring.mail.properties.mail.smtp.socketFactory.class=javax.net.ssl.SSLSocketFactory
spring.mail.properties.mail.debug=false

server.port=8111
spring.boot.admin.client.url=http://localhost:8080
```

### 使用数据库
数据库名:**webtest**
*我们不建议更改使用的数据库名*
```sql
CREATE TABLE `MessageBoard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `by` varchar(255) DEFAULT NULL,
  `byid` int(11) DEFAULT NULL,
  `body` varchar(255) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `locked` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text_id` int(11) NOT NULL,
  `byEmail` varchar(255) DEFAULT NULL,
  `body` varchar(255) DEFAULT NULL,
  `locked` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`,`text_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `fan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guanzhiid` int(11) DEFAULT NULL,
  `userid` int(11) NOT NULL,
  `date` char(20) DEFAULT NULL,
  `locked` int(3) DEFAULT NULL,
  `username` char(255) DEFAULT NULL,
  `guanzhuname` char(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `jb` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jb_id` int(11) DEFAULT NULL,
  `jb_mode` int(3) DEFAULT NULL,
  `jb_by_player` int(11) DEFAULT NULL,
  `date` char(30) DEFAULT NULL,
  `isOver` int(2) NOT NULL,
  `res` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`,`isOver`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `text` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `target` varchar(20) DEFAULT NULL,
  `body` mediumtext,
  `author` varchar(255) DEFAULT NULL,
  `quanzhong` int(4) DEFAULT NULL,
  `look` int(11) DEFAULT NULL,
  `locked` int(2) DEFAULT NULL,
  `date` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`,`title`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `money` int(11) DEFAULT '0',
  `level` int(11) DEFAULT '1',
  `notenumber` int(11) DEFAULT '0',
  `locked` tinyint(1) DEFAULT '0',
  `email` varchar(255) NOT NULL DEFAULT '无',
  `date` varchar(32) DEFAULT NULL,
  `role` int(10) DEFAULT NULL,
  `title` char(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`email`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

```
### 启动
您可以直接使用
```cmd
java -jar server.jar
```
来启动,但我们更建议您使用**docker**启动!

## API接口
### 用户类
|  位置 |  使用HTTP方法 |  传入参数 |成功时返回参数| 描述 |
| ------------ | ------------ | ------|---- |---|
|  /user/getUserLogin | POST  |无 |用户信息(json)|获取一个用户信息的json|
|  /user/rega |  POST | email,password,username|int|注册的第一步|
| /user/regb|POST|auth|用户信息(json)|注册的第二步|
|/user/login|POST|email,password|用户信息(json)|登录|
|/user/sign|GET|无|今日已签到!|每日签到的接口|
|/user/logout|GET|无|完成|退出登录状态,删除session|
|/user/change/username|GET|username|完成|更改用户名|
|/user/change/passwor|GET|password|完成|更改密码|
|/user/getUserAllText|POST|无|文章(json[list])|获取改用户发布全部文章|
|/user/fan/get|GET|id|(json[list])|获取改用户的粉丝|
|/user/fan/get2|GET|id|(json[list])|获取改用户的关注|
|/user/fan/guanzhu|GET|id|String|关注|
|/user/quguan|GET|id|String|取关|

### 文章类
|  位置 |  使用HTTP方法 |  传入参数 |成功时返回参数| 描述 |
| ------------ | ------------ | ------|---- |---|
|  /text/getUserLogin | POST  |无 |用户信息(json)|获取一个用户信息的json|
|/text/rest|GET|id|文章(json)|根据文章id获取文章信息|
|/text/rest|POST|id|文章内容(string)|根据文章id获取文章内容|
|/text/getComment|GET|id|文章评论(json[list])|获取文章评论|
|/text/create|POST|title,target,body|int|发布文章|
|/text/search|GET|title,start,end|文章(json[list])|查找文章|
|/text/out|GET|无|文章(json[list])|主动推送文章|
|/text/add|POST|body,textid|int|评论文章|

### 留言板
|  位置 |  使用HTTP方法 |  传入参数 |成功时返回参数| 描述 |
| ------------ | ------------ | ------|---- |---|
|  /mb/getUserLogin | POST  |无 |用户信息(json)|获取一个用户信息的json|
|/mb/get|GET|无|留言(json[list])|获取全部留言|
|/mb/add|POST|body|int|留言|
