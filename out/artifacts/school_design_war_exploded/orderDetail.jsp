<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="css/manage.css"/>
</head>
<body>
<div id="background">
    <div id="list">
        <c:if test="${adminName==null }"><a href="index.jsp">请先登录</a></c:if>
        <c:if test="${adminName!=null }">${adminName},欢迎你<br><br>
            <c:if test="${type==0}">
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
                    <tr>订单金额:${order.totalPrice }</tr>
                </table>
                订单金额:<input type="text" value="${order.totalPrice }" readonly="readonly"><br><br>
                地址:<input type="text" value="${order.location }" readonly="readonly"><br><br>
                联系电话:<input type="text" value="${order.phone }" readonly="readonly"><br><br>
                联系人:<input type="text" value="${order.contact }" readonly="readonly"><br><br>
                成交时间:<input type="text" value="${order.createTime }" readonly="readonly"><br><br>
                支付状态:<input type="text" value="${order.payStateName }" readonly="readonly"><br><br>
                物流状态:<input type="text" value="${order.transStateName }" readonly="readonly"><br><br>
                <a href="order_list.do?pagenumber=1&adminName=${admin.name }">返回</a>
            </c:if>
            <c:if test="${type==1}">
                <form action="order_update.do">
                    <input type="hidden" name="adminName" value="${adminName}">
                    <input type="hidden" name="id" value="${order.id}">
                    订单编号:<input type="text" value="${order.orderNo }"><br><br>
                    用户:<input type="text" value="${order.user.nickname }"><br><br>
                    <table>
                        <tr>
                            <th>商品编号</th>
                            <th>商品名称</th>
                            <th>单价</th>
                            <th>数量</th>
                        </tr>
                        <c:forEach var="item" items="${order.orderItem}">
                            <tr>
                            <td><c:out value="${item.goods.goodNo}"></c:out></td>
                            <td><c:out value="${item.goods.name}"></c:out></td>
                            <td><c:out value="${item.goods.price}"></c:out></td>
                            <td><c:out value="${item.amount}"></c:out></td>
                            </tr>
                        </c:forEach>
                        <tr>订单金额:${order.totalPrice}</tr>
                    </table>
                    地址信息:<br>
                    地址： <input type="text" value="${order.location }" name="location"><br>
                    联系人：<input type="text" value="${order.contact}" name="contact"><br>
                    联系电话：<input type="text" value="${order.phone}" name="phone"><br><br>
                    成交时间:<input type="text" value="${order.createTime }" readonly="readonly"><br><br>
                    支付状态:
                    <c:if test="${order.payState==1}">
                        <input type="hidden" name="payState" value="${order.payState}">
                        支付状态:<input type="text" value="${order.payStateName }" readonly="readonly"><br><br>
                    </c:if>
                    <c:if test="${order.payState==0}"> <select name="payState"> <c:forEach var="payState" items="${payState}">
                        <c:choose>
                            <c:when test="${order.payState==payState.key}">
                                <option name="payState" value="${payState.key}" selected="selected">
                                        ${payState.value}
                                </option>
                            </c:when>
                            <c:otherwise>
                                <option name="payState" value="${payState.key}">
                                        ${payState.value}
                                </option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach></select></c:if><br><br>
                    <!--支付状态:<input type="text" value="${order.payStateName }"><br><br>-->
                    物流状态:
                    <!--<input type="text" value="${order.transStateName }"><br><br>-->
                    <c:choose>
                        <c:when test="${order.tranState==0}">
                            <select name="tranState"> <c:forEach var="tranState" items="${tranState}">
                                <c:choose>
                                    <c:when test="${order.tranState==tranState.key}">
                                        <option  name="tranState" value="${tranState.key}" selected="selected">
                                                ${tranState.value}
                                        </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option name="tranState" value="${tranState.key}">
                                                ${tranState.value}
                                        </option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach></select>
                        </c:when>
                       <c:otherwise>
                           <input type="hidden" name="tranState" value="${order.tranState}">
                           物流状态:<input type="text" value="${order.transStateName }" readonly="readonly"><br><br>
                       </c:otherwise>
                    </c:choose>
                    <br><br>
                    <input type="submit" value="提交">
                </form>
            </c:if>
        </c:if>
    </div>
</div>
</body>
</html>