var vd = new Object();

vd.init = function(eleId){
    $("#"+eleId).validate({
        debug: true, //调试模式取消submit的默认提交功能   
        errorClass: "label.error-msg-label", //默认为错误的样式类为：error   
        focusInvalid: false, //当为false时，验证无效时，没有焦点响应  
        onkeyup: false,   
        submitHandler: function(form){   //表单提交句柄,为一回调函数，带一个参数：form   
            // alert("提交表单");   
            console.log(form);
            form.submit();   //提交表单   
        },   
        errorPlacement:function(errorEle,confEle){
            vd.toError(errorEle,confEle);   
        },
        success:function(label){
            vd.toSuccess(label);
        },
        rules:{
            username:{
                required:true,
//                remote: {
//                    url: "userIsExist",     //后台处理程序
//                    type: "get",               //数据发送方式
//                    dataType: "json",           //接受数据格式   
//                    data: {                     //要传递的数据
//                        username: function() {
//                            return $("#username").val();
//                        }
//                    }
//                }
            },
            password:{
                required:true,
                rangelength:[3,10]
            }
        },
        messages:{
            username:{
                required:"用户名不能为空",
//                remote:"ajax验证出现问题"
            },
            password:{
                required: "密码不能为空",
                rangelength:"长度限制"
            }
        }
    });
}

//验证出现错误时需要做的处理
vd.toError = function(errorEle,confEle){
    //获取错误提示消息
    var errorMsg = errorEle.text();
    //把错误提示信息设置到用于提示的label标签中
    confEle.next().text(errorMsg);
    
    //如果错误信息为空,说明没有错误
    if(v.isNull(errorMsg)){
        confEle.parents('.page-form').removeClass('has-error');
    }else{
        //给当前输入框的box加上errorclass
        confEle.parents('.page-form').addClass('has-error');
    }
    console.log("error");
    console.log(errorEle);
    console.log(confEle);
}
vd.toSuccess = function(label){
    console.log("success");
//    console.log(label);
}
