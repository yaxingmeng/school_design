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
 <th>商品名称</th>
 <th>商品编号</th>
<th>类型</th>
<th>价格</th>
<th>单位</th>
<th>库存</th>
<th>操作</th>
</tr>
 <c:forEach var="good" items="${goods }">
 <tr>
 <td><c:out value="${good.name }"></c:out></td>
 <td><c:out value="${good.goodNo }"></c:out></td>
 <td><c:out value="${good.typeName}"></c:out></td>
 <td><c:out value="${good.price }"></c:out></td>
 <td>/<c:out value="${good.unit }"></c:out></td>
 <td><c:out value="${good.amount }"></c:out></td>
 <td><a href="good_detail.do?id=${good.id }&adminName=${adminName}">修改</a>/
 <c:if test="${good.state==0 }">
 <a href="good_change_state.do?id=${good.id }&state=1&adminName=${adminName}&pageNumber=${page[0]}" >下架</a>
 </c:if>
 <c:if test="${good.state==1 }">
 <a href="good_change_state.do?id=${good.id }&state=0&adminName=${adminName}&pageNumber=${page[0]}" >上架</a>
 </c:if>
 </td>
 </tr>
 </c:forEach>
 </table><br/>
	<a href = "good_list_user.do?pagesize=10&pagenumber=1&userName=${userName}" >首页</a>
<c:if test="${page[0]>1}">
<a href = "good_list_user.do?pagesize=10&pagenumber=${page[0]-1 }&userName=${userName}" >上一页</a>
</c:if>
<c:if test="${page[0]<page[1]}">
<a href = "good_list_user.do?pagesize=10&pagenumber=${page[0]+1 }&userName=${userName}" >下一页</a>
</c:if>
<a href = "good_list_user.do?pagesize=10&pagenumber=${page[1] }&userName=${userName}" >尾页</a>
第${page[0]}页/共${page[1]}页&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<a href="backMain.do?adminName=${adminName }">返回主菜单</a>
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