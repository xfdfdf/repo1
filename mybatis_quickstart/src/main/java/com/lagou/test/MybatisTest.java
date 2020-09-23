package com.lagou.test;

import com.lagou.domain1.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: wjy
 * @DateTime: 2020/8/9 19:45
 * @ClassName MybatisTest
 */
public class MybatisTest {

    /**
     * 查询操作
     * @throws IOException
     */
    @Test
    public  void mybatisQuickStart() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = factory.openSession();
        List<User> listUser = sqlSession.selectList("UserMapper.findAll");
        for(User user : listUser) {
            System.out.println(user);
        }
        System.out.println("" + 'a' + 1);
        Integer a = 333;
        Integer b = 333;
        System.out.println(a == b);
        System.out.println(a.compareTo(b));

    }

    /**
     * 插入数据操作
     * @throws IOException
     */
    @Test
    public  void mybatisInsert() throws IOException, ParseException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = factory.openSession(true);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        User user = new User();
        user.setAddress("踏平东京");
        user.setUsername("自动提交事务");
        user.setBirthday( sdf.parse("2011-11-11"));
        user.setSex("男");
        int insert = sqlSession.insert("UserMapper.insert", user);
        if(insert > 0) {

            System.out.println("插入成功");
        } else {
            System.out.println("数据插入失败");
        }
    }


    /**
     * 更新数据操作
     * @throws IOException
     */
    @Test
    public  void mybatisUpdate() throws IOException, ParseException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession session = factory.openSession();
        User user = new User();
        user.setId(5);
        user.setBirthday(new Date());
        user.setSex("女");
        user.setUsername("赵云");
        user.setAddress("仓上");
        int update = session.update("UserMapper.update", user);
        if(update > 0) {
            System.out.println("更新成功");
            session.commit(true);
        } else {
            System.out.println("更新失败");
        }
    }

    /**
     * 删除数据操作
     * @throws IOException
     */
    @Test
    public  void mybatisDelete() throws IOException, ParseException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession session = factory.openSession();

        int delete = session.delete("UserMapper.delete", 9);
        if(delete > 0) {
            System.out.println("删除成功");
            session.commit(true);
        } else {
            System.out.println("删除失败");
        }
    }
}
