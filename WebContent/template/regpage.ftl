<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${basePath}/public/lib/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/public/lib/bootstrap/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="${basePath}/public/css/reg.css">
    <title>用户注册</title>
</head>
<body>
    <div class="container">
        <form class="form-reg">
            <h2 class="form-signin-heading">用户注册</h2>
            <input id="username" type="text" class="form-control" placeholder="请输入用户名" required="" autofocus="">
            <input id="password" type="password" class="form-control" placeholder="请输入密码" required="">
            <input id="password2" type="password" class="form-control" placeholder="请重复密码" required="">
            <button id="confirm" class="btn btn-lg btn-primary btn-block" type="button">确认注册</button>
        </form>
    </div>
<input id="basePath" type="hidden" value="${basePath}">
<script src="${basePath}/public/lib/jquery/jquery.min.js"></script>
<script src="${basePath}/public/lib/layer/src/layer.js"></script>
<script src="${basePath}/public/js/reg.js"></script>
</body>
</html>