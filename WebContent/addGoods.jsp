<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="js/jquery.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

</head>
<body>
	<form action="addGoods.do">
		name:<input type="text" name="name" id="name">
		 <input type="button" value="提交" id="submit" >
	</form>
	<script type="text/javascript">
	$("#submit").click(function(){
	 var a=$('#name').val();
	 console.log(a);
		$.ajax({
			dataType:"json",
			url:'/day0905/addGoods.do?name='+a,
			success:function(date){
				console.log("aaa");
			},
			error:function(data,errorThrown){
				alert(data.responseText);
				console.log(arguments);
			}
		}); 
		
	});
	
</script>
</body>
</html>