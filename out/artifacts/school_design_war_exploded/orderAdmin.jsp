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
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<body>
<div id="background">
	<div id="list">
	<c:if test="${adminName==null }"><a href="index.jsp">请先登录</a></c:if>
<c:if test="${adminName!=null }">${adminName},欢迎你
<form action="order_list.do">
 <input type="hidden" name="pagenumber" value="${page[0]}">
 <input type="hidden" name="adminName" value="${adminName}">
用户名或订单号： <input type="text" name="nameOrNo"><br>
 订单起止时间：<input type="text" name="start" id="start" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"/>--
 <input type="text" name="end" id="end" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"/>&emsp;&emsp;&emsp;
 <input type="submit" value="查询">
 <br><br>
<table border="1" spacing="2">
 <tr>
 <th>订单编号</th>
 <th>用户</th>
<th>订单金额</th>
<th>地址</th>
<th>成交时间</th>
<th>支付状态</th>
<th>物流状态</th>
</tr>
 <c:forEach var="order" items="${order }">
 <tr>
 <td><c:out value="${order.orderNo }"></c:out></td>
 <td><c:out value="${order.user.nickname }"></c:out></td>
 <td><c:out value="${order.totalPrice}"></c:out></td>
 <td><c:out value="${order.location}"></c:out></td>
  <td><c:out value="${order.createTime}"></c:out></td>
 <td><c:out value="${order.payStateName}"></c:out></td>
 <td><c:out value="${order.transStateName }"></c:out></td>
 <td><a href="order_findone.do?id=${order.id }&adminName=${adminName}&type=1">修改</a>/
  <a href="order_findone.do?id=${order.id}&adminName=${adminName}&type=0">查看</a>
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
第${page[0]}页/共${page[1]}页&emsp;&emsp;&emsp;&emsp;&emsp;
 <a href="backMain.do?adminName=${adminName }">返回主菜单</a>
</form>
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