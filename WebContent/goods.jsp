<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="js/jquery.js"></script>
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
<th>查询入库记录</th>
<th>查询出库记录</th>
<th>状态</th>
</tr>

<c:forEach var="goods" items="${goods}">
<tr c="goods">
<td><c:out value="${goods.id}"></c:out></td>
<td><c:out value="${goods.name}"></c:out></td>
<td><a href="updateQuery.do?id=${goods.id }">修改商品名字</a>/\
<a href="queryStock.do?id=${goods.id }">查看库存信息</a></td>
<td><a href="goodsInStock.do?id=${goods.id }">入库记录</a></td>
<td><a href="goodsOutStock.do?id=${goods.id }">出库记录</a></td>
<c:if test="${goods.state==0 }">
<td t="test" v="${ goods.state}" bgcolor="red">不可用<a href="changeState.do?id=${goods.id}&state=1">改变状态</a> </td>
</c:if>
<c:if test="${goods.state==1}">
<td t="test" v="${ goods.state}">可用<a href="changeState.do?id=${goods.id}&state=0" >改变状态</a></td>
</c:if>
</tr>
</c:forEach>
</table>
<a href="addGoods.jsp">增加商品</a>
</form>
<form action="timeInStock.do">
查看某段时间内的出/入库信息:<br>
起：<input type="text" name="start">
止：<input type="text" name="end">
<input type="submit" value="查询">
</form>
<script type="text/javascript">
$("tr[c=goods]").click(function(){
	var state=$(this).find('td[t=test]').attr('v');
	if(state==0){
		alert("此商品不可用");
		return false;
	}else if(state==1){
		return true;
	}
});
</script>
</body>
</html>