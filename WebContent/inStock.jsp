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
<form action="addStock.do">
供应商：<select name="provideId">
<c:forEach var="provide" items="${provides }">
<option value="${provide.id }">${provide.name }</option>
</c:forEach>
</select>
<br>
商品编号：
<input type="text" value="${goods[0]}" name="goodsId" readonly="readonly"><br>
商品名称：
<input type="text" value="${goods[1]}" name="goodsName" readonly="readonly"><br>
数量：
<input type="text" name="amount"><br>
<input type="submit" value="提交">
</form>
</body>
</html>