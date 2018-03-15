<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>
</head>
<body>
订单编号:<input type="text" value="${order.orderNo }" readonly="readonly" ><br><br>
用户:<input type="text" value="${order.user.nickname }" readonly="readonly"><br><br>
<table>
    <tr>
        <th>商品编号</th>
        <th>商品名称</th>
        <th>单价</th>
        <th>数量</th>
    </tr>
    <c:forEach var="item" items="${order.orderItem}">
        <td><c:out value="${item.goods.goodNo}"></c:out></td>
        <td><c:out value="${item.goods.name}"></c:out></td>
        <td><c:out value="${item.goods.price}"></c:out></td>
        <td><c:out value="${item.amount}"></c:out></td>
    </c:forEach>
</table>
订单金额:${order.totalPrice }<br><br>
地址:<input type="text" value="${order.location }" readonly="readonly"><br><br>
联系电话:<input type="text" value="${order.phone }" readonly="readonly"><br><br>
联系人:<input type="text" value="${order.contact }" readonly="readonly"><br><br>
成交时间:<input type="text" value="${order.createTime }" readonly="readonly"><br><br>
支付状态:<input type="text" value="${order.payStateName }" readonly="readonly"><br><br>
物流状态:<input type="text" value="${order.transStateName }" readonly="readonly"><br><br>
</body>
</html>