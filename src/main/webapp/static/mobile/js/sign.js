$(function(){
//    sign.addPages('./common/signin.html','main-container','用户名或密码错误');//页面一加载即登录页面
//    sign.change();//登录注册页面切换
})

var  sign = new Object();

//加载页面
sign.addPages = function(Url,Id,Error) {
    $.ajax({
        url:Url,
        data:{},
        type:'get',
        cache:false, 
        async:false,
        dataType:'html',
        success:function(data) {
            $('#'+Id).html(data);
            $('.error-text').html(Error);
        },
        error:function() {
            console.log('加载页面出错...');
        }
    });
}

//登录注册页面切换
sign.change = function(){
    $(document).on('click','[data-tips-change="signin"]',function(){
         sign.addPages('./common/signup.html','main-container','此用户名已存在');
    })
    $(document).on('click','[data-tips-change="signup"]',function(){
         sign.addPages('./common/signin.html','main-container','用户名或密码错误');
    })
}
