/*
 * 用于封装提示框的js文件
 */

var imessenger = new Object();
$(function() {
     //设置Messenger相应的参数
    Messenger.options = {
//        设置显示位置和显示样式
        extraClasses: 'messenger-fixed messenger-on-top',
        //设置显示的主题
        theme: 'air'
    }

});

imessenger.error = function(text){
    //Messenger().hideAll();
    Messenger().post({
        message: text,
        type: 'error',
        hideAfter: 8,
        showCloseButton: true
    });
}
imessenger.success = function(text){
    //Messenger().hideAll();
    Messenger().post({
        message: text,
        type: 'success',
        hideAfter: 8,
        showCloseButton: true
    });
}
imessenger.info = function(text){
    //Messenger().hideAll();
    Messenger().post({
        message: text,
        type: 'info',
        hideAfter: 8,
        showCloseButton: true
    });
}