package com.util;

import com.bean.Book;
import com.bean.Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOUtil {
    /**
     * 通用的增、删、改操作
     */
    public static void update(String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1.获取数据库的连接
            conn = JDBCUtils.getConnection();

            //2.获取PreparedStatement的实例 (或：预编译sql语句)
            ps = conn.prepareStatement(sql);
            //3.填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            //4.执行sql语句
            ps.execute();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            //5.关闭资源
            JDBCUtils.closeResource(conn, ps, null);

        }
    }

    /**
     * 针对于manger的查询:返回账号和密码符合条件的对象的链表
     */

    public static List<Manager> getForList(String username,String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;//查询出来的数据先放到rs中
        try {
            conn = JDBCUtils.getConnection();
            List<Manager> ar = new ArrayList<>();//存储从数据库中取出来的数据
            //sql执行器对象
            ps = null;
            //结果集对象
            rs = null;
            String sql = "select * from t_manager where username = ? and password = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,username);
            ps.setObject(2,password);
            rs = ps.executeQuery();//执行数据库查询的方法，放到rs中

            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("username");
                String password1 = rs.getString("password");
                String email = rs.getString("email");
                Manager manager = new Manager(id,name,password1,email);
                ar.add(manager);
            }
            return ar;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,ps,rs);
        }

        return null;
    }

    /**
     * 针对于book的查询:返回书的总数
     */

    public static int getTotal() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;//查询出来的数据先放到rs中
        try {
            conn = JDBCUtils.getConnection();
            int total = 0;
            //sql执行器对象
            ps = null;
            //结果集对象
            rs = null;
            String sql = " select * from t_book";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();//执行数据库查询的方法，放到rs中
            while (rs.next()){
                total++;
            }
            return total;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,ps,rs);
        }

        return 0;
    }

    /**
     * 针对于book的查询:返回书的总数
     */

    public static List<Book> getAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;//查询出来的数据先放到rs中
        try {
            conn = JDBCUtils.getConnection();
            ArrayList<Book> arr = new ArrayList<>();
            //sql执行器对象
            ps = null;
            //结果集对象
            rs = null;
            String sql = " select * from t_book";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();//执行数据库查询的方法，放到rs中
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String author = rs.getString("author");
                double price = rs.getDouble("price");
                int sales = rs.getInt("sales");
                int stock = rs.getInt("stock");
                Book book = new Book(id,name,author,price,sales,stock);
                arr.add(book);
            }
            return arr;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,ps,rs);
        }

        return null;
    }

    /**
     * 针对于manger的查询:返回指定页数的书籍信息
     */

    public static List<Book> queryForItem(int begin,int size) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;//查询出来的数据先放到rs中
        try {
            conn = JDBCUtils.getConnection();
            List<Book> ar = new ArrayList<>();//存储从数据库中取出来的数据
            //sql执行器对象
            ps = null;
            //结果集对象
            rs = null;
            String sql = "SELECT * FROM t_book LIMIT ?,?;";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,begin);
            ps.setObject(2,size);
            rs = ps.executeQuery();//执行数据库查询的方法，放到rs中

            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String author = rs.getString("author");
                double price = rs.getDouble("price");
                int sales = rs.getInt("sales");
                int stock = rs.getInt("stock");
                Book book = new Book(id,name,author,price,sales,stock);
                ar.add(book);
            }
            return ar;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,ps,rs);
        }

        return null;
    }

    /**
     * 针对于manger的查询:对应ID的书籍信息
     */

    public static Book queryById(int Inid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;//查询出来的数据先放到rs中
        try {
            conn = JDBCUtils.getConnection();
            Book book = null;
            //sql执行器对象
            ps = null;
            //结果集对象
            rs = null;
            String sql = "SELECT * FROM t_book where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,Inid);
            rs = ps.executeQuery();//执行数据库查询的方法，放到rs中

            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String author = rs.getString("author");
                double price = rs.getDouble("price");
                int sales = rs.getInt("sales");
                int stock = rs.getInt("stock");
                book = new Book(id,name,author,price,sales,stock);

            }
            return book;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,ps,rs);
        }

        return null;
    }
}

