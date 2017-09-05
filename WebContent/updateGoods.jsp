<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<form action="updateGoods.do">
id:<input type="text" name="id" readonly="readonly" value="${goods.id }"><br>
name:<input type="text" name="name" value="${goods.name }">
<input type="submit" value="提交">
</form>
</body>
</html>