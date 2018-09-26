package org.lanqiao.student.test;

import org.junit.Test;
import org.lanqiao.dao.Impl.IStudentDaoImpl;
import org.lanqiao.dao.StudentDao;
import org.lanqiao.pojo.Student;

import java.io.IOException;

public class studentTest {
    @Test
    public void  addStudent() throws IOException {
        StudentDao studentDao = new IStudentDaoImpl();
        Student student1= new Student("李斯",29,"男");
        studentDao.addStu(student1);
    }
}
