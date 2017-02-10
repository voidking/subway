$(function(){
    $('#confirm').on('click',function(){
        var username = $('#username').val();
        var password = $('#password').val();
        var password2 = $('#password2').val();
        var data = {
            username: username,
            password: password,
            password2: password2
        };

        var basePath = $('#basePath').val();
        $.ajax({
            url: basePath + '/Reg',
            type: 'POST',
            dataType: 'json',
            data: data,
            success: function(data){
                console.log(data);
                if(data.code == 0){
                    layer.msg('注册成功，正在转向登录页面');
                    setTimeout(function(){
                        window.location.href = basePath + '/LoginPage';
                    },2000);
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