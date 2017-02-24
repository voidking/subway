<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

  <title>后台管理-订单管理</title>

  <!-- Bootstrap core CSS -->
  <link href="${basePath}/public/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="${basePath}/public/css/dashboard.css" rel="stylesheet">
  <link rel="stylesheet" href="${basePath}/public/css/manage-order.css">
</head>

<body>

  <nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">南京地铁后台管理</a>
      </div>
      <div id="navbar" class="navbar-collapse collapse">
        <ul class="nav navbar-nav navbar-right">
          <li><a href="javascript:void(0);">${admin.username}</a></li>
          <li><a href="javascript:void(0);" id="logout">退出</a></li>
        </ul>
        <!--
        <form class="navbar-form navbar-right">
          <input type="text" class="form-control" placeholder="Search...">
        </form>
        -->
      </div>
    </div>
  </nav>

  <div class="container-fluid">
    <div class="row">
      <div class="col-sm-3 col-md-2 sidebar">
        <ul class="nav nav-sidebar">
          <li><a href="${basePath}/Manage">用户管理</a></li>
          <li class="active"><a href="${basePath}/ManageOrder">订单管理</a></li>
          <li><a href="${basePath}/ManageCount">销售统计</a></li>
        </ul>
        <!--
        <ul class="nav nav-sidebar">
          <li><a href="">Nav item</a></li>
          <li><a href="">Nav item again</a></li>
          <li><a href="">One more nav</a></li>
          <li><a href="">Another nav item</a></li>
          <li><a href="">More navigation</a></li>
        </ul>
        -->
      </div>
      <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        <h1 class="page-header">订单管理</h1>
        <div class="search input-group">
          <input type="text" id="key" class="form-control" placeholder="输入关键词">
          <span class="input-group-btn">
            <button class="btn btn-default" type="button" id="search">搜索</button>
          </span>
        </div>
        <h2 class="sub-header">订单信息</h2>
        <div class="table-responsive">
          <table class="order-table table table-striped">
            <thead>
              <tr>
                <th>订单号</th>
                <th>用户名</th>
                <th>出发站点</th>
                <th>到达站点</th>
                <th>购票时间</th>
                <th>状态</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody class="order-tbody">
              <#list orderList as order>
              <tr data-id="${order.id}" data-price="${order.price}" data-re-price="${order.rePrice}">
                <td class="orderNumber">${order.orderNumber}</td>
                <td class="username">${order.username}</td>
                <td class="oneSite">${order.oneSite}</td>
                <td class="twoSite">${order.twoSite}</td>
                <td class="createAt">${order.createAt}</td>
                <td class="state">${order.state}</td>
                <td><a class="detail" href="javascript:void(0);">查看详情</a></td>
              </tr>
              </#list>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>

<div class="pane-hide" style="display: none;">
  <div class="pane" data-id="">
    <p>订单号：<span class="orderNumber">12000000</span></p>
    <p>用户名：<span class="username">voidking</span></p>
    <p>出发站点：<span class="oneSite">龙眠大道</span></p>
    <p>到达站点：<span class="twoSite">软件大道</span></p>
    <p>购买时间：<span class="createAt">2017-02-11 12:00:00</span></p>
    <p>票价：<span class="price">2</span>元</p>
    <p>退票价：<span class="rePrice">0</span>元</p>
    <p>状态：<span class="state">未取票</span></p>
    <p><button class="btn btn-warning return">退票</button></p>
    <p><button class="btn btn-danger delete">删除订单</button></p>
  </div>
</div>

<input id="basePath" type="hidden" value="${basePath}">
<script type="text/html" id="order-template">
  {{each orderList as order}}
  <tr data-id="{{order.id}}" data-price="{{order.price}}" data-re-price="{{order.rePrice}}">
    <td class="orderNumber">{{order.orderNumber}}</td>
    <td class="username">{{order.username}}</td>
    <td class="oneSite">{{order.oneSite}}</td>
    <td class="twoSite">{{order.twoSite}}</td>
    <td class="createAt">{{order.createAt}}</td>
    <td class="state">{{order.state}}</td>
    <td><a class="detail" href="javascript:void(0);">查看详情</a></td>
  </tr>
  {{/each}}
