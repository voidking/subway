<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

  <title>后台管理-限制管理</title>

  <!-- Bootstrap core CSS -->
  <link href="${basePath}/public/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="${basePath}/public/lib/bootstrap-switch/dist/css/bootstrap3/bootstrap-switch.min.css" rel="stylesheet" >
  <link href="${basePath}/public/css/dashboard.css" rel="stylesheet">
  
  <style>
    p{
      font-size: 18px;
      text-align: center;
    }
  </style>
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
          <li><a href="${basePath}/ManageOrder">订单管理</a></li>
          <li><a href="${basePath}/ManageCount">销售统计</a></li>
          <li class="active"><a href="${basePath}/ManageLimit">限制管理</a></li>
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
        <h1 class="page-header">限制管理</h1>
        <h2 class="sub-header">站点设置</h2>
        <div class="table-responsive">
          <p>
            <span>可售票站点1：</span>
            <select name="" id="oneSite">
              <#list stopList as stop>
                <#if (stop.stopName == limit.oneSite)>
                  <option value="${stop.stopName}" selected="selected">${stop.stopName}</option>
                <#else>
                  <option value="${stop.stopName}">${stop.stopName}</option>
                </#if>
              </#list>
            </select>
          </p>
          <p>
            <span>可售票站点2：</span>
            <select name="" id="twoSite">
              <#list stopList as stop>
                <#if (stop.stopName == limit.twoSite)>
                  <option value="${stop.stopName}" selected="selected">${stop.stopName}</option>
                <#else>
                  <option value="${stop.stopName}">${stop.stopName}</option>
                </#if>
              </#list>
            </select>
          </p>
          <p>
            <span>票数上限：</span>
            <input id="totalTickets" type="text" value="${limit.totalTickets}">
          </p>
          <p>
            <span>是否启用：</span>
             <#if (limit.started == 0)>
              <input id="switch" type="checkbox" />
             <#else>
              <input id="switch" type="checkbox" checked />
             </#if>
          </p>
          <p>
            <button id="confirm" class="btn btn-primary">确定</button>
          </p>
        </div>
      </div>
    </div>
  </div>

<input id="basePath" type="hidden" value="${basePath}">
<script src="${basePath}/public/lib/jquery/jquery.min.js"></script>
<script src="${basePath}/public/lib/art-template/dist/template.js"></script> 
<script src="${basePath}/public/lib/layer/src/layer.js"></script>
<script src="${basePath}/public/js/adminlogout.js"></script>
<script src="${basePath}/public/lib/bootstrap-switch/dist/js/bootstrap-switch.min.js"></script>
<script>
$(function(){
  $('#switch').bootstrapSwitch();

  $('#confirm').click(function(){
    var started = 0;
    if($("#switch").is(":checked")){
      started = 1;
    }else{
      started = 0;
    }

    var data = {
      oneSite: $('#oneSite').val(),
      twoSite: $('#twoSite').val(),
      totalTickets: $('#totalTickets').val(),
      started: started
    };

    console.log(data);
    var basePath = $('#basePath').val();
    $.ajax({
        url: basePath + '/SetLimit',
        type: 'POST',
        dataType: 'json',
        data: data,
        success: function(data){
            console.log(data);
            if(data.code == 0){
                layer.msg('设置成功！');
            }else{
                layer.msg(data.ext);
            }
        },
        error: function(xhr){
            console.log(data);
        }
    });
  });
});
</script>
</body>
</html>
