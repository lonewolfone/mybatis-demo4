package org.lanqiao.dao;

import org.lanqiao.pojo.Student;

import java.io.IOException;

public interface StudentDao {
//    添加一条数据
    public void addStu(Student student) throws IOException;
}
