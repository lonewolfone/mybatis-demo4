# SSM框架

SSM，即SpringMVC、Spring与MyBatis三大框架。三者在三层架构中所处的位置不同，功能不同，各司其职。

- SpringMVC:作为View层的实现者，完成用户的请求接受功能。SpringMVC的Controller作为整个应用的控制器，完成用户的响应及对用户请求的转发。
- MyBatis:作为Dao层的实现者，完成对数据库的增删改查功能。
- Spring:以整个官家的大管家的身份出现。整个应用中所有的Bean的生命周期行为，均由Spring管理。及整个应用中所有对象的创建、初始化、销毁、及对对象健关联关系的维护，均由Spring进行管理。

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\01.png)

​      **View层**						 **Service层**					 **Dao层**

框架的必要性：

- 消除重复

- 花繁为简

- 减少依赖，消除耦合

------

## 一、JavaEE开发采用常见三层架构

将整个应用划分为：

1、表现层(View层)

主要对用户的请求接受，以及数据的返回，为客户端提供应用程序的访问。负责处理与客户的交互操作(Struts2/SpringMVC)。

2、业务层(Dao/Service层)

主要是针对具体问题的操作，即对数据层的操作，对数据业务逻辑处理。负责复杂的业务逻辑计算和判断。(Spring)

3、持久层(Controller层)

主要是对原始数据(数据库或文本文件等存放数据的形式)的操作层，而不是指原始数据，是对数据的操作，而不是数据库，具体为业务逻辑层或表示层提供数据服务。负责将数据进行持久化操作。(hibernate/Mybatis)

*****区分层次的目的：高内聚低耦合，单一责任原则(低耦合：过多功能放一个类中完成，减少类与类间相互依赖）。

------

## 二、MyBatis：持久层框架

### 1、ORM(包括MyBatis框架）：对象关系映射

​	 应用开发中，需要将Java对象存储到数据表中，即需要将Java对象转换为数据库，处理数据间的管理关系。

​	 为解决面向对象与关系数据库存在的互不匹配的现象的技术。通过使用描述对象和数据库之间映射的元数据，将Java程序中的对象自动持久化到关系数据库中。避免直接使用SQL语句对关系型数据库中的数据进行操作。

#### 	A.ORM主要解决对象 -- 关系的映射：

​	   面向对象			面向关系

| 类   | 表     |
| :--- | ------ |
| 对象 | 表的行 |
| 属性 | 表的列 |

#### 	B.ORM的实现思想：

​	   将关系数据库中表中的记录映射成为对象，以对象的形式展现，将数据库的操作转为对对象的操作。

​	    ORM目的：以面向对象的思想来实现对数据库的操作。

​	    ORM:采用元数据来描述：对象 -- 关系映射细节：

​			元数据(即对数据的描述)：通常采用XML格式，存放在专门的对象 -- 关系映射文件中。

#### 	 C.目前流行的ORM框架：

​		1.JPA

​		2.Hibernate

​		3.MyBatis:本是apache的一个开源项目iBatis，提供的持久层框架包括：SQL Maps 和 Dao,允许开发人							    员直接编写SQL等。

### 2、什么是MyBatis?

MyBatis 是一款优秀的持久层框架，它支持定制化 SQL、存储过程以及高级映射。MyBatis 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集。MyBatis 可以使用简单的 XML 或注解来配置和映射原生信息，将接口和 Java 的 POJOs(Plain Old Java Objects,普通的 Java对象)映射成数据库中的记录。

### 3、MyBatis的入门

#### a.MyBatis的下载

[https://github.com/mybatis]: https://github.com/mybatis

#### b.新建一个项目

- ##### 新建一个Maven

  ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\02.png)

  ------

  ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\03.png)

