<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
	 <table border="1" spacing="2">
 <tr>
 <th>类型</th>
<th>创建人</th>
<th>创建时间</th>
<th>修改人</th>
<th>修改时间</th>
<th>操作</th>
</tr>
 <c:forEach var="type" items="${type }">
 <tr>
 <td><c:out value="${type.name }"></c:out></td>
 <td><c:out value="${type.createdBy }"></c:out></td>
 <td><c:out value="${type.createTime }"></c:out></td>
 <td><c:out value="${type.updatedBy }"></c:out></td>
 <td><c:out value="${type.updateTime }"></c:out></td>
 <td><a href="goodsetDetail.do?id=${type.id }&adminName=${adminName}">修改</a>/
 <c:if test="${type.state==0 }">
 <a href="change_goodtype_state.do?id=${type.id }&adminName=${adminName}&state=1" >禁用</a>
 </c:if>
 <c:if test="${type.state==1 }">
 <a href="change_goodtype_state.do?id=${type.id }&adminName=${adminName}&state=0" >启用</a>
 </c:if>
 </td>
 </tr>
 </c:forEach>
 </table><br/>
	<a href = "goodset_list.do?pagesize=10&pagenumber=1&adminName=${adminName }" >首页</a>
<c:if test="${page[0]>1}">
<a href = "goodset_list.do?pagesize=10&pagenumber=${page[0]-1 }&adminName=${adminName }" >上一页</a>
</c:if>
<c:if test="${page[0]<page[1]}">
<a href = "goodset_list.do?pagesize=10&pagenumber=${page[0]+1 }&adminName=${adminName }" >下一页</a>
</c:if>
<a href = "goodset_list.do?pagesize=10&pagenumber=${page[1] }&adminName=${adminName }" >尾页</a>
第${page[0]}页/共${page[1]}页&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
<a href="backMain.do?adminName=${adminName }">返回主菜单</a>
<div id="tagContent">
<form action="update_goodtype.do">添加类型：<br><br>
类型名称：<input type="text" name="name">
<input type="hidden" name="adminName" value="${adminName }">
<input type="hidden" name="state" value="0">
<input type="submit" value="添加">
</form>
</div>

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