# Guides

- 一级标题标识版本。版本号跟随 `Spring boot` 的版本号演进。版本号可以跳着发布。

# To do Tasks

- !!! 适配 spring 3.0.0
- !! 整合几个到一起，将**代码生成**项目单独作为一个项目运行。
- !! websocket 在系统中是否能去掉
- !! 修改包名为：com.fuqssi。
- ! 了解 Yauaa 的用途，https://yauaa.basjes.nl。

# Version

## 2.7.5(unpublished)

### Working

将所有日期格式更新成 jdk8 中的 java.time。

1. 修改所有所有类的 java.sql.Date, java.util.Date 为 java.time.LocalDate 或者 java.time.LocalDateTime 。

**still have some, keep searching**



1. 删除后端 common 包下若干类：
   1. me.zhengjie.utils.DateUtil.java	/ me.zhengjie.utils.DateUtilTest.java (in test folder)

### Completed Work

- 2022-11-13
  - 配合 2022-06-04 任务，修改 SQL 文件，eladmin_2_2_fornow.sql

- 2022-06-13

  - 升级 JDK 17。

  - Remove copyright information from some source code files.


- 2022-06-05

  - 替换数据库连接池，druid —> hikari。

  - 删除 fastjson，使用 springboot 支持的 jackson。

  - 删除 log4jdbc.log4j2.properties。

  - 删除 SQL 监控相关代码。


- 2022-06-04

  - 删除后端 tools 包下：阿里支付、七牛存储相关代码。

  - 删除后端 system 包下：运维管理、服务器监控相关代码，该运维管理无实际意义。

  - 删除前端阿里支付、七牛存储、运维管理、服务器监控相关代码。


### 升级过程中遇到的问题及解决方法

#### 从 fastjson 替换成 jackson

注意 JwtUserDto 类反序列化时，要加一个 setRoles() 的方法。



elAdmin- 2022-11-06 23:16:13 [ELADMIN-HikariCP housekeeper] WARN  com.zaxxer.hikari.pool.HikariPool - ELADMIN-HikariCP - Thread starvation or clock leap detected (housekeeper delta=9m47s448ms).

## 2.7

- 升级 JDK 11。
- 升级 Spring boot 2.4.4 。
- 升级 Swagger 3.0.0 。
- 升级 commons-pool2 2.9.0。
- 升级 mapstruct 1.4.2.Final 。
- 升级 druid 1.2.6 。
- 升级 fastjson 1.2.76 。
- 优化数据库脚本。
- 替换容器，tomcat -> undertow 。

### 升级过程中遇到的问题及解决方法

#### 执行数据库脚本报异常

MySQL 中 int，bigint 等类型不需要写长度数字。将 int(8) 这类内容改为 int。

#### 跨域配置问题

> When allowCredentials is true, allowedOrigins cannot contain the special value "*“since that cannot be set on the “Access-Control-Allow-Origin” response header. To allow credentials to a set of origins, list them explicitly or consider using"allowedOriginPatterns” instead.

修改如下代码：

```java
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
      // 增加这一行代码
        config.addAllowedOriginPattern("*");
      // 注释掉这一行代码
//        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
```

