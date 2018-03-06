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
				onclick="window.location='good_list_user.do?pagesize=10&pagenumber=1&userName=${userName}'">商品</a></li>
			<li class="selectTag0"><a href="javascript:void(0)"
				onclick="selectTag('tagContent0','selectTag0')">购物车</a></li>
			<li class="selectTag1"><a href="javascript:void(0)"
				onclick="selectTag('tagContent1','selectTag1')">我的订单</a></li>
			<li class="selectTag2"><a href="javascript:void(0)"
				onclick="window.location='user_info.do?nickname=${userName}'">我的信息</a></li>
		</ul>
</div>
<div id="tagContent">
             <form action="">
				<div id="tagContent3" class="tagContent">
					商品:
					<br>&emsp;&emsp;&emsp;&emsp;
					<input type="hidden" name="pagesize" value="10">
					<input type="hidden" name="pagenumber" value="1"> 
					<input type="hidden" name="userName" value="${userName}"> 
					<input type="text" name="name" value="名称">&emsp;&emsp;
					<select id="type" name="goodtype" >
					 <option value="" selected="selected">全部</option>
                         <c:forEach var="goodType" items="${goodType }">
                          <option value="${goodType.id }">${goodType.name}</option>
                          </c:forEach>
                        </select>
                        <input type="submit" value="搜索">
					<div id="good">
					<jsp:include page="goodsUser.jsp" />
					</div>
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
			
				<div id="tagContent2" class="tagContent">
				<div id="word">
				<c:if test="${type==1 }"><!-- type=1,查看信息 -->
					我的信息:<br>
					<br>
					用户名:<input type="text" name="nickname" value="${user.nickname }" readonly="readonly">&emsp;<br />
					<br /> 密&emsp; 码:<input type="text" name="password" value="${user.password }" readonly="readonly"><br />
					<br /> 手机号码：<input type="text" name="telephone" value="${user.phone }" readonly="readonly"><br /> <br />
					<input type="button" value="编辑基本信息" onclick="window.location='user_detail.do?nickname=${user.nickname }'">
					<input type="button" value="新增收货信息" onclick="show('location')"><br><br>
						<c:choose>
					<c:when test="${fn:length(user.locations)==0 }">
					<form action="add_location.do">
					你还没有收货地址，添加一下吧！<br>
					<input type="hidden" name="nickname" value="${user.nickname }">
					地址：<textarea rows="1" cols="20" name="location" ></textarea>
					联系人：<input type="text" name="connector" >
					联系电话：<input type="text" name="phone">
					<input type="submit" value="提交">
					</form>
					</c:when>
					<c:otherwise>
					收货信息:&emsp;&emsp;&emsp;&emsp;<span style="color:red">${success}</span><br>
					<div id="location" class="tagContent">
					<form action="add_location.do">
					<input type="hidden" name="nickname" value="${user.nickname }">
					地址：<textarea rows="1" cols="20" name="location" ></textarea>
					联系人：<input type="text" name="connector" >
					联系电话：<input type="text" name="phone">
					<input type="submit" value="提交">
					</form>
					</div>
					<c:forEach var="location" items="${user.locations }">
					<form action="update_location.do">
					<input type="hidden" name="id" value="${location.id }">
					<input type="hidden" name="nickname" value="${user.nickname }">
					地址：<textarea rows="1" cols="20" name="location"  >${location.location}</textarea><
					联系人：<input type="text" name="connector" value="${location.connector }" ><
					联系电话：<input type="text" value="${location.phone }" name="phone">
					<input type="submit" value="提交修改">
					<a href="delete_location.do?id=${location.id }&nickname=${user.nickname}" onclick="del()">删除</a><br>
					</form>
					</c:forEach>
					</c:otherwise>
					</c:choose>
					</c:if>
					<c:if test="${type==0 }"><!-- type==0,修改信息页面 -->
					<form action="user_update.do">
					我的信息:<br>${regist_error }<br>
					<input type="hidden" name="id" value="${user.id }">
					用户名:<input type="text" name="nickname" value="${user.nickname }" >&emsp;<br /><br />
					姓&emsp;名:<input type="text" name="name" value="${user.name }" >(非必填)&emsp;<br /><br />
					 密&emsp; 码:<input type="text" name="password" value="${user.password }" ><br /><br /> 
					手机号码：<input type="text" name="telephone" value="${user.phone }" ><br /> <br />
					<input type="submit" value="提交">&emsp;&emsp;&emsp;&emsp;
					</form>
					</c:if>
					</div>
				</div>
			
		</div>
	</div>
   <c:choose>
	<c:when test="${type<=1 }">
	<script type="text/javascript">
	window.onload = function() {
		selectTag('tagContent2', 'selectTag2');
	}
	  </script>
	</c:when>
	<c:when test="${type==3 }">
	<script type="text/javascript">
	window.onload = function() {
		selectTag('tagContent3', 'selectTag3');
	}
	  </script>
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
	function del() {
		var msg = "删除后不可修复？\n\n请确认！";
		if (confirm(msg)==true){
		return true;
		}else{
		return false;
		}
		}
	function show(showContent){
		var tag = document.getElementById(showContent);
		tag.style.display="block";
	}
	
	
		function selectTag(showContent, selfObj) {
			var a=document.getElementById("location");
			if(a!=null){
				a.style.display = "none";
			}
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