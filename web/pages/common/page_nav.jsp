<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--分页开始--%>
<div id="page_nav">
    <%--判断是否为第一页，如果是第一页则隐藏--%>
    <c:if test="${requestScope.page.pageNum > 1}">
        <a href="${requestScope.page.url}&pageNum=1">首页</a>
        <a href="${requestScope.page.url}&pageNum=${requestScope.page.pageNum-1}">上一页</a>
    </c:if>

    <%--页码输出开始--%>
    <c:choose>
        <%--当总页面小于等于5时--%>
        <c:when test="${requestScope.page.pageTotal <= 5}">
            <c:set var="begin" value="1"/>
            <c:set var="end" value="${requestScope.page.pageTotal}"/>
        </c:when>
        <%--当总页面大于5时--%>
        <c:when test="${requestScope.page.pageTotal > 5}">
            <c:choose>
                <%--当前页码小于等于3时--%>
                <c:when test="${requestScope.page.pageNum <= 3}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="5"/>
                </c:when>
                <%--当前页码为最后3个时--%>
                <c:when test="${requestScope.page.pageNum > requestScope.page.pageTotal - 3}">
                    <c:set var="begin" value="${requestScope.page.pageTotal - 4}"/>
                    <c:set var="end" value="${requestScope.page.pageTotal}"/>
                </c:when>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNum - 2}"/>
                    <c:set var="end" value="${requestScope.page.pageNum + 2}"/>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>
    <%--       页码输出结束--%>
    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i == requestScope.page.pageNum}">
            【${i}】
        </c:if>
        <c:if test="${i != requestScope.page.pageNum}">
            <a href="${requestScope.page.url}&pageNum=${i}">${i}</a>
        </c:if>
    </c:forEach>
    <%--判断是否为最后一页，如果是最后一页则隐藏--%>
    <c:if test="${requestScope.page.pageNum < requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNum=${requestScope.page.pageNum+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNum=${requestScope.page.pageTotal}">末页</a>
    </c:if>

    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
    &nbsp;
    到第<input value="${requestScope.page.pageNum}" name="pn"
             id="pn_input"/>页
    <input id="searchButton" type="button" value="确定">
    <script type="text/javascript">
        $(function () {
            // 绑定事件，跳到指定页码
            $("#searchButton").click(function () {
                var pageNum = $("#pn_input").val();
                //对查询数据边境的判断
                var pageTotal = ${requestScope.page.pageTotal};

                if (!/(^[1-9]\d*$)/.test(pageNum)){
                    alert("输入的数字有误！！！");
                } else if (pageNum > pageTotal || pageNum < 1) {
                    alert("输入的数字有误！！！");
                } else {
                    location.href = "${pageContext.getAttribute("basePath")}${requestScope.page.url}&pageNum=" + pageNum;
                }

            })
        })
    </script>
</div>
<%--分页结束--%>