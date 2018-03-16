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
<form action="order_car_add.do" onsubmit="sure()">

        <table>
            <tr>
                <th>商品名称</th>
                <th>商品编号</th>
                <th>类型</th>
                <th>价格</th>
                <th>库存</th>
                <th>购买数量</th>
                <th>总价</th>
            </tr>
            <c:forEach var="goodCar" items="${goodCar}">
            <tr>
                <td><c:out value="${goodCar.goods.name}"></c:out></td>
                <td><c:out value="${goodCar.goods.goodNo}"></c:out></td>
                <td><c:out value="${goodCar.goods.typeName}"></c:out></td>
                <td><c:out value="${goodCar.goods.price}"></c:out></td>
                <td><c:out value="${goodCar.goods.amount}"></c:out></td>
                <td><c:out value="${goodCar.amount}"></c:out></td>
                <td><c:out value="${goodCar.amount*goodCar.goods.price}"></c:out></td>
            </tr>
            </c:forEach>
            <tr>合计:${totalPrice}</tr>

        </table>
    -----------------------------------------------------------<<br><br>
    请选择收货地址：<br><br>
    <table>
        <tr>
            <th></th>
            <th>联系人</th>
            <th>收货地址</th>
            <th>联系电话</th>
        </tr>
        <c:forEach var="location" items="${user.locations}">
            <tr>
                <td><input type="checkbox" name="locationId" value="${location.id}"></td>
                <td><c:out value="${location.connector}"></c:out></td>
                <td><c:out value="${location.location}"></c:out></td>
                <td><c:out value="${location.phone}"></c:out></td>
            </tr>
        </c:forEach>
        </tr>
    </table>
    <input type="hidden" name="ck1" value="${ck1}">
    <input type="hidden" name="userName" value="${userName}">
    <input type="hidden" name="payState" id="patState" >
    <input type="submit" value="提交">
</form>
<script type="text/javascript">

    function sure() {
        var a="确认付款吗？？？";
        var tag=document.getElementById("payState");
        if (confirm(a)){
            tag.value=1;
        }else {
            tag.value=0;
        }
    }

    function loading(amount,id)
    {
        var a=window.prompt("数量",1);
        a=check(amount,a);
        document.getElementById("amount"+id).value=a ;
        if(confirm(a)==true){
            return true;
        }else{
            return false;
        }

    }

    function check(amount,mount){
        if(mount>amount){
            alert("库存为"+amount+",超出库存，请重新填写数量");
            mount=window.prompt("数量",1);
            check(amount,mount);
        }
        return mount;
    }

</script>
</body>
</html>