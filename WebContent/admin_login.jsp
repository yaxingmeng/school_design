<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" type="text/css" href="css/login.css" />
</head>
<body>
	<div id="con">
		<p>${admin.name}， 欢迎你</p>
		<ul id="tags">
			<c:if test="${admin.type==1 }">
			<script type="text/javascript">
					window.onload = function() {
						selectTag('tagContent3', 'selectTag3');
					}
				</script>
				<li class="selectTag0"><a href="javascript:void(0)"
					onclick="selectTag('tagContent3','selectTag3')">人员管理</a></li>
			</c:if>
			<c:if test="${admin.rights==2 }">
			<script type="text/javascript">
					window.onload = function() {
						selectTag('tagContent2', 'selectTag2');
					}
				</script>
				<li class="selectTag1"><a href="javascript:void(0)"
					onclick="selectTag('tagContent1','selectTag1')">订单管理</a></li>
				<li class="selectTag2"><a href="javascript:void(0)"
					onclick="selectTag('tagContent2','selectTag2')">商品管理</a></li>
			</c:if>
			<c:if test="${admin.rights==1 }">
				<li class="selectTag2">商品管理 
				<script type="text/javascript">
					window.onload = function() {
						selectTag('tagContent2', 'selectTag2');
					}
				</script>
				</li>
			</c:if>
			<c:if test="${admin.rights==0 }">
				<li class="selectTag1">订单管理 
				<script type="text/javascript">
					window.onload = function() {
						selectTag('tagContent1', 'selectTag1')
					}
				</script>
				</li>
			</c:if>
		</ul>

		<div id="tagContent">
	<div id="tagContent3" class="tagContent">
	<a href="adminList.do?pagesize=10&pagenumber=1">管理员列表</a><br/>
	<a href="javascript:void(0)" onclick="selectTag('tagContent0','selectTag0')">添加管理员</a>
	</div>
			<form action="addAdmin.do">
				<div id="tagContent0" class="tagContent">
					人员管理：<br /> <input type="hidden" name="operator"
						value="${admin.name }"> 账号:<input type="text" name="no"><br />
					<br /> 姓名:<input type="text" name="name"><br /> <br /> 密
					码:<input type="text" name="password"><br /> <br /> 权限 ：<select
						id="right" name="right">
						<option value="0">订单管理</option>
						<option value="1">商品管理</option>
						<option value="2">全部</option>
					</select><br /> <input type="submit" value="提交">
				</div>
			</form>
			<form>
				<div id="tagContent1" class="tagContent">
					订单管理：<br> 用户名:<input type="text" name="name"><br /> <br />
					密 码:<input type="text" name="password"><br /> <input
						type="submit" value="提交">
				</div>
			</form>
			<form action="check.do">
				<div id="tagContent2" class="tagContent">
					商品管理：<br> 用户名/账号:<input type="text" name="name"><br />
					<br /> 密 码:<input type="text" name="password"><br /> <input
						type="submit" value="提交">
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		function selectTag(showContent, selfObj) {
			/* var oUl = document.getElementById("tags");        
			var aLi = oUl.getElementsByTagName("li");
			var i = 0;
			for(i =0; aLi.length; i++){
			       if(aLi[i].className == selfObj){
			              aLi[i].style.background = "#FF9900";
			       }
			} 
			alert(333); */
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