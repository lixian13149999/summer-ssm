var vd = new Object();
    
//验证出现错误时需要做的处理
vd.toError = function(errorEle,confEle){
    //获取错误提示消息
    var errorMsg = errorEle.text();
    //把错误提示信息设置到用于提示的label标签中
    confEle.parent().nextAll('label').text(errorMsg);
    
    //如果错误信息为空,说明没有错误
    if(v.isNull(errorMsg)){
        confEle.parents('.page-form').removeClass('has-error');
    }else{
        //给当前输入框的box加上errorclass
        confEle.parents('.page-form').addClass('has-error');
    }
}

//验证出现错误时需要做的处理
vd.toBeautError = function(errorEle,confEle){
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
}

vd.alertError = function(errorEle){
	console.log(errorEle);
	console.log(errorEle.text());
    //获取错误提示消息
    var errorMsg = errorEle.text();
    if(!v.isNull(errorMsg)){
    	imessenger.error(errorMsg);
    }
    // body...
};

vd.toSuccess = function(label){
//    console.log("success");
}


//eleId  form表单的id
//inputType  输入框的类型(现有两个,1.default/2.beaut)
//hasMsg  是否弹出提示信息(1.弹出,2.不弹出)
vd.init = function(eleId,inputType,hasMsg){
//	console.log("进入验证初始化");
    $("#"+eleId).validate({
        debug: true, //调试模式取消submit的默认提交功能   
        errorClass: "label.error-msg-label", //默认为错误的样式类为：error   
        focusInvalid: false, //当为false时，验证无效时，没有焦点响应  
        onkeyup: false,   
        submitHandler: function(form){   //表单提交句柄,为一回调函数，带一个参数：form   
            // alert("提交表单");   
//            console.log(form);
            form.submit();   //提交表单   
        },   
        errorPlacement:function(errorEle,confEle){
            if(inputType==1){
                vd.toError(errorEle,confEle);
            }else if(inputType==2){
                vd.toBeautError(errorEle,confEle);
            }
            
            if(hasMsg==1){
                // imessenger.error()
                vd.alertError(errorEle);
//                console.log("弹出消息信息");
            }
        },
        success:function(label){
            vd.toSuccess(label);
        },
        rules:{
            userName:{
                required:true,
                minlength:4,
                maxlength:20
            },
            signupUserName:{
            	required:true,
                minlength:4,
                maxlength:20,
                remote: {
                    url: "user/userNameCanUse",     //后台处理程序
                    type: "get",               //数据发送方式
                    dataType: "json",           //接受数据格式   
                    data: {                     //要传递的数据
                        userName: function() {
                            return $("#signupUserName").val();
                        }
                    }
                }
            },
            pwd:{
                required:true,
                minlength:4,
            	maxlength:30
            },
            confirmPwd:{
            	equalTo:"#pwd"
            },
            email:{
                required:true,
                email:true,
            }
        },
        messages:{
        	userName:{
                required:"用户名不能为空",
                minlength:"用户名长度不能小于4位",
                maxlength:"用户名不得大于20位"
            },
            signupUserName:{
            	required:"用户名不能为空",
            	minlength:"用户名长度不能小于4位",
            	maxlength:"用户名不得大于20位",
                remote:"此用户名已被占用"
            },
            pwd:{
                required: "密码不能为空",
                minlength:"密码不能小于4位",
                maxlength:"密码不能大于30位"
            },
            confirmPwd:{
            	equalTo:"两次密码输入不一致"
            },
            email:{
                required:"邮件地址不能为空",
                email:"请输入正确的邮箱",
            }
        }
    });
}
