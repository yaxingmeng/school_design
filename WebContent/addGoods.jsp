<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/manage.css" />
</head>
<body> 
<div id="background">
	<div id="list"> 
	<c:if test="${adminName==null }"><a href="index.jsp">请先登录</a></c:if>
<c:if test="${adminName!=null }">${adminName},欢迎你
<c:if test="${ type==0}">
<form action="add_good.do" id="add">
<br>
<input type="hidden" name="adminName" value="${adminName }">
名称：<input type="text" name="name" >&emsp;&emsp;&emsp;<span style="color:red">${exception }</span><br><br>
商品编号：<input type="text" name="goodNo"><br><br>
价格：<input type="text" name="price"><br><br>
单位：<input type="text" name="unit"><br><br>
库存：<input type="text" name="amount"><br><br>
类型:<select id="type" name="type" >
<c:forEach var="goodType" items="${goodType }">
<option value="${goodType.id }">${goodType.name}</option>
</c:forEach>
<option>
</select><br><br>
<input type="submit" value="提交">
</form>
</c:if>
<c:if test="${type==null }">
<form action="add_good.do" id="add">
<br>
<input type="hidden" name="adminName" value="${adminName }">
<input type="hidden" name="id" value="${good.id }">
名称：<input type="text" name="name" value="${good.name }" readonly="readonly" >&emsp;&emsp;&emsp;<span style="color:red">${exception }</span><br><br>
商品编号：<input type="text" name="goodNo" value="${good.goodNo }" readonly="readonly"><br><br>
价格：<input type="text" name="price" value="${good.price }"><br><br>
单位：<input type="text" name="price" value="${good.unit }"><br><br>
库存：<input type="text" name="amount" value="${good.amount }"><br><br>
类型:<select id="type" name="type" >
<c:forEach var="goodType" items="${goodType }">
<c:if test="${good.type==goodType.id }">
<option value="${goodType.id }" selected="selected">${goodType.name}</option>
</c:if>
<option value="${goodType.id }">${goodType.name}</option>
</c:forEach>
<option>
</select><br><br>
<input type="submit" value="提交">
</form>
</c:if>
	</c:if>
	</div>
	</div>


</body>
</html>