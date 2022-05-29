# SSMTrain
+ 搭建SSM平台  
+ 过几天再补充完整的配置过程，这个过程还是可圈可点的。
---
2022/5/29补充
# SSM项目快速搭建

> 来源自黄文毅的《Web轻量级框架Spring+Spring MVC+MyBatis整合开发实战》的第二章

## 一、快速搭建Web项目

### 1.1 安装JDK、Tomcat、MySQL、idea

略

### 1.2 搭建项目

1. 进入idea，点击`New Product`

2. 选择`Maven`项目
3. 勾选`Create from archetype`，再选择其中的`maven-archetype-webapp`
4. 一路Next到创建完成
5. 在main目录下创建java文件夹，并标记为java（让它变为蓝色）

## 二、集成Spring

### 2.1 引入依赖

在pom.xml中导入Spring相关的包

```xml
<properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.7</maven.compiler.source>
        <maven.compiler.target>1.7</maven.compiler.target>
        <spring.version>5.3.20</spring.version>
        <jstl.version>1.2</jstl.version>
        <javax.servlet.version>4.0.1</javax.servlet.version>
        <mysql.connector.java.version>8.0.29</mysql.connector.java.version>
        <druid.version>1.2.10</druid.version>
        <mybatis.version>3.5.10</mybatis.version>
        <mybatis.spring.version>2.0.7</mybatis.spring.version>
        <lombok.version>1.18.24</lombok.version>
        <javax.annotation.version>1.3.2</javax.annotation.version>
    </properties>
<!-- https://mvnrepository.com/artifact/org.springframework/spring-core -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.springframework/spring-beans -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.springframework/spring-aop -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.springframework/spring-context-support -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context-support</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.springframework/spring-aspects -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aspects</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.springframework/spring-expression -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-expression</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.springframework/spring-tx -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.springframework/spring-test -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>${spring.version}</version>
            <scope>test</scope>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.springframework/spring-web -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
            <version>${spring.version}</version>
        </dependency>
```

以上的参数部分有些在后面才会用到。

我开发的时候直接用了最新的版本，若包有更新的版本，修改最前方参数即可。

### 2.2 创建spring配置文件

在/src/main下创建resources目录并标记为resources。

在resources目录下创建applicationContext.xml文件。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-2.5.xsd">

    <context:component-scan base-package="com.donovan"/>
</beans>
```

其中的`<context:component-scan base-package="com.donovan"/>`目的是将改包下的java类扫描成bean对象。

接着，在web.xml中添加如下代码：

```xml
<!DOCTYPE web-app PUBLIC
        "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
        "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app>
    <display-name>Archetype Created Web Application</display-name>
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:applicationContext.xml</param-value>
    </context-param>
    <listener>
        <listener-class>
            org.springframework.web.context.ContextLoaderListener
        </listener-class>
    </listener>
</web-app>

```

监听器，使得spring项目启动时装配配置信息。

### 2.3 测试

在/src/main/test/com.donovan.test/目录下创建测试类。

```java
package com.donovan.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class SpringTest {

    @Test
    public void testSpring() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        SpringTest springTest = (SpringTest) applicationContext.getBean("springTest");

        springTest.sayHello();
    }

    public void sayHello() {
        System.out.println("hello donovan");
    }

}
```

在控制台看到结果即为成功。

## 三、集成Spring MVC

添加依赖如下：

```xml
<!--    springmvc start-->

<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>${spring.version}</version>
</dependency>

<!-- https://mvnrepository.com/artifact/javax.servlet.jsp.jstl/jstl -->
<dependency>
    <groupId>jstl</groupId>
    <artifactId>jstl</artifactId>
    <version>${jstl.version}</version>
</dependency>

<!-- https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api -->
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>${javax.servlet.version}</version>
    <scope>provided</scope>
</dependency>

<!-- https://mvnrepository.com/artifact/javax.annotation/javax.annotation-api -->
<dependency>
    <groupId>javax.annotation</groupId>
    <artifactId>javax.annotation-api</artifactId>
    <version>${javax.annotation.version}</version>
</dependency>
```

### 3.1 添加拦截控制器

在web.xml中添加DispatcherServlet配置。

```xml
<servlet>
    <servlet-name>spring-dispatcher</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:spring-mvc.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
    <servlet-name>spring-dispatcher</servlet-name>
    <url-pattern>/</url-pattern>
</servlet-mapping>
```

### 3.2 配置视图解析器

在/src/main/resources目录下创建配置文件spring-mvc.xml，并填写以下内容。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"

       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context
                           https://www.springframework.org/schema/context/spring-context.xsd
                           http://www.springframework.org/schema/mvc
                           https://www.springframework.org/schema/mvc/spring-mvc.xsd">
    <context:component-scan base-package="com.donovan.controller"/>

    <mvc:annotation-driven/>

    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
        <property name="prefix" value="/WEB-INF/views/"/>
        <property name="suffix" value=".jsp"/>
    </bean>
</beans>
```

“视图解析器”意为将视图层jsp文件解析成字符串，允许controller中的函数直接返回字符串作为跳转到某一页面的行为，大大降低了代码量！

