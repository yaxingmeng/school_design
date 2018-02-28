<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/main.css" />
</head>
<body>
<div id="back">
<div id="title">
<ul id="tags">
            <li class="selectTag3"><a href="javascript:void(0)"
				onclick="selectTag('tagContent3','selectTag3')">商品</a></li>
			<li class="selectTag0"><a href="javascript:void(0)"
				onclick="selectTag('tagContent0','selectTag0')">购物车</a></li>
			<li class="selectTag1"><a href="javascript:void(0)"
				onclick="selectTag('tagContent1','selectTag1')">我的订单</a></li>
			<li class="selectTag2"><a href="javascript:void(0)"
				onclick="selectTag('tagContent2','selectTag2')">我的信息</a></li>
		</ul>
</div>
<div id="tagContent">
             <form action="">
				<div id="tagContent3" class="tagContent">
					商品
				</div>
			</form>
			<form action="">
				<div id="tagContent0" class="tagContent">
					购物车
				</div>
			</form>
			<form action="">
				<div id="tagContent1" class="tagContent">
					我的订单
				</div>
			</form>
			<form action="">
				<div id="tagContent2" class="tagContent">
				<div id="word">
				<c:if test="${type==1 }">
					我的信息:<br><a href="user_detail.do?nickname=${user.nickname }">编辑</a><br>
					用户名:<input type="text" name="nickname" value="${user.nickname }" readonly="readonly">&emsp;<br />
					<br /> 密&emsp; 码:<input type="text" name="password" value="${user.password }" readonly="readonly"><br />
					<br /> 手机号码：<input type="text" name="telephone" value="${user.phone }" readonly="readonly"><br /> <br />
					<c:choose>
					<c:when test="${fn:length(user.locations)==0 }">
					添加地址
					</c:when>
					<c:otherwise>
					<c:forEach var="location" items="${user.locations }">
					地址：<textarea rows="1" cols="20" name="location" readonly="readonly">${location.location}</textarea><br><br>
					联系电话：<input type="text" value="${location.phone }">
					</c:forEach>
					</c:otherwise>
					</c:choose>
					</c:if>
					<c:if test="${type==0 }">
					我的信息:<br><br>
					用户名:<input type="text" name="nickname" value="${user.nickname }" >&emsp;<br />
					<br /> 密&emsp; 码:<input type="text" name="password" value="${user.password }" ><br />
					<br /> 手机号码：<input type="text" name="telephone" value="${user.phone }" ><br /> <br />
					<c:choose>
					<c:when test="${fn:length(user.locations)==0 }">
					添加地址
					</c:when>
					<c:otherwise>
					<c:forEach var="location" items="${user.locations }">
					地址：<textarea rows="1" cols="20" name="location" >${location.location}</textarea><br><br>
					联系电话：<input type="text" value="${location.phone }">
					</c:forEach>
					</c:otherwise>
					</c:choose>
					</c:if>
					</div>
				</div>
			</form>
		</div>
	</div>
	<c:choose>
	<c:when test="${type<=1 }">
	window.onload = function() {
		selectTag('tagContent2', 'selectTag2');
	}
	</c:when>
	<c:otherwise>
	<script type="text/javascript">
	window.onload = function() {
		selectTag('tagContent3', 'selectTag3');
	}
     </script>
	</c:otherwise>
	</c:choose>
	
	<script type="text/javascript">
		function selectTag(showContent, selfObj) {
			 var oUl = document.getElementById("tags");        
				var aLi = oUl.getElementsByTagName("li");
				var length=aLi.length;
				var i = 0;
				
				for(i =0; i<length; i++){
				       if(aLi[i].className == selfObj){
				              aLi[i].style.background = "#c0c0c0";
				       }else{
				    	   aLi[i].style.background = "#ffffff";
				       }
				} 
			var tag = document.getElementById(showContent);
			for (i = 0; j = document.getElementById("tagContent" + i); i++) {
				if (j.id === tag.id) {
					j.style.display = "block";
				} else {
					j.style.display = "none";
				}
			}
		}
	</script>
</body>
</html>