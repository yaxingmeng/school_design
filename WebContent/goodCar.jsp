<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
 <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript">
        $(function(){
            calc(); //计算方法
            //全选\反选
            $("#selectAll").click(function(){
                var $obj =$(this).is(":checked");
                if($obj){
                    $("input[name='ck1']").prop('checked',true);
                }else{
                    $("input[name='ck1']").prop('checked',false);
                }
            });
 
            //删除所选
            $("#deleteSelete").click(function(){
               /*  $("input[name='ck1']:checked").each(function(){
                    $(this).parent().parent().remove();
                }); */
                calc();
            });
            //单击删除
            $("#mytable .delete").click(function(){
               $(this).parent().parent().remove();
                calc();
            });
            //单机商品数量+
            $("#mytable .add").click(function(){
                //获得单前数量
                var $goodsCount= parseInt($(this).prev().val());
                $(this).prev().val($goodsCount+1);
                calc();
            });
            //单机商品数量-
            $("#mytable .cut").click(function(){
                //获得单前数量
                var $goodsCount= parseInt($(this).next().val());
                $(this).next().val($goodsCount<=1?1:$goodsCount-1);
                calc();
            });
 
            //计算价格
            function calc(){
                //找到共有对象
                var $obj = $("input[name='deleteGoods']");
                //var $obj = $("#mytable .delete");//也可以获得对象
                var sum =0;
                //循环共有对象，让它循环获得我们想要的对象的值
                $obj.each(function(){
                    //获得商品价格
                    var $price = parseInt($(this).parent().prev().prev().prev().html());
                    //获得商品数量
                    var $count = parseFloat($(this).parent().prev().prev().find("input").val());
                    var timSum =$count*$price;
                    $(this).parent().prev().html("￥"+timSum);//给商品小计赋值
                    sum +=timSum;  //累加计算总价
                });
                $("#sum").html(sum);
            };
            //绑定所有输入数量输入框的事件,输入值后自动计算
            $("input[name='inputCount']").each(function(){
                $(this).keyup(function(){
                    var num = $(this).val();
                    if(num!=""){
                        calc();
                    }else{
                        $("#mytable .cut").next().val(1);  //赋一个默认数量1
                        calc();
                    }
                });
            });
        });
    </script>
<title>表单操作</title>
<style type="text/css">
    .inputs{
        width:20px;
    }
    #main{
      padding:0px;
      margin:0px;
        position:relative;
    }
    #mytable{
        width:800px;
    }
    #mytable td{
        width:500px;       
    }
</style>
</head>
<body>
<div>
    <div id="main">
   
        <table id="mytable">
            <tr>
                <td>
                    <input type="checkbox" name="all" id="selectAll" >全选
                </td>
                <th>商品名称</th>
                <th>商品编号</th>
                <th>单价</th>
                <th>购买个数</th>
                <th>费用</th>
            </tr>
            <c:forEach var="goodCar" items="${goodCar }">
            <tr>
                <td><input type="checkbox" name="ck1" id="ck1" value="${goodCar.id }"></td>
                <td><c:out value="${goodCar.goods.name }"></c:out></td>
                <td><c:out value="${goodCar.goods.goodNo }"></c:out></td>
                <td><c:out value="${goodCar.goods.price }"></c:out></td>
                <td>
                <c:if test="${goodCar.amount>1 }">
                    <img src="images/cut.PNG" class="cut"></c:if>
                    <input type="text" size="4" value="${goodCar.amount }" style="width:20px" name="inputCount"/>
                    <c:if test="${goodCar.amount<goodCar.goods.amount }"><img src="images/add.PNG" class="add"></c:if></td>
                <td></td>
                <td><input type="button" value="删除" class="delete" name="deleteGoods" onclick="window.location='goodcar_delete.do?goodCarId=${goodCar.id }&pagesize=10&pagenumber=${page[0] }&userName=${userName }'"></td>
                <td><input type="button" value="结算" class="delete" name="pay"></td>
            </tr>
            </c:forEach>
          <!--   <tr>
                <td><input type="checkbox" name="ck1" id="ck1"></td>
                <td>123</td>
                <td>
                    <img src="images/cut.PNG" class="cut">
                    <input type="text" size="4" value="1" style="width:20px" name="inputCount"/>
                    <img src="images/add.PNG" class="add"></td>
                <td></td>
                <td><input type="button" value="删除" class="delete" name="deleteGoods"></td>
            </tr>
            <tr>
                <td><input type="checkbox" name="ck1" id="ck2"></td>
                <td>432</td>
                <td>
                    <img src="img/taobao_minus.jpg" class="cut">
                    <input type="text" size="4" value="1" style="width:20px" name="inputCount"/>
                    <img src="img/taobao_adding.jpg" class="add"></td>
                <td></td>
                <td><input type="button" value="删除" class="delete" name="deleteGoods"></td>
            </tr>
            <tr>
                <td><input type="checkbox" name="ck1" id="ck3"></td>
                <td>789</td>
                <td>
                    <img src="img/taobao_minus.jpg" class="cut">
                    <input type="text" size="4" value="1" style="width:20px" name="inputCount"/>
                    <img src="img/taobao_adding.jpg" class="add"></td>
                <td></td>
                <td><input type="button" value="删除" class="delete" name="deleteGoods"></td>
            </tr>
            <tr>
                <td><input type="checkbox" name="ck1" id="ck4"></td>
                <td>153</td>
                <td>
                    <img src="img/taobao_minus.jpg" class="cut">
                    <input type="text" size="4" value="1" style="width:20px" name="inputCount"/>
                    <img src="img/taobao_adding.jpg" class="add"></td>
                <td></td>
                <td><input type="button" value="删除" class="delete" name="deleteGoods"></td>
            </tr> -->
            <tr><td colspan="5" align="right">总费用：<span id="sum"></span></td></tr>
            <tr><td colspan="5"><input type="submit" value="删除选中的项" id="deleteSelete"> </td></tr>
        </table>
<a href = "goodcar_list.do?pagesize=10&pagenumber=1&userName=${userName}" >首页</a>
<c:if test="${page[0]>1}">
<a href = "goodcar_list.do?pagesize=10&pagenumber=${page[0]-1 }&userName=${userName }" >上一页</a>
</c:if>
<c:if test="${page[0]<page[1]}">
<a href = "goodcar_list.do?pagesize=10&pagenumber=${page[0]+1 }&userName=${userName }" >下一页</a>
</c:if>
<a href = "goodcar_list.do?pagesize=10&pagenumber=${page[1] }&userName=${userName }" >尾页</a>
第${page[0]}页/共${page[1]}页&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
    </div>   
</div>
</body>
</html>