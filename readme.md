>####说明：项目依赖activemq、Redis和ZooKeeper服务。

```
* maven启动SYS-Scheduler命令：clean:clean package -P build tomcat7:run-war-only -f pom-scheduler-server.xml
* maven启动SYS-Service命令：clean:clean package -P build tomcat7:run-war-only -f pom-sys-server.xml
* maven启动Web命令：clean:clean package -P build tomcat7:run-war-only -f pom-web-server.xml
* 服务启动后，使用nginx代理UI：修改配置里的UI目录后启动。
```

系统主要功能如下：
======
1、数据库
======

    Druid数据库连接池，监控数据库访问性能，统计SQL的执行性能。 数据库密码加密。

2、持久层
======

    mybatis持久化，aop切换数据库实现读写分离，PageHelper分页。Transtraction注解事务。

3、MVC
======

    基于spring mvc注解,Rest风格Controller。Exception统一管理。
    基于session的国际化提示信息，职责链模式的本地语言拦截器,Shiro登录、URL权限管理。
    QQ、微信、新浪微博第三方登录。

4、调度
======

    Spring+quartz, 可以查询、修改周期、暂停、删除、新增、立即执行，查询执行记录等。

5、缓存和Session
===========

    注解redis缓存数据，Spring-session和redis实现分布式session同步，重启服务会话不丢失。会话管理，强制结束会话。

6、多系统交互
===========

    Dubbo,ActiveMQ多系统交互，ftp/sftp/fastdafs发送文件到独立服务器，使文件服务分离。没有权限的文件只用nginx代理即可。

7、日志
===========

    log4j2打印日志，业务日志和调试日志分开打印。同时基于时间和文件大小分割日志文件。

8、工具类
===========

    上传下载excel，汉字转拼音，身份证号码验证，数字转大写人民币，FTP/SFTP/fastdafs上传下载，发送邮件，redis缓存，加密等等。

9、项目构建
===========

    maven构建项目，mybatis generator生成mybatis映射文件。 

10、其它
===========

    还需要什么功能? ? ? ?
加QQ群交流技术问题，下载项目文档和一键启动依赖服务工具。
![QQ](http://pub.idqqimg.com/wpa/images/group.png "QQ")
[538240548](http://shang.qq.com/wpa/qunwpa?idkey=b0fb32618d54e6a7f3cb718cd469b2952c8a968b1ef6f17fd68c83338ae4bce3)

![QQ](http://git.oschina.net/iBase4J/iBase4J/raw/master/img/1464169485871.png "QQ")

maven启动配置图
---
![配置](http://git.oschina.net/iBase4J/iBase4J/raw/9caa79d7beb3f528bcaa66feec472315024d82ee/maven-config.png "maven配置")

UI效果图
[http://git.oschina.net/iBase4J/iBase4J-UI](http://git.oschina.net/iBase4J/iBase4J-UI)


![登录](http://git.oschina.net/iBase4J/iBase4J/raw/master/img/login.png "登录")

![主页](http://git.oschina.net/iBase4J/iBase4J/raw/master/img/index.png "主页")

![捐赠](http://git.oschina.net/iBase4J/iBase4J/raw/master/img/contribute.png "捐赠")