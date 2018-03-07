<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
                var $obj =$(this).attr("checked");
                if($obj){
                    $("input[name='ck1']").attr("checked",true);
                }else{
                    $("input[name='ck1']").attr("checked",false);
                }
            });
 
            //删除所选
            $("#deleteSelete").click(function(){
                $("input[name='ck1']:checked").each(function(){
                    $(this).parent().parent().remove();
                });
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
        width:400px;
    }
    #mytable td{
        width:200px;       
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
                <td>单价</td>
                <td>购买个数</td>
                <td>费用</td>
                <td>删除</td>
            </tr>
            <tr>
                <td><input type="checkbox" name="ck1" id="ck1"></td>
                <td>123</td>
                <td>
                    <img src="img/taobao_minus.jpg" class="cut">
                    <input type="text" size="4" value="1" style="width:20px" name="inputCount"/>
                    <img src="img/taobao_adding.jpg" class="add"></td>
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
            </tr>
            <tr><td colspan="5" align="right">总费用：<span id="sum"></span></td></tr>
            <tr><td colspan="5"><input type="button" value="删除选中的项" id="deleteSelete"> </td></tr>
        </table>
    </div>   
</div>
</body>
</html>