- #####引入依赖(在配置文件pom.xml中)：

  [http://www.mybatis.org/mybatis-3/]: http://www.mybatis.org/mybatis-3/

  ```properties
  <dependencies>
          <!--MyBatis依赖-->
          <dependency>
              <groupId>org.mybatis</groupId>
              <artifactId>mybatis</artifactId>
              <version>3.4.4</version>
          </dependency>
          <!--引入mysql依赖-->
          <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
          <dependency>
              <groupId>mysql</groupId>
              <artifactId>mysql-connector-java</artifactId>
              <version>5.1.46</version>
          </dependency>
           <!--测试：Test-->
          <dependency>
              <groupId>junit</groupId>
              <artifactId>junit</artifactId>
              <version>4.12</version>
              <scope>test</scope>
          </dependency>
      </dependencies>
  ```

- ##### 创建链接数据源和事务管理的配置文件  mybatis-config,xml(放在resources)

  ```properties
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE configuration
          PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-config.dtd">
  <configuration>
      <environments default="development">
          <environment id="development">
              <transactionManager type="JDBC"/>
              <dataSource type="POOLED">
                  <property name="driver" value="com.mysql.jdbc.Driver"/>
                  <property name="url" value="jdbc:mysql://localhost:3306/world/"/>
                  <property name="username" value="root"/>
                  <property name="password" value="root"/>
              </dataSource>
          </environment>
      </environments>
     <!--注册映射文件(StudentMapper.xml)-->
      <mappers>
          <mapper resource="org/lanqiao/dao/StudentMapper.xml"/>
      </mappers>
  </configuration>
  ```

- ##### 建立数据库

  ![数据库表](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\数据库表.PNG)

- ##### 编写表所对应的实体类

  ```java
  public class Student {
      private int id;
      private String sname;
      private int   sage;
      private String ssex;
      public Student(){
  
      }
  
      public Student(String sname, int sage, String ssex) {
          this.sname = sname;
          this.sage = sage;
          this.ssex = ssex;
      }
  
      public Student(int id, String sname, int sage, String ssex) {
          this.id = id;
          this.sname = sname;
          this.sage = sage;
          this.ssex = ssex;
      }
  
      public int getId() {
          return id;
      }
  
      public void setId(int id) {
          this.id = id;
      }
  
      public String getSname() {
          return sname;
      }
  
      public void setSname(String sname) {
          this.sname = sname;
      }
  
      public int getSage() {
          return sage;
      }
  
      public void setSage(int sage) {
          this.sage = sage;
      }
  
      public String getSsex() {
          return ssex;
      }
  
      public void setSsex(String ssex) {
          this.ssex = ssex;
      }
  
      @Override
      public boolean equals(Object o) {
          if (this == o) return true;
          if (o == null || getClass() != o.getClass()) return false;
          Student student = (Student) o;
          return id == student.id &&
                  sage == student.sage &&
                  Objects.equals(sname, student.sname) &&
                  Objects.equals(ssex, student.ssex);
      }
  
      @Override
      public int hashCode() {
          return Objects.hash(id, sname, sage, ssex);
      }
  
      @Override
      public String toString() {
          return "Student{" +
                  "id=" + id +
                  ", sname='" + sname + '\'' +
                  ", sage=" + sage +
                  ", ssex='" + ssex + '\'' +
                  '}';
      }
  }
  ```

- ##### dao接口

  - 新建多个包的接口步骤

    ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\04.png)

    ------

    ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\05.png)

    ------

    ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\06.png)

    ------

    ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\07.png)

  - **dao层中的方法**

    ```java
    import java.io.IOException;
    
    public interface StudentDao {
    //    添加一条数据
        public void addStu(Student student) throws IOException;
    }
    ```

  - ##### **建立映射文件（StudentMapper.xml）**

    映射文件一般和接口放在一起

    ```xml
    <?xml version="1.0" encoding="UTF-8" ?>
            <!DOCTYPE mapper
            PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    <!--接口-->
    <mapper namespace="org.lanqiao.dao.IStudentDao">
        <!--sql语句-->
        <!--方法  参数类型-->
        <insert id="addStudent" parameterType="org.lanqiao.pojo.Student">
            insert  into stu(sname,sage,ssex) values (#{sname},#{sage},#{ssex})
        </insert>
    </mapper>
    ```

  - 注册映射文件(StudentMapper.xml)

    ```xml
    <!--注册映射文件-->
        <mappers>
            <mapper resource="org/lanqiao/dao/StudentMapper.xml"/>
        </mappers>
    ```

  - **dao层的具体实现类(Impl)**

    - 新建包及类的步骤

      ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\08.png)

      ------

      ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\09.png)

    - 代码

      1.编写中所遇到的问题

      ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\10.png)

      ------

      ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\11.png)

      2.代码

      ```java
      import org.apache.ibatis.session.SqlSessionFactory;
      import org.apache.ibatis.session.SqlSessionFactoryBuilder;
      import org.lanqiao.dao.IStudent;
      import org.lanqiao.pojo.Student;
      
      import java.io.IOException;
      import java.io.InputStream;
      
      public class IStudentDaoImpl implements StudentDao {
          @Override
          public void addStu(Student student) throws IOException {
                  //0 读取配置文件
                  InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
                  // 1 建立SqlSessionFactory 对象
                  SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
                  //2 获取SqlSession对象
                  SqlSession sqlSession =  sqlSessionFactory.openSession();
                  //3 执行sql
                  sqlSession.insert("addStudent",student);
      //            MySql不自动提交事务，此处为手动提交事务
                  sqlSession.commit();
                  //4 关闭sqlsession
                  if(sqlSession!=null){
                      sqlSession.close();
                  }
              }
          }
      ```

    - 当当

  - 当当

- ##### 测试

  ```java
  public class studentTest {
      @Test
      public void addStuTest() throws IOException {
          //创建接口对象
          IStudent iStudent = new IStudentDaoImpl();
          //创建实体类对象
          Student student = new Student("张三",22,"男");
          iStudent.addStu(student);
      }
  }
  ```

- ##### 解决maven项目中找不到mybatis的映射文件的问题

  ```xml
   <build>
          <resources>
              <resource>
                  <directory>src/main/java</directory>
                  <includes>
                      <include>**/dao/*Mapper.xml</include>
                  </includes>
              </resource>
          </resources>
      </build>
  ```

- 解决插入数据中文乱码的问题

  ```xml
  <property name="url" value="jdbc:mysql://localhost:3306/world?useUnicode=true&amp;characterEncoding=utf8&amp;useSSL=false&amp;serverTimezone=UTC"/>
  ```

-  将数据库链接属性单独提出一个properties属性文件

  ```properties
  driver=com.mysql.jdbc.Driver
  url=jdbc:mysql://localhost:3306/world?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC
  username=root
  password=root
  ```

-  如何让mybatis读取到properties属性文件

  ```properties
   <properties resource="jdbc.properties">    </properties>
  ```

- da

- da

- da



#### 



