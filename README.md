# 				spring-boot-learn-example
此文件 为 自己 学习spring- boot 全家桶 的例子 
JDK版本 1.7+

spring-boot 版本 1.4.7+

## spring-boot-example 

sayHello 项目

## spring-boot-jar-example 

以jar的方式运行的项目 此方式自带tomcat

## spring-boot-war-example 

以war的形式运行的项目 此方式打包或者编译会自动剔除tomcat 如果放在独立的tomcat webapp目录下 访问需要带项目名称

主要依赖：

```xml
<!-- 编译的时候会自动去掉 tomcat 依赖-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-tomcat</artifactId>
    <scope>provided</scope>
</dependency>
```



## spring-boot-jdbc-example

使用JDBC调用数据库连接 其实和spring jdbc一样的方式 ，本示例实现了简单的CRUD操作

主要依赖：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
```



## spring-boot-data-jpa-example

使用spring-data-jpa 的方式实现数据库操作，本示例实现了简单的CRUD操作

主要依赖：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

## spring-boot-profiles-example

使用多个配置文件来区分环境，例如 开发 测试 生产 

```properties
#通过这个参数来配置加载的配置文件 可以切换开发环境和生产环境 如： dev or prod
spring.profiles.active=prod
```

## spring-boot-log4j-example

使用log4j来进行日志记录功能

主要依赖：

```xml
<dependency>
    <groupId>commons-logging</groupId>
    <artifactId>commons-logging</artifactId>
    <version>1.2</version>
</dependency>
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.17</version>
</dependency>
```

log4j配置：

```properties
##调试打印日志
log4j.rootLogger=INFO,stdout
log4j.appender.stdout=org.apache.log4j.ConsoleAppender 
log4j.appender.stdout.Target=System.out
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout 
log4j.appender.stdout.layout.ConversionPattern=%5p %d %C: %m%n   %d{ABSOLUTE} %5p %c{1}:%L - %m%n
log4j.appender.stdout.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss, SSS} %5p %c{1}:%L - %m%n
##########linux日志打印
##设置日志存放路径
#log4j.rootLogger = INFO, FILE,ERROR_FILE
#############################本机测试
#logger = D:/log4j/manage/
#log4j.appender.FILE=org.apache.log4j.DailyRollingFileAppender
#log4j.appender.FILE.File=../logs/info_logger.log
#log4j.appender.FILE.ImmediateFlush=true
#log4j.appender.FILE.Threshold=DEBUG
#log4j.appender.FILE.Append=true
#log4j.appender.FILE.DatePattern='.'yyyy-MM-dd-HH
#log4j.appender.FILE.layout=org.apache.log4j.PatternLayout
#log4j.appender.FILE.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss, SSS}-%t-%x-%-5p-%-10c:%m%n
#########################错误日志
#log4j.appender.ERROR_FILE=org.apache.log4j.DailyRollingFileAppender
#log4j.appender.ERROR_FILE.File=../logs/error_logger.log
#log4j.appender.ERROR_FILE.ImmediateFlush=true
#log4j.appender.ERROR_FILE.Threshold=ERROR
#log4j.appender.ERROR_FILE.Append=true
#log4j.appender.ERROR_FILE.DatePattern='.'yyyy-MM-dd
#log4j.appender.ERROR_FILE.layout=org.apache.log4j.PatternLayout
#log4j.appender.ERROR_FILE.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss, SSS}-%t-%x-%-5p-%-10c:%m%n
```

引入log4j 配置：

```java
package com.julong.main;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 引入log4j日志模块
 * @author julong
 * @date 2020年2月12日 下午9:54:00
 * @desc 
 */
@Configuration
@ConfigurationProperties("classpath:log4j.properties")//引入 log4j 配置文件
public class Log4jConfiguration {

}

```

## spring-boot-log4j2-example

使用log4j2日志记录功能

主要依赖：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
```

