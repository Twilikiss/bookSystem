package com.server;

import com.bean.Book;
import com.bean.Page;
import com.util.DAOUtil;
import java.util.List;

public class BookServer {
    /**
     *  本方法实现了传入当前页码和每一页显示数据的个数就可以返回Page<Book>的对象，供Servlet调用
     * @ClassName BookServer
     * @Param [pageNum, pageSize]
     */
    public static Page<Book> queryForItem(Integer pageNum, Integer pageSize) throws Exception {

        Integer pageTotalCount = DAOUtil.getTotal();

        Integer pageTotal;

        if (pageTotalCount % pageSize == 0) {
            pageTotal = pageTotalCount / pageSize;
        } else {
            pageTotal = (pageTotalCount / pageSize) + 1;
        }


        //提供两个if来保证数据边界有效
        if (pageNum < 1){
            pageNum = 1;
        }

        if (pageNum > pageTotal){
            pageNum = pageTotal;
        }


        List<Book> pageItems = DAOUtil.queryForItem((pageNum - 1) * pageSize, pageSize);



        Page<Book> page = new Page<>(pageNum,pageTotal,pageSize,pageTotalCount,pageItems,null);

        return page;
    }

    /**
     * 通过id查询书籍信息
     * @param id
     * @return
     */
    public static Book queryBookById(Integer id) {
        Book book = DAOUtil.queryById(id);
        return book;
    }

    /**
     *  添加书籍到数据库
     */
    public static void addBook(Book book) {
        String sql = "insert into t_book(`name`, `author`, `price`, `sales`, `stock`) values (?, ?, ?, ?, ?)";
        DAOUtil.update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock());
    }

    /**
     * 通过id删除对应书籍
     * @param id
     */
    public static void deleteBook(Integer id) {
        String sql = " delete from t_book where id = ?";
        DAOUtil.update(sql, id);
    }

    /**
     * 通过id修改书籍信息
     * @param book
     * @param id
     */
    public static void updateBook(Book book, Integer id) {
        String sql = " update t_book set `name` = ?, `author` = ?,`price` = ? ,`sales` = ? ,`stock` = ? where id = ?";
        DAOUtil.update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),id);
    }

    /**
     * 查询所有书籍信息并返回
     * @return
     */
    public static List<Book> queryAll() {
        return DAOUtil.getAll();
    }
}