</script>
<script src="${basePath}/public/lib/jquery/jquery.min.js"></script>
<script src="${basePath}/public/lib/art-template/dist/template.js"></script> 
<script src="${basePath}/public/lib/layer/src/layer.js"></script>
<script src="${basePath}/public/js/adminlogout.js"></script>
<script>
$(function(){
  $('#search').on('click',function(){
    var key = $('#key').val();
    var basePath = $('#basePath').val();
    $.ajax({
        url: basePath + '/SearchOrders',
        type: 'POST',
        dataType: 'json',
        data: {key: key},
        success: function(data){
            console.log(data);
            if(data.code == 0){
              var html = template('order-template',data);
                $('.order-tbody').html(html);
            }
        },
        error: function(xhr){
            console.log(data);
        }
    });
  });

  $('.order-table').on('click','.detail',function(){
    var $tr = $(this).parents('tr');
    var id = $tr.attr('data-id');
    var price = $tr.attr('data-price');
    var rePrice = $tr.attr('data-re-price');
    var orderNumber = $tr.find('.orderNumber').html();
    var username = $tr.find('.username').html();
    var oneSite = $tr.find('.oneSite').html();
    var twoSite = $tr.find('.twoSite').html();
    var createAt = $tr.find('.createAt').html();
    var state = $tr.find('.state').html();

    var $pane = $('.pane');
    $pane.attr({'data-id':id});
    $pane.find('.orderNumber').html(orderNumber);
    $pane.find('.username').html(username);
    $pane.find('.oneSite').html(oneSite);
    $pane.find('.twoSite').html(twoSite);
    $pane.find('.createAt').html(createAt);
    $pane.find('.state').html(state);
    $pane.find('.price').html(price);
    $pane.find('.rePrice').html(rePrice);

    if(state == '已乘坐' || state == '已退票'){
      $pane.find('.return').hide();
    }else{
      $pane.find('.return').show();
    }
   
    //页面层
    var index = layer.open({
      type: 1,
      title: '订单详情',
      skin: 'layui-layer-rim', //加上边框
      area: ['500px', '500px'], //宽高
      content: $('.pane-hide').html()
    });
    
    $('.return').unbind().click(function(){
        $that = $(this);
        var id = $(this).parents('.pane').attr('data-id');
        var re_price = 0;
        if(state == '待取票'){
          re_price = price;
        }else if(state = '已取票'){
          re_price = price*0.8;
        }
        var data = {
            id: id,
            state: '已退票',
            rePrice: re_price
        };
        var index2 = layer.confirm('确认退票？', {
            btn: ['是的','取消'] //按钮
        }, function(){
            var basePath = $('#basePath').val();
            $.ajax({
                url: basePath + '/UpdateOrder',
                type: 'POST',
                dataType: 'json',
                data: data,
                success: function(data){
                    console.log(data);
                    if(data.code == 0){
                      $tr.attr({'data-re-price':re_price});
                      $tr.find('.state').html('已退票');
                      layer.close(index2);
                      layer.close(index);
                      layer.msg('退票成功，退票价为'+re_price+'元');
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

    $('.pane').unbind().on('click','.delete',function(){
        $that = $(this);
        var id = $(this).parents('.pane').attr('data-id');
        var data = {
            orderId: id
        };
        var index2 = layer.confirm('确认删除？', {
            btn: ['是的','取消'] //按钮
        }, function(){
            var basePath = $('#basePath').val();
            $.ajax({
                url: basePath + '/DeleteOrder',
                type: 'POST',
                dataType: 'json',
                data: data,
                success: function(data){
                    console.log(data);
                    if(data.code == 0){
                      layer.close(index2);
                      layer.close(index);
                      $tr.remove();
                      layer.msg('删除成功！');
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
    
  });




});
</script>
</body>
</html>
