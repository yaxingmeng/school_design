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
<form>
<table>
<tr>
<th>商品编号</th>
<th>商品名称</th>
<th>库存</th>
</tr>
<tr>
<td>${stock.goods.id }</td>
<td>${stock.goods.name }</td>
<td>${stock.amount}</td>
</tr>
</table>
<a href="getProvide.do?goodsId=${stock.goods.id  }&goodsName=${stock.goods.name }">入库</a><br>
<c:if test="${stock.amount==0 }">
<h2>没有库存,暂停销售</h2>
</c:if>
<c:if test="${stock.amount>0 }">
<a href="getSale.do?goodsId=${stock.goods.id  }&goodsName=${stock.goods.name }&Amount=${stock.amount}">出库</a>
</c:if>
</form>
</body>
</html>