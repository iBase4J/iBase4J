系统主要功能如下：
=====
1、数据库
======

    Druid数据库连接池，监控数据库访问性能，详细统计SQL的执行性能，这对于线上分析数据库访问性能有帮助。 数据库密码加密。

2、持久层
======

    mybatis持久化，aop切换数据库实现读写分离，PageHelper分页。Transtraction注解Jta事务。

3、MVC
======

    基于spring mvc注解。Exception统一管理。
    基于session的国际化提示信息，职责链模式的本地语言拦截器,Shiro登录、URL权限管理。
    QQ、微信、新浪微博第三方登录。

4、调度
======

    Spring task, 可以查询已经注册的任务。立即执行一次任务。

5、缓存和Session
===========

    注解redis缓存数据，Spring-session和redis实现分布式session同步，重启服务会话不丢失。会话管理，强制结束会话。

6、多系统交互
===========

    Dubbo,ActiveMQ多系统交互，ftp/sftp发送文件到独立服务器，使文件服务分离。没有权限的文件只用nginx代理即可。

7、日志
===========

    log4j2打印日志，业务日志和调试日志分开打印。同时基于时间和文件大小分割日志文件。

8、工具类
===========

    上传下载excel，汉字转拼音，身份证号码验证，数字转大写人民币，FTP/SFTP上传下载，发送邮件，redis缓存，加密等等。

9、项目构建
===========

    maven构建项目。 

## 加入QQ群[538240548](http://shang.qq.com/wpa/qunwpa?idkey=b0fb32618d54e6a7f3cb718cd469b2952c8a968b1ef6f17fd68c83338ae4bce3) [498085331](http://shang.qq.com/wpa/qunwpa?idkey=0a7344955bb9b9f6e366d34be42c02709c933f308ab435b1a52ac17d40efdff5)
![QQ群](http://git.oschina.net/iBase4J/iBase4J/raw/master/img/1464169485871.png "QQ群一")
![QQ群](http://git.oschina.net/iBase4J/iBase4J/raw/master/img/1482378175294.png "QQ群二")

>####说明：启动项目前请安装activemq、Redis和ZooKeeper，并启动服务，您可以在附件中下载。系统中均使用默认配置。

```
eclipse使用maven命令: 

    tomcat:run  或 jetty:run
   
```
![配置](http://git.oschina.net/iBase4J/iBase4J/raw/9caa79d7beb3f528bcaa66feec472315024d82ee/maven-config.png "maven配置")

![捐赠](http://git.oschina.net/iBase4J/iBase4J/raw/master/img/contribute.png "捐赠")