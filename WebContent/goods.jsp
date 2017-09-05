<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<form>
<table>
<tr>
<th>商品id</th>
<th>商品名字</th>
<th>操作</th>
</tr>
<c:forEach var="goods" items="${goods}">
<tr>
<td><c:out value="${goods.id}"></c:out></td>
<td><c:out value="${goods.name}"></c:out></td>
<td><a href="updateQuery.do?id=${goods.id }">修改商品名字</a>/<a href="queryStack?id=${goods.id }">查看库存信息</a></td>
</tr>
</c:forEach>
<a href="addGoods.jsp">增加商品</a>
</table>
</form>
</body>
</html>