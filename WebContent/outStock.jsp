<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<!-- <script type="text/javascript">
function confirm(){
	alert(111);
	int a=(int)document.getElementById("amount").value;
	var b=(int)document.getElementById("limit").value;
	var a1=parseFloat(a);
	var b1=parseFloat(b);
	alert(a+":"+b);
	alert(a1+"!"+b1);
	if(a>b){
		alert("超出库存,请重新填写");
		return false;
	}
	return true;
}
</script> -->
</head>
<body>
<form action="outStock.do">
销售商：<select name="saleId">
<c:forEach var="sale" items="${sales }">
<option value="${sale.id }">${sale.name }</option>
</c:forEach>
</select>
<br>
商品编号：
<input type="text" value="${goods[0]}" name="goodsId" readonly="readonly"><br>
商品名称：
<input type="text" value="${goods[1]}" name="goodsName" readonly="readonly"><br>

数量：
<input type="text" name="amount" id="amount" onchange="confirm()"><br>
<input type="hidden" value="${goods[2] }" id="limit">
<input type="submit" value="提交">
</form>
</body>
</html>