package com.cmcc.pay.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ech0 on 2016/3/13.
 */
public class MybatisUtil {
    private static ApplicationContext context ;

    private static SqlSessionFactory sqlSessionFactory;

//    static {
//        context = new ClassPathXmlApplicationContext("applicationContext.xml");
//
//        initMybatis();
//    }

    public  void initMybatis() {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setSqlSessionFactory(new SqlSessionFactoryBuilder().build(inputStream));
    }


    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public void   setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
}
