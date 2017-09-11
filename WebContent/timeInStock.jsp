<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<table>
<tr>
<th>商品</th>
<th>供应商/销售商</th>
<th>入/出库数量</th>
<th>入/出库时间</th>
</tr>
<c:forEach var="stock" items="${stocks}">
<c:if test="${stock.state==0 }">
<tr>
<td><c:out value="${stock.goods.name}"></c:out></td>
<td>供应商：<c:out value="${stock.name }"></c:out></td>
<td><c:out value="${stock.amount }"></c:out></td>
<td><c:out value="${stock.time }"></c:out></td>
</tr>
</c:if>
<c:if test="${stock.state==1 }">
<tr>
<td><c:out value="${stock.goods.name}"></c:out></td>
<td>销售商:<c:out value="${stock.name }"></c:out></td>
<td><c:out value="${stock.amount }"></c:out></td>
<td><c:out value="${stock.time }"></c:out></td>
</tr>
</c:if>
</c:forEach>

</table>
<%-- <table>
<tr>
<th>商品</th>
<th>供应商</th>
<th>出库数量</th>
<th>出库时间</th>
</tr>
<c:forEach var="outstock" items="${outstocks}">
<tr>
<td><c:out value="${outstock.goods.name }"></c:out></td>
<td><c:out value="${outstock.sale.name }"></c:out></td>
<td><c:out value="${outstock.amount }"></c:out></td>
<td><c:out value="${outstock.outtime }"></c:out></td>
</tr>
</c:forEach>
</table> --%>
</body>
</html>