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
<div id="tags">管理员详情</div><br>
<form action="addAdmin.do">
<c:if test="${type==1 }">
<input type="hidden" name="operator" value="${adminName }">
账号:<input type="text" name="no" value="${admin.adminNo }"><br /><br />
姓名:<input type="text" name="name" value="${admin.name }" ><br /> <br />
 密码:<input type="text" name="password" value="${admin.password }"><br /> <br /> 
 权限 ：<select id="right" name="right" >
			<option value="0"  >订单管理</option>
			<option value="1"  >商品管理</option>
			<option value="2" >全部</option>
			</select><br/><br /> 
<input type="submit" value="提交"><br><br>
</c:if>
</form>
<c:if test="${type==0 }">
账号:<input type="text" name="no" value="${admin.adminNo }" readonly="readonly"><br /><br />
姓名:<input type="text" name="name" value="${admin.name }" readonly="readonly"><br /> <br />
 密码:<input type="text" name="password" value="${admin.password }" readonly="readonly"><br /> <br /> 
权限：<input type="text" value="${admin.rightName }"readonly="readonly"><br><br>
</c:if>
<a href="adminList.do?pagesize=10&pagenumber=1&adminName=${adminName }">返回</a>
</c:if>
</div>
</div>
</body>
</html>