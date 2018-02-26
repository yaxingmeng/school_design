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
 <table border="1" spacing="2">
 <tr>
 <th>用户名</th>
<th>姓名</th>
<th>类型</th>
<th>权限</th>
<th>操作</th>
</tr>
 <c:forEach var="admin" items="${admin }">
 <tr>
 <td><a href=""><c:out value="${admin.adminNo }"></c:out></a></td>
 <td><c:out value="${admin.name }"></c:out></td>
 <td><c:out value="${admin.adminType }"></c:out></td>
 <td><c:out value="${admin.rightName }"></c:out></td>
 <td>修改/删除</td>
 </tr>
 </c:forEach>
 </table>
<a href = "adminList.do?pagesize=10&pagenumber=1" >首页</a>
<c:if test="${page[0]>1}">
<a href = "adminList.do?pagesize=10&pagenumber=${page[0]-1 }" >上一页</a>
</c:if>
<c:if test="${page[0]<page[1]}">
<a href = "adminList.do?pagesize=10&pagenumber=${page[0]+1 }" >下一页</a>
</c:if>
<a href = "adminList.do?pagesize=10&pagenumber=1" >尾页</a>
第${page[0]}页/共${page[1]}页
</body>
</html>