<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body> 
<table border="0" spacing="1">
 <tr>
 <th>商品名称</th>
<th>类型</th>
<th>价格</th>
<th>单位</th>
<th>库存</th>
<th>操作</th>
</tr>
 <c:forEach var="good" items="${goods }">
 <tr>
 <td><c:out value="${good.name }"></c:out></td>
 <td><c:out value="${good.typeName}"></c:out></td>
 <td><c:out value="${good.price }"></c:out></td>
 <td>/<c:out value="${good.unit }"></c:out></td>
 <td><c:out value="${good.amount }"></c:out></td>
 <td><a href="good_detail.do?id=${good.id }&adminName=${adminName}">加入购物车</a>
 </td>
 </tr>
 </c:forEach>
 </table>
 <a href = "good_list_user.do?pagesize=10&pagenumber=1&adminName=${admin.name }&type=0&name=${name}&goodtype=${goodtype}" >首页</a>
<c:if test="${page[0]>1}">
<a href = "good_list_user.do?pagesize=10&pagenumber=${page[0]-1 }&adminName=${adminName }&type=0&type=0&name=${name}&goodtype=${goodtype}" >上一页</a>
</c:if>
<c:if test="${page[0]<page[1]}">
<a href = "good_list_user.do?pagesize=10&pagenumber=${page[0]+1 }&adminName=${adminName }&type=0&type=0&name=${name}&goodtype=${goodtype}" >下一页</a>
</c:if>
<a href = "good_list_user.do?pagesize=10&pagenumber=${page[1] }&adminName=${adminName }&type=0&type=0&name=${name}&goodtype=${goodtype}" >尾页</a>
第${page[0]}页/共${page[1]}页&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
</body>
</html>