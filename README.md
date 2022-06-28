<img src="img_1.png" alt="img_1.png"  />

# [ad-astra 视频网站V1.0版本](http://adastra.isamumu.cn/#/)

 项目持续维护中.......

[前端地址](https://gitee.com/arturiamu/libilibi-web) | [后端地址](https://gitee.com/arturiamu/libilibi-host.git)

## 关于ad-astra

ad-astra是一个由SpringBoot、MyBatis、MySQL、Redis主流等技术以及aliyun(阿里云)、alipay(阿里支付)、tencentcloudapi（腾讯云服务）等第三方接口开发的**个性化视频推荐**网站，该网站收录了B站内包含舞蹈、音乐、动画、电影等在内的20种类型、共计20余万条视频、供用户免费观看，同时也实现了用户对视频的点赞、收藏和用户间的交流功能，更多功能持续更新中...

## 项目规模

项目共计使用15张数据表、57个api接口

## 快速开始

### 需求

- 你需要安装mysql8.0.29或其他能够兼容此版本的mysql数据库
- 你需要创建数据库`adastra`
- 你需要安装Redis3.2.100或其他能够兼容此版本的Redis数据库
- 你需要申请腾讯云短信服务接口
- 你需要申请支付宝沙箱支付服务接口
- 你需要配置一个支持smtp协议的QQ邮箱
- 你的 `PATH` 环境变量必须包含所需要的构建工具，如Tomcat、Maven

- 你需要安装Node.js并能正常使用npm包管理器下载项目所需依赖

### 修改配置文件

#### 默认配置

```yml
server:
  port: 9000
  address: 0.0.0.0
  servlet:
    encoding:
      force: true
      charset: UTF-8
spring:
  mvc:
    static-path-pattern: /static/**
  jackson:
    defaultPropertyInclusion: non_null
    deserialization:
      fail_on_unknown_properties: false
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://175.24.175.35:3306/adastra?characterEncoding=utf-8&serverTimezone=Asia/Shanghai
    username: adastra
    password: mlb20000102
    type: com.alibaba.druid.pool.DruidDataSource
  redis:
    host: localhost
    port: 6379
    password:
    database: 0
    timeout: 1800000
    lettuce:
      cluster:
        refresh:
          adaptive: true
          period: 60s
      pool:
        max-active: 20
        max-wait: -1
        max-idle: 5
        min-idle: 1
  mail:
    host: smtp.qq.com
    username: 2762404119@qq.com
    password: wopenrzwqnfhdhdh
    default-encoding: utf-8
    port: 465
    properties:
      mail.smtp.ssl.enable: true
    protocol: smtps
  thymeleaf:
    prefix: classpath:/templates/
    suffix: .html
    mode: HTML5
    encoding: UTF-8
    servlet:
      content-type: text/html
    cache: false
sms:
  app:
    id: 1400683116
    key: 95f32e5cdb35bbc88dbcedb41b6934e4
    template:
      universal: 1419186
    sign: isamumu
  size: 6

aliyun:
  oss:
    file:
      endpoint: oss-cn-hangzhou.aliyuncs.com
      keyId: LTAI5tJTSkD8cSxk1dazEqLV
      keysecret: 0iN9FRV610rfHP5bfzKVuZxIcCdlc5
      bucketname: libilibi-host

mybatis:
  mapper-locations: classpath*:mapper/*Mapper.xml
  type-aliases-package: com.am.adastra.entity
  configuration:
    cache-enabled: true
    map-underscore-to-camel-case: true
logging:
  level:
    com:
      am:
        adastra: warn
knife4j:
  enable: true
```

## todo list

