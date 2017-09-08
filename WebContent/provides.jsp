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
<form action="getAll.do">
<table>
<tr>
<th>供应商id</th>
<th>供应商姓名</th>
<th>操作</th>
<th>入库记录</th>
</tr>
<c:forEach var="provide" items="${provides }">
<c:if test="${provide.state==1}">
<tr>
<td><c:out value="${provide.id }"></c:out></td>
<td><c:out value="${provide.name }"></c:out></td>
<td>
<a href="getUpdateProvide.do?pid=${provide.id }&pname=${provide.name}">修改</a>/
<a href="deleteProvide.do?pid=${provide.id }&state=0">删除</a>
</td>
<td>
<a href="queryProvideStock.do?pid=${provide.id }">入库记录</a>
</td>
</tr>
</c:if>
<c:if test="${provide.state==0}">
<tr bgcolor="gray">
<td><c:out value="${provide.id }"></c:out></td>
<td><c:out value="${provide.name }"></c:out></td>
<td>
<a href="getUpdateProvide.do?pid=${provide.id }&pname=${provide.name}">修改</a>/
<a href="deleteProvide.do?pid=${provide.id }&state=1">变</a>
</td>
<td>
<a href="queryProvideStock.do?pid=${provide.id }">入库记录</a>
</td>
</tr>
</c:if>
</c:forEach>
</table>
<input type="submit" value="查看商品信息">
<a href="addProvide.jsp">添加供应商</a>
</form>
</body>
</html>