上文xml表示前缀为`/WEB-INF/views/`，后缀为`.jsp`，也就是说解析该目录下的jsp文件。

### 3.3 测试

创建一个Controller用来测试。

首先在/src/main/java目录下创建包`com.donovan.controller`，并创建`UserController`类。

```java
package com.donovan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class DonovanTestController {
    @GetMapping("/sayHello")
    public String sayHello() {
        return "hello";
    }

}
```

然后在/src/main/webapp/WEB-INF目录下新建views文件夹。在views目录下创建hello.jsp文件，具体代码如下：

```jsp
<%--
  Created by IntelliJ IDEA.
  User: 86135
  Date: 2022/5/28
  Time: 9:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8" />
</head>
<body>

hello,donovan

</body>
</html>
```

至此，web项目的Spring MVC完成。

在浏览器目录中输入“http://localhost:8080/test/sayHello”，即可验证是否成功。

> 注意：若出现404问题，可能是Tomcat运行但未部署。
>
> 解决方案：在Edit Configuration...中，选择Tomcat的Deployment，其中选择war exploded的包，并将下方的“Application context”设置为空。

## 四、集成MyBatis框架

### 4.1 引入依赖

导入依赖

```xml
<!--        mybatis start-->
<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>${mysql.connector.java.version}</version>
</dependency>

<!-- https://mvnrepository.com/artifact/com.alibaba/druid -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>${druid.version}</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>${spring.version}</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>${mybatis.version}</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-spring -->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis-spring</artifactId>
    <version>${mybatis.spring.version}</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>${lombok.version}</version>
    <scope>provided</scope>
</dependency>
```

最后一个lombok是创建实体类用的。导入该以来后要同时在idea中安装同名插件。

### 4.2 添加配置

在/recources中添加jdbc.properties中添加以下内容：

```properties
jdbc.driverClassName=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://127.0.0.1:3306/(你的数据库名字)?serverTimezone=GMT
jdbc.username=root
jdbc.password=(你的密码)
```

以上是数据库配置信息，在spring boot也要有这个。

在applicationContext.xml中添加以下配置：

```xml
<!--    数据库相关参数-->
<context:property-placeholder location="classpath:jdbc.properties" ignore-unresolvable="true"/>

<!--    数据源 druid-->
<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource" init-method="init" destroy-method="close">
    <property name="driverClassName" value="${jdbc.driverClassName}"/>
    <property name="url" value="${jdbc.url}"/>
    <property name="username" value="${jdbc.username}"/>
    <property name="password" value="${jdbc.password}"/>
</bean>

<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource"/>
    <property name="mapperLocations" value="classpath:mapper/*.xml"/>
</bean>

<bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
    <constructor-arg index="0" ref="sqlSessionFactory"/>
</bean>

<bean id="mapperScannerConfigurer" class="org.mybatis.spring.mapper.MapperScannerConfigurer">
    <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
    <property name="basePackage" value="com.donovan.dao"/>
</bean>
```

以上完成了这两个任务：

1. 利用spring的特性将MyBatis的SqlSessionFactory等对象注入。
2. 利用阿里的druid包添加数据源。
3. 扫描mapper的xml

>两个任务有三条，很合理

### 4.3 创建表和实体类

创建表略。

实体类：利用lombok插件，为创建好的实体类属性添加getter、setter和有参、无参构造方法。

> 注意，默认lombok的@Data注解只生成getter、setter。若不手动添加构造方法会导致ajax的415错误。

在/java/com.donovan.model目录下创建实体类，代码：

```java
package com.donovan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Integer id;
    private String name;
    private String password;
}
```

实体类完成后，在/java/com.donovan.dao类下创建对应的DAO对象。

```java
package com.donovan.dao;

import com.donovan.model.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();
}
```

再在/java/com.donovan.service类下创建对应的服务层接口。

```java
package com.donovan.service;

import com.donovan.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
}
```

服务层接口开发完后，在/java/com.donovan.service.impl目录下开发对应的实现类。该实现类主要功能是注入Dao接口，并通过调用Dao方法实现findAll方法。

```java
package com.donovan.service.impl;

import com.donovan.dao.UserDao;
import com.donovan.model.User;
import com.donovan.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
```

最后，在/src/main/java/com.donovan.controller目录下创建控制器层类。

```java
package com.donovan.controller;

import com.donovan.model.User;
import com.donovan.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/findAll")
    public String findAll(Model model) {
        List<User> userList = userService.findAll();
        for (User user : userList) {
            System.out.println("id:" + user.getId());
            System.out.println("name:" + user.getName());
        }

        return "hello";

    }

}
```

在/src/main/resources/mapper目录下创建UserMapper.xml文件

```xml
<?xml version="1.0" encoding="utf-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.donovan.dao.UserDao">

    <select id="findAll" resultType="com.donovan.model.User">
        select *
        from user
    </select>


</mapper>
```

### 4.4 测试

其实也没啥了，点击运行，会跳到集成Spring MVC后的页面，然后在终端中会输出数据库中取出来的实体类对象。

至此快速配置SSM完成，你也可以再添加Log4j一类的包。
