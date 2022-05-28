<%--
  Created by IntelliJ IDEA.
  User: 86135
  Date: 2022/5/28
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>新增商品</title>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8"/>
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
<div align="center">新增商品</div>
<br>
<table border="1" align="center">
    <tr>
        <td>商品名称</td>
        <td><input id="name"/></td>
    </tr>
    <tr>
        <td>商品类型</td>
        <td><input id="type"/></td>
    </tr>
    <tr>
        <td>商品描述</td>
        <td><input id="comment"/></td>
    </tr>
</table>

<div align="center">
    <button id="join">保存</button>
</div>

</body>
<script>

    $("#join").click(function () {
        var url = "http://localhost:8080/commodity/save";
        var name = $("input[id='name']").val();
        var type = $("input[id='type']").val();
        var comment = $("input[id='comment']").val();
        var state = "出售中";
        var data = {
            "id": "0",
            "name": $("#name").val(),
            "type": $("#type").val(),
            "comment": $("#comment").val(),
            "state": state
        }
        $.ajax({
            type: "post",
            url: "http://localhost:8080/commodity/save",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(data),
            success: function (data) {
                var url = "http://localhost:8080/commodity/findAll"
                window.open(url, '_self');
            }
        });
    });
</script>
</html>
