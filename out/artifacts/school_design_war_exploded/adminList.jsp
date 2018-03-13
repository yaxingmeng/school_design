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
<div id="tags">管理员列表</div><br>
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
 <td><a href="adminDetail.do?adminName=${admin.adminNo }&type=0&oprator=${adminName}"><c:out value="${admin.adminNo }"></c:out></a></td>
 <td><c:out value="${admin.name }"></c:out></td>
 <td><c:out value="${admin.adminType }"></c:out></td>
 <td><c:out value="${admin.rightName }"></c:out></td>
 <td><a href="adminDetail.do?adminName=${admin.adminNo }&type=1&oprator=${adminName}">修改</a>/
 <a href="deleteAdmin.do?operator=${adminName}&adminName=${admin.adminNo }" onclick="return del();">删除</a></td>
 </tr>
 </c:forEach>
 </table><br/>
<a href = "adminList.do?pagesize=10&pagenumber=1&adminName=${adminName}" >首页</a>
<c:if test="${page[0]>1}">
<a href = "adminList.do?pagesize=10&pagenumber=${page[0]-1 }&adminName=${adminName }" >上一页</a>
</c:if>
<c:if test="${page[0]<page[1]}">
<a href = "adminList.do?pagesize=10&pagenumber=${page[0]+1 }&adminName=${adminName }" >下一页</a>
</c:if>
<a href = "adminList.do?pagesize=10&pagenumber=${page[1] }&adminName=${adminName }" >尾页</a>
第${page[0]}页/共${page[1]}页&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
<a href="backMain.do?adminName=${adminName }">返回主菜单</a>
</c:if>
</div>
</div>
<script type="text/javascript" language="javascript"> 
function del() {
	var msg = "删除后不可修复？\n\n请确认！";
	if (confirm(msg)==true){
	return true;
	}else{
	return false;
	}
	}
</script> 
  
</body>
</html>