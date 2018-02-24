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
#con { font-size:12px; width:600px; margin:0 auto}  
#tags { height:23px; width:400px; margin:0; padding:0; margin-left:10px}  
#tags li { float:left; margin-right:1px; background:url(images/tagleft.gif) no-repeat left bottom; height:23px; list-style-type:none}  
#tags li a { text-decoration:none; float:left; background:url(images/tagright.gif) no-repeat right bottom; height:23px; padding:0px 10px; line-height:23px; color:#999}  
#tags li.emptyTag { width:4px; background:none}  
#tags li.selectTag { background-position: left top; position:relative; height:25px; margin-bottom:-2px}  
#tags li.selectTag a { background-position: right top; color:#000; height:25px; line-height:25px;}  
#tagContent { padding:1px; background-color:#fff; border:1px solid #aecbd4;}  
.tagContent { background:url(images/bg.gif) repeat-x;  padding:10px; color:#474747; width:576px; display:none}  
#tagContent div.selectTag{ display:block}  
</style>
</head>
<body>
<h1>标签示例</h1>  
<ol>  
    <li>使用JS实现tab标签效果</li>  
    <li>标签宽度随文字的数量自适应</li>    
    <li>支持 IE、Firefox、Chrome、Safari、opera</li>  
      
      
</ol>  
<div id="con">  
  <ul id="tags">  
    <li><a href="javascript:void(0)" onclick="selectTag('tagContent0',this)">标签一</a></li>  
    <li class="selectTag"><a href="javascript:void(0)" onclick="selectTag('tagContent1',this)">标签二</a></li>  
    <li><a href="javascript:void(0)" onclick="selectTag('tagContent2',this)">自适应宽度的标签</a></li>  
  </ul>  
  <div id="tagContent">  
    <div id="tagContent0" class="tagContent">第一个标签的内容<br />第一个标签的内容<br />第一个标签的内容</div>  
    <div id="tagContent1" class="tagContent selectTag">第二个标签的内容<p>标签背景图1：<img src="images/tagleft.gif" align="top"><br>标签背景图2：<img src="images/tagright.gif" align="top"><br>内容渐变背景图(1象素宽)：<img src="images/bg.gif" align="top"></p></div>  
    <div id="tagContent2" class="tagContent">第三个标签的内容<p>放大观看标签背景图：<img src="images/tagleft.gif" align="top" width="300" height="100"></p></div>  
  </div>    
</div>  
<script type="text/javascript">  
function selectTag(showContent,selfObj){  
    selfObj.blur();  
    // 操作标签  
    var tag = document.getElementById("tags").getElementsByTagName("li");  
    var tagtaglength = tag.length;  
    for(i=0; i<taglength; i++){  
        tag[i].className = "";  
    }  
    selfObj.parentNode.className = "selectTag";  
    // 操作内容  
    for(i=0; j=document.getElementById("tagContent"+i); i++){  
        j.style.display = "none";  
    }  
    document.getElementById(showContent).style.display = "block";  
      
      
}  
</script>  
</body>  
</body>
</html>