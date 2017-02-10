<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${basePath}/public/css/stop.css">
    <title>地铁购票</title>
</head>
<body>
    <div class="header">
        <h1>选择出发和到达站点</h1>
    </div>
    <div class="container">
        <div class="stop">
            <input id="oneSite" type="text" name="oneSite" placeholder="出发站点">
            <span>-></span>
            <input id="twoSite" type="text" name="twoSite" placeholder="到达站点">
        </div>
        <div class="confirm">
            <span class="price">总价为：<b id="price">0</b>&nbsp;元</span>
            <input id="confirm" type="button" name="confirm" value="确认购票">
        </div>
    </div>
    <div class="footer">
        
    </div>

<input id="basePath" type="hidden" value="${basePath}">
<div id="mapframe">
    <div class="subway-map" data-columns="25" data-rows="30" data-cellSize="50" data-legendId="legend" data-textClass="text" data-gridNumbers="false" data-grid="false" data-lineWidth="10">   
    </div>
    <div id="legend"></div>
</div>
<script id="line-template" type="text/html">
{{each line_list as line}}
<ul data-color="{{line.color}}" data-label="{{line.line_name}}"> 
    {{each line.stop_list as stop}}
    <li data-coords="{{stop.x}},{{stop.y + 1}}" data-marker="{{stop.marker}}" data-dir="{{stop.dir}}" data-labelpos="{{stop.labelpos}}">
        <a href="javascript:void(0);" data-value="{{stop.value}}">{{stop.stop_name}}</a>
    </li> 
    {{/each}}               
</ul>
{{/each}}
</script>

<script src="${basePath}/public/lib/jquery/jquery.min.js"></script> 
<script src="${basePath}/public/lib/subwaymap/jquery.subwayMap-0.5.0.js"></script>
<script src="${basePath}/public/lib/art-template/dist/template.js"></script> 
<script src="${basePath}/public/lib/layer/src/layer.js"></script>
<script>
$(function(){
    var basePath = $('#basePath').val();
    $.ajax({
        url: basePath + '/public/data/line.json',
        type: 'GET',
        dataType: 'json',
        data: {},
        success: function(data){
            console.log(data);
            var html = template('line-template', data);
            $('.subway-map').html(html);
            $('.subway-map').subwayMap({ debug: true });
        },
        error: function(xhr){
            console.log(data);
        }
    });

    $('#oneSite,#twoSite').on('click',function(){
        $that = $(this);
        $('#mapframe').show();
        $('#mapframe').unbind().on('click','a',function(){
            var stop_name = $(this).html().trim();
            $that.val(stop_name);
            var data_value = $(this).attr('data-value');
            $that.attr({"data-value": data_value});
            $('#mapframe').hide();

            var oneSite = $('#oneSite').attr('data-value');
            var twoSite = $('#twoSite').attr('data-value');
            if(oneSite !== undefined && twoSite !== undefined && oneSite != '' && twoSite != ''){
                $.ajax({
                    url: '/subway/GetPrice',
                    type: 'GET',
                    dataType: 'json',
                    data: {
                        oneSite: oneSite,
                        twoSite: twoSite
                    },
                    success: function(data){
                        console.log(data);
                        $('.price b').html(data.price);
                    },
                    error: function(xhr){
                        console.log(data);
                    }
                });
            }
        });
    });

    $('#confirm').on('click',function(){
        var oneSite = $('#oneSite').val();
        var twoSite = $('#twoSite').val();
        var price = $('#price').html();

        if(oneSite == '' || twoSite == ''){
            layer.msg('请先选择站点！');
            return;
        }
        if(oneSite == twoSite){
            layer.msg('请选择不同站点！');
            return;
        }

        var basePath = $('#basePath').val();
        var data = {
            oneSite: oneSite,
            twoSite: twoSite,
            price: price
        };
        $.ajax({
            url: basePath + '/Buy',
            type: 'POST',
            dataType: 'json',
            data: data,
            success: function(data){
                console.log(data);
                if(data.code == 0){
                    layer.msg('购买成功，请到自助取票机取票！');
                    setTimeout(function(){
                        window.location.href = basePath + '/UserPage';
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
</script>
</body>
</html>