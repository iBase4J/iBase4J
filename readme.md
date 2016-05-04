主要功能如下：
=====
1、数据库
======

    Druid数据库连接池，监控数据库访问性能，详细统计SQL的执行性能，这对于线上分析数据库访问性能有帮助。 数据库密码加密。

2、持久层
======

    mybatis持久化，aop切换数据库实现读写分离，PageHelper分页。Transtraction注解Jta事务。

3、MVC
======

    基于spring mvc注解。Exception统一管理。基于session的国际化提示信息，职责链模式的本地语言拦截器、登录拦截器、权限拦截器。

4、调度
======

    Spring task, 可以查询已经注册的任务。立即执行一次任务。

5、缓存和Session
===========

    注解redis缓存数据，Spring-session和redis实现分布式session同步。

6、多系统交互
===========

    Dubbo,ActiveMQ多系统交互，ftp/sftp发送文件到独立服务器，使文件服务分离。没有权限的文件只用negix代理即可。

7、日志
===========

    log4j2打印日志，业务日志和调试日志分开打印。同时基于时间和文件大小分割日志文件。

8、工具类
===========

    上传下载excel，汉字转拼音，身份证号码验证，数字转大写人民币，FTP/SFTP上传下载，发送邮件，redis缓存，加密等等。

9、项目构建
===========

    maven构建项目，mybatis generator生成mybatis映射文件。 

10、其它
===========

    还需要什么功能? ? ? ?
![QQ](http://pub.idqqimg.com/wpa/images/group.png "QQ")
[538240548](http://shang.qq.com/wpa/qunwpa?idkey=b0fb32618d54e6a7f3cb718cd469b2952c8a968b1ef6f17fd68c83338ae4bce3)

>####说明：启动项目前请安装Redis和ZooKeeper，您可以在附件中下载。系统中均使用默认配置。

```
eclipse使用maven命令: 
    mybatis-generator:generate生成mybatis文件；
    clean:clean package -P build tomcat7:run-war-only 启动tomcat7。
    ![配置](http://git.oschina.net/iBase4J/iBase4J/raw/9caa79d7beb3f528bcaa66feec472315024d82ee/maven-config.png "maven配置")
```
