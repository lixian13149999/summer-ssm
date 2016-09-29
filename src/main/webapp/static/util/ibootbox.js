/*
 * 用于处理确认对话框的js文件
 */
var ibootbox = new Object();

ibootbox.confirmCode = 404;

//定义删除确认对话框的方法
ibootbox.confirm = function(options, callBack) {
//    console.log(options.text);
    //设置传入的参数(确认框中提示的内)
    $('[data-remove-confirm="modal-text"]').first().text(options.text);
    //展开模态框
    $("#modal_confirm_remove").modal('toggle');
    //获取setInterval的值,并设置定时器开始定时处理
    var intervalVal = self.setInterval(function() {
        if (ibootbox.confirmCode == 200) {
            callBack();
            window.clearInterval(intervalVal);
            $("#modal_confirm_remove").modal('toggle');
            ibootbox.confirmCode = 404;
        } else if (ibootbox.confirmCode == 500) {
            window.clearInterval(intervalVal);
            $("#modal_confirm_remove").modal('toggle');
            ibootbox.confirmCode = 404;
        }
    }, 1000);
}

$(function() {
    
    //这只是一个调用的例子
    //真实使用时不一定要这样写
    
    //ibootbox.confirm(options,callBack);
    
    $(document).on("click", '[data-modal="remove"]', function() {
        //执行自定义的删除确认对话框
        ibootbox.confirm({text: "aaaa"}, function() {
            console.log("confirmCallBack");
        });
    });
    
    //点击对话框中取消按钮时,需要做的处理
    $(document).on('click', '[data-confirm-modal-button="false"]', function() {
        //把检测值设置成500,表示点击了取消按钮
        ibootbox.confirmCode = 500;
    });
    //点击对话框中确定按钮时,需要做的处理
    $(document).on('click', '[data-confirm-modal-button="true"]', function() {
        //把检测值设置成200,表示点击了确定
        ibootbox.confirmCode = 200;
    });
});