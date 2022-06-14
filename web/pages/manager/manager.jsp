<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理</title>

    <%--静态包含头部信息--%>
    <%@include file="/pages/common/head.jsp" %>

    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">后台管理系统</span>

    <%--静态包含manager菜单--%>
    <%@include file="/pages/common/manager_menu.jsp" %>

</div>

<div id="main">
    <h1>欢迎管理员${sessionScope.manager.username}进入后台管理系统</h1>
</div>


<%--页脚部分内容--%>
<%@include file="/pages/common/footer.jsp"%>

</body>
</html>