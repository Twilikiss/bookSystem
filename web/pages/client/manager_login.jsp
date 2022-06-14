<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理员登陆页面</title>

    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!--指定路径确保访问页面与资源正常-->
    <%
        String basePath = request.getScheme()
                + "://"
                + request.getServerName()
                + ":"
                + request.getServerPort()
                + request.getContextPath()
                + "/";

        pageContext.setAttribute("basePath",basePath);
    %>
    <base href="<%=basePath%>%">
    <link type="text/css" rel="stylesheet" href="static/css/manager_style.css" >
    <script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>

</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎登录</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>管理员你好！</h1>
                </div>
                <div class="msg_cont">
                    <b></b>
                    <%--请输入用户名和密码--%>
                    <span class="errorMsg">
                        <%=request.getAttribute("msg") == null? "请输入用户名和密码" : request.getAttribute("msg")%>
                    </span>
                </div>
                <div class="form">
                    <form action="manager/managerServlet" method="post">
                        <input type="hidden" name="action" value="managerlogin">
                        <label>管理员名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
                               name="managerName"
                        value="<%=request.getAttribute("managerName") == null? "" : request.getAttribute("managerName")%>"/>
                        <br/>
                        <br/>
                        &nbsp;
                        &nbsp;
                        <label>终端密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
                               name="password"/>
                        <br/>
                        <br/>
                        <input type="submit" value="登录" id="sub_btn"/>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>

<%--页脚部分内容--%>
<%@include file="/pages/common/footer.jsp"%>


</body>
</html>