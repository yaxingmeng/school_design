<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
	window.onload = function() {
		selectTag('tagContent1', 'selectTag1');
	}
</script>
<link rel="stylesheet" type="text/css" href="css/login.css" />
</head>
<body>
<div id="back">
	<div id="con">
		<ul id="tags">
			<li class="selectTag0"><a href="javascript:void(0)"
				onclick="selectTag('tagContent0','selectTag0')">注册</a></li>
			<li class="selectTag1"><a href="javascript:void(0)"
				onclick="selectTag('tagContent1','selectTag1')">普通用户登陆</a></li>
			<li class="selectTag2"><a href="javascript:void(0)"
				onclick="selectTag('tagContent2','selectTag2')">管理员登陆</a></li>
		</ul>

		<div id="tagContent">
			<form>
				<div id="tagContent0" class="tagContent">
					注册：<br /> <br />用户名:<input type="text" name="name"><br />
					<br /> 密&emsp; 码:<input type="text" name="password"><br />
					<br /> 手机号码：<input type="text" name="telephone"><br /> <br /><input
						type="submit" value="提交">
				</div>
			</form>
			<form>
				<div id="tagContent1" class="tagContent">
					登陆：<br /><br> 用户名:<input type="text" name="name"><br />
					<br /> 密&emsp; 码:<input type="text" name="password"><br /><br /> <input
						type="submit" value="提交">
				</div>
			</form>
			<form action="check.do">
				<div id="tagContent2" class="tagContent">
					管理员：<br /><br> 用户名/账号:<input type="text" name="name"><br />
					<br /> &emsp;密&emsp; 码:&emsp;<input type="text" name="password"><br /><br /> <input
						type="submit" value="提交">
				</div>
			</form>
		</div>
	</div>
	</div>
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