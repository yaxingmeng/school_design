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
	${adminName},欢迎你
	<div id="tags">商品配置详情</div><br>
<form action="update_goodtype.do">
<input type="hidden" name="id" value="${goodType.id }">
<input type="hidden" name="adminName" value="${adminName}">
名称：<input type="text" name="name" value="${goodType.name }"><br /><br />
创建人:<input type="text"  readonly="readonly" name="createdby" value="${goodType.createdBy}" ><br /> <br />
创建时间:<input type="text" readonly="readonly" name="createTime" value="${goodType.createTime }"><br /> <br />
修改人:<input type="text" readonly="readonly" name="updatedBy" value="${goodType.updatedBy }" ><br /> <br />
修改时间:<input type="text" readonly="readonly" name="updateTime" value="${goodType.updateTime }"><br /> <br />
<input type="submit" value="提交">
</form>
	</div>
	</div>
</body>
</html>