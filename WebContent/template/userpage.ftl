<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${basePath}/public/lib/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/public/lib/bootstrap/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="${basePath}/public/css/user.css">
    <title>用户中心</title>
</head>
<body>
    <div class="container">
        <div class="header clearfix">
            <nav>
              <ul class="nav nav-pills pull-right">
                <li role="presentation" class="active"><a href="${basePath}/UserPage">主页</a></li>
                <li role="presentation"><a href="${basePath}/UserDetail">资料</a></li>
                <li id="logout" role="presentation"><a href="javascript:void(0);">退出</a></li>
              </ul>
            </nav>
            <h3 class="text-muted">欢迎您：${user.username}</h3>
        </div>

        <div class="jumbotron">
            <h1>南京地铁</h1>
            <p class="lead">地铁票限单人、单次、限时使用，限购票站进站；在乘客出闸时，单程票被出站闸机回收。</p>
            <p><a class="btn btn-lg btn-success" href="${basePath}/StopPage" role="button">立即购票</a></p>
        </div>
        
        <div class="col-lg-12 box">
            <h3>历史订单</h3>
            <ul class="list-group">
                <#list orderList as order>
                    <#if (order.state == "已乘坐")>
                    <li class="list-group-item list-group-item-success" data-id="${order.id}">
                        <span class="badge">已乘坐</span>
                        订单号：${order.orderNumber}&nbsp;&nbsp;时间：${order.createAt}&nbsp;&nbsp;${order.oneSite}->${order.twoSite}
                    </li>
                    <#elseif (order.state == "已退票")>
                    <li class="list-group-item list-group-item-danger" data-id="${order.id}">
                        <span class="badge">已退票</span>
                        订单号：${order.orderNumber}&nbsp;&nbsp;时间：${order.createAt}&nbsp;&nbsp;${order.oneSite}->${order.twoSite}
                    </li>
                    <#elseif (order.state == "待取票")>
                    <li class="list-group-item list-group-item-info" data-id="${order.id}">
                        <span class="badge state1" style="background-color: #fff;"><a href="javascript:void(0);">退票</a></span>
                        订单号：${order.orderNumber}&nbsp;&nbsp;时间：${order.createAt}&nbsp;&nbsp;${order.oneSite}->${order.twoSite}
                    </li>
                    <#elseif (order.state == "已取票")>
                    <li class="list-group-item list-group-item-warning" data-id="${order.id}">
                        <span class="badge state2" style="background-color: #fff;"><a href="javascript:void(0);" data-state="2">退票</a></span>
                        订单号：${order.orderNumber}&nbsp;&nbsp;时间：${order.createAt}&nbsp;&nbsp;${order.oneSite}->${order.twoSite}
                    </li>
                    </#if>
                </#list>
            </ul>
        </div>
    </div> <!-- /container -->
<input id="basePath" type="hidden" value="${basePath}">
<script src="${basePath}/public/lib/jquery/jquery.min.js"></script>
<script src="${basePath}/public/lib/layer/src/layer.js"></script>
<script src="${basePath}/public/lib/art-template/dist/template.js"></script> 
<script src="${basePath}/public/js/logout.js"></script>
<script>
$(function(){
    $('.state1').on('click',function(){
        $that = $(this);
        var id = $(this).parents('li').attr('data-id');
        var data = {
            id: id,
            state: '已退票'
        };
        layer.confirm('确认退票？', {
            btn: ['是的','取消'] //按钮
        }, function(){
            var basePath = $('#basePath').val();
            $.ajax({
                url: basePath + '/Return',
                type: 'POST',
                dataType: 'json',
                data: data,
                success: function(data){
                    console.log(data);
                    if(data.code == 0){
                        window.location.href = basePath + '/UserPage';
                    }else{
                        layer.msg(data.ext);
                    }
                },
                error: function(xhr){
                    console.log(data);
                }
            });
        }, function(){

        });
    });

    $('.state2').on('click',function(){
        layer.msg('已取票，请到人工窗口退票！');
    });
});
</script>
</body>
</html>