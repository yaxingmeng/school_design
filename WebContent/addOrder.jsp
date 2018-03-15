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
<form action="order_add_good.do" onsubmit="sure()">
 商品名称:<input type="text" value="${good.name }" readonly="readonly" ><br><br>
商品编号:<input type="text" value="${good.goodNo }" readonly="readonly" ><br><br>
类型:<input type="text" value="${good.typeName }" readonly="readonly"><br><br>
价格:<input type="text" value="${good.price }" readonly="readonly">/${good.unit}<br><br>
库存<input type="text" value="${good.amount }" readonly="readonly"><br><br>
-----------------------------------------------------------<<br>
 <br><br>
 购买数量：<input type="text" name="mount"><br><br>
 收货地址：<br>
 <input type="hidden" name="goodId" value="${good.id}">
<input type="hidden" name="payState" id="payState">
 <input type="hidden" name="userName" value="${userName}">
  <table>
   <tr>
    <th></th>
    <th>联系人</th>
    <th>收货地址</th>
    <th>联系电话</th>
   </tr>
   <c:forEach var="location" items="${user.locations}">
    <tr>
    <td><input type="checkbox" name="locationId" value="${location.id}"></td>
    <td><c:out value="${location.connector}"></c:out></td>
    <td><c:out value="${location.location}"></c:out></td>
    <td><c:out value="${location.phone}"></c:out></td>
    </tr>
   </c:forEach>
   </tr>
  </table>
 <input type="submit" value="提交">
</form>
<script type="text/javascript">

 function sure() {
     var a="确认付款吗？";
     var tag=document.getElementById("payState");
     if (confirm(a)){
         tag.value=1;
     }else {
         tag.value=0;
     }
 }

function loading(amount,id)
{
var a=window.prompt("数量",1);
a=check(amount,a);
document.getElementById("amount"+id).value=a ; 
if(confirm(a)==true){
	return true;
}else{
	return false;
}

}
 
 function check(amount,mount){
	  if(mount>amount){
		 alert("库存为"+amount+",超出库存，请重新填写数量");
		  mount=window.prompt("数量",1);
		 check(amount,mount);
	 }
	return mount;
 }
 
</script>
</body>
</html>