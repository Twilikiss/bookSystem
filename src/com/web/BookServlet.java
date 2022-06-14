package com.web;

import com.bean.Book;
import com.bean.Page;
import com.server.BookServer;
import com.util.WebUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class BookServlet extends BaseServlet{


    /**
     * 添加图书数据
     * @param req
     * @param resp
     * @throws Exception
     */
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Integer pageNum = Integer.valueOf(req.getParameter("pageNum"));

        pageNum = pageNum + 1;
        //获取请求的参数并封装成对象
        Book book = WebUtil.copyParamTo(req.getParameterMap(), new Book());
        //添加保存图书至数据库中
        BookServer.addBook(book);
        //请求重定向
//        req.getRequestDispatcher("/manager/bookServlet?action=queryList").forward(req,resp) 会引起表单重复提交
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNum=" + pageNum);
    }

    /**
     * 删除图书数据
     * @param req
     * @param resp
     * @throws Exception
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Integer id = Integer.valueOf(req.getParameter("id"));

        BookServer.deleteBook(id);

        //请求重定向，防止表单重复提交
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNum=" + req.getParameter("pageNum"));
    }


    /**
     * 修改图书数据
     * @param req
     * @param resp
     * @throws Exception
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Integer id = Integer.valueOf(req.getParameter("id"));

        Book book = WebUtil.copyParamTo(req.getParameterMap(), new Book());

        BookServer.updateBook(book,id);

        //请求重定向，防止表单重复提交
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNum=" + req.getParameter("pageNum"));

    }

    /**
     * 获取要修改的图书便于回显数据
     * @param req
     * @param resp
     * @throws Exception
     */
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Integer id = Integer.valueOf(req.getParameter("id"));

        Book book = BookServer.queryBookById(id);

        //将查询出来的图书信息设置到request域中,便于表单回显
        req.setAttribute("book",book);

        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }


    /**
     * 查询所有图书数据
     * @param req
     * @param resp
     * @throws Exception
     */
    protected void queryList(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //查询图书数据
        List<Book> books = BookServer.queryAll();
        //将查询出来的图书信息设置到request域中
        req.setAttribute("books",books);
        //请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }


    /**
     * @Description 处理分页功能的模块
     * @ClassName BookServlet
     * @author   mlp52
     * @createTime  2022/3/15 13:22
     * @return void
     * @Param [req, resp]
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Integer pageNum = 1;
        Integer pageSize = Page.SIZE;
        if(req.getParameter("pageNum") != null){
            pageNum = Integer.valueOf(req.getParameter("pageNum"));
        }
        if (req.getParameter("pageSize") != null){
            pageSize = Integer.valueOf(req.getParameter("pageSize"));
        }

        //得到分页查询出来的Page类的对象
        Page<Book> page = BookServer.queryForItem(pageNum,pageSize);

        page.setUrl("manager/bookServlet?action=page");

        req.setAttribute("page",page);

        //请求转发，将request域的值传递到jsp页面展示
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}
