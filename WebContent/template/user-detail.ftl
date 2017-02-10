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
                <li role="presentation" ><a href="${basePath}/UserPage">主页</a></li>
                <li role="presentation" class="active"><a href="${basePath}/UserDetail">资料</a></li>
                <li id="logout" role="presentation"><a href="javascript:void(0);">退出</a></li>
              </ul>
            </nav>
            <h3 class="text-muted">欢迎您：${user.username}</h3>
        </div>

        <div class="row marketing">
            <div class="col-lg-12 box">
                <form class="form-horizontal">
                  <div class="form-group">
                    <label for="username" class="col-sm-2 control-label">用户名</label>
                    <div class="col-sm-10">
                      <input type="text" class="form-control" id="username" placeholder="用户名" disabled="true" value="${user.username}">
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="email" class="col-sm-2 control-label">邮箱</label>
                    <div class="col-sm-10">
                      <input type="text" class="form-control" id="email" placeholder="邮箱" value="${user.email?default('')}">
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="tel" class="col-sm-2 control-label">手机</label>
                    <div class="col-sm-10">
                      <input type="text" class="form-control" id="tel" placeholder="手机" value="${user.tel?default('')}">
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                      <button id="updateInfo" type="button" class="btn btn-default">修改资料</button>
                    </div>
                  </div>
                </form>
            </div>

            <div class="col-lg-12 box">
                <form class="form-horizontal">
                  <div class="form-group">
                    <label for="old-password" class="col-sm-2 control-label">原密码</label>
                    <div class="col-sm-10">
                      <input type="password" class="form-control" id="old-password" placeholder="******">
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="new-password" class="col-sm-2 control-label">新密码</label>
                    <div class="col-sm-10">
                      <input type="password" class="form-control" id="new-password" >
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="new-password2" class="col-sm-2 control-label">重复新密码</label>
                    <div class="col-sm-10">
                      <input type="password" class="form-control" id="new-password2" >
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                      <button id="updataPwd" type="button" class="btn btn-default">修改密码</button>
                    </div>
                  </div>
                </form>
            </div>

        </div>


    </div> <!-- /container -->

<input id="basePath" type="hidden" value="${basePath}">
<script src="${basePath}/public/lib/jquery/jquery.min.js"></script>
<script src="${basePath}/public/lib/layer/src/layer.js"></script>
<script src="${basePath}/public/lib/art-template/dist/template.js"></script> 
<script src="${basePath}/public/js/logout.js"></script>
<script>
$(function(){
    $('#updateInfo').click(function(){
      var email = $('#email').val();
      var tel = $('#tel').val();
      var emailreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
      if(!emailreg.test(email)){
        layer.msg('请输入有效的邮箱地址！'); 
        return; 
      }

      var telreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
      if(!telreg.test(tel)) 
      { 
        layer.msg('请输入有效的手机号码！'); 
        return; 
      } 

      var data = {
        newEmail: email,
        newTel: tel
      };
      
      var basePath = $('#basePath').val();
      $.ajax({
          url: basePath + '/UpdateInfo',
          type: 'POST',
          dataType: 'json',
          data: data,
          success: function(data){
              console.log(data);
              if(data.code == 0){
                  layer.msg('修改信息成功！');
              }else{
                  layer.msg(data.ext);
              }
          },
          error: function(xhr){
              console.log(data);
          }
      });

    });

    $('#updataPwd').click(function(){
      var oldPwd = $('#old-password').val();
      var newPwd = $('#new-password').val();
      var newPwd2 = $('#new-password2').val();

      var data = {
        oldPwd: oldPwd,
        newPwd: newPwd,
        newPwd2: newPwd2
      };

      var basePath = $('#basePath').val();
      $.ajax({
          url: basePath + '/UpdatePwd',
          type: 'POST',
          dataType: 'json',
          data: data,
          success: function(data){
              console.log(data);
              if(data.code == 0){
                  layer.msg('修改密码成功！');
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