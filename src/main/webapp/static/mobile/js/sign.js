$(document).on('input propertychange','[data-input-box="input"]',function(){
    var content = $(this).val();
    if(content != '' || content != ' ' ){
        $(this).siblings('.delete').removeClass('hide');
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

$(document).on('keyup','[data-input-box="input"]',function(){
    var content = $('.input-box').length;
    for(var i=0;i<content;i++){
        if($('.input-box').eq(i).val()==''||$('.input-box').eq(i).val()==' '){
            $('.mobile-button').attr('disabled',true);
        }else{
            $('.mobile-button').removeAttr('disabled');
        }
    }
})

