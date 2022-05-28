<%--
  Created by IntelliJ IDEA.
  User: 86135
  Date: 2022/5/28
  Time: 19:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>删除商品</title>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8"/>
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>

<div align="center">删除商品</div>
<br>
<table border="1" align="center">
    <tr>
        <td>商品名称</td>
        <td><input id="name" value="${commodity.name}"/></td>
    </tr>
    <tr>
        <td>商品类型</td>
        <td><input id="type" value="${commodity.type}"/></td>
    </tr>
    <tr>
        <td>商品描述</td>
        <td><input id="comment" value="${commodity.comment}"/></td>
    </tr>
    <tr>
        <td>商品状态</td>
        <td><input id="state" value="${commodity.state}"/></td>
    </tr>
</table>
<div align="center">
    <button id="out" value="${commodity.id}">确认</button>
</div>
</body>

<script>
    $("#out").click(function () {
        console.log($("#name").val())
        var _this = this;
        var id = _this.value;
        var url = "http://localhost:8080/commodity/updateState";
        var state = "已删除";
        var data = {
            "id": id,
            "name": $("#name").val(),
            "type": $("#type").val(),
            "comment": $("#comment").val(),
            "state": state
        }
        console.log(data);
        $.ajax({
            type: "post",
            url: "http://localhost:8080/commodity/updateState",
            contextType: "application/json",
            data: JSON.stringify(data),
            success: function (data) {
                var url = "http://localhost:8080/commodity/findAll"
                window.open(url, '_self');
                console.log(data)
            }
        });
    });
</script>

</html>
