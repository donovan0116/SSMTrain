<%--
  Created by IntelliJ IDEA.
  User: 86135
  Date: 2022/5/28
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>commodityManage</title>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8"/>
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
<div align="center">商品管理系统</div><br>
<div style="margin-left: 63%">
    <button id="in" onclick="into()">新增商品</button>
</div>

<table border="1" align="center">
    <tr>
        <td>商品名称</td>
        <td>商品类型</td>
        <td>商品描述</td>
        <td>状态</td>
        <td>操作</td>
    </tr>

    <c:forEach items="${commodityList}" var="commodity">

        <tr>
            <td width="50">${commodity.name}</td>
            <td width="50">${commodity.type}</td>
            <td width="150">${commodity.comment}</td>
            <td width="100">${commodity.state}</td>
            <td width="200">
                <button id="out" onclick="out(${commodity.id})">删除商品</button>
                <button id="update" onclick="info(${commodity.id})">商品更新</button>
            </td>
        </tr>

    </c:forEach>


</table>


</body>

<script>
    function into() {
        const url = "http://localhost:8080/commodity/in";
        window.open(url, '_self')
    }

    function out(id) {
        const url = "http://localhost:8080/commodity/out?id=" + id;
        window.open(url, '_self')
    }

    function info(id) {
        const url = "http://localhost:8080/commodity/updateInfo?id=" + id;
        window.open(url, '_self')
    }
</script>

</html>
