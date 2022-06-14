package com.web;

import com.bean.Manager;
import com.server.ManagerServer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ManagerServlet extends BaseServlet {
    protected void managerlogin(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        req.setCharacterEncoding("UTF-8");
        String managerName = req.getParameter("managerName");
        String password = req.getParameter("password");

        List<Manager> managers = ManagerServer.Managerlogin(managerName, password);

        if (managers.size() == 1) {

            req.getSession().setAttribute("manager", managers.get(0));

            req.getRequestDispatcher("/pages/manager/manager.jsp").forward(req, resp);
        } else {
            //回传错误信息和表单信息，将它们保存到request作用域中
            req.setAttribute("msg", "用户名或密码错误！请重新输入");
            req.setAttribute("managerName",managerName);

            //跳回登录页面
            req.getRequestDispatcher("/pages/client/manager_login.jsp").forward(req, resp);
        }

    }
}
