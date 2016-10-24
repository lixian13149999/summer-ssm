/*
 * 用于处理确认对话框的js文件
 */
var ibootbox = new Object();

ibootbox.confirmCode = 404;

ibootbox.confirm = function (options) {
    //修改模态框中的提示信息
    $("#page-delete-modal-text").text(options.text);
    //显示模态框
    $('#deleteModal').modal({backdrop:false});

    //获取setInterval的值,并设置定时器开始定时处理
    var intervalVal = self.setInterval(function() {
        // console.log(ibootbox.confirmCode);
        if (ibootbox.confirmCode == 200) {
            eval(options.fun);
            // callBack();

            window.clearInterval(intervalVal);
            $("#deleteModal").modal('toggle');
            ibootbox.confirmCode = 404;
        } else if (ibootbox.confirmCode == 500) {

            window.clearInterval(intervalVal);
            $("#deleteModal").modal('toggle');
            ibootbox.confirmCode = 404;
        }
    }, 1000);

}

$(function() {
    //点击对话框中取消按钮时,需要做的处理
    $(document).on('click', '[data-page-confirm="false"]', function() {
        //把检测值设置成500,表示点击了取消按钮
        ibootbox.confirmCode = 500;
    });
    //点击对话框中确定按钮时,需要做的处理
    $(document).on('click', '[data-page-confirm="true"]', function() {
        //把检测值设置成200,表示点击了确定
        ibootbox.confirmCode = 200;
    });
});