log配置文件 log4j2-spring.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARE" name="log4j2-example" packages="">
	  <Properties>
	    <Property name="baseDir">./logs</Property>
	  </Properties>
	<!-- 打印到控制台的日志 -->
	<Appenders>
		<!-- 控制台打印 -->
		<!-- target Either "SYSTEM_OUT" or "SYSTEM_ERR". The default is "SYSTEM_OUT". -->
		<Console name="STDOUT" target="SYSTEM_OUT">
			<PatternLayout>
				<Pattern>%d{yyyy-MM-dd HH:mm:ss.SSS}  [%thread] %-5level %logger{50}	- %msg%n</Pattern>
			</PatternLayout>
		</Console>
		<!-- 生产环境打印方式 -->
		<RollingFile name="FILE_INFO" fileName="${baseDir}/info.log" filePattern="${baseDir}/$${date:yyyy-MM}/app-%d{yyyy-MM-dd-HH}.info.gz">
			<PatternLayout>
				<Pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50}	- %msg%n</Pattern>
			</PatternLayout>
		   	<!-- 打印级别 -->
			<ThresholdFilter level="INFO" onMatch="ACCEPT" onMismatch="DENY"/>
			<Policies>
				<!-- 参数配置为1 标识一小时一个文件  interval 此处填写 几就是 几小时滚动一次-->
				<TimeBasedTriggeringPolicy interval="1" modulate="true"/>
				<!-- 每个文件的大小  如果使用这个配置 如果和时间的配置一起使用 需要在 生成的文件上加入 %i  参数 用户日志产生序列 -->
				<!-- <TimeBasedTriggeringPolicy />
				<SizeBasedTriggeringPolicy size="100 KB" /> -->
			</Policies>
		</RollingFile>
		<RollingFile name="FILE_ERROR" fileName="${baseDir}/error.log" filePattern="${baseDir}/$${date:yyyy-MM}/app-%d{yyyy-MM-dd-HH}.error.gz">
			<PatternLayout>
				<Pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50}	- %msg%n</Pattern>
			</PatternLayout>
			<!-- 打印级别 -->
			 <ThresholdFilter level="ERROR" onMatch="ACCEPT" onMismatch="DENY"/>
			<Policies>
				<!-- 参数配置为1 标识一小时一个文件 -->
				<TimeBasedTriggeringPolicy interval="1" modulate="true"/>
			</Policies>
		</RollingFile>
	</Appenders>
	<Loggers>
		<!-- 打印debug日志 -->
		<Root level="DEBUG">
			<AppenderRef ref="STDOUT" />
			<!-- 生产环境放开此配置-->
			<AppenderRef ref="FILE_INFO" />
			<AppenderRef ref="FILE_ERROR" /> 
		</Root>
	</Loggers>
</Configuration>

```

## spring-boot-logback-example

使用logback来进行日志记录

主要依赖：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

logback-spring.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
	<appender name="console" class="ch.qos.logback.core.ConsoleAppender">
        <!--设置输出格式-->
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%n是换行符-->
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
        </encoder>
    </appender>
	 <!--文件输出,时间窗口滚动-->
    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <!--日志名,指定最新的文件名，其他文件名使用FileNamePattern -->
        <File>./logs/info.log</File>
        <!-- 过滤日志级别 -->
        <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
      		<level>INFO</level>
   		</filter>
        <!--文件滚动模式-->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!--日志文件输出的文件名,可设置文件类型为gz,开启文件压缩-->
            <FileNamePattern>./logs/info.%d{yyyy-MM-dd}.%i.log.gz</FileNamePattern>
            <!--日志文件保留天数-->
            <MaxHistory>30</MaxHistory>
            <!--按大小分割同一天的-->
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <maxFileSize>10MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
        </rollingPolicy>
		
        <!--输出格式-->
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%n是换行符-->
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
        </encoder>
    </appender>
	 <!--文件输出,时间窗口滚动-->
    <appender name="FILE_ERROR" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <!--日志名,指定最新的文件名，其他文件名使用FileNamePattern -->
        <File>./logs/error.log</File>
        <!-- 过滤日志级别 -->
        <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
      		<level>ERROR</level>
   		</filter>
        <!--文件滚动模式-->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!--日志文件输出的文件名,可设置文件类型为gz,开启文件压缩-->
            <FileNamePattern>./logs/error.%d{yyyy-MM-dd}.%i.log.gz</FileNamePattern>
            <!--日志文件保留天数-->
            <MaxHistory>30</MaxHistory>
            <!--按大小分割同一天的-->
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <maxFileSize>10MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
        </rollingPolicy>
		
        <!--输出格式-->
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%n是换行符-->
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
        </encoder>
    </appender>
	<root level="DEBUG">
		<appender-ref ref="console" />
		<appender-ref ref="FILE" />
		<appender-ref ref="FILE_ERROR" />
	</root>
</configuration>
```

