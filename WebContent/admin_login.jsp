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
<body>
<div id="background">
	<div id="con">
		<c:if test="${admin.name==null }"><a href="index.jsp">请先登录</a></c:if>
<c:if test="${admin.name!=null }">${admin.name},欢迎你
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<a href="index.jsp">首页</a>
		<ul id="tags">
			<c:if test="${admin.type==1 }">
			<script type="text/javascript">
					window.onload = function() {
						selectTag('tagContent3', 'selectTag3');
					}
				</script>
				<li class="selectTag0"><a href="javascript:void(0)"
					onclick="selectTag('tagContent0','selectTag0')">人员管理</a></li>
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
			<li class="selectTag3"><a href="javascript:void(0)"
					onclick="selectTag('tagContent3','selectTag3')">修改密码</a></li>
		</ul>

		<div id="tagContent">
	<div id="tagContent0" class="tagContent">
	<br/><a href="adminList.do?pagesize=10&pagenumber=1&adminName=${admin.name }">管理员列表</a><br/><br/><br/>
	<a href="javascript:void(0)" onclick="selectTag('tagContent4','selectTag4')">添加管理员</a><br/><br/><br/>
	</div>
			<form action="addAdmin.do">
				<div id="tagContent4" class="tagContent">
					人员管理：<br /> <br/><input type="hidden" name="operator"
						value="${admin.name }"> 账号:<input type="text" name="no"><br />
					<br /> 姓名:<input type="text" name="name"><br /> <br /> 密
					码:<input type="text" name="password"><br /> <br /> 权限 ：<select
						id="right" name="right">
						<option value="0">订单管理</option>
						<option value="1">商品管理</option>
						<option value="2">全部</option>
					</select><br/><br /> <input type="submit" value="提交">
				</div>
			</form>
			<form>
				<div id="tagContent1" class="tagContent">
					订单管理：<br/><br> 用户名:<input type="text" name="name"><br /> <br />
					密&emsp; 码:<input type="text" name="password"><br /><br/> <input
						type="submit" value="提交">
				</div>
			</form>
			<form action="">
				<div id="tagContent2" class="tagContent">
					商品管理：<br><br/> 
					<a href="goodset_list.do?pagesize=10&pagenumber=1&adminName=${admin.name }">商品配置</a><br><br>
					<a href="">商品信息</a><br><br>
				</div>
			</form>
			<form action="change_password.do">
				<div id="tagContent3" class="tagContent">
					修改密码：&emsp;&emsp;<span style="color:red">${change }</span><br><br/> 
					<input type="hidden" name="operator" value="${admin.name}">
					用户名:&emsp;<input type="text" name="name" readonly="readonly" value="${admin.name }"><br />
					<br />密&emsp; 码:&emsp;<input type="text" name="password" value="${admin.password }"><br /> <br/>
					<input type="submit" value="提交">
				</div>
			</form>
		</div>
		</c:if>
	</div>
	</div>
	<c:if test="${change!=null }">
	<script type="text/javascript">
	window.onload = function() {
		selectTag('tagContent3', 'selectTag3');
	}
	  </script>
	</c:if>
	<script type="text/javascript">
		function selectTag(showContent, selfObj) {
			 var oUl = document.getElementById("tags");        
			var aLi = oUl.getElementsByTagName("li");
			var length=aLi.length;
			var i = 0;
			if(selfObj==="selectTag4"){
					aLi[0].style.background = "#c0c0c0";
					document.getElementById("tagContent4").style.display = "block";
					for(i=0;i<length;i++){
						document.getElementById("tagContent"+i).style.display = "none";
					}
			}else{
				document.getElementById("tagContent4").style.display = "none";
			for(i =0; i<length; i++){
			       if(aLi[i].className == selfObj){
			              aLi[i].style.background = "#c0c0c0";
			       }else{
			    	   aLi[i].style.background = "#ffffff";
			       }
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