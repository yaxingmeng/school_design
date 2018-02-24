<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">  
body { font-size:14px; font-family:"宋体"}  
ol li { margin:8px}  
#con { font-size:12px; width:600px; margin:250px auto}  
#tags { height:23px; width:400px; margin:0; padding:0; margin-left:10px}  
#tags li { float:left; margin-right:1px; background:url(images/tagleft.gif) no-repeat left bottom; height:23px; list-style-type:none}  
#tags li a { text-decoration:none; float:left; background:url(images/tagright.gif) no-repeat right bottom; height:23px; padding:0px 10px; line-height:23px; color:#999}  
#tags li.emptyTag { width:4px; background:none}  
#tags li.selectTag0 { background-position: left top; position:relative; height:25px; margin-bottom:-2px}  
#tags li.selectTag1 { background-position: left top; position:relative; height:25px; margin-bottom:-2px}  
#tags li.selectTag2 { background-position: left top; position:relative; height:25px; margin-bottom:-2px}  
#tags li.selectTag a { background-position: right top; color:#000; height:25px; line-height:25px;}  
#tagContent { padding:1px; background-color:#fff; border:1px solid #aecbd4;}  
.tagContent { background:url(images/bg.gif) repeat-x;  padding:10px; color:#474747; width:576px; display:none}  
#tagContent div.selectTag{ display:block}  
</style>
</head>
<body>
<div id="con">  
  <ul id="tags">  
    <li class="selectTag0"><a href="javascript:void(0)" onclick="selectTag('tagContent0','selectTag0')">注册</a></li>  
    <li class="selectTag1"><a href="javascript:void(0)" onclick="selectTag('tagContent1','selectTag1')">普通用户登陆</a></li>  
    <li class="selectTag2"><a href="javascript:void(0)" onclick="selectTag('tagContent2','selectTag2')">管理员登陆</a></li>  
  </ul> 
  
  <div id="tagContent"> 
  <form>  
    <div id="tagContent0" class="tagContent">
    注册：<br/>
               用户名:<input type="text" name="name"><br/><br/>
               密    码:<input type="text" name="password"><br/><br/>
              手机号码：<input type="text" name="telephone"><br/>
            <input type="submit" value="提交">
    </div>  
    </form>
    <form> 
    <div id="tagContent1" class="tagContent">
    登陆：<br>
                用户名:<input type="text" name="name"><br/><br/>
               密    码:<input type="text" name="password"><br/>
               <input type="submit" value="提交">
    </div>  
    </form>
    <form action="check.do"> 
    <div id="tagContent2" class="tagContent">
    管理员：<br>
               用户名/账号:<input type="text" name="name"><br/><br/>
               密                码:<input type="text" name="password"><br/>
            <input type="submit" value="提交">
</div>  
</form>
</div>
  </div>    
<script type="text/javascript">  
function selectTag(showContent,selfObj){
	 /* var oUl = document.getElementById("tags");        
     var aLi = oUl.getElementsByTagName("li");
     var i = 0;
     for(i =0; aLi.length; i++){
            if(aLi[i].className == selfObj){
                   aLi[i].style.background = "#FF9900";
            }
     } 
     alert(333); */
	var tag=document.getElementById(showContent);
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