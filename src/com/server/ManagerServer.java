package com.server;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.bean.Manager;
import com.util.DAOUtil;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;

public class ManagerServer {

    public static List<Manager> Managerlogin(String managerName, String password) throws  Exception{
        //加载配置文件
        Properties pro = new Properties();
        pro.load(ManagerServer.class.getClassLoader().getResourceAsStream("druid.properties"));
        DataSource ds = DruidDataSourceFactory.createDataSource(pro);

        //获取连接
        Connection conn = ds.getConnection();

        List<Manager> forList = DAOUtil.getForList(managerName, password);

        return forList;
    }
}
