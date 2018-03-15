<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<body>
 <br><br>
<table border="1" spacing="1" width="800px">
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
 <td><a href="order_findone_user.do?id=${order.id}&userName=${userName}">查看</a>
  <c:if test="${order.payState==0}">
   <input type="button" onclick="window.location='order_user_change.do?pagenumber=${page[0]}&orderId=${order.id}&state=0&userName=${userName}'" value="支付">
  </c:if>
  <c:if test="${order.tranState==1}">
   <input type="button" onclick="window.location='order_user_change.do?pagenumber=${page[0]}&orderId=${order.id}&state=1&userName=${userName}'" value="收货">
  </c:if>
 </td>
 </tr>
 </c:forEach>
 </table><br/>
	<a href = "order_list_user.do?pagesize=10&pagenumber=1&userName=${userName}" >首页</a>
<c:if test="${page[0]>1}">
<a href = "order_list_user.do?pagesize=10&pagenumber=${page[0]-1 }&userName=${userName}" >上一页</a>
</c:if>
<c:if test="${page[0]<page[1]}">
<a href = "order_list_user.do?pagesize=10&pagenumber=${page[0]+1 }&userName=${userName}" >下一页</a>
</c:if>
<a href = "order_list_user.do?pagesize=10&pagenumber=${page[1] }&userName=${userName}" >尾页</a>
第${page[0]}页/共${page[1]}页&emsp;&emsp;&emsp;&emsp;&emsp;
<script type="text/javascript" language="javascript">
function pay() {
	var msg = "确认支付吗？";
	if (confirm(msg)==true){
	return true;
	}else{
	return false;
	}
	}
function rec() {
    var msg = "确认收货吗？";
    if (confirm(msg)==true){
        return true;
    }else{
        return false;
    }
}
</script> 
  
</body>
</html>