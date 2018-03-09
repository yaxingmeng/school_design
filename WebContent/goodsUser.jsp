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
<table border="0" spacing="1">
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
 <td><!-- <a href="javascript:void(0)" onclick="test()">加入购物车</a> -->
 <%-- <input type="button" value="添加购物车"onclick="show(${good.id})">
  <div id="content${good.id }">asdf
 </div> --%>
<form action="add_goodcar.do" name="myForm" onsubmit="return loading(${good.amount },${good.id})">
<input type="hidden" name="goodtype" value="${goodtype}">
<input type="hidden" name="name" value="${name}">
<input type="hidden" name="pagenumber" value="${page[0] }">
 <input type="submit" value="加入购物车" >
 <input type="hidden" name="amount" id="amount${good.id }">
  <input type="hidden" name="userName" value="${userName }">
  <input type="hidden" name="goodId" value="${good.id }">
 </form>
 </td>
 </tr>
 </c:forEach>
 </table>
 <a href = "good_list_user.do?pagesize=10&pagenumber=1&userName=${userName}&type=0&name=${name}&goodtype=${goodtype}" >首页</a>
<c:if test="${page[0]>1}">
<a href = "good_list_user.do?pagesize=10&pagenumber=${page[0]-1 }&userName=${userName }&type=0&name=${name}&goodtype=${goodtype}" >上一页</a>
</c:if>
<c:if test="${page[0]<page[1]}">
<a href = "good_list_user.do?pagesize=10&pagenumber=${page[0]+1 }&userName=${userName }&type=0&name=${name}&goodtype=${goodtype}" >下一页</a>
</c:if>
<a href = "good_list_user.do?pagesize=10&pagenumber=${page[1] }&userName=${userName }&type=0&name=${name}&goodtype=${goodtype}" >尾页</a>
第${page[0]}页/共${page[1]}页&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;

<script type="text/javascript">
/* 
function show(id){
	var tag = document.getElementById(content+id);
	tag.style.display="block";
	alert(222);
}
 */
function loading(amount,id)
{
var a=window.prompt("数量",1);
a=check(amount,a);
document.getElementById("amount"+id).value=a ; 
if(confirm(a)==true){
	return turn;
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