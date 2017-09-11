<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="js/jquery.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="outStock.do">
		销售商：<select id="saleId">
			<c:forEach var="sale" items="${sales }">
				<option  value="${sale.id }">${sale.name }</option>
			</c:forEach> 
		</select> <br> 商品编号：
		 <input type="text" value="${goods[0]}" name="goodsId" id="goodsId" readonly="readonly"><br> 
		 商品名称： <input type="text" value="${goods[1]}" name="goodsName" id="goodsName" readonly="readonly"><br>
		  数量： <input type="text" name="amount" id="amount"><br> 
		  <input type="hidden" value="${goods[2] }" id="limit"> 
		  <input type="button" id="submit" value="提交">
	</form>
	<script type="text/javascript">
		$("#submit").click(function() {
			var saleId=$('#saleId option:selected').val();
			var amount =$('#amount').val();
			var goods = $("#goodsId").val();
			var goodsId=parseInt(goods);
			var goodsName = $("#goodsName").val();
			console.log(amount);
			 $.ajax({
				dataType:"json",
				url:'/day0905/outStock.do?saleId='+saleId+'&goodsId='+goodsId+'&goodsName='+goodsName+'&amount='+amount,
				success:function(date){
					console.log("aaa");
					window.location.href="getAll.do";
				},
				error:function(data,errorThrown){
					if(data.status==200){
						window.location.href="getAll.do";
					}
					alert(data.responseText);
					console.log(arguments);
				}
			});
		});
	</script>
</body>
</html>