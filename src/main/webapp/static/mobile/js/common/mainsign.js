$(function(){
    mainsign.input();//输入框焦点获失时样式变化
    mainsign.deleteIcon();//输入框清空按钮的变化及相关操作
    mainsign.verify();//表单验证及提交按钮的样式变化
})

var mainsign = new Object();

mainsign.input = function(){
    $(document).on('focus','[data-input-box="input"]',function(){
        $(this).parent('.input-block').addClass("border");
    })
    $(document).on('blur','[data-input-box="input"]',function(){
        $(this).parent('.input-block').removeClass('border');
    })
}

mainsign.deleteIcon = function(){
//当输入框内容发生改变时，清空按钮的变化
    $(document).on('input propertychange','[data-input-box="input"]',function(){
        var content = $(this).val();
        if(content != '' && content != ' ' ){
            $(this).siblings('.delete').removeClass('hide');
//清空按钮点击的时候，输入框变化
            $(document).on('click touchstart','[data-text-delete="delete"]',function(){
                $(this).siblings('.input-box').val('');
                $(this).addClass('hide');
                $('.mobile-button').attr('disabled',true);
            })
        }
        else{
            $(this).siblings('.delete').addClass('hide');
        }
    })
}

//方法引用自ipopover.js
mainsign.verify = function(){
    $(document).on('input propertychange','[data-input-box="input"]',function(){
        if($('#password-again').length>0){
            input.verfyDo(input.checkUserName(),input.checkPassword(),input.checkEqual());
        }else{
            input.verfyDo(input.checkUserName(),input.checkPassword(),true);
        }
    })
}

//登录错误
mainsign.signerror = function(){
    $('#sign-error').modal();
    setTimeout("$('#sign-error').modal('hide')",2000); 
}

//确认操作
$(document).on('click','[data-sure-button="sure"]',function(){
    $('#sure-tips').modal();
})