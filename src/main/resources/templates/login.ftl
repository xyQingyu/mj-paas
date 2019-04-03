<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=emulateIE7" />
    <link rel="stylesheet" type="text/css" href="/css/style.css" />
    <link rel="stylesheet" type="text/css" href="/css/skin_/login.css" />
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/jquery.select.js"></script>

    <title>蓝色企业数字办公后台管理模板_用户登录</title>
</head>

<body>
<form method="post" action="" id="loginForm">
<div id="container">
    <div id="bd">
        <div id="main">
            <div class="login-box">
                <div id="logo"></div>
                <h1></h1>
                <div class="input username" id="username">
                    <label for="userName">用户名</label>
                    <span></span>
                    <input type="text" id="userName" />
                </div>
                <div class="input psw" id="psw">
                    <label for="password">密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
                    <span></span>
                    <input type="password" id="password" />
                </div>
                <div class="input validate" id="validate">
                    <label for="valiDate">验证码</label>
                    <input type="text" id="valiDate" />
                    <div class="value">X3D5</div>
                </div>
                <div class="styleArea">
                    <div class="styleWrap">
                        <select name="style">
                            <option value="默认风格">默认风格</option>
                            <option value="红色风格">红色风格</option>
                            <option value="绿色风格">绿色风格</option>
                        </select>
                    </div>
                </div>
                <div id="btn" class="loginButton">
                    <input type="button" class="button" value="登录"  />
                </div>
            </div>
        </div>
    </div>

</div>
</form>
</body>
<script type="text/javascript">
    var height = $(window).height() > 445 ? $(window).height() : 445;
    $("#container").height(height);
    var bdheight = ($(window).height() - $('#bd').height()) / 2 - 20;
    $('#bd').css('padding-top', bdheight);
    $(window).resize(function(e) {
        var height = $(window).height() > 445 ? $(window).height() : 445;
        $("#container").height(height);
        var bdheight = ($(window).height() - $('#bd').height()) / 2 - 20;
        $('#bd').css('padding-top', bdheight);
    });
    $('select').select();
    /**
     * 登录js
     * */
    $('.loginButton').click(function(e) {
        var data = {};
       //
        var username = $("#userName").val();//用户名
        var password = $("#password").val();//密码
        var checkcode = $("#valiDate").val();//验证码

        //登录请求路径
        var url = '/loginUser?username='+username+"&password="+password+"&checkcode="+checkcode;
        // $("#loginForm").attr("action",url);
        // $('.loginButton').submit();
        $.ajax({
            url : url ,
            type : 'POST',/*POST*/
            contentType : 'application/json',
            dataType : 'json',
            data : data,/*JSON.stringify(data),*/
            success : function(data) {
                if(data.result == "fail"){
                   alert(data.msg);
                   return false;
                }
                if(data.result == "success"){
                    document.location.href = "/main";
                }
            }
        });
    });
</script>

</html>
