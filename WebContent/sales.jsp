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
<th >销售商id</th>
<th>销售商姓名</th>
<th>操作</th>
<th>入库记录</th>
</tr>
<c:forEach var="sale" items="${sales }">
<c:if test="${sale.state==0}">
<tr bgcolor="gray">
<td bgcolor="gray"><c:out value="${sale.id }"></c:out></td>
<td><c:out value="${sale.name }"></c:out></td>
<td><a href="getUpdateSale.do?sid=${sale.id }&sname=${sale.name}">修改</a>/
<a href="deleteSale.do?sid=${sale.id }&state=1">变</a>
</td>
<td>
<a href="querySaleStock.do?sid=${sale.id }">入库记录</a>
</td>
</tr>
</c:if>
<c:if test="${sale.state ==1}">
<tr>
<td ><c:out value="${sale.id }"></c:out></td>
<td><c:out value="${sale.name }"></c:out></td>
<td><a href="getUpdateSale.do?sid=${sale.id }&sname=${sale.name}">修改</a>/
<a href="deleteSale.do?sid=${sale.id }&state=0">删除</a>
</td>
<td>
<a href="querySaleStock.do?sid=${sale.id }">入库记录</a>
</td>
</tr>
</c:if>
</c:forEach>
</table>
<input type="submit" value="查看商品信息">
<a href="addSale.jsp">添加供应商</a>
</form>
</body>
</html>