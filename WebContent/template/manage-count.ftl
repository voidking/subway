<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

  <title>后台管理-销售统计</title>

  <!-- Bootstrap core CSS -->
  <link href="${basePath}/public/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="${basePath}/public/css/dashboard.css" rel="stylesheet">
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
          <li class="active"><a href="${basePath}/ManageCount">销售统计</a></li>
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
        <h1 class="page-header">销售统计</h1>
        <h2 class="sub-header">统计信息</h2>
        <div class="table-responsive">
          <table class="table table-striped">
            <thead>
              <tr>
                <th>日期</th>
                <th>销量</th>
                <th>退票量</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
              <#list salesList as sales>
              <tr>
                <td>${sales.date}</td>
                <td>${sales.sell}</td>
                <td>${sales.ret}</td>
                <td><a target="_blank" href="${basePath}/ManageOrder2?date=${sales.date}">查看当日详情</a></td>
              </tr>
              </#list>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>

<input id="basePath" type="hidden" value="${basePath}">
<script type="text/html" id="order-template">

</script>
<script src="${basePath}/public/lib/jquery/jquery.min.js"></script>
<script src="${basePath}/public/lib/art-template/dist/template.js"></script> 
<script src="${basePath}/public/lib/layer/src/layer.js"></script>
<script src="${basePath}/public/js/adminlogout.js"></script>
<script>
$(function(){

});
</script>
</body>
</html>
