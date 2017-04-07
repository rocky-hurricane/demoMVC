package com.atrocky.demo.test;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

import java.sql.SQLException;

/**
 * Created by rocky on 17/3/30.
 */
public class  connectDB {

    ApplicationContext applicationContext = null;
    {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

    }

    @Test
    //test the connection to mysql by DataSource
    public void test() throws SQLException {
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        System.out.printf(String.valueOf(dataSource.getConnection()));
    }

}
