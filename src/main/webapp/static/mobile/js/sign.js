$(function(){
    sign.input();//输入框焦点获失时样式变化
    sign.deleteIcon();//输入框清空按钮的变化及相关操作
    sign.verify();//表单验证后提交按钮的样式变化
})

var sign = new Object();

sign.input = function(){
    $(document).on('focus','[data-input-box="input"]',function(){
        $(this).parent('.input-block').addClass("border");
    })
    $(document).on('blur','[data-input-box="input"]',function(){
        $(this).parent('.input-block').removeClass('border');
    })
}

sign.deleteIcon = function(){
//当输入框内容发生改变时，清空按钮的变化
    $(document).on('input propertychange','[data-input-box="input"]',function(){
        var content = $(this).val();
        if(content != '' && content != ' ' ){
            $(this).siblings('.delete').removeClass('hide');
//清空按钮点击的时候，输入框变化
            $(document).on('click touchstart','[data-text-delete="delete"]',function(){
                $(this).siblings('.input-box').val('');
                $(this).addClass('hide');
            })
        }
        else{
            $(this).siblings('.delete').addClass('hide');
        }
    })
}


sign.verify = function(){
    $(document).on('input propertychange','[data-input-box="input"]',function(){
        if($('#password-again').length>0){
            input.verfyDo(input.checkUserName(),input.checkPassword(),input.checkEqual());
        }else{
            input.verfyDo(input.checkUserName(),input.checkPassword(),true);
        }
    })
}

//执行登录操作
sign.signin = function() {
	var user = iform.parse("signin_form");
	var url = "http://localhost:8080";
	$.ajax({
		url: url,
		type: 'POST',
		dataType: "json",
		data: user,
		async: false,
		success: function(data) {
			console.log(data);
			sign.signinSuccessBack(data);
			//cb(data);
		},
		error: function() {
			alert('密码或用户名错误')
		}
	});
}





