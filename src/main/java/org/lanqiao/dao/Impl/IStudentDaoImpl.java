package org.lanqiao.dao.Impl;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.lanqiao.dao.StudentDao;
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

